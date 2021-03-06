import java.util.*;
import java.io.*;

/**
 * This class creates the schedule for the supply of warehouse.
 * 
 * @author Ivan Evtimov
 */
public class DistributionScheduler{
  
  /**
   * Field to store the graph creator
   * (allows access to all the scanned in data).
   */
  private GraphCreator gc;
  
  /**
   * Field to store the graph itself
   * with all the nodes and stuff.
   */
  private Graph<Integer, DistributionNetworkMember> graph;
  
  /**
   * This field holds the distance to the start node for a given 
   * start node of all nodes in the radius.
   */
  private HashMap<Integer, Integer> shortestDistMap;
  
  /**
   * Holds the shortest path in ascending order.
   * Key is the key for the node in the graph and 
   * value is the corresponding shortest distance.
   */
  private ArrayList<Integer> keysWithinRadius;
  
  /**
   * Array list to hold the keys of stores that haven't
   * had ALL of their needs met. A store will be in this container
   * if it still needs at least one type of resource.
   */
  private ArrayDeque<Integer> keysOfStores;
  
  /**
   * This stores the "trucks" that were sent out
   * in order to be able to print out the information
   * after that.
   */
  private ArrayList<Truck> trucksArr;
  
  /**
   * The "traveller" is the truck that visits
   * the different stores in each iteration
   * of the outer-most loop of the goFromWarehouse
   * method.
   */
  private Truck traveller;
  
  /**
   * Holds all stores that were processed.
   */
  private ArrayList<Integer> processedStoresArr;
  
  /**
   * Constructor method initiates the graph
   * creator and gets the graph.
   * It also initializes several internal containers
   * of the class and sets the truck's static fields based
   * on the data from the GraphCreator.
   */
  public DistributionScheduler(){
    gc = new GraphCreator();//create the graph creator
    
    graph = gc.createGraph();//get the graph
    
    trucksArr = new ArrayList<Truck>();
    processedStoresArr = new ArrayList<Integer>();
    
    //reset the trucks
    Truck.reset();
    
    //set up the trucks based on scanned in data
    Truck.setCapacityNeeds( gc.getCapacityNeeds() );
    Truck.setMilesCap( gc.getMileage() );
    Truck.setCarryingCap( gc.getMaxCapacity() );
    
  }
  
  /**
   * Find all elements within a given radius
   * of a given start node and store
   * their shortest distances to the start node
   * in an array list.
   * Method finds only nodes that have stores in them.
   * @param center key of the center node
   * @param radius the radius we are looking for
   */
  public void findRadius( int center, int radius ){
    
    //get all nodes in radius
     keysWithinRadius = graph.radius( center, radius );
    
    //initialize the map
     shortestDistMap = new HashMap<Integer, Integer>();
  
     //vars for the loop
     Graph.UnDirectedGraphNode currNode = null;
     int currShortestDist;
     keysOfStores = new ArrayDeque<Integer>();
     
     //go through that array and store the shortest distances in the map FOR STORES ONLY
    for( int currKey : keysWithinRadius ){
      
      //get the current node
      currNode = graph.getNode( currKey );
      
      //check if store
      if ( currNode.getValue() instanceof Store ){
      
        //get its shortest distance
        currShortestDist = currNode.getShortestDist();
        
        //associate this short distance with that key
        shortestDistMap.put( currKey, currShortestDist );
        
        //add to the keys of stores
        keysOfStores.add( currKey );
      } 
      
      else {
        continue;//if not a store, skip this entry
      }
    }
 }
  
  /**
   * Generates trucks that go out of the given warehouse
   * and sends them to supply the stores. 
   * Please, note that this method is unlike a simulation
   * in that the truck only finds out what resources it will
   * receive once it reaches the store (it leaves the warehouse empty).
   * In other words, the Truck is not used to simulate distribution, 
   * but to keep track of what the truck has visited
   * and what it has supplied to stores in general.
   * @param warehouseKey the key for the warehouse we want to start with
   */
  public void goFromWarehouse( int warehouseKey ){
    
    //initialize the variable for the warehouse 
    Warehouse currWarehouse = null;
    
    //perform a check of whether the node at this key holds a warehouse
    try{
       
      currWarehouse = (Warehouse) graph.get( warehouseKey );
       
    } catch (ClassCastException e){
      
      throw new IllegalArgumentException ("No warehouse at: " + warehouseKey + " " + e);
      
    }
    
    //get stores within half the mileage of the trucks (stored in fields of this class)
    findRadius( warehouseKey, gc.getMileage()/2 );
    System.out.println("keysOfStores after findRadius: " + keysOfStores);
    
    //declare variables for the loop
    int currKey = 0; // will store the key of the current store in the iteration
    int nextShortestDist =0; //will store the shortest distance to the next store
    int nextKey = 0; //will store the next key that could be visited
    int currNeed = 0; //keeps track of the need for a given resource at this step of the inner-most loop
    
    int iteration = 0;//keeps track of the iteration of the inner loop (used in debugging)
    
    //loop through the trucks that the warehouse has
    //as long as there are still stores with unmet needs 
    //OR the warehouse has run out of trucks
    while ( currWarehouse.getRemainingTrucks() > 0 && keysOfStores.isEmpty() == false ){
      
      //get the truck, store it in the class's field so that other methods have access
      traveller = currWarehouse.sendTruck();
      
      //add it to the array that keeps track of which trucks went out
      trucksArr.add( traveller );
      
      //send it to the first (closest store within radius)
      currKey = keysOfStores.peek();
      traveller.travel( shortestDistMap.get( currKey ) );
      processStore( currKey );
      
      //check if the queue with all the stores is empty
      if( keysOfStores.isEmpty() ){
        break; //if it is empty, we've processed all possible stores
      }

      //get the next store to be visited, but don't remove it yet
      nextKey = keysOfStores.peek();

      //get the shortest distance to it
      nextShortestDist = graph.findShortestDistance( currKey, nextKey );
      
      //as long as the truck can go from this node to the next
      //node and back to its origin (the warehouse), then
      //and as long as the truck still has free space
      //and as long as there are more stores that have needs
      while( traveller.canGo( nextShortestDist + shortestDistMap.get( nextKey ) ) 
              && traveller.getFree() > 0 && keysOfStores.isEmpty() == false ){
        
        //check if this would be an infinte loop 
        //(the truck can't find what the next store in the queue needs)
        if ( isInfinite( nextKey ) ){
          break;
        }
                  
        //let the truck travel the calculated shortest distance
        //(upon first iteration coming from outside,
        //then updated at end of loop)
        traveller.travel( nextShortestDist );
        
        //call the processStore helper method for the current store
        processStore( nextKey );
        
        //if there are no more stores after this one was processed,
        //we should break the loop
        //a NullPointerException in the statement in the try block
        //can only be thrown if the keysOfStores is empty
        try{
          
          //get the next store that still needs something
          nextKey = keysOfStores.peek();
          
        } catch (NullPointerException e ){
          break;
        }
        
        //if we haven't broken out of the loop, calculate the
        //shortest distance to that next node
        nextShortestDist = graph.findShortestDistance( currKey, nextKey );
        
        iteration ++;

     }     
   }
  }
  
  /**
   * This method is designed to print the report
   * of the schedule to the screen.
   * 
   */
  public void printReport(){
    
    for( Truck currentTruck : trucksArr ){
      System.out.println( currentTruck.printReport() );
    }
    
    System.out.println();
    System.out.println("Stores with unmet needs: " +  keysOfStores);
    for ( Integer currKey : keysOfStores ){
      Store tempStore = (Store) graph.get( currKey );
      Set<String> remainingNeeds = tempStore.getNeededResources();
      for( String currNeed : remainingNeeds ){
        System.out.println( "Store: " + currKey + " need for: " + currNeed + " " + tempStore.getNeedFor( currNeed ) );
      }
      //System.out.println(currKey + " " + "resources: " + remainingNeeds.keySet() + " needs: " + remainingNeeds.values() );
    }
    
    System.out.println("Unprocessed stores: ");
    ArrayList<Integer> allStoresArr = gc.getAllStoresArr();
    
    allStoresArr.removeAll( processedStoresArr );
    
    System.out.println(allStoresArr);
  
  }
 
  /**
   * This method "processes" the store at the current
   * key. It has the traveller visit and supply that
   * store and record in the traveller's internal containers
   * what kind of resources it has supplied.
   * @param currKey the key where we are processing the restaurant
   */
  public void processStore( int currKey ){
    
      processedStoresArr.add( currKey );
      
      //declare variables that will be used in the loop
      String currResource = null;
      int currNeed = 0;
      
      //get the current store (assumed this would be store, otherwise ClassCastException -
      //I don't want to deal with it here
      Store currStore = (Store) graph.get( currKey );
      
      //make the truck travel
    //  traveller.travel( shortestDistMap.get( currKey ) );
      Set<String> needsOfCurrStore = currStore.getNeededResources();
      traveller.visit( currKey );
      
      for( Iterator it = needsOfCurrStore.iterator(); it.hasNext(); ){
        currResource = (String) it.next();
        
      try{
          currNeed = currStore.getNeedFor( currResource );
          if ( currNeed != 0 ){
            traveller.loadResource( currResource,  currNeed );
            currStore.supply( currResource, currNeed );
            System.out.println("Store " + currKey + " was supplied with " + currResource + " " + currNeed );
          }
        } catch (UnsupportedOperationException e){
         // storesWithUnmetNeeds.add( currKey );
          continue;
        }
      }
      
      if( currStore.hasMoreNeeds() == false ){
        //storesWithUnmetNeeds.add( currKey );
        keysOfStores.poll();
      }
  }
  
  /**
   * This method is designed to look at whethe the truck
   * can fit the needs of the next store in the queue. If it cannot,
   * this creates an infinite loop. 
   * @param currStoreKey the key of the next store in the queue
   * @return true if its needs cannot be satisfied by the current truck,
   * false if its needs can be satisfied by the current truck
   */
  public boolean isInfinite( int currStoreKey ){
    
    //get the store, ClassCastException if not store, not handled here
    Store currStore = (Store) graph.get( currStoreKey );
    
    //get its needed resources
    Set<String> needsOfCurrStore =  currStore.getNeededResources();
    
    //for each one of them, check if it can fit on the truck
    for( String currResource : needsOfCurrStore ){
      
      //if there is a resource that can't fit, return true
      if ( traveller.canLoad( currResource, currStore.getNeedFor( currResource ) ) == false ) {
        return true;
      }
    }
   
    //if here, all resources can fit
    return false;
  }
  
  /**
   * This method runs the scheduler for all the warehouses found
   * in the graph.
   */
  public void runAll(){
    
    //System.out.println("keysOfStores: " + keysOfStores);
    
    //get the array with the keys of warehouses
    ArrayList<Integer> warehouses = gc.getWarehouseKeys();
    
    //go through each one of them
    for( int currKey : warehouses ){
      goFromWarehouse( currKey );
    }
    
  }
  
  /**
   * Method returns a string that contains the schedule.
   * @return sting in format of schedule
   */
  public String getSchedule(){
  
    //run the scheduler
    runAll();
    
    //declare the strung
    String schedule = "";
    
    //the trucks have all the necessary info
    for( Truck currentTruck : trucksArr ){
      schedule = schedule + currentTruck.printReport() +"\n";
    }
    
    return schedule;
  }
  
  public boolean createAndWriteSchedule(){
  
    //initialize it here, so that it can be used in finally
    BufferedWriter bw = null;
    
    boolean exception = false;
    
    //FileWriter may cause exceptions
    try{
      bw = new BufferedWriter( new FileWriter ( "schedule.txt" ) );//open the writing stream
      String schedule = getSchedule();
      bw.write( schedule );
    
    } 
    
    catch (IOException e){
      
      //update the boolean to indicate what happened
      exception = true;
      System.out.println("Writing failed due to exception: " + e);//print out error message
    }
    
    finally{
      
      //attempt to close the input stream
      try{
        if( bw != null ){
          bw.close();//CLOSE the input stream
        }
      }
      catch (IOException e){
        System.out.println("Output stream not closed due to exception: " + e);
      }
      
      return !exception;//return the inverse of boolean (if exception happened, there was a failure)
    
    }
    
  }
  
  public static void main(String[] args){
    DistributionScheduler ds = new DistributionScheduler();
    
//    ds.runAll();
//    
   
    
    boolean writingResult = ds.createAndWriteSchedule();
    
    
    System.out.println("Writing result: " + writingResult);
    
     ds.printReport();
    
  }
}
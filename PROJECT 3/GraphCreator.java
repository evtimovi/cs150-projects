import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * This class is designed to read the input files
 * location.txt, locations.txt, connectivity.txt, 
 * needs.txt and resources.txt and create the distribution network
 * model as a graph.
 * 
 * This functionality is achieved through several internal helper methods
 * and through the method createGraph() that returns an 
 * instance of the Graph class filled with stores and warehouses
 * based on the information in the source files.
 * 
 * Imposed order of scanning:
 * resources.txt
 * locations.txt
 * location.txt
 * 
 * @author Ivan Evtimov
 */
public class GraphCreator{
  
  /**
   * This field is the graph itself that will be constructed
   * based on the information in the text files.
   * It holds an integer as a key and an instance or child of the DistributionNetworkMember class.
   */
  private Graph<Integer, DistributionNetworkMember> theGraph;
  
  /**
   * This map is used to temporarily store
   * the information about which nodes are restaurants and 
   * which nodes are warehouses.
   */
  private HashMap<Integer, String> locationMap;
  
  /**
   * This field should hold the number of trucks we have at each warehouse.
   */
  private int numOfTrucks;
  
  /**
   * This field should hold the mileage for each truck.
   */
  private int mileage;
  
  /**
   * This field should hold the capacity for each truck
   */
  private int maxCapacity;
  
  /**
   * This map stores how much units each resource
   * takes up on the truck.
   */
  private HashMap<String, Integer> capacityNeeds;
  
  /**
   * This field serves to prevent access to other fields
   * before the creation of the graph.
   */
  private boolean graphCreated;
  
  
  /**
   * Stores the keys of the nodes that have warehouses in them/
   */
  private ArrayList<Integer> keysOfWarehousesArr;
  
  /**
   * Stores the keys of the stores.
   * 
   */
  private ArrayList<Integer> keysOfStoresArr;
  
  
  /**
   * The constructor method initializes the graph. It is still empty, but shoould hold
   * an integer as a key and an instance or child of the DistributionNetworkMember class.
   */
  public GraphCreator(){
    //initialize the graph itself
    theGraph = new Graph<Integer, DistributionNetworkMember>();
    
    //initialize the location map
    locationMap = new HashMap<Integer, String>();
    
    //initialize the capacity needs map
    capacityNeeds = new HashMap<String, Integer>();
  
    //set the graph created to false
    graphCreated = false;
    
    //create the array that will hold the keys of the warehouses
    keysOfWarehousesArr = new ArrayList<Integer>();
    keysOfStoresArr = new ArrayList<Integer>();
  }
  
  /**
   * This method is used to scan in the
   * information from the resources.txt and
   * store it in this class's internal fields.
   * @return true if successful scanning
   */
  private boolean scanResources(){
    
     //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "resources.txt" ) ));
      
      //scan the whole thing in one giant string with semicolons separating what were the lines
      String inputString = "";
      
      while ( sc.hasNextLine() ){
        inputString = inputString + ";" + sc.nextLine();
      }
      
      //compile pattern for warehouse
      Pattern warehousePat = Pattern.compile("warehouse:\\s(\\d+)");
      
      //match it
      Matcher warehouseMat = warehousePat.matcher( inputString );
      
      //find it
      warehouseMat.find();
      
      //first group is number of trucks per warehouse, so set that accordingly
      numOfTrucks = Integer.parseInt( warehouseMat.group( 1 ) );
      
      
      //compile pattern for truck
      Pattern truckPat = Pattern.compile( "truck:\\s(\\d+)\\s(\\d+)" );
      
      //match it
      Matcher truckMat = truckPat.matcher( inputString );
      
      //find it
      truckMat.find();
      
      //first group is capacity of trucks
      maxCapacity = Integer.parseInt( truckMat.group( 1 ) );
      
      //second group is the mileage
      mileage = Integer.parseInt( truckMat.group( 2 ) );
      
      
      //get rid of all of truck and warehouse keywords in the string
      inputString = truckMat.replaceAll("");
      Matcher warehouseReplacementMat = warehousePat.matcher( inputString );//because the inputString was updated
      inputString = warehouseReplacementMat.replaceAll("");
      
      //now the temp string should have just a semicolon in place of all the warehouse and truck keywords
      //therefore, all that is left are resources
      //scan them all in
      //compile a pattern
      Pattern resourcesPat = Pattern.compile( "(\\w+):\\s(\\d+)" );
      
      //get the matcher
      Matcher resourcesMat = resourcesPat.matcher( inputString );
      
      //as long as we can find another match of the regex
      while( resourcesMat.find() ){
        
        //store in the capacity needs map
        capacityNeeds.put( resourcesMat.group( 1 ), Integer.parseInt( resourcesMat.group( 2 ) ) );
      }
      
      //everything went okay:
      return true;
      
      
    } 
    catch (Exception e){
      
      System.out.println("Input not scanned in due to exception (resources): " + e);
      //unsuccessful due to exception
      return false;
    } 
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception: " + e);
          return false; //unsuccessful due to exception
        }
      }
    }
  
  }
  
  /**
   * This method is used to scan in the information 
   * about the locations of stores and warehouses and put
   * them in the internal map.
   * @return true if operation successful
   */
  private boolean scanLocation1(){
    
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "locations.txt" ) ));
      
      //initialize a temp string
      String tempString = "";
      
      //pattern for scanning in
      Pattern pat = Pattern.compile( "(\\w+)\\s(\\d+)" );
      
      //declare the matcher
      Matcher mat = null;
      
      //temporary variables
      int key;
      String type = "";
      
      //for the loop
      Store tempStore = null;
      Warehouse tempWarehouse = null;
      
      //scan all lines
      while ( sc.hasNextLine() ){
        
        //scan in the next line
        tempString = sc.nextLine();
        
        //match it
        mat = pat.matcher(tempString);
        
        //find to capture groups
        mat.find();
        
        //the type is the first thing
        type = mat.group(1);
        
        //the key is the second thing
        key = Integer.parseInt( mat.group(2) );
        
        //put that in the map
        locationMap.put( key, type );
        
      }
      
      return true;//no errors occured
      
    } 
    catch (Exception e){
      
      System.out.println("Input not scanned in due to exception (location1): " + e);
      //unsuccessful due to exception
      return false;
    } 
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception: " + e);
          return false; //unsuccessful due to exception
        }
      }
    }
  }
  
  /**
   * This method scans in the information
   * in the file location.txt, node elements
   * are initialized according to information in the map
   * on locations (stores which node is a restaurant, warehouse, etc.).
   * @return true if operation successful
   */
  private boolean scanLocation2(){

    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "location.txt" ) ));
      
      //initialize a temp string
      String tempString = "";
      
      //pattern for scanning in
      Pattern pat = Pattern.compile( "(\\d+)\\s\\((\\d+),\\s(\\d+)\\)" );
      
      //declare the matcher
      Matcher mat = null;
      
      //temporary variables
      int x, y, key;
      
      //for the loop
      Store tempStore = null;
      Warehouse tempWarehouse = null;
      
      //scan all lines
      while ( sc.hasNextLine() ){
        
        //scan in the next line
        tempString = sc.nextLine();
        
        //match it
        mat = pat.matcher(tempString);
        
        //find to capture groups
        mat.find();
        
        //first group is the key
        key = Integer.parseInt( mat.group(1) );
        
        //second group is x
        x = Integer.parseInt( mat.group(2) );
        
        //third group is y
        y = Integer.parseInt( mat.group(3) );
        
        String type = locationMap.get( key );
        
        if( type == null ){
          //create a node with a null value
          theGraph.addNode( key, null );
          
        } else  if( type.equals("store") ){  //check whether this lcoation has a store or a warehouse
          
          //create a new store
          tempStore = new Store( key );
          
          //add it to the graph
          theGraph.addNode( key, tempStore );
          
          //add the current key to the stores array
          keysOfStoresArr.add( key );
        }
        else if ( type.equals( "warehouse" ) ){
          
          //create a new warehouse
          tempWarehouse = new Warehouse( numOfTrucks, key );
          
          //add it to the graph
          theGraph.addNode( key, tempWarehouse );
          
          //add it to the keys of warehouses array
          keysOfWarehousesArr.add( key );
        } 
      }
      
      //everything went okay
      return true;
      
    } 
    catch (Exception e){
      
      System.out.println("Input not scanned in due to exception (location2): " + e);
      e.printStackTrace();
      //unsuccessful due to exception
      return false;
    } 
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception: " + e);
          return false; //unsuccessful due to exception
        }
      }
    }
  }
  
  /**
   * This method scans in and assigns the needs to each store.
   * @return true if operation successful
   */
  private boolean scanNeeds(){
  
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "needs.txt" ) ));
      
      //compile pattern
      Pattern needsPat = Pattern.compile("(\\d+)\\s(\\w+)\\s(\\d+)");
      
      //declare matchr
      Matcher needsMat = null;
      
      //used in line by line scan
      String tempString = "";
      
      //variables used in loop
      int key;
      int need;
      String resource;
      Store tempStore = null;
      
      //scan line by line
      while( sc.hasNextLine() ){
      
        //get the next line
        tempString = sc.nextLine();
        
        //get a matcher
        needsMat = needsPat.matcher( tempString );
        
        //find
        needsMat.find();
        
        //first group is key
        key = Integer.parseInt( needsMat.group(1) );
        
        //second group is the resource
        resource = needsMat.group( 2 );
        
        //third group is the need for that resource
        need = Integer.parseInt( needsMat.group( 3 ) );
        
        //get the store in the graph and assign that need to it
        tempStore = (Store) theGraph.get( key );
        boolean result = tempStore.setNeed( resource, need );
        
        if( result == false ){
          return false;//something went wrong if the result was false
        }
      }
      
      //everything went okay
      return true;
      
    } 
    catch (Exception e){
      
      System.out.println("Input not scanned in due to exception (needs): " + e);
      //unsuccessful due to exception
      return false;
    } 
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception: " + e);
          return false; //unsuccessful due to exception
        }
      }
    }
  }
  
  /**
   * This method is used to scan in the connectivity.txt
   * and connect the graph by creating the specified edges.
   * @return true only if operation succeeds
   */
  private boolean scanConnectivity(){
  
      //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "connectivity.txt" ) ));
      
      //compile pattern
      Pattern connPat = Pattern.compile("(\\d+)\\s-\\s(\\d+)\\s(\\d+)");
      
      //declare matchr
      Matcher connMat = null;
      
      //used in line by line scan
      String tempString = "";
      
      //variables used in loop
      int key1, key2, weight;
            
      //scan line by line
      while( sc.hasNextLine() ){
      
        //get the next line
        tempString = sc.nextLine();
        
        //get a matcher
        connMat = connPat.matcher( tempString );
        
        //find to capture groups
        connMat.find();
        
        //first group is key1
        key1 = Integer.parseInt( connMat.group(1) );
        
        //second group is key2
        key2 = Integer.parseInt( connMat.group(2) );
        
        //third group is the edge's weight
        weight = Integer.parseInt( connMat.group(3) );
        
        //check for duplicate edges, if 
        //the weight between the two keys is not 0, then the adding of the duplicate edge will not occur
        if( theGraph.getWeight( key1, key2 ) == 0 ){
          //add the approrpiate edge
          theGraph.addEdge( key1, key2, weight );
        }
        
        
      }
      
      //everything went okay
      return true;
      
    } 
    catch (Exception e){
      
      System.out.println("Input not scanned in due to exception: " + e);
      //unsuccessful due to exception
      return false;
    } 
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          return false; //unsuccessful due to exception
        }
      }
    }
    
  }
  
  /**
   * Method used to get the graph!
   * @return the graph constructed based on the input files
   */
  public Graph<Integer, DistributionNetworkMember> createGraph(){
    scanResources();
    scanLocation1();
    scanLocation2();
    scanNeeds();
    scanConnectivity();
    
    //modify the graphCreated to true
    graphCreated = true;
    
    return theGraph; //scanned in and complete
  }
  
  /**
   * Method to get the capacity needs for the resources
   * based on the information in the text file.
   * Please, note that this method may only be called after
   * createGraph has been called because createGraph is the only
   * publicly accessible method that scans in the text files.
   * @return a hash map of the capacity needs for each resource where the key is the resource and 
   * the value is an integer indicating how many units does it take up on the truck
   * @throws IllegalStateException if createGraph hasn't been called 
   */
  public HashMap<String, Integer> getCapacityNeeds() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return capacityNeeds;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
  /**
   * Method to get the maximum mileage of the trucks
   * based on the information in the text files.
   * Please, note that this method may only be called after
   * createGraph has been called because createGraph is the only
   * publicly accessible method that scans in the text files.
   * @return an int indicating the maximum mileage of the trucks
   * @throws IllegalStateException if createGraph hasn't been called 
   */
  public int getMileage() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return mileage;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
    /**
   * Method to get the maximum capacity of the trucks
   * based on the information in the text files.
   * Please, note that this method may only be called after
   * createGraph has been called because createGraph is the only
   * publicly accessible method that scans in the text files.
   * @return an int indicating the capacity constraints of the trucks
   * @throws IllegalStateException if createGraph hasn't been called 
   */
  public int getMaxCapacity() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return maxCapacity;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
  /**
   * Method to get the number of trucks at each warehouse, same implementation as other getters.
   */
  public int getNumOfTrucks() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return numOfTrucks;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
   /**
   * Method to get the keys of the warehouses, same implementation as other getters.
   * @return Arraylist with all the keys of warehouses in the graph
   */
  public ArrayList<Integer> getWarehouseKeys() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return keysOfWarehousesArr;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
     /**
   * Method to get the keys of the stores, same implementation as other getters.
   * @return Arraylist with all the keys of stores in the graph
   */
  public ArrayList<Integer> getAllStoresArr() throws IllegalStateException{
    if( graphCreated ){//check if the graph has been created
      return keysOfStoresArr;
    } else { //if not, say how bad it is
      throw new IllegalStateException("Nothing has been scanned in yet! Call createGraph() first!");
    }
  }
  
  
  
  public static void main(String[] args){
    GraphCreator gc = new GraphCreator();
    
    gc.createGraph();
  }
  
}
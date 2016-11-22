import java.util.*;

/**
 * This class simulates the delivery truck 
 * that goes between warehouses and stores.
 * It has static constants that ensure that 
 * each truck does not exceed its capacity or mileage.
 * 
 * @author Ivan Evtimov
 */
public class Truck{

  /**
   * This constant holds the storage capacity of each truck.
   * It is universal for each truck.
   */
  private static int MAX_CAPACITY;
  
  /**
   * This constant holds the mileage of each truck.
   * It is universal for all trucks.
   */
  private static int MILEAGE;
  
  /**
   * This boolean prevents the mileage from being tampered
   * with once initially set.
   */
  private static boolean mileageSet = false;
  
  /**
   * This boolean prevents the max capacity from being tampered
   * with once initially set.
   */
  private static boolean capSet = false;
  
  /**
   * This field is the container that holds the information on how
   * much of each resource this truck is carrying
   */
  private HashMap<String, Integer> loadMap;
  
  /**
   * This filed stores the number of trucks that have been created
   * and is used to assign a unique number from 1 on to each truck.
   */
  private static int truckCounter = 0;
  
  /**
   * This field keeps track of the total load of the truck.
   * It removes the need to sum the different resources in the map
   * each time we want to know how much stuff is on the truck
   */
  private int totalLoad;
  
  /**
   * This field keeps track of how many miles the truck has gone. 
   */
  private int distanceCovered;
  
  /**
   * This field holds the truck's unique number.
   */
  private int truckNum;
  
  /**
   * This field is static and common for all instances of the class.
   * It keeps track of how much storage units each resource takes up.
   */
  private static HashMap<String, Integer> capacityNeeds;
  
  /**
   * Boolean to prevent multiple modifications of capacityNeeds.
   */
  private  static boolean capNeedsSet = false;
  
  /**
   * Array of visited nodes.
   */
  private ArrayList<Integer> visitedArr;
  
  /**
   * Holds the truck's originating warehouse
   */
  private int start;
  
  /**
   * This field creates the truck by initializing its internal container for the resources.
   * The truck is also carrying nothing yet.
   */
  public Truck(){
    loadMap = new HashMap<String, Integer>();//initialize the internal map
    totalLoad = 0;//the truck hasn't been loaded upon its creation
    distanceCovered = 0; //the truck hasn't gone anywhere yet
    
    //update the truck counter
    truckCounter++;
    
    //assign the updated value to this truck
    truckNum = truckCounter;
    
    visitedArr = new ArrayList<Integer>();
    

  }
  
  /**
   * Method to set the storage needs for each resource the trucks will be carrying.
   * @param capacityNeedsMap the HashMap containing the storage requirements for each resource
   * @throws IllegalStateException if the capacity needs of resources have already been set
   */
  public static void setCapacityNeeds( HashMap<String,Integer> capacityNeedsMap ) throws IllegalStateException{
    
    //check for previous modifications
    if( capNeedsSet == false ){
      capacityNeeds = new HashMap<String, Integer> (capacityNeedsMap);//set the capacity needs
      capNeedsSet = true; //update the boolean to prevent further modification
    
     } else {
      throw new IllegalStateException("Storage requirements can only be set once!");
    }
  }
  
  
  /**
   * Accessor method for the static truck counter.
   * @return how many trucks have been created
   */
  public static int getCounter(){
    return truckCounter;
  }
  
  /**
   * Accessor method for the individual truck's number.
   * @return the truck's unique number
   */
  public int getTruckNum(){
    return truckNum;
  }
  /**
   * This method is designed to assign the total capcity of each truck.
   * Since this is a variable universal to all instances of the class, the method is static.
   * @param totalCap the total number of units that each truck can carry
   * @throws IllegalStateException if the carrying capacity has already been set
   */
  public static void setCarryingCap( int totalCap ) throws IllegalStateException{
    if( capSet == false ){
      MAX_CAPACITY = totalCap;//set accordingly
      
      //update the boolean to allow no further changes
      capSet = true;
      
    } else {
      throw new IllegalStateException("Carrying capacity can only be set once!");
    }
  }
  
  /**
   * This method is designed to assign the total mileage of each truck.
   * Since this is a variable universal to all instances of the class, the method is static.
   * @param totalMiles the total number of miles that each truck can go
   * @throws IllegalStateException if mileage has already been set once
   */
  public static void setMilesCap( int totalMiles ) throws IllegalStateException{
    if( mileageSet == false ){
      MILEAGE = totalMiles;//set accordingly
      
      //update the boolean to allow no further changes
      mileageSet = true;
    } else {
      throw new IllegalStateException("Mileage can only be set once!");
    }
  }
  
  /**
   * This method returns the static mileage cap on the trucks.
   * @return the mileage restriction on each truck
   */
  private static int getMileage(){ return MILEAGE; }
  
  /**
   * This method returns the static load cap on the trucks.
   * @return the load restriction on each truck
   */
  private static int getMaxCapacity(){ return MAX_CAPACITY; }
  
  /**
   * This method loads a given resource on the truck.
   * If the truck does not have that resource, it just adds it to its
   * container. If it does have the resource, the container is updated
   * accordingly. Hence, unlike the Store, the trucks accept duplicated loading
   * of the same resource.
   * This method will throw an exception if an attempt
   * to load more units than it can hold is made.
   * An exception will also be thrown if the truck is full before the loading/
   * Therefore, this method should be best used with the isFull and/or the getFree methods.
   * @param resource the resource that we want to load on the truck
   * @param units the number of units we want to load from that resource
   * @return the number of units of that resource that are on the truck NOT IN TERMS OF STORAGE
   * @throws UnsupportedOperationException if truck is full before loading or if its capacity would be exceeded
   * OR if there is no information on how many units of storage each unit of the resource takes up
   */
  public int loadResource( String resource, int units ) throws UnsupportedOperationException{
  
    //check if truck has already reached its capacity
    if( totalLoad == MAX_CAPACITY ){
      
      throw new UnsupportedOperationException("Truck is already full");
    }
    
    //check how many units of storage this resource takes up
    Integer storageNeed = capacityNeeds.get( resource );
    
    //check if the truck knows how many units of storage this resource will take up
    if( storageNeed == null ){
      throw new UnsupportedOperationException("Truck not designed to carry this resource!");
    }
    
    //caclulate what the new total load of the truck would be
    int newTotalLoad = totalLoad + units * storageNeed;

    //check if the truck's capacity would be exceeded
    if( newTotalLoad > MAX_CAPACITY ){
      
      throw new UnsupportedOperationException("Truck's capacity would be exceeded!");
    
    } else {
       //the truck's capacity won't be exceeded 
      //load freely and return how many units of that resource are now on the truck
      return load ( resource, units, storageNeed );

    }
  }
  
  /**
   * Private helper method to perform loading
   * when capacity is ensured.
   * Please, note this method performs no checks on 
   * @param resource the resource that we want to load on the truck
   * @param units the number of units we want to load from that resource
   * @return how much of that resource is the truck now carrying
   */
  private int load( String resource, int units, int storageNeed ){
    
        
    //update the totalLoad counter
    totalLoad = totalLoad + units*storageNeed;
    
    //get the current amount of that resource on the truck
    Integer currUnits = loadMap.get( resource );
    
    //check if resource is already there
    if( currUnits == null ){
    
      //it's not
      //add it and return units
      loadMap.put( resource, units );
      
      return units;
      
    } else {
    
      //the resource is already here
      //update and set accordingly
      currUnits = currUnits + units;
      
      loadMap.put( resource, currUnits );
      
      return currUnits;//this is the new amount of units of that resource on the truck
    }
  }
  
  /**
   * This method checks if the truck is full, returning true if soe
   * @return true if max capacity is matched by total load, false otherwise
   */
  public boolean isFull(){
    return totalLoad == MAX_CAPACITY;
  }
  
  /**
   * This method advances the truck a given number of miles.
   * If the mileage would not be exceeded, then the truck advances those miles.
   * If it would be exceeded, then the truck stays where it is (in terms of miles travelled).
   * @param miles the number of miles to be covered
   * @return true if truck moves, false if mileage would be exceeded
   */
  public boolean travel( int miles ){
  
    //check if mileage would be exceeded
    if ( miles + distanceCovered > MILEAGE ){
      //if so, return false and don't advance the truck
      return false;
      
    } else {
      //if the distance would not be exceeded, 
      //advance the truck...
      distanceCovered = distanceCovered + miles;
      
      //... and return true
      return true;
    }
  }
  
  /**
   * This method calculates how many miles the truck has remaining 
   * before its mileage is exceeded. It does so by subtracting the distance
   * covered from the mileage.
   * @return remaining range of miles the truck can cover
   */
  public int getRemainingMiles(){
    return MILEAGE - distanceCovered;//subtract distance covered from mileage and return
  }
  
  /**
   * This method returns how many units of storage are taken up on the truck.
   * @return the number of spots taken up on the truck by all resources
   */
  public int getTotalLoad(){ return totalLoad;  }
  
  /**
   * This methods calculates the number of free storage spaces that the truck has
   * by subtracting the totalLoad from the maximum capacity.
   * @return how many more units the truck can take up
   */
  public int getFree(){ return MAX_CAPACITY - totalLoad; }
  
  /**
   * This method is used only to ensure optimal unit-testing.
   * It resets the mileage and the maximum capacity as well
   * as the total number of trucks created.
   * It should never be called when this class is actually being used.
   */
  public static void reset(){
    //reset the booleans
    capSet = false;
    mileageSet = false;
    capNeedsSet = false;
    //reset the truck counter
    truckCounter = 0;
    
  }
  
  /**
   * Method to return the load of a given resource IN ITS OWN UNITS, not storage units.
   * @param resource the resource we want to have
   * @return the number of units of that resource on the truck, 0 if none
   */
  public int getLoadOf( String resource ){
  
    //get the units of a given resource on the truck
    Integer currLoad = loadMap.get( resource );
    
    //check if the resource is availbale
    if( currLoad == null ){
      return 0;//if not, return 0
    } else {
      return currLoad; //if yes, return how many units are taken up by it
    }
  }
  
  /**
   * This method allows you to unload the given number of units
   * of a given resource.
   * @param resource the resource we want to unload from the truck
   * @param unitsToBeUnloaded the units of that resource we want
   * @return the number of units that have been unloaded from that resource, 
   * 0 if the resource isn't on the truck at all OR if there are more units
   * demanded for unload than there are on the truck
   */
  public int unload( String resource, int unitsToBeUnloaded ){
    
    //get the number of units of that resource currently on the truck
    Integer currUnits = loadMap.get( resource );
    
    //get how much each unit of that resource takes up
    Integer spaceNeeded = capacityNeeds.get( resource );
    
    if ( currUnits == null || currUnits == 0 ){
      return 0; //nothing there from that resource
    } else {
      //resource is present
      //calculate remaining number of units of that resource
      int remainingUnits = currUnits - unitsToBeUnloaded;
      
      //check if negative
      if( remainingUnits < 0 ){
        return 0;
      } else {
        
       // System.out.println(resource+"\t"+remainingUnits);
        //we have a non-negative number of resources remaining on the truck
        //update the total Load
        totalLoad = totalLoad - unitsToBeUnloaded * spaceNeeded;
        
        //put the new number of resources
        loadMap.put( resource, remainingUnits );
        
        //return the number of resources unloaded
        return currUnits - loadMap.get( resource );
      }
    }
  }
 
  /**
   * Method to tell outside users of truck class
   * if this instance can cover more miles without
   * its mileage capacity being exceeded.
   * @param miles how many miles we would like the truck to go
   * @return true if it can go those miles, false otherwise
   */
  public boolean canGo( int miles ){
  
    return miles + distanceCovered < MILEAGE;//will tell if that range is doable
  }
  
  public void visit( int key ){
    visitedArr.add( key );
  }
  
  public void startedFrom( int key ){
    start = key;
  }
  
  /**
   * Method to print report in desired format
   * @return String in format
   * <start node of truck> <ID of truck> (<list of visited nodes>) (<list of resources and amounts>)
   */
  public String printReport(){
  
    String report = "";//initialize
    report = report + start + " "; //add starting node
    report = report + this.truckNum + " "; //add this truck's number
    report = report + visitedArr.toString() + " "; // add the visited nodes
    
    Set<String> keysOfLoadMap = loadMap.keySet(); //get the keys in the load map (the resources)
    
    for( String currKey : keysOfLoadMap ){ //iterate over the map
    
      report = report + currKey + " " + loadMap.get(currKey) + " "; //add each resource
    }
    
    return report; //return the compiled report
  }
  
  public boolean canLoad( String resource, int units ){
  
     //check if truck has already reached its capacity
    if( totalLoad == MAX_CAPACITY ){
      
      return false;
    }
    
    //check how many units of storage this resource takes up
    Integer storageNeed = capacityNeeds.get( resource );
    
    //check if the truck knows how many units of storage this resource will take up
    if( storageNeed == null ){
      throw new UnsupportedOperationException("Truck not designed to carry this resource!");
    }
    
    //caclulate what the new total load of the truck would be
    int newTotalLoad = totalLoad + units * storageNeed;

    //check if the truck's capacity would be exceeded and return appropriately
    return newTotalLoad < MAX_CAPACITY;
  }
  
}
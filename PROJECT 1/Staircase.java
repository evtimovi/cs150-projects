import java.util.*;

/**
 * This class will represent the staircase(s)
 * in the buidling. It is designed so that it controls
 * access to specific "entry points", which are designated indices
 * of the internal container that can be used for adding. 
 * In other words,
 * upon the creation and based on the capacity passed as a parameter,
 * the Staircase class will make only certain entries in the list available to 
 * the add method.
 * 
 * Since we need a data structure that can quickly access people 
 * at any index (has a constant time complexity for get from a random index),
 * I use an array list.
 * 
 * In essence, this class will represent a queue with multiple entry points.
 * 
 * At each time step, everybody who is already in the list will
 * move one index forward. Only if the "entry points"
 * are null will the add method for the specific floor be allowed.
 * 
 * @author Ivan Evtimov
 */
public class Staircase {
  
  /**
   * This field keeps track of the capacity between floors.
   * Since it is uniform for all staircases, it should be static (so
   * that we do not have any staircases that do not go to all floors).
   * 
   */
  private static int capacityBtwFloors;
  
  /**
   * This is the instance variable for the number of floors.
   * For the same reasons outlined for making capacityBtwFloors
   * static and final, this variable should also be static and final.
   */
  private static int numOfFloors;
  
  /**
   * This is the instance variable for the total staircase capacity.
   * It will be used to cap the size of the container used for the staircase.
   * Again, it is static as it is the same for all staircases.
   */
  private static int totalCapacity; 
  
  
  /**
   * This field will ensure that the setUp method is not called more than once.
   * Since the one-time call limit applies to all instances of the class (all staircases
   * have the given capacity, the buidling only has a predetermined number of floors), 
   * the variable is static.
   */
  private static boolean setUpCheck = true;
  
  /**
   * This field is the Staircase class's internal container.
   * Of course, it is different among staircases, so the variable is not static.
   */
  private ArrayList<Person> stairContainer;
  
  /**
   * This static field keeps track of all the people who have been evacuated.
   * Since the count of the evacuees is universal for the simulation, it should
   * not be instance-specific.
   */
  private static int evacuatedCount;
  
    
  /**
   * The constructor initializes the container for the staircase.
   * It will have the total capacity that has been set for all instances 
   * of the class by the setUp method. Therefore, the constructor will not be able
   * to run if the setUp method has not been called beforehand.
   * Moreover, this container fills up the array list with null objects. The null objects
   * represent "empty steps" on the staircase and will serve to avoid IndexOutOfBoundsException-s
   * when calling the set method. This also attempts to prevent the general chaos 
   * of different indices for the adding while the staircases have not yet filled up.
   * @throws IllegalStateException if the constructor is called before the environment
   * for the floors has been set up, e.g. the setUp method has not been called 
   */
  public Staircase(){
  
    if(totalCapacity != 0){
      //initialize the container with the specified totalCapacity
      stairContainer = new ArrayList<Person>(totalCapacity);
      
      //fill up the container with null objects that will serve as placeHolders
      //do not fill up the last two spots of the container
      for (int i = 0; i<totalCapacity; i++){
      
        stairContainer.add(null);
      }
    }else{
      throw new IllegalStateException("Set up method has not been called. Data unavailable for capacity of staircases.");
    }
  }
  
  /**
   * This method is designed to act as a constructor, but it is different in two important ways:
   * First of all, it can only be called once. Since we want to set up the capacity of each staircase
   * and the number of floors in the building only once,
   * I have created an instance variable that is true upon its initialization and is set
   * to false when the setUp method is called. If the instance variable's value is false
   * and the setUp method is called nonetheless, an exception will be thrown.
   * More importantly, this method sets static variables that are the same for all
   * instances of the class. This is done to ensure that the capacity of the staircases
   * or their entry points or the number of floors in the building do not differ
   * among the instances of the staircases. This is also the reason why this method
   * is static - it makes modifications to all instances, so calling it with
   * a specific controlling object would be a nonsense. Also,
   * it will need to be called before any constructor
   * is called as the constructors rely on the calculations for the totalCapacity
   * to build their internal containers.
   * @param paramCapacityBtwFloors the specified capacity of the staircase between the floors (entry point included)
   * @param paramNumOfFloors the specified number of floors in the building
   * @throws UnsupportedOperationException if this method is called more than once.
   */
  public static void setUp(int paramCapacityBtwFloors, int paramNumOfFloors)
                           throws UnsupportedOperationException{
  
    //execute the method only if setUpCheck is true
    if(setUpCheck){
    
      //set the specified number of floors and the capacity of the staircase
      capacityBtwFloors = paramCapacityBtwFloors;
      numOfFloors = paramNumOfFloors;
      
      //calculate the total capacity of each staircase
      //because there should be no steps after the last floor, exclude the capacity btw floors
      totalCapacity = numOfFloors * capacityBtwFloors - capacityBtwFloors +1;
      
      //set setUpCheck to false so that this method cannot be called again
      setUpCheck = false;
      
      //if setUpCheck is false, somebody is trying to call this method multiple times
      //so throw an exception to prevent that
    } else{
    
      throw new UnsupportedOperationException("Set-up method can be called only once!");
    }
  }
  
  /**
   * This method determines the appropriate entry point for the person at the specified floor
   * and then adds that person at that entry point. However, if the entry point is taken,
   * the person will not be allowed on the floor and the method will return false.
   * @return true if the person was successfully added to the staircase
   * @return false if the person could not enter
   * @throws IllegalArgumentException if trying to add a person from a floor that is non-existent in this building
   */
  public boolean addFromFloor(int floorOfEntry, Person incomingP) throws IllegalArgumentException{
  
    //cause exception if the floor doesn't exist or is the first floor
    if(floorOfEntry <1 || floorOfEntry > numOfFloors){
    
      throw new IllegalArgumentException("Please, choose a floor between 1 and " + numOfFloors +", including.");
    }
    
      //calculate entry point
      int entryPoint = (floorOfEntry-1)*capacityBtwFloors;
      
      //if the entry point is free, insert the person there
      if(stairContainer.get(entryPoint)==null){
        stairContainer.set(entryPoint, incomingP);
        
      //  System.out.println("///Staircase: Person " + incomingP + " was added at position " + entryPoint);
        
        return true;//return true to indicate success
      } else{
        
        return false; //if the entry point was taken, return false
      }
    }
  
  
  /**
   * This method lets the person at the bottom of the staircase out of the building.
   * Since we no longer care about that object, we just discard it.
   * The method does, however, update the evacuatedCount counter so that
   * we have an idea of when the building was evacuated.
   * Please, note that this will also move every every person one step down,
   * thereby removing the last entry in the array.
   * This is the reason why we use the add method if we are adding someone from the last floor.
   */
  public void letOut(){
  
    Person removedP;
    
    //remove the person at the front
    removedP = stairContainer.remove(0);
    
  // System.out.println("Evacuated " + removedP);
    
    //add a null object at the end of the array to make sure that the capacity of the stairContainer stays the same
    stairContainer.add(null);
    
    //update the counter IF THE OBJECT REMOVED WAS NOT NULL
    if(removedP != null){
      evacuatedCount++;
    }
    
    //System.out.println("Staircase class says: Evacuated person is: " + removedP);
    
  }
  
  /**
   * Accessor method to get the total evacuated count.
   * Since the variable for the total count of evacuated persons
   * is universal (static), this method is also static
   * @return the total number of evacuated Person-s
   */
  public static int getEvacuatedCount(){return evacuatedCount;}
  
  /**
   * Accessor method to get the capacity between floors.
   * It is static since that capacity is universal between staircases.
   * @return capcity between floors.
   */
  public static int getCap(){ return capacityBtwFloors;}
  
  /**
   * This method resets the staircases to zero and allows the setUp 
   * method to be called again. All instances of the class should be set
   * to null so that they will have to be redeclared.
   * 
   * IMPORTANT this method has been designed only for testing purposes
   * and is not to be used int he simulation.
   */
  public static void reset(){
    
    //set all instance variables to 0 or null
    capacityBtwFloors=0;
    
    numOfFloors=0;

    totalCapacity=0; 
    
    
    evacuatedCount=0;
    
    setUpCheck = true;
  }
  
  /**
   * A class to get the internal container's iterator.
   * IMPORTANT: Class needed only for testing. It should NOT
   * be used in the simulation.
   * @return iterator for internal container
   */
  public Iterator getIterator(){
    return stairContainer.iterator();
  }
  
  
  public static void main(String[] args){
 
  }
  
}
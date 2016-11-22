import java.util.*;

/**
 * This class contains all the Person objects
 * at the start of the simulation and assigns them
 * their timeToArrive using a standard Gaussian distribution.
 * Since we need a container with quick access that we won't be inserting
 * anything to after the initial set-up stage,
 * the ArrayList can do the job of holding the people
 * on the floor.
 * @author Ivan Evtimov
 */
public class Floor{

  /**
   * This field is the container for the floor.
   */
  private ArrayList<Person> arrContainer;
  
  /**
   * This field indicates which floor the instance variable is.
   */
  private int currentFloorNumber;
  
  /**
   * This field keeps track of how many floors have been created
   * and is used to determine what the number of the next floor is.
   */
  private static int totalFloorCount;
  
  /**
   * This is the Random number generator that will perform the Gaussian distribution.
   */
  private Random randGen;
  
  
  /**
   * This field keeps track of the floor's size (i.e. how many people
   * are actually on the floor as compared to the capcity which keeps
   * track of how many people CAN be on the floor).
   */
  private int size;
  

  
  /**
   * The constructor creates a new floor.
   * It checks what was the number of the last floor that was 
   * created and sets this floor's number to the next value.
   * It also creates an array list container
   * with the indicated capacity.
   * @param capacity the capcity for the floor.
   */
  public Floor(){
  
    //get the last floor number as indicated by the static variable
    int lastFloorNumber = totalFloorCount;
  
    //set this floor's number
    this.currentFloorNumber = lastFloorNumber + 1;
    
    //indent the static counter
    totalFloorCount++;
    
      
    //create the container to hold the Person objects
    arrContainer = new ArrayList<Person>();
    
    //set the size to 0 for now as the floor is empty yet
    size = 0;
  }
  
  /**
   * Accessor method to get the current floor's number.
   * @return the current floor's number
   */
  public int getFloorNumber(){ return currentFloorNumber; }
    
  /**
   * Accessor method to get the current floor's current size.
   * @return the number of Person objects currently on the floor
   */
  public int getFloorSize(){ return size; }
  
  /**
   * This method fills up 
   * the floor with Tenant and Visitor objects
   * where each one of them gets a randomly assigned
   * timeToArrive. The objects are generated based on the number of Tenants.
   * The number of visitors is determined by the number of tenants by dividing it by three.
   * The visitors are 3 times less than the tenants.
   * @param numOfTenants the number of tenants we want on this floor
   * @param seed the seed for the random number generator that will distribute
   * the times to arrive for the person objects
   * @param mean the mean for the Gaussian distribution
   * @param variance the variance for the Gaussian distribution
   * @throws UnsupportedOperationException if the number of people
   * we want to add exceeds the capacity we have set for teh floor
   */
  public void fillUp(int numOfTenants, int seed, double mean, double variance){
     
    //calculate number of visitors
    int numOfVisitors = (int) numOfTenants/3;
    
    //calculate total number of people
    int numOfPeople = numOfVisitors + numOfTenants;
  
      //initialize the random number generator
      randGen = new Random(seed);
      
      //declare a variable for the person object
      Person newGuy;
      
      //declare a variable to hold the time to arrive that was generated for the current Person
      double generatedTimeToArrive;
      
      //common index
      int i;
      
      for(i=0; i < numOfTenants; i++){
      
        //generate a time to arrive for this person
        
        generatedTimeToArrive = Math.abs( mean + randGen.nextGaussian() * variance);
        
          
        //create this visitor with the generated time to arrive and the current floor's number
        newGuy = new Tenant(currentFloorNumber, (int) generatedTimeToArrive);
        
        //add this Person to the array list container of the Floor class
        arrContainer.add(i, newGuy);
        
        //update the size counter
        size++;
      }
      
      for(; i < numOfPeople; i++){
      
        //generate a time to arrive for this person
        
        generatedTimeToArrive = Math.abs( mean + randGen.nextGaussian() * variance);
        
          
        //create this visitor with the generated time to arrive and the current floor's number
        newGuy = new Visitor (currentFloorNumber, (int) generatedTimeToArrive);
        
        //add this Person to the array list container of the Floor class
        arrContainer.add(i, newGuy);
        
        //update the size counter
        size++;
      }
   
  
  }
  
  
  
  /**
   * This method returns the person who is supposed to go to the queue for the staircase
   * and removes it from the Floor.
   * @param index the index we want to remove a person from
   * @return the Person at the specified index
   */
  public Person removePerson(int index){
  
    //create a placeholder for the person
    Person placeHolder = arrContainer.get(index);
    
    //set the entry in the container at its index to null
    arrContainer.set(index, null);
    
    //update the floor size
    this.size--;
    
    //return the Person in the placeHolder
    return placeHolder;
  }
  
 
  
  /**
   * Method to check if the time each Person has waited equals
   * its assigned time to arrive.
   * @param currentTime the current time step in the simulation
   * @return the index of the person if some object has waited enough
   * and -1 if no object has waited enough or the floor is empty
   */
  public int checkArrive(int currentTime){
    
    if(size!=0){
   
      //declare a placeholder variable to modify the current person at each iteration of the loop
      Person placeHolder;
      
      
      //go through the array
      for (int i = 0; i < arrContainer.size(); i++){
        
        //get the current object
        placeHolder = arrContainer.get(i);
        
        //if it is not empty
        if (placeHolder != null && placeHolder.getTimeToArrive() == currentTime){
          
          return i;
          
        }
      }
   
      
      //if no one has waited enough, return -1
      return -1;
    
    } else {
    
      return -1;
    }
  }
  
  /**
   * Method to check if the floor is empty.
   * @return true if size is equal to 0
   * @return false if size is equal to anything else
   */
  public boolean isEmpty(){
  
    if(size==0){
      return true;
    }
    else{ 
      return false;
    }
  }
  
  public String toString(){
  
    String report = "";
    for (int i = 0; i<arrContainer.size(); i++){
    
      if(arrContainer.get(i)!=null)
        report=report+"[Person at " + i + " time to arrive: " + arrContainer.get(i).getTimeToArrive()+"]";
    }
    return report;
  }
  
  /**
   * Method to get floor's iterator.
   */
  public Iterator getIterator(){
    return arrContainer.iterator();
  }
  
 
  
}
/**
 * This class will define the functionality for the Tenant and the Visitor classes.
 * @author Ivan Evtimov
 */
public class Person{
  
  /**
   * This constant sets the maximum number of Person objects that can be created.
   * It is unrealistic to expect a building with more than 10,000 occupants.
   */
  private static final int MAX_NUM_OF_PEOPLE = 10000;

  /**
   * This field will contain the person's ID. 
   * The ID number will be in the form (floor number)****
   * where the last three four will indicate the person's unique ID
   * and the first one (or the first two or three based on the number of floors
   * will indicate which floor it was created on. This ID system
   * does not anticipate that there will be any more than 10,000
   * Person objects created at any time.
   */
  private int personID;
  
  /**
   * This is the person's "assigned" time to arrive to the entrance to the staricase.
   * This value will be determined by the normal distribution.
   */
  private int timeToArrive;
  
  /**
   * This variable will help with the creation of the IDs.
   * It will be set to 1000 if the person was created on the first
   * floor, 2000 if it was created on the second floor and so on.
   */
  private int floorOfPerson;
  
  /**
   * This variable will keep track of how many people were created.
   * Hence, it is static (shared by all instances).
   */
  private static int totalCount;
  
  
  /**
   * Constructor method to create a Person object
   * based on the floor that he or she is created on
   * and its assigned time to arrive to the staircase.
   * @param floorNumber the floor the person was created on
   * @param timeToArrive the time to arrive to the staircase that the person gets assigned
   */
  public Person (int floorNumber, int timeToArrive){
  
    this.floorOfPerson = floorNumber;
    personID = floorNumber*10000+totalCount;
    totalCount++;
    this.timeToArrive = timeToArrive;
  }
  
  /**
   * Accessor method to get the person's ID.
   * @return the person's unique ID.
   */
  public int getID(){ return personID;}
  
   
  /**
   * Accessor method to get the person's floor.
   * @return the floor the person was created on.
   */
  private int getFloor(){ return floorOfPerson;}
  
  
  /**
   * Accessor method to get the person's time to arrive.
   * @return the time it takes the person to arrive
   * to the waiting queue for the staircase
   * that has been assigned to it by the Gaussian distribution
   */
  public int getTimeToArrive(){ return timeToArrive;  }
  
  public String toString(){
  
    String report = "Person has time to arrive: " + timeToArrive + " and ID: " + personID;
  
    return report;
  }
}
import java.util.*;

/**
 * This class comprises the containers used for holding the different floors, staircases, and waiting lines.
 * 
 * @author Ivan Evtimov
 */

public class Building{
  
  /**
   * These fields control the number of people per floor,
   * the number of floors and the total number of people in the buidling.
   * The field numOfFloors is used to "build the building."
   */
    private int numOfTenantsPerFloor;
    private int numOfFloors;
    private int totalPeople;
    private int numOfPeoplePerFloor;
    private int numOfVisitorsPerFloor;
    
  
    
    /**
     * These fields control the number of staircases in the building
     * and the individual staircase capacity.
     * They are both used in "building" the building.
     */
    private int numOfStaircases;
    private int capacityOfStaircases;
       
    
    /**
     * These fields serve as storage for the floors and their corresponding waiting lines.
     * IMPORTANT: since the items are stored in an array, each floor and each waiting line
     * has an index that is one less than its true order.
     * For instance, first floor is at index 0, 2nd floors is at position 1 and so on.
     * The are arrays because we need quick and efficient access (we will be accessing thos multiple
     * times during each time step).
     */
    private ArrayList<Floor> floorsContainer;
    private ArrayList<WaitingLine> waitingLinesContainer;
    private ArrayList<Staircase> stairsContainer;
    
    /**
     * These fields ensure that the proper constructor has been called.
     * They are initialized to false because the respective createFloor methods
     * should not be able to have been called without the appropriate constructor.
     */
    private boolean checkEqualConstructor = false;
    private boolean checkNormalDistConstructor = false;

    /**
     * The constructor sets up "the building."
     * First, it assigns all of the parameters to instance fileds, so that they can be referenced by the other methods.
     * Second, it calculates the total number of people in the building, which will be vital
     * for stopping the simulation.
     * Third, it creates containers for easier access to the floors, waiting lines and staircases.
     * Please, note that none of these variables should be modified for the simulations' run, 
     * so there are no modifier methods for them.
     * @param numOfFloors the number of floors we want the building to have
     * @param capOfStaircases the "capacity" of each staircase, that is, the number of entries between floors
     * @param numOfStaircases the number of staircases in the building
     * @param tenantsPerFloor the number of people each floor has
     * 
     */
  public Building(int numOfFloors, int numOfStaircases, int capOfStaircases, int tenantsPerFloor){
      
      //assign the passed parameters to the instance variables
      this.numOfFloors = numOfFloors;
      this.numOfStaircases = numOfStaircases;
      this.capacityOfStaircases = capOfStaircases;
      this.numOfTenantsPerFloor = tenantsPerFloor;
      this.numOfVisitorsPerFloor = (int) numOfTenantsPerFloor/3;
      this.numOfPeoplePerFloor = numOfTenantsPerFloor + numOfVisitorsPerFloor;
      
      //calculate the number of people in the building and store in a field
      this.totalPeople = numOfFloors * numOfPeoplePerFloor;
      
//      System.out.println("Building class says: total people at set-up: " + totalPeople + "\n");
//      System.out.println("Building class says: people per floor at set-up: " + numOfPeoplePerFloor + "\n");
//      System.out.println("Building class says: numOfVisitorsPerFloor at set-up: " + numOfVisitorsPerFloor + "\n");
//      System.out.println("Building class says: numOfVisitorsPerFloor at set-up: " + numOfTenantsPerFloor + "\n");
      
      //initialize the container that will hold the floors with the given size (numOfFloors)
      floorsContainer = new ArrayList<Floor>(numOfFloors);
      
      //initialize the container that will hold the waiting lines
      waitingLinesContainer = new ArrayList<WaitingLine>(numOfFloors); //there should be one waiting line for each floor
  
      //initialize the staircases container
      stairsContainer = new ArrayList<Staircase>(numOfStaircases);
      
      //set the checkEqual variable to true
      checkEqualConstructor = true;
  }
  
   /**
     * This constructor sets up the building for the normal distribution of people per floor.
     * @param numOfFloors the number of floors we want the building to have
     * @param capOfStaircases the "capacity" of each staircase, that is, the number of entries between floors
     * @param numOfStaircases the number of staircases in the building
     * 
     * 
     */
  public Building(int numOfFloors, int numOfStaircases, int capOfStaircases){
      
      //assign the passed parameters to the instance variables
      this.numOfFloors = numOfFloors;
      this.numOfStaircases = numOfStaircases;
      this.capacityOfStaircases = capOfStaircases;
 
      
     
      
      //initialize the container that will hold the floors with the given size (numOfFloors)
      floorsContainer = new ArrayList<Floor>(numOfFloors);
      
      //initialize the container that will hold the waiting lines
      waitingLinesContainer = new ArrayList<WaitingLine>(numOfFloors); //there should be one waiting line for each floor
  
      //initialize the staircases container
      stairsContainer = new ArrayList<Staircase>(numOfStaircases);
      
      //set the check normal distribution constructor variable to true
      checkNormalDistConstructor = true;
  }

    /**
     * This method creates and fills up the floors with the given parameters.
     * @param seed the seed for the random number generator used in the Gaussian distribution
     * @param mean the mean for the normal distribution
     * @param deviation the standard deviation for the normal distribution
     * @throws IllegalStateException if the wrong constructor has been called
     */
  public void createFloors(int seed, int mean, int deviation) throws IllegalStateException{
    
    if(checkEqualConstructor){
      //create a placeholder variable for the floor to be re-used in the loops
      Floor tempFloor;
      
      //1. create floors
      for (int i = 0; i<numOfFloors; i++){
      
        //create the floor with the given total capacity (numOfPeoplePerFloor)
        tempFloor = new Floor();
        
        //fill it up 
        tempFloor.fillUp(numOfTenantsPerFloor, seed, mean, deviation); 
        
        //add it to the floorsContainer
        //!!! Important: first floor will be at index 0 and each subsequent floor will
        //be at a position one less than its number
        floorsContainer.add(tempFloor);
      }
    } else {
    
      throw new IllegalStateException("Wrong constructor was called!");
    }

    }
  
   /**
     * This method creates and fills up the floors with the given parameters.
     * In comparison to the other createFloors method, it varies the number
     * of tenants per floor according to a normal distribution.
     * THIS METHOD IS TO BE USED ONLY IF THE SECOND CONSTRUCTOR HAS BEEN CALLED.
     * @param seedOfArrival the seed for the random number generator used in the Gaussian distribution for the arrival
     * times for Person-s
     * @param meanOfArrival the mean for the normal distribution for the arrival for the arrival
     * time for Person-s
     * @param deviationOfArrival the standard deviation for the normal distribution for the arrival times for Person-s
     * @param seedOfFloors the seed used for generating the random distribution of tenants per floor
     * @param meanOfFloors the mean for the normal distribution of tenants per floor
     * @param deviationOfFloors the deviation for the normal distribution of tenants per floor
     * @throws IllegalStateException if the wrong constructor was called
     */
  public void createFloors(int seedOfArrival, int meanOfArrival, int deviationOfArrival, 
                           int seedOfFloors, int meanOfFloors, int deviationOfFloors)
                           throws IllegalStateException{
    
    if(checkNormalDistConstructor){
    
      //initialize a random number generator for the floors
      Random randGen = new Random(seedOfFloors);
    
      //create a double to hold the generated number of tenants per floor
      int generatedNumOfTenants;
      
      //generated people per floor var
      int generatedPeopleForFloor;
      
      //calculate visitors
      int numOfVisitors;
      
      
      //create a placeholder variable for the floor to be re-used in the loops
      Floor tempFloor;
      
      //1. create floors
      for (int i = 0; i<numOfFloors; i++){
      
        //generate a new number of tenants per floor and store it in a variable
        generatedNumOfTenants = (int) Math.abs(meanOfFloors + randGen.nextGaussian() * deviationOfFloors);
        
       // System.out.println("Floor number: " + i + ", generated number of tenants: " + generatedNumOfTenants);
        
        //calculate generated people for floor
        numOfVisitors = (int) (generatedNumOfTenants/3);
        generatedPeopleForFloor = (int) generatedNumOfTenants + numOfVisitors;
        
       // System.out.println("People on this floor according to building calculation: " + generatedPeopleForFloor);
        
        //update the total people instance
        this.totalPeople = totalPeople + generatedPeopleForFloor;
        
        //create the floor
        tempFloor = new Floor();
        
        //fill it up 
        tempFloor.fillUp( (int) generatedNumOfTenants, seedOfArrival, meanOfArrival, deviationOfArrival); 
        
       // System.out.println("Actual floor size: " + tempFloor.getFloorSize());
        
        //add it to the floorsContainer
        //!!! Important: first floor will be at index 0 and each subsequent floor will
        //be at a position one less than its number
        floorsContainer.add(tempFloor);
      }
    }else{
    
      throw new IllegalStateException("Wrong constructor was called!");
    }

    }
    
  /**
   * This is another method used to set up the simulation.
   * It creates the waiting lines.
   */
  public void createWaitingLines(){
      
      //create a temporary variable
      WaitingLine<Person> tempLine;
      
      //add the waiting lines to their container
      for (int i = 0; i<numOfFloors; i++){
        
        //create the waiting line
        tempLine = new WaitingLine<Person>();
        
        //add it to the waiting lines container
        waitingLinesContainer.add(tempLine);
      }
    }
    
    /**
     * This method creates the staircases accordingly and adds them to the stairsContainer.
     */
  public void createStaircases(){
    
     //set up the universal data that is true for each staircase
      Staircase.setUp(capacityOfStaircases, numOfFloors);
 
      //create a temp variable
      Staircase tempStaircase;
    
      for (int i = 0; i < numOfStaircases; i++){
    
      
        //same as before
        tempStaircase = new Staircase();
        
        stairsContainer.add(tempStaircase);
       
      }

    }

  /**
   * This method returns the iterator for accessing the container holding the floors.
   * @return the iterator for access to the floors
   */
  public Iterator getFloorsIterator(){ return floorsContainer.iterator(); }
  
  /**
   * This method returns the iterator for accessing the container holding the staircases.
   * @return the iterator for access to the staircases
   */
  public Iterator getStairsIterator(){ return stairsContainer.iterator(); }
  
    /**
   * This method returns the iterator for accessing the container holding the waiting lines.
   * @return the iterator for access to the waiting lines
   */
  public Iterator getLinesIterator(){ return waitingLinesContainer.iterator(); }
  
  /**
   * Accessor method to get the number of people currently in the building.
   * @return current number of people in the building
   */
  public int getRemaining(){
   // System.out.println("///Evacuated: " + Staircase.getEvacuatedCount());
   // System.out.println("Total people: " + totalPeople);
    return totalPeople - Staircase.getEvacuatedCount();
  }
  
  public static void main(String[] args){
    
    //create a new instance of the test class. Make sure we use the appropriate constructor
    //we only care about the first parameter, 10 number of floors
    Building build = new Building(10, 2, 1);
    
    
    //create the floors
    build.createFloors(242424, 1, 1, 565656, 10, 1);
    
    /*
    //create the same normal distribution by using the same seed
    //create an array list to store the newly generated numbers
    ArrayList<Integer> distrValues = new ArrayList<Integer>(10);
    
    //create a test random generator
    Random testRandGen = new Random(565656);
    
    //store the values generated in the array list
    for(int i = 0; i<10; i++){
    
      distrValues.add((int)Math.abs(10+testRandGen.nextGaussian()*1));
    }
    
    //go through the floors
    
    //create some helper variables
    int index = 0;
    Floor tempFloor;
    
    for (Iterator testFloorsIt = build.getFloorsIterator(); testFloorsIt.hasNext(); index++){
      
      //get the floor
      tempFloor = (Floor) testFloorsIt.next();
      
     System.out.println("Size of currentFloor: " + tempFloor.getFloorSize());
     System.out.println("Independently generated var: " + distrValues.get(distrValues.size()-1-index));
                                                           
    
    
    
    
  }*/
    
}
}
import java.util.*;
import java.io.*;

public class Simulation{
  
    /**
     * This field is the unified time measurement.
     * It is used in the iterations of the outermost loop,
     * thereby creating a universal timeframe for the simulation.
     */
    private int timeStep;
    

    /**
     * This field is the building that holds all the floors, staircases and waiting lines
     */
    Building simBuilding;
    
  

    /**
     * The constructor sets up the background operations of the Simulation class.
     * It creates a simulation based on the passed parameters.
     * @param numOfFloors the number of floors we want the building to have
     * @param capOfStaircases the "capacity" of each staircase, that is, the number of entries between floors
     * @param numOfStaircases the number of staircases in the building
     * @param tenantsPerFloor the number of tenants on each floor
     * @param seed seed for the gaussian random num generator
     * @param meanOfArrival the mean time it takes for people to arrive to the staircase
     * @param deviationOfArrival the deviation for the arrival time
     */
  public Simulation(int numOfFloors, int numOfStaircases, int capOfStaircases, 
                    int tenantsPerFloor, 
                    int seed, int meanOfArrival, int deviationOfArrival){
    //create the building with the given parameters  
    simBuilding = new Building(numOfFloors, numOfStaircases, capOfStaircases, tenantsPerFloor);
    
    simBuilding.createFloors(seed, meanOfArrival, deviationOfArrival);
    simBuilding.createWaitingLines();
    simBuilding.createStaircases();
  }

  
    /**
     * The constructor sets up the background operations of the Simulation class.
     * It creates a simulation based on the passed parameters for varying number of people per floor.
     * @param numOfFloors the number of floors we want the building to have
     * @param capOfStaircases the "capacity" of each staircase, that is, the number of entries between floors
     * @param numOfStaircases the number of staircases in the building
     * @param seedOfArrival the seed for the random number generator used for creating people on the floors
     * @param meanOfArrival the mean for the people's time to arrive
     * @param deviationOfArrival the deviation for the people's time to arrive
     * @param seedOfFloors the seed for distributing the number of people on floors randomly
     * @param meanOfFloors the mean for the number of tenants on each floor
     * @param deviationOfFloors the deviation for the number of tenants on each floor
     * 
     */
  public Simulation(int numOfFloors, int numOfStaircases, int capOfStaircases,
                    int seedOfArrival, int meanOfArrival, int deviationOfArrival,
                    int seedOfFloors, int meanOfFloors, int deviationOfFloors){
    //create the building with the given parameters  
    simBuilding = new Building(numOfFloors, numOfStaircases, capOfStaircases);
    
    simBuilding.createFloors(seedOfArrival, meanOfArrival, deviationOfArrival,
                             seedOfFloors, meanOfFloors, deviationOfArrival);
    simBuilding.createWaitingLines();
    simBuilding.createStaircases();
  }
   
    
  /**
   * Accessor method for the current time.
   * @return current timeStep
   */
  public int getTime(){ return timeStep;}
  
  
  /**
   * This method runs the simulation for whatever is going on on the floors.
   * Specifically, it goes through each floor by using the floors iterator given by the Building
   * and for each floor performs the set of tasks that need to
   * be performed. First, it verifies that the floor is not empty. If it is,
   * this floor is left alone and we go on to the next one.
   * Then, given a non-empty floor, the method checks if somebody has arrived at the current
   * timestep (their arrival time has to match the current timestep, functionality provided
   * by checkArrive() method of Floor). If there is a new arrival, it gets added to the queue for this floor. 
   */
  public void runFloors(){
  
    //create a placeholder floor variable
    Floor currentFloor;
    
    //create a placeHolder variable for the current queue
    WaitingLine<Person> currentFloorQueue;
    
    //create a variable to hold the result of the check if somebody has arrived
    //set it to zero because we want to enter the loop the first time
    int arrivalResult = 0;
    
    //placeholder for arriving person
    Person arrivingPerson;
    
    //iterator for the waiting lines
    Iterator lineIt = simBuilding.getLinesIterator();
    
    int floorsCounter = 0;
    
    for (Iterator floorIt = simBuilding.getFloorsIterator(); floorIt.hasNext(); floorsCounter++){
      
      //get the first, second, etc. floor and store it in the placeholder variable
      currentFloor = (Floor) floorIt.next();
      currentFloorQueue = (WaitingLine) lineIt.next();
      
      //System.out.println("CurrentFloor (" +floorsCounter+") "  + currentFloor);
      
      //check for arrivals before entering the loop
      //if there are none, the loop will not be entered
      arrivalResult = currentFloor.checkArrive(timeStep);
      
      //as long as there is a new arrival at this timestep (some people might arrive simultaneously)
      while(arrivalResult > -1){
 
        //get the arriving person and store it in the temp variable
        arrivingPerson = currentFloor.removePerson(arrivalResult);
        
         //add them to the corresponding queue
         currentFloorQueue.add(arrivingPerson);
    
        //update the arrivalResult to reflect the new situation on the floor
        arrivalResult = currentFloor.checkArrive(timeStep);
        //System.out.println("Arrival result: " + arrivalResult);
        
       
        
      }
      
      //System.out.println(currentFloor);
    }
  
  }
  
  /**
   * This method controls the transfer from the waiting line to the staircase. 
   * First, it accesses every waiting line (for each floor).
   * For each one of them, it checks whether there is somebody in fact waiting.
   * IF there is nobody waiting to go into the staircase, it will proceed to the next floor.
   * Then it goes through all staircases and checks if they have an open spot.
   * The first one that does gets the person.
   * If not a single one does, then the simulation moves on to the waiting line
   * for the next floor. Please, note that
   * to check whether somebody has entered the staircase I use the boolean result
   * of the add method in the Staircase class. As long as that is false, the
   * loop will keep checking other available staircases. It will also stop
   * if all staircases have been checked.
   */
  public void runWaitingLines(){
    
    //create a temp placeholder for the first person in the queue
    Person firstPersonInQueue;
    
    //create a temp variable for the current queue
    WaitingLine currentQueue;
    

    
    //temp variable for staircase
    Staircase tempStaircase;
    
    //get a floor counter to keep track of which floor we are working on, initialzie at0 (first floor)
    int floorCounter = 1;
    
    boolean staircaseEntryResult;
    
    for(Iterator linesIt = simBuilding.getLinesIterator(); linesIt.hasNext(); ){
      
      //get the waiting line for the current floor
      currentQueue = (WaitingLine) linesIt.next();
      
     
    
      //get the first person in that queue WITHOUT REMOVING IT
      firstPersonInQueue = (Person) currentQueue.peek();
      
    // System.out.println("First person in queue: " + firstPersonInQueue);
      
      if (firstPersonInQueue != null){
        
        //go through the staircases as long as person hasn't entered at least one staircase
        for (Iterator stairsIt = simBuilding.getStairsIterator(); stairsIt.hasNext();){
        
          //get the current staircase
          tempStaircase = (Staircase) stairsIt.next();
          
          //insert a person from the staircases (or attempt to)
          staircaseEntryResult = tempStaircase.addFromFloor(floorCounter, firstPersonInQueue);
          
       //System.out.println("Was person admitted to staircase? " + staircaseEntryResult);
          
          //if the person has been added to the staircase successfully, remove it from the queue
          if(staircaseEntryResult){
            currentQueue.poll();
          }
        }
        
       // System.out.println("Waiting line: " + currentQueue);
        
        //update floor counter to advance to next floor
        floorCounter++;
        
      }
    }
  }
  
  /**
   * This method lets the first person at the staircase exit the building.
   * It loops through all staircases and calls the letOut method on all of them.
   */
  public void exitStaircases(){
  
    Staircase tempStaircase;
    
    for (Iterator stairsIt = simBuilding.getStairsIterator(); stairsIt.hasNext();){
      
      //System.out.println("Entered iterator for stairs.");
    
      tempStaircase = (Staircase) stairsIt.next();
      
      //System.out.println("Staircase: " + tempStaircase);
      
      tempStaircase.letOut();
      
    }
  }
  
  /**
   * This method keeps checking if the evacuation is complete by using the building's
   * getRemaining method.
   * @return true if there are people remaining in the building
   * false if there are no people remaining in the building
   */
  public boolean checkEvac(){
    return simBuilding.getRemaining()>0;
  }
  
  /**
   * This method simply moves the timestep one forward.
   */
  public void indentTimeStep(){ timeStep++; }


  /**
   * This method runs the simulation and returns the time taken for the evacuation/
   * @return the time taken for full evacuation of the building
   */
  public int runSim(){
  
    //set the boolean to true in order to be able to enter the loop
    boolean evacBool = true;

    //as long as there are still people in the building, keep evacuating
    while(evacBool){
   //for(int i = 0; i<5; i++){
    // System.out.println("\nTimestep: " + this.getTime());
    
      this.runFloors();
     // System.out.println("RunFloors okay");
      this.runWaitingLines();
    // System.out.println("RunWaitingLines okay");
      this.exitStaircases();
   //  System.out.println("exitStaircase okay");
      
      //System.out.println("Remaining at end of timestep " + this.getTime() + " are " + remainingPeople + " people");

      //recheck if the buidling was evacuated
      evacBool = this.checkEvac();
      
      //indent time step
      this.indentTimeStep();
      
      //System.out.println();
      //System.out.println();
    }
    
    
    return this.getTime();
  }
  
 


  /**
   * This method runs the simulation and returns the time taken for the evacuation/
   * @return the number of people remaining in the building after the time has passed
   */
  public int runSim(int requiredTime){
  
    //set the boolean to true in order to be able to enter the loop
    boolean evacBool = true;

    //as long as there are still people in the building, keep evacuating
   // while(evacBool){
   for(int i = 0; i<requiredTime; i++){
    // System.out.println("\nTimestep: " + this.getTime());
    
      this.runFloors();
     // System.out.println("RunFloors okay");
      this.runWaitingLines();
    // System.out.println("RunWaitingLines okay");
      this.exitStaircases();
   //  System.out.println("exitStaircase okay");
      
      //System.out.println("Remaining at end of timestep " + this.getTime() + " are " + remainingPeople + " people");

      //recheck if the buidling was evacuated
      evacBool = this.checkEvac();
      
      //indent time step
      this.indentTimeStep();
      
      //System.out.println();
      //System.out.println();
    }
    
    
    return simBuilding.getRemaining();
  }
  
  /**
   * Accessor method to the building to perform tests.
   * @return the building used for the simulation
   */
  public Building getBuilding(){return simBuilding;}
 
  
  public static void main(String[] args){
    

    
   
    
     int numOfFloors;
     int capOfStaircases = 15; 
     int seedOfFloors;
     int meanTenantsPerFloor = 50;
     int deviationTenantsPerFloor = 10;

    
  
        
        //get the 6 different seeds for the experiment in an array
        ArrayList<Integer> seedsArr = new ArrayList<Integer>(6);
        seedsArr.add(54543689);
        seedsArr.add(21928374);
        seedsArr.add(1);
        seedsArr.add(591848);
        seedsArr.add(11111111);
        seedsArr.add(6790245);
        
        int seed;
        
        numOfFloors = 50;
        
        System.out.print(numOfFloors);
        int output = 10;
        
        for (int meanNumOfTenants = 10; meanNumOfTenants<100; meanNumOfTenants = meanNumOfTenants + 10){
          for (int i = 0; i<1; i++){
            
            seed = seedsArr.get(i);
            int numOfStaircases;
            
            seedOfFloors = seed + 11223344;
            
            for (numOfStaircases = 100; output != 0; numOfStaircases=numOfStaircases+5){
              //reset the staircases
              Staircase.reset();
              
              //create the simulation instances
              Simulation simFixed = new Simulation(numOfFloors, numOfStaircases, capOfStaircases,
                                                   seed, 30, 10,
                                                   seedOfFloors, meanNumOfTenants, deviationTenantsPerFloor);
              
              output = simFixed.runSim(900);
              
              
              //System.out.println();
            }
            
            System.out.print("\t" + numOfStaircases);
            
          }
          System.out.println();
          //System.out.println("/////////////////////////////");
        }
  
//     }catch (Exception e){
//      System.out.println(e);
//    }
    }
    
    
    
 
    
}
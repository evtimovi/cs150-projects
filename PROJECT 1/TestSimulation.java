import junit.framework.TestCase;
import java.util.*;


/**
 * A JUnit test case class for the functionality of the Simulation class.
 * 
 * @author Ivan Evtimov
 */
public class TestSimulation extends TestCase {
  
  /**
   * A test method to test the runFloors method
   * with an equal number of people on each floor.
   */
  public void testRunFloors() {
    
    //create an instance of the simulation class
    //with 1 floor, 1 staircase with capacity of 2
    //10 tenants (13 people) and a seed of 25252525
    //for now, set the mean to 1 and the variation to 0
    Staircase.reset(); //we're not testing them, reset them
    Simulation testSim = new Simulation(1, 1, 2,
                                        10, 
                                        252525, 1, 0);
    
    //run the floors twice
   //this should get all person objects in the waiting line
   testSim.runFloors();
   testSim.indentTimeStep();
   testSim.runFloors();
    
    //get the simulation building
    Building testBuild = testSim.getBuilding();
    
    //declare a temp floor
    Floor tempFloor;
    
    //get the floors iterator and run through the floors (only 1) to make sure it is empty
    for (Iterator floorsIt = testBuild.getFloorsIterator(); floorsIt.hasNext();){
    
      tempFloor = (Floor) floorsIt.next();
      
      if(tempFloor.getFloorSize() != 0 ){
      
        fail("First floor is not empty when it should be! Time step is: " + testSim.getTime()
            + " Size of floor is: " + tempFloor.getFloorSize());
      }
    }
    
    //get the lines iterator and run throught the waiting lines (only 1) to make sure it is of the right size
    WaitingLine<Person> testLine = new WaitingLine<Person>();
    int currSize;
    
    for (Iterator linesIt = testBuild.getLinesIterator(); linesIt.hasNext();){
    
      testLine = (WaitingLine<Person>) linesIt.next();
      currSize = testLine.size();
      
      //check if the waiting lines are of the expected length
      if (currSize != 13){
      
        fail("Error with length of lines!" + "Length is: " + currSize);
      }
    }
    
    
  }
  
  /**
   * A test method for the functionality of the runFloors clas. This one runs more comprehensive tests 
   * (lets the arrival time for people on the floor vary).
   */
  public void testRunFloors2(){
    //create an instance of the simulation class
    //with 1 floor, 1 staircase with capacity of 2
    //10 tenants (13 people) and a seed of 25252525
    //for now, set the mean to 5 and the variation to 2
    Staircase.reset();//reset the staircases
    Simulation testSim = new Simulation(1, 1, 2,
                                        10, 
                                        252525, 1, 2);
    
    //get the simulation building
    Building testBuild = testSim.getBuilding();
    
    //get the floors iterator
    Iterator testFloorsIt = testBuild.getFloorsIterator();
    
    //get the first floor
    Floor firstFloor = (Floor) testFloorsIt.next();
    
    //run the floor twice for as long as they are not empty
    while(firstFloor.isEmpty()!=true){
    
      testSim.runFloors();
      testSim.indentTimeStep();
    }
    
    
    
    //declare a temp floor
    Floor tempFloor;
    
    //get the floors iterator and run through the floors (only 1) to make sure it is empty
    for (Iterator floorsIt = testBuild.getFloorsIterator(); floorsIt.hasNext();){
    
      tempFloor = (Floor) floorsIt.next();
      
      if(tempFloor.getFloorSize() != 0 ){
      
        fail("First floor is not empty when it should be! Time step is: " + testSim.getTime()
            + " Size of floor is: " + tempFloor.getFloorSize());
      }
    }
    
    //generate the same sequence of arrival times for the people on the floor
    Random testRandGen = new Random(252525);
    
    TreeSet<Double> arrivalVals = new TreeSet<Double>();
    
    for (int i = 0; i<13; i++){
    
      arrivalVals.add(Math.abs(1 + testRandGen.nextGaussian()*2));
    }
    
    int index = 0;//for the loop
    Person testP;
    
    //get the lines iterator and run throught the waiting lines (only 1) to make sure it is of the right size
    WaitingLine<Person> testLine = new WaitingLine<Person>();
    
    Iterator treeIt = arrivalVals.iterator();
    
    double val1;
    double val2;
   
    for (Iterator linesIt = testBuild.getLinesIterator(); linesIt.hasNext(); ){
    
      testLine = (WaitingLine<Person>) linesIt.next();
      
      while(testLine.size()!=0){
      
        testP = testLine.poll();
        
        val1 = (Double) treeIt.next();
        val2 = (double)testP.getTimeToArrive();
        
        if( val1 - val2  > 1){
          fail("Problem with arrival! Arrival value (independently generated) is: " + val1 
                 +" Person's time to arrive is: " + testP.getTimeToArrive() + " at index: " + index);
        }
        
        index++;
      }
    }
  }
  
  /**
   * A test method to check the functionality of the runFloors method for when
   * there is varying number of people among floors.
   */
  public void testRunFloors3(){
    
    //reset the staircases since we are not testing them 
    Staircase.reset();
    
    //create an instance of the test class
    //all people arrive at the same time in this test instance (mean 1, deviation 0)
    //5 floors, 1 staircase with capacity of 1
    //mean of 10 people per floor with deviation of 1
    Simulation testSim = new Simulation(5, 1, 1,
                    252525, 1, 0,
                    646464, 10, 3);
    
  
    
    //estimate what each waiting line should have at the end of the second time step (all people
    //are in the waiting lines)
    Random testRandGen = new Random (646464);
    
    //store the variables in an array, we should keep the order
    ArrayList<Double> linesSizes = new ArrayList<Double>();
    
    for (int i = 0; i<13; i++){
    
      linesSizes.add(Math.abs(10 + testRandGen.nextGaussian()*3));
    }
    
    //run the floors twice
    testSim.runFloors();
    testSim.indentTimeStep();
    testSim.runFloors();
    
    //now get the test building used for this sim
    Building testBuild = testSim.getBuilding();
    
    //create a tempLine
    WaitingLine<Person> tempLine;
    
    //declare an index variable for access to the linesSizes array
    int index = 0;
    
    //declare a variable for the size of the current line
    int currSize;
    
    //declare a variable for the current independently generated value
    double currInd;
    
    //go through the lines
    for(Iterator linesIt = testBuild.getLinesIterator(); linesIt.hasNext(); index++){
    
      
      tempLine = (WaitingLine) linesIt.next();
      currSize = tempLine.size();
      currInd = linesSizes.get(index);
      
      assertTrue("error message", currInd - (double)currSize <1);
      
      
    }
    
    
    
    
  }
  
  /**
   * Test the run of the waiting lines.
   */
  public void testRunWaitingLines(){
    
     //create an instance of the simulation class
    //with 1 floor, 1 staircase with capacity of 2
    //10 tenants (13 people) and a seed of 25252525
    //for now, set the mean to 5 and the variation to 2
    Staircase.reset();//reset the staircases
    
    //set up the simulation so that everybody arrives at the waiting lines
    //at the first run
    Simulation testSim = new Simulation(5, 5, 6,
                                        20, 
                                        252525, 0, 0);
    
    
    //run runFloors once to push everybody in the waiting lines
    //then run runWaitingLines
    testSim.runFloors();
    testSim.runWaitingLines();
 
   
    //now we should have one person at each entry point in each staircase
    //We test for that
    
    //get the test building from the sim
    Building testBuild = testSim.getBuilding();
    
    //create a temporary staircase
    Staircase tempStairs;
    
    //tempPerson
    Person tempPerson;
    
    //counter to bound stairs iterator for last floor (no entries past last floor)
   
    
    int stairsCounter = 0;
    
    for (Iterator stairsIt = testBuild.getStairsIterator(); stairsIt.hasNext(); stairsCounter++){
    
      //get the staircase
      tempStairs = (Staircase) stairsIt.next();
      
      //a NoSuchElementException should occur when we try to access anything after the fifth floor
      //if it does, our test is successful
     
      int floorsCounter = 1;
      //go through its entry points
      for (Iterator testIt = tempStairs.getIterator(); testIt.hasNext(); floorsCounter++){
      
        tempPerson = (Person) testIt.next();
        
        assertTrue("Entry point is empty at floor " + floorsCounter + "!", tempPerson != null);
        
        //only go there if we are not at the last floor
       
        if(floorsCounter < 5){
          //run five more times to skip over empty slots in the staircase
          for (int i = 0; i< 5; i++){
            assertTrue("Empty slots problem!", testIt.next()==null);
         
        }
        }
        
      }
     
    }
 
  }
  
  
  /**
   * Test the run of the waiting lines with varying number of people per floor.
   */
  public void testRunWaitingLines2(){
    
     //create an instance of the simulation class
    //with 1 floor, 1 staircase with capacity of 2
    //10 tenants (13 people) and a seed of 25252525
    //for now, set the mean to 5 and the variation to 2
    Staircase.reset();//reset the staircases
    
    //set up the simulation so that everybody arrives at the waiting lines
    //at the first run
    Simulation testSim = new Simulation(5, 5, 6,
                                        6464654, 0, 0, 
                                        252525, 30, 5);
    
    
    //run runFloors once to push everybody in the waiting lines
    //then run runWaitingLines
    testSim.runFloors();
    testSim.runWaitingLines();
    testSim.indentTimeStep();

 
   
    //now we should have one person at each entry point in each staircase
    //We test for that
    
    //get the test building from the sim
    Building testBuild = testSim.getBuilding();
    
    //create a temporary staircase
    Staircase tempStairs;
    
    //tempPerson
    Person tempPerson;
    
    //counter to bound stairs iterator for last floor (no entries past last floor)
   
    
    int stairsCounter = 0;
    
    for (Iterator stairsIt = testBuild.getStairsIterator(); stairsIt.hasNext(); stairsCounter++){
    
      //get the staircase
      tempStairs = (Staircase) stairsIt.next();
      
      //a NoSuchElementException should occur when we try to access anything after the fifth floor
      //if it does, our test is successful
     
      int floorsCounter = 1;
      //go through its entry points
      for (Iterator testIt = tempStairs.getIterator(); testIt.hasNext(); floorsCounter++){
      
        tempPerson = (Person) testIt.next();
        
        assertTrue("Entry point is empty at floor " + floorsCounter + " of staircase " + stairsCounter,
                   tempPerson != null);
        
        //only go there if we are not at the last floor
       
        if(floorsCounter < 5){
          //run five more times to skip over empty slots in the staircase
          for (int i = 0; i< 5; i++){
            assertTrue("Empty slots problem!", testIt.next()==null);
         
          }
        }
        
      }
     
    }
 
  }
  
  /**
   * Method checks if the exit call works well.
   */
  public void testExit(){
    
    //create an instance of the simulation class
    //with 1 floor, 1 staircase with capacity of 1
    //10 tenants (13 people) and a seed of 25252525
    //for now, set the mean to 0 and the variation to 0
    Staircase.reset();//reset the staircases
    
    //set up the simulation so that everybody arrives at the waiting lines
    //at the first run
    Simulation testSim = new Simulation(1, 1, 1,
                                        10,
                                        252525, 0, 0);
    
    
    //run runFloors once to push everybody in the waiting lines
    //then run runWaitingLines
    testSim.runFloors();
    testSim.runWaitingLines();
    testSim.exitStaircases();
    testSim.indentTimeStep();
    
    //get the test building from the sim
    Building testBuild = testSim.getBuilding();
    
    int remaining = testBuild.getRemaining();
    
    assertTrue("Wrong number of people remaining in building: " + remaining + ", should be " + 12,
               remaining == 12);
  }
  
}

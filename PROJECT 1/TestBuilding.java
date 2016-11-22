import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for testing the functionality of the Building class.
 * @author Ivan Evtimov
 */
public class TestBuilding extends TestCase {
  
  /**
   * A test method to test the creation of the staircases.
   * This also implicitly tests the getStairsIterator method.
   */
  public void testStaircases() {
    
    //reset the staircases to have a "clean slate"
    Staircase.reset();
    
    //create an instance of the tested class
    //all we care about is the second argument - the number of staircases
    Building build = new Building(10, 15, 5, 1);
    
    //create the staircases
    build.createStaircases();
    
    //now when we get the staircases throught the iterator, there should be fifteen of them
    //More importantly, they should be empty and each one of them should be of size 5.
    Iterator stairsIt;
    
    //create a temp array to hold our staircases
    ArrayList<Staircase> stairsArray = new ArrayList<Staircase>();
    
    Staircase tempStaircase;
    
    for (stairsIt = build.getStairsIterator(); stairsIt.hasNext(); ){
    
      tempStaircase = (Staircase) stairsIt.next();
      //add the staircases to the array
      stairsArray.add(tempStaircase);
      
    }
    
    
    //boolean variable to compare the size of the array with the number of staircases
    boolean checkNumber = (stairsArray.size()==15);
    
    //check the staircase's evacuated count. It should be 0
    boolean checkEvacuated = (Staircase.getEvacuatedCount() == 0);
    
    //check the staircase's capacity, it should be 5
    boolean checkCap = (Staircase.getCap() == 5);
  
    
    //finally assert that those three boolean variables are true
    assertTrue("checkNumber = " + checkNumber + "checkEvacuated = " + checkEvacuated +
               "checkCapacity " + checkCap, checkNumber && checkEvacuated && checkCap);
     
    }
  
  /**
   * This test method is designed to test the operations of the method createFloors in the tested class.
   */
  public void testFloors(){
  
     //create an instance of the tested class
    //we are interested in the first argument (number of floors) and the last argument (tenants per floor)
    Building build = new Building(10, 15, 5, 20);
    
    //create and fill up the floors
    build.createFloors(356475, 30, 10);
    
    ////////////////////// 1st test ////////////////////////////
    
    //now we want to get the floors through the iterator
    Iterator floorsIt;
    ArrayList<Floor> floorsArr = new ArrayList<Floor>();
    Floor tempFloor;
    
    for(floorsIt = build.getFloorsIterator(); floorsIt.hasNext(); ){
    
      tempFloor = (Floor) floorsIt.next();
      
      floorsArr.add(tempFloor);
    }
    
    //check size, it should equal 10, the number of floors
    boolean checkSize = (floorsArr.size() == 10);
    
    
    ////////////////////////// 2nd test////////////////////////////////
    
    //check the number of people on each floor, they should be 26 (20 tenants and 6 visitors)
    boolean checkPeople = true;
    for (int i = 0; i<floorsArr.size(); i++){
    
      tempFloor = floorsArr.get(i);
      if(tempFloor.getFloorSize() != 26){
      
        checkPeople = false;
      }
    }
    
    //////////////////////// 3rd test//////////////////////////////////////
    
    //create 10 floors with 15 people independently, but by using the same seed, mean and deviation
    Floor testTempFloor;
    
    ArrayList<Floor> testFloorsArr = new ArrayList<Floor>();
    
    boolean checkSame = true;
    
    for(int i = 0; i<15; i++){
      testTempFloor = new Floor();
      
      testTempFloor.fillUp(15, 356475, 30, 10);
      
      testFloorsArr.add(testTempFloor);
      
    }
    
    int index = 0;
    //go through the building's floors and through the independently created floors and compare them
    for (Iterator newIterator = build.getFloorsIterator(); newIterator.hasNext(); index++){
      
      if(newIterator.next().equals((Object)floorsArr.get(index)) != true){
      
        System.out.println("Something went wrong at: " + index);
        checkSame = false;
      }
      
        
    }
    
    /////////////////assertion ///////////////////////////
    //assert the three booleans are true
    assertTrue("checkSame: " + checkSame + " checkPeople: " + checkPeople + " checkSize: " + checkSize,
               checkSame&&checkPeople&&checkSize);
  }
  
  
  /**
   * This method checks the functionality of the createWaitingLines method.
   */
  public void testWaitingLines(){
  
    //create an instance of the tested class
    //we are interested in the first argument (number of floors) as each floor must have a waiting line
    Building build = new Building(10, 15, 5, 15);
    
    //create a boolean variable to store the result of the test for the size
    boolean testSize = true;
    
    //create a boolean var to store the result of the test for whether the lines are empty
    boolean testEmpty = true;
    
    
    //create the waiting lines
    build.createWaitingLines();
    
    //temp waiting line for the test
    WaitingLine tempLine;
    
    //counter for the number of lines created
    int counter = 0;
    
    for (Iterator linesIt = build.getLinesIterator(); linesIt.hasNext(); ){
    
      tempLine = (WaitingLine) linesIt.next(); 
        
      //if a waiting line is not empty, fail the test
      if (tempLine.size()!=0){
      
        testEmpty = false;
        
        fail("Waiting line number " + counter + " is not empty!");
      }
      counter++;
    }
    
    //if our counter does not equal 15 (the number of floors),
    //then we have a wrong number of waiting lines
    //and we fail the test
    if(counter != 10){
      testSize = false;
      fail("Wrong number of waiting lines! Current number is " + counter + ". It should be " + 10);
    }
    
    //as a double check, assert that both booleans are true
    assertTrue("testSize: " +testSize + " testEmpty: " + testEmpty, testSize&&testEmpty);
    
   }
  
  /**
   * Test method to check the functionality of the createFloors method
   * that uses standard deviation to assign the number of people per floor.
   */
  public void testCreateFloorsStand(){
    
    //create a new instance of the test class. Make sure we use the appropriate constructor
    //we only care about the first parameter, 10 number of floors
    Building build = new Building(10, 2, 1);
    
    
    //create the floors
    build.createFloors(242424, 1, 1, 565656, 2, 5);
    
    //create the same normal distribution by using the same seed
    //create an array list to store the newly generated numbers
    ArrayList<Integer> distrValues = new ArrayList<Integer>(10);
    
    //create a test random generator
    Random testRandGen = new Random(565656);
    
    //store the values generated in the array list
    for(int i = 0; i<10; i++){
    
      distrValues.add((int)Math.abs(2+testRandGen.nextGaussian()*5));
    }
    
    //go through the floors
    
    //create some helper variables
    int index = 0;
    Floor tempFloor;
    
    //var for people on floor 
    int peopleOnFloor;
    
    //var for independently distributed people on floor
    int distrPeople;
     
    for (Iterator testFloorsIt = build.getFloorsIterator(); testFloorsIt.hasNext(); index++){
      
      //get the floor
      tempFloor = (Floor) testFloorsIt.next();
      peopleOnFloor = tempFloor.getFloorSize();
      
      distrPeople = distrValues.get(index) + (int) distrValues.get(index)/3;
      
      //check if its size is correct
      if(peopleOnFloor != distrPeople){
      
        fail ("Test failed at " + index + " floor.");
      }
                                                           
    
    }
    
    
    
  
    
  }
  }
  


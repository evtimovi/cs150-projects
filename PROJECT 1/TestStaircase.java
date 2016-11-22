import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the Staircase method.
 * @author Ivan Evtimov
 */
public class TestStaircase extends TestCase {
  
  /**
   * A test method to test whether the constructor can be called
   * before setUp method is called.
   */
  public void testConstructor() {
    
    //reset the staircase class
    Staircase.reset();
    
    try{
      Staircase j = new Staircase();
      
      fail("Exception went uncaught or was not thrown!");
    } catch (IllegalStateException e){
    
      //the method works
    }
  }
  
  /**
   * A test method to test whether the constructor works after
   * the static setUp method has been called.
   */
  public void testConstructor2(){
    
    //reset the staircase class
    Staircase.reset();
  
    //set up the staircases
    Staircase.setUp(10, 15);
    
    //call the constructor, no exception should be thrown
    try{
      Staircase j = new Staircase();
    } catch (Exception e){
    
      fail("Exception was thrown! Exception message: " + e);
    }
    
  }
  
  /**
   * This method performs a test on the Staircase class's behaviour if
   * a call to the setUp method is attempted more than once.
   */
  public void testSetUp(){
    
    //reset the staircase class
    Staircase.reset();
  
    try{
      Staircase.setUp(5, 13);
      Staircase.setUp(6, 16);//this call should generate an exception
      fail("Exception was not generated!"); //if we get here, test has failed, exception went unthrown
    } catch (UnsupportedOperationException e){
      //if it does, we'll be here and the test will succeed
    }
  }
  
  /**
   * This method tests the functionality of the addFromFloor() method.
   */
  public void testAddFromFloor(){
  
    //reset the staircase
    Staircase.reset();
    
    //set up a staircase with 2 capacity between floors (includes entry point) and access from 5 floors
    Staircase.setUp(2, 5);
    
    //create a staircase
    Staircase stairs = new Staircase();
    
    //create a person to be added at each floor
    Person firstFloorP = new Person(1, 1);
    Person secondFloorP = new Person(2, 1);
    Person thirdFloorP = new Person(3, 1);
    Person fourthFloorP = new Person(4, 1);
    Person fifthFloorP = new Person(5, 1);
    
    //add them to the staircases at appropriate entry points
    stairs.addFromFloor(1, firstFloorP);
    stairs.addFromFloor(2, secondFloorP);
    stairs.addFromFloor(3, thirdFloorP);
    stairs.addFromFloor(4, fourthFloorP);
    stairs.addFromFloor(5, fifthFloorP);
    
    //traverse the internal container of people in the staircase. 
    //It should have one person with a gap between each one of them.
    //Person-s should be at their floor's entry point.
    
    //get the iterator
    Iterator stairsIt = stairs.getIterator();
    
    //get the people in the staircase and store them in variables
    Person firstEntryP = (Person) stairsIt.next();
    Object shouldBeNull1 = stairsIt.next();
    Person secondEntryP = (Person) stairsIt.next();
    Object shouldBeNull2 =  stairsIt.next();
    Person thirdEntryP = (Person) stairsIt.next();
    Object shouldBeNull3 = stairsIt.next();
    Person fourthEntryP = (Person) stairsIt.next();
    Object shouldBeNull4 = stairsIt.next();
    Person fifthEntryP =(Person) stairsIt.next();
   
 
    //test if the array has been exhausted
    boolean testIfExhausted = (stairsIt.hasNext() == false);
    
    //test if the person at the first floor has entered at the first floor and so on
    boolean testFirstEntry = firstEntryP.equals(firstFloorP);
    boolean testSecondEntry = secondEntryP.equals(secondFloorP);
    boolean testThirdEntry = thirdEntryP.equals(thirdFloorP);
    boolean testFourthEntry = fourthEntryP.equals(fourthFloorP);
    boolean testFifthEntry = fifthEntryP.equals(fifthFloorP);
    
    //test the "emptiness" of the slots
    //there should be one empty slot between each floor
    boolean testEmpty = (shouldBeNull1 == null && shouldBeNull2 == null
                           && shouldBeNull3 == null
                         && shouldBeNull4 == null);
    
    
    //assert the entries are all true
    assertTrue("Entry at 1, 2, 3, 4, 5 tests accordingly: " +
               testFirstEntry + testSecondEntry + testThirdEntry +                 
               testFourthEntry + testFifthEntry,
               testFirstEntry && testSecondEntry && testThirdEntry 
                 && testFourthEntry && testFifthEntry);
  
    //assert the empties are empty
    assertTrue("Should be null test failed.", testEmpty);
  
    //assert that the array was truly exhausted
    assertTrue("Array was not exhausted!", testIfExhausted);
  }
  
  /**
   * A test method to test the functionality of the evacuated counter.
   */
  public void testEvacCounter(){
  
    //reset the staircases
    Staircase.reset();
    
    //create a staircase instance with capacity btw floors = 1 and 5 floors
    Staircase.setUp(2, 5);
    
    //create the staircase
    Staircase stairs = new Staircase();
    
    //fill up the staircase
     //create a person to be added at each floor
    Person firstFloorP = new Person(1, 1);
    Person secondFloorP = new Person(2, 1);
    Person thirdFloorP = new Person(3, 1);
    Person fourthFloorP = new Person(4, 1);
    Person fifthFloorP = new Person(5, 1);
    
    //add them to the staircases at appropriate entry points
    stairs.addFromFloor(1, firstFloorP);
    stairs.addFromFloor(2, secondFloorP);
    stairs.addFromFloor(3, thirdFloorP);
    stairs.addFromFloor(4, fourthFloorP);
    stairs.addFromFloor(5, fifthFloorP);
    
    //evacuate the people who entered on first and second floor. This would require 3 calls to letOut()
    stairs.letOut();
    stairs.letOut();
    stairs.letOut();
    
    //get the evacuated count and store it in a variable
    int evacuatedCount = Staircase.getEvacuatedCount();
    
    assertTrue("Wrong number of people evacuated. Evacuated: " + evacuatedCount +
               " They should be: " + 2, evacuatedCount == 2);
  }
  
  /**
   * A test method to test the functionality of the let out method.
   */
  public void testLetOut(){
  
    //reset the staircases
    Staircase.reset();
    
    //create a staircase instance with capacity btw floors = 1 and 5 floors
    Staircase.setUp(2, 5);
    
    //create the staircase
    Staircase stairs = new Staircase();
    
    //fill up the staircase
     //create a person to be added at each floor
    Person firstFloorP = new Person(1, 1);
    Person secondFloorP = new Person(2, 1);
    Person thirdFloorP = new Person(3, 1);
    Person fourthFloorP = new Person(4, 1);
    Person fifthFloorP = new Person(5, 1);
    
    //add them to the staircases at appropriate entry points
    stairs.addFromFloor(1, firstFloorP);
    stairs.addFromFloor(2, secondFloorP);
    stairs.addFromFloor(3, thirdFloorP);
    stairs.addFromFloor(4, fourthFloorP);
    stairs.addFromFloor(5, fifthFloorP);
    
    //evacuate the people who entered on first and second floor. This would require 3 calls to letOut()
    //but we will call it 4 times, so that the person who was originally on third floor
    //should now be up front
    stairs.letOut();
    stairs.letOut();
    stairs.letOut();
    stairs.letOut();
    
    //traverse the list. It shouldn't have anyone on the last two floors (letOut also advances
    //everybody on the staircase one step ahead) and the first person to come out
    //should be the one who was originally on third floor, the second one should be the one from
    //fourth floor and so on
    
    //get the iterator
    Iterator stairsIt = stairs.getIterator();
    
    //get the people in the staircase and store them in variables
    Person firstRemP = (Person) stairsIt.next();
    Object shouldBeNull1 = stairsIt.next();
    Person secondRemP = (Person) stairsIt.next();
    Object shouldBeNull2 =  stairsIt.next();
    Person thirdRemP = (Person) stairsIt.next();
    Object shouldBeNull3 = stairsIt.next();
    Object shouldBeNull4 = stairsIt.next();
    Object shouldBeNull5 = stairsIt.next();
    Object shouldBeNull6 = stairsIt.next();
    
    //test if all the null variables are null
    boolean testEmpty = (shouldBeNull1 == null && shouldBeNull2 == null && shouldBeNull3 == null
                        && shouldBeNull3 == null && shouldBeNull4 == null && shouldBeNull5 == null
                        && shouldBeNull6 == null);
    
    
    //assert those are true
   assertTrue("SPots in the staircase that should be empty are not!", testEmpty);
    
    //asert the staircase was exhausted
   assertTrue("Staircase was not exhausted!", stairsIt.hasNext() == false);
    
    //assert the first of the remaining persons was the one originally on third floor
    assertTrue("First person of the remaining ones not as expected!", firstRemP.equals(thirdFloorP));
    
    //assert the second of the remaining persons was the one originally on third floor
    assertTrue("Second remaining person not as expected!", secondRemP.equals(fourthFloorP));
    
    //assert the third of the remaining persons was the one originally on third floor
    assertTrue("Third remaining person not as expected!", thirdRemP.equals(fifthFloorP));
  }
}

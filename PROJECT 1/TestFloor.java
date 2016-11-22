import junit.framework.TestCase;
import java.util.*;


/**
 * A JUnit test case class for the functionality of floor.
 * @author Ivan Evtimov
 */
public class TestFloor extends TestCase {
  
  /**
   * A test method to check the floor's fill up method.
   */
  public void testFillUp() {
    
   //create an instance of the floor
    Floor testFloor = new Floor();
    
    //fill it up with 10 tenants (13 people total), seed 252525, mean 5 and distribution 2
    testFloor.fillUp(10, 252525, 5, 2);
    
    //generate a bunch of int's with the same seed, mean and variance
    Random testRandGen = new Random(252525);
    
    ArrayList<Integer> valuesCont = new ArrayList<Integer>(13);
    
    for(int i = 0; i<13; i++){
    
      valuesCont.add( (int) Math.abs(5 + testRandGen.nextGaussian()*2));
    }
    
    //now go throught the floor, each person must have the expected time to arrive
    Person tempPerson;
    int tempTime;
    int index = 0;
    
    for (Iterator floorIt = testFloor.getIterator(); floorIt.hasNext(); index++){
    
      //get the person
      tempPerson = (Person) floorIt.next();
      
      //get its time to arrive
      tempTime = tempPerson.getTimeToArrive();
      
      //check if the independently generated time to arrive and this person's time to arrive match
      if(tempTime != valuesCont.get(index)){
      
        fail("Test failed at index " + index);
      }
      
    }
    
  }
  
  /**
   * This method tests the functionality of the isEmpty method.
   */
  public void testIsEmpty(){
    
    //create an instance of the floor
    Floor testFloor = new Floor();
    
    //fill it up with 10 tenants (13 people total), seed 252525, mean 5 and distribution 2
    testFloor.fillUp(10, 252525, 5, 2);
    
    //remove 13 people
    for (int i = 0; i < 13; i++){
      testFloor.removePerson(i);
    }
    
    assertTrue("Problem with isEmpty. Size of floor is: "+ testFloor.getFloorSize(), testFloor.isEmpty()==true);
    
    
  }
  
  /**
   * This method tests the functionality of the removePerson method.
   */
  public void testRemove(){
    
     //create an instance of the floor
    Floor testFloor = new Floor();
    
    //fill it up with 10 tenants (13 people total), seed 252525, mean 5 and distribution 2
    testFloor.fillUp(10, 252525, 5, 2);
  
     //generate a bunch of int's with the same seed, mean and variance
    Random testRandGen = new Random(252525);
    
    ArrayList<Integer> valuesCont = new ArrayList<Integer>(13);
    
    for(int i = 0; i<13; i++){
    
      valuesCont.add( (int) Math.abs(5 + testRandGen.nextGaussian()*2));
    }
    
    //now go throught the floor, each person must have the expected time to arrive
    Person tempPerson;
    
    for (int i = 0; i<13; i++){
    
      tempPerson = testFloor.removePerson(i);
      
      if(tempPerson.getTimeToArrive()!=valuesCont.get(i)){
      
        fail("Test failed at index: " + i);
      }
    }
    
  }
  
  /**
   * This method tests the functionality of the checkArrive method.
   */
  public void testCheckArrive(){
    
     //create an instance of the floor
    Floor testFloor = new Floor();
    
    //fill it up with 10 tenants (13 people total), seed 252525, mean 5 and distribution 2
    testFloor.fillUp(10, 252525, 5, 2);
  
     //generate a bunch of int's with the same seed, mean and variance
    Random testRandGen = new Random(252525);
    
    ArrayList<Integer> valuesCont = new ArrayList<Integer>(13);
    
    for(int i = 0; i<13; i++){
    
      valuesCont.add( (int) Math.abs(5 + testRandGen.nextGaussian()*2));
    }
    
    //now go throught the floor, each person must have the expected time to arrive
    Person tempPerson;
    
    for (int i = 0; i<13; i++){
    
      //get the persons using the check arrive method and the independently generated
      //times to arrive
      tempPerson = testFloor.removePerson(testFloor.checkArrive(valuesCont.get(i)));
      
      //they should match
      if(tempPerson.getTimeToArrive()!=valuesCont.get(i)){
      
        fail("Test failed at index: " + i);
      }
    }
    
  }
  
}

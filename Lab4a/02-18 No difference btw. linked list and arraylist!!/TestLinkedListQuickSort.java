import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the LinkedListQuickSort class.
 * 
 * 
 * @author Ivan Evtimov
 */
public class TestLinkedListQuickSort extends TestCase {
  
  /**
   * A test method to perform tests on the addData method.
   * This method tests if every integer from the original array has been
   * copied to the exact same position in the new container.
   */
  public void testAddData() {
    
    //create an instance of the class to be tested
    LinkedListQuickSort arrayContainer = new LinkedListQuickSort();
    
    //create an LinkedList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
     
    //fill up the LinkedList with some random numbers
    for(int i = 0; i<255; i++){
      originalArray.add(i+76);
    
    }
    
    //add the data from the original array to the container of LinkedListQuickSort
    arrayContainer.addData(originalArray);
    
    for(int k = 0; k<52; k++){
      assertTrue("Data not copied properly!"+" Copying failed at " + k +"-th iteration.\n"+
                 "Original value: " + originalArray.get(k) + "Actual value: " + arrayContainer.getInt(k), 
                 originalArray.get(k) == arrayContainer.getInt(k));
    
    }
    
    
  }
   
  
  /**
   * A test method to perform tests on the sort method.
   * This method tests if the array has been sorted properly after sort has been called.
   */
  public void testSort(){
    
    //create an instance of the class to be tested
    LinkedListQuickSort arrayContainer = new LinkedListQuickSort();
    
    
    //create an LinkedList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
    //add some random numbers in a random order
    originalArray.add(51);
    originalArray.add(2);
    originalArray.add(75);
    originalArray.add(152);
    originalArray.add(15);
    originalArray.add(35);
    
    
    //add the data from the original array to the container of LinkedListQuickSort
    arrayContainer.addData(originalArray);
  
    //sort
    arrayContainer.sort(0, arrayContainer.getSize()-1);
    
     //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("Array is not sorted!", arrayContainer.getInt(i)>arrayContainer.getInt(i-1));
    
    }
    
    
  }
  
  
}

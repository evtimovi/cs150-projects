import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the ArrayListQuickSort class.
 * 
 * Please, note that some tests
 * work with the parent class methods, which are designed
 * to work with parameters and not with fields. Hence,
 * the instances of the class ArrayListQuickSort
 * are used only to verify that the parent class's 
 * methods work properly.
 * 
 * @author Ivan Evtimov
 */
public class TestArrayListQuickSort extends TestCase {
  
 
  /**
   * A test method to perform tests on the copy method of the parent class OrderedList.
   * This method tests if every integer from the original array has been
   * copied to the exact same position in the new container.
   */
  public void testCopy() {
    
    //create an instance of the class to be tested
    ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
    
    //create an ArrayList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
    //create another empty ArrayList to receive the information
    ArrayList<Integer> destinationArray = new ArrayList<Integer>();
    
     
    //fill up the ArrayList with some random numbers
    for(int i = 0; i<255; i++){
      originalArray.add(i+76);
    
    }
    
    //add the data from the original array to the container of ArrayListQuickSort
    arrayContainer.copy(originalArray, destinationArray);
    
    for(int k = 0; k<52; k++){
      assertTrue("Data not copied properly!"+" Copying failed at " + k +"-th iteration.\n"+
                 "Original value: " + originalArray.get(k) + "Actual value: " + destinationArray.get(k), 
                 originalArray.get(k) == destinationArray.get(k));
    
    }
    
    
  }
   
  
  /**
   * A test method to perform tests on the sort method of the parent OrderedList.
   * This method tests if the array has been sorted properly after sort has been called.
   */
  public void testSort(){
    
    //create an instance of the class to be tested
    ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
    
    
    //create an ArrayList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
    //add some random numbers in a random order
    originalArray.add(51);
    originalArray.add(2);
    originalArray.add(75);
    originalArray.add(152);
    originalArray.add(15);
    originalArray.add(35);
    
    
    //create another empty ArrayList to receive the information
    ArrayList<Integer> destinationArray = new ArrayList<Integer>();
    
    //add the data from the original array to the destination container
    arrayContainer.copy(originalArray, destinationArray);
  
    //sort
    arrayContainer.sort(destinationArray, 0, destinationArray.size()-1);
    
     //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("Array is not sorted!", destinationArray.get(i)>destinationArray.get(i-1));
    
    }
  }
    
  /**
   * A test method to perform tests on the sort method of the ArrayListQuickSort class.
   * This method tests the sort method's reaction to an empty container.
   * Please, note that it also implicitly tests the sort() method of the parent
   * class for the exact same issue.
   */
    public void testSortOnEmpty(){
       
      //create an instance of the class to be tested
      ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
      try{
      
        arrayContainer.sort();
        
        //if an exception is not caught, fail the test
        fail("Sorting is attempted on an empty container!");
      }catch(UnsupportedOperationException e){
      
        //if such an exception is caugt, the test succeeds
        //if it is any other kind of exception, the test should fail
      }
      
    }
    
  /**
   * A test method to perform tests on the sort method.
   * This method tests the sort method's reaction to a container containing only one element.
   * Please, note that it also implicitly tests the sort() method of the parent
   * class for the exact same issue.
   */
    
    public void testSortOneElement(){
      
      try{
        //create an instance of the class to be tested
        ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
        
        //create an ArrayList 
        ArrayList<Integer> originalArray = new ArrayList<Integer>();
        
        //add only one element to the array that we are copying from
        originalArray.add(51);
        
        //create another empty ArrayList to receive the information
        ArrayList<Integer> destinationArray = new ArrayList<Integer>();
        
        //add the data to ArrayListQuickSort container
        arrayContainer.copy(originalArray, destinationArray);
        
        //sort
        arrayContainer.sort(destinationArray, 0, destinationArray.size()-1);
      
        //if the sorting continues despite the size of the array,
        fail("Sort attempts to sort array of 1 element!!!");
        
       }catch(UnsupportedOperationException e){
      
        //if such an exception is caugt, the test succeeds
        //if it is any other kind of exception, the test should fail
      }
       
      
    }
    
    
       
    /**
     * This test method verifies that we are indeed working
     * with an instance of ArrayList and not its parent, List.
     * 
     */
    public void testIfLinkedList(){
      ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
      
      
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
      
           
      //assert that even after the sort and copy methods, the container we are working with is a LinkedList
      assertTrue("Methods are not working with LinkedList!!", arrayContainer.getContainer() instanceof ArrayList);
    
    }
    
    /**
     * This test method tests the addData method from the ArrayListQuickSort class.
     * It verifies that the data is both copied and sorted appropriately.
     */
    public void testAddData(){
      
      //create an instance of the class to be tested
      ArrayListQuickSort arrayContainer = new ArrayListQuickSort();
      
      
      //create an ArrayList 
      ArrayList<Integer> originalArray = new ArrayList<Integer>();
      
      //add some random numbers in a random order
      originalArray.add(51);
      originalArray.add(2);
      originalArray.add(75);
      originalArray.add(152);
      originalArray.add(15);
      originalArray.add(35);
      
      //call addData in ArrayListQuickSort
      arrayContainer.addData(originalArray); 

     //after that, the data from originalArray should be copied and sorted
     assertTrue("Add data doesn't work!", arrayContainer.getInt(0) == 2 &&
                 arrayContainer.getInt(1) == 15 &&
                 arrayContainer.getInt(2) == 35 &&
                 arrayContainer.getInt(3) == 51 &&
                 arrayContainer.getInt(4) == 75 &&
                 arrayContainer.getInt(5) == 152);
    }
    
    
  }
  
  


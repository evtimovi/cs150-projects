import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the LinkedListQuickSort class.
 * 
 * Please, note that some tests
 * work with the parent class methods, which are designed
 * to work with parameters and not with fields. Hence, for those methods,
 * the instances of the class LinkedListQuickSort
 * are used only to verify that the parent class's 
 * methods work properly.
 * 
 * @author Ivan Evtimov
 */
public class TestLinkedListQuickSort extends TestCase {
  
  
  /**
   * A test method to perform tests on the copy method of the parent class OrderedList.
   * This method tests if every integer from the original array has been
   * copied to the exact same position in the new container.
   */
  public void testCopy() {
    
    //create an instance of the class to be tested
    LinkedListQuickSort listContainer = new LinkedListQuickSort();
    
    //create an ArrayList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
    //create another empty LinkedList to receive the information
    LinkedList<Integer> destinationList = new LinkedList<Integer>();
    
     
    //fill up the ArrayList with some random numbers
    for(int i = 0; i<255; i++){
      originalArray.add(i+76);
    
    }
    
    //add the data from the original array to the container of LinkedListQuickSort
    listContainer.copy(originalArray, destinationList);
    
    for(int k = 0; k<52; k++){
      assertTrue("Data not copied properly!"+" Copying failed at " + k +"-th iteration.\n"+
                 "Original value: " + originalArray.get(k) + "Actual value: " + destinationList.get(k), 
                 originalArray.get(k) == destinationList.get(k));
    
    }
    
    
  }
   
  
  /**
   * A test method to perform tests on the sort method of the parent OrderedList.
   * This method tests if the linked list has been sorted properly after sort has been called.
   */
  public void testSort(){
    
    //create an instance of the class to be tested
    LinkedListQuickSort listContainer = new LinkedListQuickSort();
    
    
    //create an ArrayList 
    ArrayList<Integer> originalArray = new ArrayList<Integer>();
    
    //add some random numbers in a random order
    originalArray.add(51);
    originalArray.add(2);
    originalArray.add(75);
    originalArray.add(152);
    originalArray.add(15);
    originalArray.add(35);
    
    
    //create an empty LinkedList to receive the information
    LinkedList<Integer> destinationList = new LinkedList<Integer>();
    
    //add the data from the original array to the destination container
    listContainer.copy(originalArray, destinationList);
  
    //sort
    listContainer.sort(destinationList, 0, destinationList.size()-1);
    
     //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("List is not sorted!", destinationList.get(i)>destinationList.get(i-1));
    
    }
  }
    
  /**
   * A test method to perform tests on the sort method of the LinkedListQuickSort class.
   * This method tests the sort method's reaction to an empty container.
   * Please, note that it also implicitly tests the sort() method of the parent
   * class for the exact same issue.
   */
    public void testSortOnEmpty(){
       
      //create an instance of the class to be tested
      LinkedListQuickSort listContainer = new LinkedListQuickSort();
      try{
      
        listContainer.sort();
        
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
        LinkedListQuickSort listContainer = new LinkedListQuickSort();
        
        //create an ArrayList 
        ArrayList<Integer> originalArray = new ArrayList<Integer>();
        
        //add only one element to the array that we are copying from
        originalArray.add(51);
        
        //create an empty LinkedList to receive the information
        LinkedList<Integer> destinationList = new LinkedList<Integer>();
        
        //add the data to LinkedListQuickSort container
        listContainer.copy(originalArray, destinationList);
        
        //sort
        listContainer.sort(destinationList, 0, destinationList.size()-1);
      
        //if the sorting continues despite the size of the list,
        fail("Sort attempts to sort list of 1 element!!!");
        
       }catch(UnsupportedOperationException e){
      
        //if such an exception is caugt, the test succeeds
        //if it is any other kind of exception, the test should fail
      }
       
      
    }
    
    
       
    /**
     * This test method verifies that we are indeed working
     * with an instance of LinkedList and not its parent, List.
     * 
     */
    public void testIfLinkedList(){
      LinkedListQuickSort listContainer = new LinkedListQuickSort();
      
      
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
      listContainer.addData(originalArray);
      
           
      //assert that even after the sort and copy methods, the container we are working with is a LinkedList
      assertTrue("Methods are not working with LinkedList!!", listContainer.getContainer() instanceof LinkedList);
    
    }
    
    /**
     * This test method tests the addData method from the LinkedListQuickSort class.
     * It verifies that the data is both copied and sorted appropriately.
     */
    public void testAddData(){
      
      //create an instance of the class to be tested
      LinkedListQuickSort listContainer = new LinkedListQuickSort();
      
      
      //create an ArrayList 
      ArrayList<Integer> originalArray = new ArrayList<Integer>();
      
      //add some random numbers in a random order
      originalArray.add(51);
      originalArray.add(2);
      originalArray.add(75);
      originalArray.add(152);
      originalArray.add(15);
      originalArray.add(35);
      
      //call addData in LinkedListQuickSort
      listContainer.addData(originalArray); 

     //after that, the data from originalArray should be copied and sorted
     assertTrue("Add data doesn't work!", listContainer.getInt(0) == 2 &&
                 listContainer.getInt(1) == 15 &&
                 listContainer.getInt(2) == 35 &&
                 listContainer.getInt(3) == 51 &&
                 listContainer.getInt(4) == 75 &&
                 listContainer.getInt(5) == 152);
    }
  
}

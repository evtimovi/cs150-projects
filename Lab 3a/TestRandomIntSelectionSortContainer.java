import junit.framework.TestCase;

/**
 * A JUnit test case class for the RandomIntSelectionSortContainer.
 * 
 * Since the RandomIntSelectionSortContainer is inherited from RandomIntContainer
 * and uses its fields, construcotr, get-method, and addFromFront() method,
 * this test class only tests the new methods sort() and search().
 * 
 * All methods print out appropriate error messages to point to where the problem occured.
 */
public class TestRandomIntSelectionSortContainer extends TestCase {
  
  /**
   * A test method to test if the array has been sorted properly.
   * 
   * The method asserts that each number starting at position 1 is greater than its preceding number.
   * 
   * The method uses 6 randomly chosen numbers to perform the test.
   */
  public void testSorting() {
    
    //create an instance of the class to be tested
    RandomIntSelectionSortContainer rissc = new RandomIntSelectionSortContainer(1);
    
    //add integers in random order
    rissc.addFromFront(6);
    rissc.addFromFront(3);
    rissc.addFromFront(1);
    rissc.addFromFront(75);
    rissc.addFromFront(59);
    rissc.sort();
    
    rissc.addFromFront(124);
    
    rissc.sort();
    
    //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("Array is not sorted!", rissc.getNum(i)>rissc.getNum(i-1));
    
    }
  }
  
  //Since the method addFromFront has already been tested in TestRandomIntContainer,
  //there is no need to test it again.
  
  
  
   
}

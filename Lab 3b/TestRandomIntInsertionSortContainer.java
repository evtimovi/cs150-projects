import junit.framework.TestCase;

/**
 * A JUnit test case class for the RandomIntInsertionSortContainer.
 * 
 * Since the RandomIntInsertionSortContainer is inherited from RandomIntContainer
 * and uses its fields, construcotr, get-method, and addFromFront() method,
 * this test class only tests the new methods sort() and search().
 * 
 * All methods print out appropriate error messages to point to where the problem occured.
 * @author Ivan Evtimov
 */
public class TestRandomIntInsertionSortContainer extends TestCase {
  
  /**
   * A test method to test if the array has been sorted properly.
   * 
   * The method asserts that each number starting at position 1 is greater than its preceding number.
   * 
   * The method uses 6 randomly chosen numbers to perform the test.
   */
  public void testSorting() {
    
    //create an instance of the class to be tested
    RandomIntInsertionSortContainer randContainer = new RandomIntInsertionSortContainer(1);
    
    //add integers in random order
    randContainer.addFromFront(6);
    randContainer.addFromFront(3);
    randContainer.addFromFront(1);
    randContainer.sort();
    randContainer.addFromFront(75);
    randContainer.addFromFront(59);
    randContainer.sort();
    randContainer.addFromFront(124);
    
    randContainer.sort();
    
    //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("Array is not sorted!", randContainer.getNum(i)>randContainer.getNum(i-1));
    
    }
  }
  
  //Since the method addFromFront has already been tested in TestRandomIntContainer,
  //there is no need to test it again.
  
   /**
   * A test method to test if the search method works given a sorted array.
   * The method adds the numbers 6, 3, 1, 75, 59, 124.
   *  Since for this test we are focusing on the search itself,
   * we also call the sort() method. Hence,
   * 124 should be at index 5,
   * 59 at index 3,
   * 75 at index 4,
   * 1 at index 0,
   * 3 at index 1,
   * 6 at index 2.
   * 
   * It is asserted that the search method returns those indices.
   * 
   * Then it is asserted that the method returns -1 for missing numbers.
   * 
   * Finally, it is asserted that the method returns only the first occurence of a searched item.
   * 
   * 
   */
  public void testSearch(){
    
    //create an instance of the class that is being tested
    RandomIntInsertionSortContainer randContainer = new RandomIntInsertionSortContainer(1);
  
    //add a couple of random numbers
    randContainer.addFromFront(6);
    randContainer.addFromFront(3);
    randContainer.addFromFront(1);
    randContainer.sort();
    randContainer.addFromFront(75);
    randContainer.addFromFront(59);
    randContainer.addFromFront(124);
    
    //sort the array, since we want to test only searching
    randContainer.sort();
    
    /**
     * Hence,
     * 124 should be at index 5,
     * 59 at index 3,
     * 75 at index 4,
     * 1 at index 0,
     * 3 at index 1,
     * 6 at index 2.
     * 
     * Let's assert that the search method returns those numbers.
     * To make the code more readable, I introduce a boolean variable for each one of them.
     */
    
    boolean test124 = randContainer.search(124)==5;
    boolean test59 = randContainer.search(59)==3;
    boolean test75 = randContainer.search(75)==4;
    boolean test1 = randContainer.search(1)==0;
    boolean test3 = randContainer.search(3)==1;
    boolean test6 = randContainer.search(6)==2;
    
    /**
     * Let's also search for some numbers that are not in the array.
     * All of the returned values should be -1;
     */
    
    boolean testMissing1 = randContainer.search(2)==-1;
    boolean testMissing2 = randContainer.search(55)==-1;
    boolean testMissing3 = randContainer.search(76)==-1;
    
    /**
     * I also add a second occurence of a number.
     */
    randContainer.addFromFront(6);
    randContainer.sort();
    
    /**
     * Now, 6 appears twice in the container: once at 2 and once at 3.
     * Since this is binary search, the first occurence will be the one
     * closer to the middle of the array. Hence, it should be at index 3.
     */
    boolean testFirstOccurence = randContainer.search(6)==3;
    
    /**
     * For the test to be true, all boolean variables have to be true at the same time
     * (logical AND).
     * 
     * To make it more clear what went wrong, I institute three tests:
     * 1) for returning the correct position;
     * 2) for returning -1 if number is missing;
     * 3) for returning only the first occurence.
     */
    
    //1)
    boolean testPosition = test124 && test59 && test75 && test1 && test3 && test6;
    assertTrue("Search method does not return the correct position of the searched item!", 
               testPosition);
    
    //2)
    boolean testMissing = testMissing1 && testMissing2 && testMissing3;
    assertTrue("Search method does not return -1 if number is not in the container!", 
               testMissing);
    
    //3)
    assertTrue("Search method does not return the first occurence of the searched item!", 
               testFirstOccurence);
    
    
  }
  
  /**
   * This method tests out the search method's reaction to an unsorted array.
   * It uses the same methodolyg as the testSearch method. This one, however,
   * never calls sort().
   */
  public void testSearch2(){
    
    //create an instance of the class that is being tested
    RandomIntInsertionSortContainer randContainer = new RandomIntInsertionSortContainer(1);
    
    //add a couple of random numbers
    randContainer.addFromFront(6);
    randContainer.addFromFront(3);
    randContainer.addFromFront(1);
    randContainer.addFromFront(75);
    randContainer.addFromFront(59);
    randContainer.addFromFront(124);
    
      /**
     * Hence, if this unsorted array is properly searched,
     * 124 should be at index 5,
     * 59 at index 3,
     * 75 at index 4,
     * 1 at index 0,
     * 3 at index 1,
     * 6 at index 2.
     * 
     * Let's assert that the search method returns those numbers.
     * To make the code more readable, I introduce a boolean variable for each one of them.
     */
    
    boolean test124 = randContainer.search(124)==5;
    boolean test59 = randContainer.search(59)==3;
    boolean test75 = randContainer.search(75)==4;
    boolean test1 = randContainer.search(1)==0;
    boolean test3 = randContainer.search(3)==1;
    boolean test6 = randContainer.search(6)==2;
    
    /**
     * Let's also search for some numbers that are not in the array.
     * All of the returned values should be -1;
     */
    
    boolean testMissing1 = randContainer.search(2)==-1;
    boolean testMissing2 = randContainer.search(55)==-1;
    boolean testMissing3 = randContainer.search(76)==-1;
    
    /**
     * I also add a second occurence of a number.
     */
    randContainer.addFromFront(6);

    
    /**
     * Now, assuming the method sorts its array,
     * 6 should appear twice in the container: once at 2 and once at 3.
     * Since this is binary search, the first occurence will be the one
     * closer to the middle of the array. Hence, it should be at index 3.
     */
    boolean testFirstOccurence = randContainer.search(6)==3;
    
    /**
     * For the test to be true, all boolean variables have to be true at the same time
     * (logical AND).
     * 
     * To make it more clear what went wrong, I institute three tests:
     * 1) for returning the correct position;
     * 2) for returning -1 if number is missing;
     * 3) for returning only the first occurence.
     */
    
    //1)
    boolean testPosition = test124 && test59 && test75 && test1 && test3 && test6;
    assertTrue("Search method does not return the correct position of the searched item!", 
               testPosition);
    
    //2)
    boolean testMissing = testMissing1 && testMissing2 && testMissing3;
    assertTrue("Search method does not return -1 if number is not in the container!", 
               testMissing);
    
    //3)
    assertTrue("Search method does not return the first occurence of the searched item!", 
               testFirstOccurence);
    
  }
   
}

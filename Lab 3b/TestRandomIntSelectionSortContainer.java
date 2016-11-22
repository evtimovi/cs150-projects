import junit.framework.TestCase;

/**
 * A JUnit test case class for the RandomIntSelectionSortContainer.
 * 
 * Since the RandomIntSelectionSortContainer is inherited from RandomIntContainer
 * and uses its fields, construcotr, get-method, and addFromFront() method,
 * this test class only tests the new methods sort() and search().
 * 
 * All methods print out appropriate error messages to point to where the problem occured.
 * @author Ivan Evtimov
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
    rissc.addFromFront(124);
    
    rissc.sort();
    
    //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<6; i++){
      assertTrue("Array is not sorted!", rissc.getNum(i)>rissc.getNum(i-1));
    
    }
  }
  
  //Since the method addFromFront has already been tested in TestRandomIntContainer,
  //there is no need to test it again.
  
   /**
   * A test method to test if the search method works.
   * The method adds the numbers 6, 3, 1, 75, 59, 124;
   *  Since the method always adds the numbers up front,
     * 124 should be at index 0,
     * 59 at index 1,
     * 75 at index 2,
     * 1 at index 3,
     * 3 at index 4,
     * 6 at index 5.
     * 
     * It is asserted that the search method returns those indices.
     * 
     * Then it is asserted that the method returns -1 for missing numbers.
     * 
     * Finally, it is asserted that the method returns only the first occurence of a searched item.
     * 
     * Please, note that due to the nature of the linearSearch algorithm,
     * the numbers need not be sorted before we run the search. Hence, the test does not
     * assume the numbers are sorted and does not call the sort() method from the tested class.
   */
  public void testSearch(){
    
    //create an instance of the class that is being tested
    RandomIntSelectionSortContainer randContainer = new RandomIntSelectionSortContainer(1);
  
    //add a couple of random numbers
    randContainer.addFromFront(6);
    randContainer.addFromFront(3);
    randContainer.addFromFront(1);
    randContainer.addFromFront(75);
    randContainer.addFromFront(59);
    randContainer.addFromFront(124);
    
    /**
     * Since the method always adds the numbers up front,
     * 124 should be at index 0,
     * 59 at index 1,
     * 75 at index 2,
     * 1 at index 3,
     * 3 at index 4,
     * 6 at index 5.
     * 
     * Let's assert that the search method returns those numbers.
     * To make the code more readable, I introduce a boolean variable for each one of them.
     */
    
    boolean test124 = randContainer.search(124)==0;
    boolean test59 = randContainer.search(59)==1;
    boolean test75 = randContainer.search(75)==2;
    boolean test1 = randContainer.search(1)==3;
    boolean test3 = randContainer.search(3)==4;
    boolean test6 = randContainer.search(6)==5;
    
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
     * Now, 6 appears twice in the container: once at 0 and once at 6.
     * The search should return only the first occurence, 0.
     */
    boolean testFirstOccurence = randContainer.search(6)==0;
    
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
   * A test method to test if the search and sort methods both function properly together.
   * The method adds the numbers 6, 3, 1, 75, 59, 124;
   *  Since the sort() method is called,
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
   */
  public void testSearchAndSort(){
    
    //create an instance of the class that is being tested
    RandomIntSelectionSortContainer randContainer = new RandomIntSelectionSortContainer(1);
  
    //add a couple of random numbers
    randContainer.addFromFront(6);
    randContainer.addFromFront(3);
    randContainer.addFromFront(1);
    randContainer.addFromFront(75);
    randContainer.addFromFront(59);
    randContainer.addFromFront(124);
    
    randContainer.sort();
    /**
     * Since the sort() method is called,
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
     * The search should return only the first occurence, 2.
     */
    boolean testFirstOccurence = randContainer.search(6)==2;
    
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

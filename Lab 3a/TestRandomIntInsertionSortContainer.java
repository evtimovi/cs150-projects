import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestRandomIntInsertionSortContainer extends TestCase {
  
    /**
   * A test method
   * to test if the array has been sorted properly.
   */
  public void testSorting() {
    
    //create an instance of the class to be tested
    RandomIntInsertionSortContainer riisc = new RandomIntInsertionSortContainer(1);
    
    
    //add integers in random order
    riisc.addFromFront(69);
    riisc.addFromFront(30);
    riisc.addFromFront(79);
    riisc.addFromFront(2);
    riisc.addFromFront(50);
   // riisc.addToFront();
    
    riisc.sort();
    
    //if any pair of numbers is not in the correct order, fail the test
    for(int i = 1; i<5; i++){
      assertTrue("Array is not sorted!", riisc.getNum(i)>riisc.getNum(i-1));
    
    }
  }
  //Since the method addFromFront has already been tested in TestRandomIntContainer,
  //there is no need to test it again.
  
  
  public void testSwap(){
    //create an instance of the class to be tested
    RandomIntInsertionSortContainer riisc = new RandomIntInsertionSortContainer(1);
    
    
    //add some random integers
    //since the method sorts automatically, 60 should be at position 1 and
    //3 should be at position 0
    riisc.addFromFront(60);
    riisc.addFromFront(3);
    riisc.addFromFront(79);
    riisc.addFromFront(30);
    
    //call the swap method
    //now 60 should be at position 0 and 
    //3 should be at position 1
    riisc.swap(0,1);
    
    riisc.swap(3,2);
    
    //fail the test if that's not true
    assertTrue("Swap method doesn't swap properly!", riisc.getNum(0)==79 &&
                                                     riisc.getNum(1)==30
                                                     &&riisc.getNum(2)==60
                                                     && riisc.getNum(3)==3);
    
    
  
  }
  
}

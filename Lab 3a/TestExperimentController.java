import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestExperimentController extends TestCase {
  
  /**
   * A test method to test whether the method timeAddFromFront() 
   * returns a reasonable (non-zero) time given 
   * a big number of items to be inserted.
   * 
   */
  public void testTimeAddFromFront() {
    //create an instance of the class to be tested
    ExperimentController expContr = new ExperimentController();
    
     //record the time returned from the method being tested
    //seed is chosen randomly
    long timeReturned = expContr.timeAddFromFront(100000, 54545454);
    
    //method should definitely not return a zero value
    assertTrue("Method is not returning a reasonable time value!", timeReturned!=0);
    
  }
  
    /**
   * A test method to test whether the method timeAddFromFrontSelectionSort
   * returns a reasonable (greater than 1000) time given 
   * a big number of items (1000) to be inserted.
   * 
   */
  public void testTimeAddToFrontSelectionSort() {
    //create an instance of the class to be tested
    ExperimentController expContr = new ExperimentController();
    
     //record the time returned from the method being tested
    //seed is chosen randomly
    long timeReturned = expContr.timeAddToFrontSelectionSort(1000, 54545454);
   
    //It should take the method much more than 10 milliseconds to perform all of its commands.
    assertTrue("Method is not returning a reasonable time value!", timeReturned>10);
    
  }
  
    /**
   * A test method to test whether the method timeAddFromFrontInsertionSort
   * returns a reasonable (greater than 100) time given 
   * a big number of items (1000) to be inserted.
   * 
   */
  public void testTimeAddToFrontInsertionSort() {
    //create an instance of the class to be tested
    ExperimentController expContr = new ExperimentController();
    
    //record the time returned from the method being tested
    //seed is chosen randomly
    long timeReturned = expContr.timeAddToFrontInsertionSort(1000, 54545454);
    
    //It should take the method much more than 10 milliseconds to perform all of its commands.
    assertTrue("Method is not returning a reasonable time value!", timeReturned>10);
    
  }
}

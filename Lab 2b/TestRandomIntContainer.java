import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestRandomIntContainer extends TestCase {
  
  /**
   * A test method to test out if 
   * the number we add really gets added to the front of the array.
   */
  public void testAddFromFront() {
    
    RandomIntContainer randContainer = new RandomIntContainer(5);
    
    //add two numbers to the container
    //3 should be at position 1 
    //and 4 should be at position 0
    randContainer.addFromFront(3);
    randContainer.addFromFront(4);

    assertTrue("addFromFront method doesn't add the numbers at the start of the ArraList!",
               randContainer.getNum(0)==4 && randContainer.getNum(1)==3);
  }
  
  /**
   * A test method to test out if 
   * the array is dynamically expanding (or, if the container can "overflow."
   */
  public void testOverflow() {
    
    RandomIntContainer randContainer = new RandomIntContainer(1);
    
    try{
      
      randContainer.addFromFront(1);
      randContainer.addFromFront(1);
      randContainer.addFromFront(1);
      randContainer.addFromFront(1);
      
    } catch (IndexOutOfBoundsException e){
      fail("Array is not dynamically expanding!!");
    }
    
  }
  
}

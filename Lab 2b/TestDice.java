import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestDice extends TestCase {
  
  /**
   * A test method to confirm that the Dice roll() method
   * doesn't generate any numbers outside of the desired bounds.
   */
  public void testRoll11(){
    //create an instance of the Dice class with a randomly chosen seed.
    Dice d = new Dice(29);
    
    //test if it returns something greater than 6 or less than 1
    assertTrue("Number should be between 1 and 6!!!", d.roll()<6||d.roll()>0);
  }
  
  
  
  /**
   * A test method to confirm that the Dice roll() method
   * returns an int and not a double or something else.
   */
  public void testRoll12(){
    //create an instance of the Dice class with a randomly chosen seed.
    Dice d = new Dice(29);
   
    //create a new object and assign the value of the roll result to it
    Object rollResult = new Object();    
    rollResult = d.roll();
    
    //if that object doesn't turn out to be an instance of the Integer class,
    //fail the test
    if(rollResult instanceof Integer == false){
       fail("Output not an int!");
    }
  }
}

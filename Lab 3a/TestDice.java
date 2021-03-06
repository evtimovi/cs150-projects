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
    for(int i = 0; i<25; i++)
      assertTrue("Number should be between 1 and 6!!!", d.roll()<7 && d.roll()>0);
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
  
  /**
   * A test method to confirm that the Dice roll() method
   * generates "random" numbers.
   * The test assumes that the numbers are random if
   * no 2 numbers that are the same are generated one after the other.
   */
  
  public void testRollRandomness(){
    
    //create an instance of the Dice class with a randomly chosen seed
    Dice testDice = new Dice(29293645);
    
    int[] resultsArray = new int[51];
    
    //generate 6 numbers and store them in an array
    for(int i = 0; i<50; i++){
      
      resultsArray[i] = testDice.roll();
      
    
    }
    
    //go through the array and compare each pair of numbers
    //if 3 identical consecutive found, fail the test
    for (int i = 0; i<resultsArray.length-2; i++){
    
    
      assertTrue("Numbers generated by Dice() not random! "+"Problem occured at " 
                   +i+"-th iteration of the test loop", 
                   (resultsArray[i]!=resultsArray[i+1] 
                   || resultsArray[i+1]!=resultsArray[i+2]
                   || resultsArray[i]!=resultsArray[i+2]));
      
      /*Please, note that the condition:
       * (resultsArray[i]!=resultsArray[i+1] 
                   || resultsArray[i+1]!=resultsArray[i+2]
                   || resultsArray[i]!=resultsArray[i+2])
       * will only evaluate to FALSE if all three consecutive numbers are the same.
       */
       
    
    }
  
  }
}

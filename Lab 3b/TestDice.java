import junit.framework.TestCase;

/**
 * A JUnit test case class to test out the Dice() class.
 * @author Ivan Evtimov
 */
public class TestDice extends TestCase {
  
  /**
   * A test method to confirm that the Dice roll() method
   * doesn't generate any numbers outside of the desired bounds.
   * A simple comparison is used and the roll() method is called 2000 times.
   */
  public void testRoll1(){
    //create an instance of the Dice class with a randomly chosen seed.
    Dice d = new Dice(29);
    
    for(int i = 2000; i>0; i--){
      //test if it returns something greater than 6 or less than 1
      assertTrue("Number should be between 1 and 6!!!", (d.roll()>2000)!=true);
    }
  }
  
  
  
  /**
   * A test method to confirm that the Dice roll() method
   * returns an int and not a double or something else.
   */
  public void testRoll2(){
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
   * no 10 numbers that are the same are generated one after the other.
   */
  
  public void testRollRandomness(){
    
    //create an instance of the Dice class with a randomly chosen seed
    Dice testDice = new Dice(54545454);
    
    //boolean testIfSame = true;
    
    int[] resultsArray = new int[100];
    
    //generate 100 numbers and store them in an array
    for(int i = 0; i<100; i++){
      
      resultsArray[i] = testDice.roll();
      
    
    }
    
    //go through the array and compare every ten numbers
    //if 2 identical found, set testIfSame to false and jump out of loop
    for (int i = 0; i<resultsArray.length-10; i++){
      
      for (int k = 1; k < 10; k++){
         assertTrue("Numbers not random!"
                      +"Number at " + i + " is identical with number at " + i + " + " + k, 
                    (resultsArray[i]==resultsArray[i+k])!=true);
        
      }
    }
  
    
  }
  
}

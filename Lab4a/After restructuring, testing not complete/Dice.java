import java.util.*;

/**
 * A class to generate random numbers using the Random() class.
 * For this lab, the Dice() generates random numbers between 0 and 2000 including.
 * @author Ivan Evtimov
 */

public class Dice{
  //constants
  
  /**
   * Ths instance variable is used to record the Random()'s seed.
   */
  private int seed;

  /** 
   * This is the random number generator that does the work.
   */
  Random rand;
  
  /**
   * Constructor.
   * The constructor for the dice method takes in an int seed as a parameter and 
   * creates a Random() number generator with that seed.
   * @param seed the seed to be used for the Random number generator.
   */
  public Dice(int seed){
  
    //set the field seed to the value of the parameter seed
    this.seed = seed;
    
    
    //create the random number generator
    rand = new Random((long) seed);
  
  }
  
  
  
  //methods
  
  /**
   * 
   * This method generates the random number between 0 and 2000
   * by calling the nextInt method of the Random() class.
   * @return the number generated
   */
  public int roll(){
    //create a variable to keep track of the result of the "roll"
    int result = rand.nextInt(2001);
    
    return result;
  
  }
  
  
  
  //main method to test out the class
  public static void main(String[] args){
    Dice d = new Dice (54545454);
    
    for(int i = 0; i<100; i++){
    
      System.out.println(d.roll());
    }
  }

}
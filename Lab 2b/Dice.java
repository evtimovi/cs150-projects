import java.util.*;

public class Dice{
  //constants
  
  //fields
  private int seed;
  Random rand;
  
  //constructor
  public Dice(int seed){
  
    //set the field seed to the value of the parameter seed
    this.seed = seed;
    
    
    //create the random number generator
    rand = new Random((long) seed);
  
  }
  
  
  
  //methods
  
  //method to generate a random number between 1 and 6
  //@return the number generated
  public int roll(){
    //create a variable to keep track of the result of the "roll"
    int result = rand.nextInt(7);
    
    //if the result is a 0, which obviously cannot be the result of a dice roll,
    //generate another random number and keep doing that 
    //until a number that is not zero is generated
    while(result == 0){
      result = rand.nextInt(7);
    }
    
    return result;
  
  }
  
  
  
  //main method to test out the class
  public static void main(String[] args){
    Dice d = new Dice (545450);
    
    for(int i = 0; i<10; i++){
    
      System.out.println(d.roll());
    }
  }

}
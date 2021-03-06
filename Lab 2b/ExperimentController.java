import java.util.*;

public class ExperimentController{
  //constants
  
  //fields
  
  //constructor
    
  //methods
  
  //method to perform a set of commands and return the time taken to 
  //generate and add a series of random numbers between 1 and 6 to a container
  //@return time to perform commands in milliseconds
  public long timeAddFromFront(int numberOfItems, int seed){
    
        
    //create a RandomIntContainer with the specified number of items
    RandomIntContainer container = new RandomIntContainer(numberOfItems);
    
    //create a Dice with the specified seed
    Dice dice = new Dice(seed);
    
    //record the start time of the loop
    long startTime = System.currentTimeMillis();
    
    //"roll the dice" the specified number of times 
    //and record the result in the container each time
    for (int i = 0; i<numberOfItems; i++){
    
      //upon each roll, record the result in the container
      container.addFromFront(dice.roll());
    
    }
    
    //record the end time of the loop
    long endTime = System.currentTimeMillis();
  
  
    return endTime - startTime;
  }
  
  //main method to perform the experiment
  public static void main(String[] args){
    ExperimentController ec = new ExperimentController();
    
    for (int i = 0;
         i<20;
         i++){
           System.out.println(ec.timeAddFromFront((int)Math.pow(2,i), 545454));
         }
  }

}
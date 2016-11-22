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

   //method to perform a set of commands and return the time taken to 
   //generate and add a series of random numbers between 1 and 6 to a container
   //and then sort them using SelectionSort
   //@return time to perform commands in milliseconds
  public long timeAddToFrontSelectionSort(int numberOfItems, int seed){
    
       
    RandomIntSelectionSortContainer randContainer = new RandomIntSelectionSortContainer(numberOfItems);
    
    //create a Dice with the specified seed
    Dice dice = new Dice(seed);
    
    //record the start time of the loop
    long startTime = System.currentTimeMillis();
    
    //"roll the dice" the specified number of times 
    //and record the result in the container each time
    for (int i = 0; i<numberOfItems; i++){
    
      //upon each roll, record the result in the container...
      randContainer.addFromFront(dice.roll());
      //... and sort the stored values
      randContainer.sort();
    
    }
    
    //record the end time of the loop
    long endTime = System.currentTimeMillis();
  
  
    return endTime - startTime;
  }
  

   //method to perform a set of commands and return the time taken to 
  //generate and add a series of random numbers between 1 and 6 to a container
  //and then sort them using insertion sort
   //@return time to perform commands in milliseconds
  public long timeAddToFrontInsertionSort(int numberOfItems, int seed){
    
       
    RandomIntInsertionSortContainer randContainer = new RandomIntInsertionSortContainer(numberOfItems);
    
    //create a Dice with the specified seed
    Dice dice = new Dice(seed);
    
    //record the start time of the loop
    long startTime = System.currentTimeMillis();
    
    //"roll the dice" the specified number of times 
    //and record the result in the container each time
    for (int i = 0; i<numberOfItems; i++){
    
      //upon each roll, record the result in the container...
      randContainer.addFromFront(dice.roll());
      //... and sort the stored values
      randContainer.sort();
    
    }
    
    //record the end time of then  loop
    long endTime = System.currentTimeMillis();
  
  
    return endTime - startTime;
  }
  
  
  //main method to perform the experiment
  public static void main(String[] args){
    
    //////////////////set up experiments//////////////////////
    ExperimentController ec = new ExperimentController();
 
    int seed = Integer.parseInt(args[0]);
    
    
    ////////////////start of experiments//////////////////////////
    //print out the seed that we are using
    System.out.println("The seed is:" + args[0]);

    //////Experiment 1: Multiples of 1000//////////
    //print out a message saying what experiment we're running
    System.out.println("Selection sort with multiples of 1,000:");  
    
    //Selection sort experiment with multiples of 1000
    for (int i = 1;
         i<10;
         i++){
           System.out.println(ec.timeAddToFrontSelectionSort(i*1000, seed));
         }
         
    
    //print out a message saying what experiment we're running
    System.out.println("Insertion sort with multiples of 1,000:");
    
    //Insertion sort experiment with multiples of 1000
    for (int i = 1;
         i<10;
         i++){
           System.out.println(ec.timeAddToFrontInsertionSort(i*1000, seed));
         }
   
    //////Experiment 2: Multiples of 10,000//////////
    //print out a message saying what experiment we're running
    System.out.println("Selection sort with multiples of 10,000:"); 

    //Selection sort experiment with multiples of 10000
    for (int i = 1;
         i<10;
         i++){
           System.out.println(ec.timeAddToFrontSelectionSort(i*10000, seed));
         }
         
    //print out a message saying what experiment we're running
    System.out.println("Insertion sort with multiples of 10,000:"); 

    //Insertion sort experiment with multiples of 10000
    for (int i = 1;
         i<10;
         i++){
           System.out.println(ec.timeAddToFrontInsertionSort(i*10000, seed));
         }

    
    //////Experiment 3: Multiples of 100,000//////////
    //print out a message saying what experiment we're running
    System.out.println("Selection sort with multiples of 100,000:"); 

    //Selection sort experiment with multiples of 100,000
    for (int i = 1;
         i<11;
         i++){
           System.out.println(ec.timeAddToFrontSelectionSort(i*100000, seed));
         }

    //print out a message saying what experiment we're running
    System.out.println("Insertion sort with multiples of 100,000:"); 

    //Insertion sort experiment with multiples of 100,000
    for (int i = 1;
         i<11;
         i++){
           System.out.println(ec.timeAddToFrontInsertionSort(i*100000, seed));
         }
         
         
  //////////////////end of experiments and end of main method//////////////////
                       
 }
}
               

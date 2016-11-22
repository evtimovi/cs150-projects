import java.util.*;


/**
 * This class sets up and runs the experiment on quicksort 
 * using two different containers LinkedList and ArrayList.
 * The functionalities for this experiment have been
 * provided by:
 * 1) the Dice class from lab 3b;
 * 2) the LinkedListQuickSort and the ArrayListQuickSort classes from lab 4a.
 * 
 * Please, note that I am simply reusing the Dice class, which has already been unit-tested.
 * Therefore, I am not unit-testing it again.
 * 
 * @author Ivan Evtimov
 */
public class ExperimentController{
  
  /**
   * This method sets up an ArrayList that will be the source of the data for 
   * the containers ArrayListQuickSort and LinkedListQuickSort.
   * @param seed the seed of the random number generator (the Dice)
   * @param numOfItems how many items we want to fill up the ArrayList with
   * @return an ArrayList of random Integer-s  with the specified length
   */
  public ArrayList<Integer> setUpOriginalArray(int seed, int numOfItems){
    
    //create a Dice that will generate random numbers
    Dice randomGenerator = new Dice(seed);
    
    //create an ArrayList to copy the information from
    ArrayList<Integer> originalArray = new ArrayList<Integer>(numOfItems);
    
    //fill up the ArrayList with random numbers provided by the dice
    for (int i = 0; i<numOfItems; i++){
      originalArray.add(randomGenerator.roll());
    
    }
    
    
    return originalArray;
  }
  
  
  /**
   * This method performs the experiment on ArrayListQuickSort.
   * It uses the seed 54545454 for the random number generator, 
   * so that setUpOriginalArray returns the exact same array
   * whether it is called in this method or in
   * timeLinkedListSort.
   * 
   * The number of items to perform the experiment for
   * can be varied, so that we can evaluate the complexity of addData.
   * 
   * @param numOfItems - how many items to copy and sort in ArrayListQuickSort
   * @return the time for completion of this experiment with the specified numOfItems
   */
  public long timeArrayListSort(int numOfItems){
    //create an instance of ArrayListQuickSort
    OrderedList arrayExperiment = new ArrayListQuickSort();
    
    //get the source array that the setUp method would provide
    ArrayList<Integer> sourceArray = setUpOriginalArray(54545454, numOfItems);
    
    //time the beginning of the experiment
    long startTime = System.currentTimeMillis();
    
    //call addData
    arrayExperiment.addData(sourceArray);
    
    //time the ending of the experimetn
    long endTime = System.currentTimeMillis();
    
    return endTime - startTime;
  
  }
  
  
    /**
   * This method performs the experiment on LinkedListQuickSort.
   * It uses the seed 54545454 for the random number generator, 
   * so that setUpOriginalArray returns the exact same array
   * whether it is called in this method or in
   * timeArrayListSort.
   * 
   * The number of items to perform the experiment for
   * can be varied, so that we can evaluate the complexity of addData.
   * 
   * @param numOfItems - how many items to copy and sort in LinkedListQuickSort
   * @return the time for completion of this experiment with the specified numOfItems
   */
  public long timeLinkedListSort(int numOfItems){
    //create an instance of LinkedListQuickSort
    OrderedList listExperiment = new LinkedListQuickSort();
    
    //get the source array that the setUp method would provide
    ArrayList<Integer> sourceArray = setUpOriginalArray(54545454, numOfItems);
    
    //time the beginning of the experiment
    long startTime = System.currentTimeMillis();
    
    //call addData
    listExperiment.addData(sourceArray);
    
    //time the ending of the experimetn
    long endTime = System.currentTimeMillis();
    
    return endTime - startTime;
  
  }
  
  
  public static void main(String[] args){
  
    ExperimentController ec = new ExperimentController();
    
    //////expreiments with multiples of 1000//////////
    for (int i = 1; i<10; i++){ 
    
       int currentNumOfItems = i*1000;
       long timeArrayList = ec.timeArrayListSort(currentNumOfItems);
       long timeLinkedList = ec.timeLinkedListSort(currentNumOfItems);
       
       System.out.println("numOfItems = " + currentNumOfItems);
       System.out.println("Time for completion of addData in ArrayListQuickSort: " + timeArrayList);
       System.out.println("Time for completion of addData in LinkedListQuickSort: " + timeLinkedList);
    }
    
    
    
    //////expreiments with multiples of 10,000//////////
    for (int i = 1; i<10; i++){ 
    
       int currentNumOfItems = i*10000;
       long timeArrayList = ec.timeArrayListSort(currentNumOfItems);
       long timeLinkedList = ec.timeLinkedListSort(currentNumOfItems);
       
       System.out.println("numOfItems = " + currentNumOfItems);
       System.out.println("Time for completion of addData in ArrayListQuickSort: " + timeArrayList);
       System.out.println("Time for completion of addData in LinkedListQuickSort: " + timeLinkedList);
    }
    
    //////expreiments with multiples of 100,000//////////
    for (int i = 1; i<10; i++){ 
    
       int currentNumOfItems = i*100000;
       long timeArrayList = ec.timeArrayListSort(currentNumOfItems);
       long timeLinkedList = ec.timeLinkedListSort(currentNumOfItems);
       
       System.out.println("numOfItems = " + currentNumOfItems);
       System.out.println("Time for completion of addData in ArrayListQuickSort: " + timeArrayList);
       System.out.println("Time for completion of addData in LinkedListQuickSort: " + timeLinkedList);
    }
    
  
    
    
    
  }

}
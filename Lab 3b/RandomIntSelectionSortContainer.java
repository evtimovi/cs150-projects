import java.util.*;

/**
 * A class that stores the values of randomly generated integers.
 * This class is a child class of RandomIntContainer and therefore uses its fields and its constructor.
 * Additional features that are not available in the parent class are the sort method that uses
 * selection sort to perform a sort of the container and the search method
 * that uses linear search to find a number in the container.
 * @author Ivan Evtimov
 */

public class RandomIntSelectionSortContainer extends RandomIntContainer{
  
  /** 
   * Fields
   * This class uses the fields of its parent class.
   */
  
   
  /**
   * Constructor for the RandomIntSelectionSortContainer class 
   * simply calls the constructor of the parent class. 
   */
  public RandomIntSelectionSortContainer (int sizeOfArray){
   
    super(sizeOfArray);
  
  }

  /**
   * Method to perform the selection sorting on the intArray from the parent class.
   * 
   * The method takes no parameters and returns nothing.
   * It directly modifies the instance of the intArray field.
   * The sorting algorithm is executed in two loops.
   * The first one goes through the whole array in descending order, thereby
   * limiting the sorting each time a new maximum number is found and put at the end of the array.
   * The second lopp goes through the portion of the intArray that has not been sorted 
   * (as indicated by the first loop) and looks for the maximum number in the unsorted portion.
   * Once found, the maximum is placed at the end of the unsorted portion. 
   * Therefore, this algorithm sorts in ascending order.
   */
  public void sort(){
    
    //initialize some help variables
    int currentMaxIndex = 0;
    int placeHolder = 0;
    int size = intArray.size();
    
    //go through the array in a descending order
    //the integer i is going to determine the first sorted element
    for(int i = size; i > 0; i--){
      
      
      //go throught the unsorted portion of the array to find where the current maximum number is
      for(int k = 0; k < i; k++){
        

        //if the number at the k-th position is bigger than the number 
        //at the currentMaxIndex position,
        //update the position for the currentMaxIndex
        if(intArray.get(currentMaxIndex)<intArray.get(k)){
          currentMaxIndex = k;
          }
      }
      
      //swap the places of the maximum that was found in the previous loop
      //and the last item of the unsorted array
      placeHolder = intArray.get(i-1);
      intArray.set(i-1, intArray.get(currentMaxIndex));
      intArray.set(currentMaxIndex,placeHolder);
      //finally, reset the currentMaxIndex variable
      currentMaxIndex = 0;
    
    }
 
    
   }
  
  /**
   * The following method takes one integer as a parameter
   * and returns the first occurence of the integer in the container intArray,
   * which is part of the parent class RandomIntContainer. 
   * 
   * If the integer is not found in the container, the method outputs -1.
   * 
   * The method uses a linear search algorithm.
   * 
   * @param numberToBeSearched the number for which the search method is going to search in the container
   * @return position of the first time the integer shows up ot -1 if not found
   */
  
  public int search(int numberToBeSearched){
    
    //loop through the container
    //if numberToBeSearched matches some member of the container,
    //return that member's index
    for (int i = 0; i< intArray.size(); i++){
      if(intArray.get(i)==numberToBeSearched)
        return i;
    
    }
    
    //if not found, return -1
    return -1;
  }
  
  
  
  
  //main method for initial tests to the program
  public static void main(String[] args){
    
    RandomIntSelectionSortContainer rissc = new RandomIntSelectionSortContainer(1);
    
    rissc.addFromFront(60);
    rissc.sort();
    rissc.addFromFront(3);
    rissc.sort();
    rissc.addFromFront(1);
    rissc.sort();
    rissc.addFromFront(2);
    rissc.sort();
    rissc.addFromFront(59);
    //rissc.sort();
    rissc.addFromFront(63);
    //rissc.sort();
    rissc.addFromFront(55);
    //rissc.sort();
    
    System.out.println("UNsorted:");
    for (int i =0; i<6; i++){
      System.out.print(rissc.intArray.get(i)+" ");
    
  }
  
    System.out.println();
    System.out.println("Sorted:");
    rissc.sort();
     for (int i =0; i<6; i++){
      System.out.print(rissc.intArray.get(i)+" ");
    
  }
    
    
    System.out.println();
    System.out.println("Sorted:");
    rissc.sort();
     for (int i =0; i<7; i++){
      System.out.print(rissc.intArray.get(i)+" ");
    
  }

}
}
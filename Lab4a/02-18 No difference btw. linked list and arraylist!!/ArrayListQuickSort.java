import java.util.*;

/**
 * A class that will store and sort integers in an ArrayList.
 * The sorting will be performed by the quicksort algorithm.
 * 
 * The class inherits from the OrderedList abstract class,
 * which implements the SortedList interface.
 * Both of those require the method to have the addData method
 * and the OrderedList requires a sort method.
 * 
 * @author Ivan Evtimov
 */
public class ArrayListQuickSort extends OrderedList{

  /**
   * This is the actual container in the class that
   * will store the integers. 
   * 
   */
  private ArrayList<Integer> intArray;
  
  
  /**
   * This field will keep track of the size of the ArrayList. 
   * 
   */
  private int size;
  
  
  /**
   * The constructor initializes an empty ArrayList
   * and sets the size field to 0.
   * Since ArrayList is dynamically expanding,
   * we do not need any information upon its creation
   * on the size of the data
   * that will be copied into it.
   * 
   */
  public ArrayListQuickSort(){
    
    //set the initial size to 0
    this.size = 0;
    
    //create the ArrayList that will hold the data
    intArray = new ArrayList<Integer>();
  
  }
  
  
  /**
   * The addData method takes in an ArrayList of Integer-s
   * as a parameter and copies its data to this class's container.
   * 
   * During the copy, each new element from the original array
   * is added to the end of the intArray field of the class.
   * Therefore, if more than one arrays are added, they will be 
   * appended to the intArray in sequential order.
   * 
   * Upon each iteration of the copy loop,
   * the size field is also indented, so that
   * we can easily keep track of the current size
   * of the intArray.
   * 
   */
  public void addData(ArrayList<Integer> arrayToBeCopied){
    
    //get the size of the arrayToBeCopied
    int originalArraySize = arrayToBeCopied.size();
    
    //create variables for the copy
    int origInt;
       
    //check if arrayToBeCopied is empty
    if(originalArraySize != 0){
      
      for (int i = 0; i<originalArraySize; i++){
        
        //get the value at i from the arrayToBeCopied
        origInt = arrayToBeCopied.get(i);
        
        /*
         * add that value to the end of the intArray container
         * Since this is a dynamically expanding array and its size
         * is currently i-1, it will just add the new value exactly at the position
         * we want it to be at.
         */
        intArray.add(origInt);
        
        //indent the size field to indicate that the size of the intArray has increased
        size++;
        
      
      }
    
    }else{
      System.out.println("The array that is to be copied is empty.");
    
    }
  
  }
  
  /**
   * Accessor method to get the integer at the specified position.
   * @param index the index in the intArray that we want to access
   * @return the integer at the specified index
   */
  public Integer getInt(int index){
    return intArray.get(index);  
  }
  
  /**
   * Accessor method to get the size of the container.
   * @return the size of the container
   */
  public Integer getSize(){
    return size;  
  }
  
  
 /**
  * This method sorts the array recursively using quicksort.
  * The parameters are needed since the sorting will be done recursively.
  * The code that is not commented out for this sorting algorithm has been adapted from 
  * http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
  * @param startIndex the index at which the sorting should start
  * @param endIndex the index at which the sorting should end
  */
  public void sort(int startIndex, int endIndex){
    
//    int i = startIndex;
//    int j = endIndex;
//    
//    //determine the pivot index and get its value
//    int pivotIndex = (endIndex - startIndex)/2;
//    int pivotValue = intArray.get(pivotIndex);
//    
//    while(i <= j){
//      
//      while(intArray.get(i) < pivotValue)
//        i++;
//      while(intArray.get(j) > pivotValue)
//        j--;
//      
//      if (i <= j){
//        swap(i, j);
//        i++;
//        j--;
//      
//      }
//    }
//      
//      if (startIndex < j)
//        sort (startIndex, j);
//      
//      if (i < endIndex)
//        sort(i, endIndex);
//    
//    }
//      
   
     int i = startIndex, j = endIndex;
     
    // Get the pivot element from the middle of the list
    int pivot = intArray.get(endIndex + (startIndex-endIndex)/2);
    
    //int pivot = intArray.get((endIndex-startIndex)/2);
       
    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (intArray.get(i) < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (intArray.get(j) > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Sort the two parts of the array recursively
    if (startIndex < j)
      sort(startIndex, j);
    if (i < endIndex)
      sort(i, endIndex);
  }
  

  
  
    /**
   * This method performs a swap of two integers in the intArray given their indices.
   * It is used only for the internal operations of the sorting,
   * so it should be private, but I have made it public, so that I can test it
   * @param firstIndex the index of the first number to be swapped
   * @param secondIndex the index of the second number to be swapped
   */
  public void swap(int firstIndex, int secondIndex){
        
        //store the value in the placeHolder
        int placeHolder = intArray.get(firstIndex);
         
        //remove the value at the currentMaxIndex from the array
        intArray.set(firstIndex, intArray.get(secondIndex));
        
        //add the value to the back
        intArray.set(secondIndex, placeHolder);
  }
 
  
  
  public static void main(String[] args){

  }
}
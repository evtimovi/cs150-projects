import java.util.*;

/**
 * An abstract class that will define the functionality of
 * its child classes, LinkedListQuickSort and ArrayListQuickSort.
 * Since its child classes will have different containers to store the given data,
 * this class has no fields.
 * 
 * @author Ivan Evtimov
 * 
 */
public abstract class OrderedList implements SortedList{
  
  /**
   * The method addData should take an ArrayList passed as a parameter and 
   * then copy its contents to the field within the child classes of the OrderedList class.
   * 
   * The method should only take an ArrayList of Integer-s.
   * 
   * @param arrayToBeCopied the array whose contents will be copied 
   */
  public abstract void addData(ArrayList<Integer> arrayToBeCopied);
  
  
   /**
   * The copy method takes in an ArrayList of Integer-s
   * as a parameter and copies its data to another container passed as a parameter.
   * By doing so, this method provides common functionality for its child classes.
   * During the copy, each new element from the original array
   * is added to the end of the new container.
   * Therefore, if more than one arrays are added, they will be 
   * appended to the container in sequential order.
   * @param container the container where the information is copied to ("pasted")
   * @param arrayToBeCopied the container from which the informatio is copied
   * @throws UnsupportedOperationException if the original array is empty
   */
  public void copy(ArrayList<Integer> arrayToBeCopied, List<Integer> container)
                          throws UnsupportedOperationException{
    
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
         * add that value to the end of the intList container
         * Since this is a linked list and its size
         * is currently i-1, it will just add the new value exactly at the position
         * we want it to be at.
         */
        container.add(origInt);
        
     }
    
    }else{
      throw new UnsupportedOperationException("The array that is to be copied is empty.");
    
    }
  
  }
  
  /**
  * This method sorts the corresponding data container using quicksort.
  * The parameters are needed since the sorting will be done recursively.
  * The code for this sorting algorithm has been adapted from 
  * http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
  * @author Lars Vogel
  * Copyright 2009-2010 Lars Vogel
  * last updated on 06/08/2010
  * Accessed: 02/18/2014, 3:00pm
  * 
  * This method throws an UnsupportedOperationException if the passed
  * container is empty or contains only one element.
  * 
  * 
  * @param startIndex the index at which the sorting should start
  * @param endIndex the index at which the sorting should end
  * @param container the container that is to be sorted. For the purposes of this lab,
  * this will either be an ArrayList or a LinkedList, both of which are inherited from List.
  * @throws UnsupportedOperationException if the passed container is empty or contains only one element
  */
  public void sort(List<Integer> container, int startIndex, int endIndex) 
                   throws UnsupportedOperationException{
    
     if(container.size() > 1){
    
         int i = startIndex, j = endIndex;
         
         // Get the pivot element from the middle of the list
         int pivot = container.get(endIndex + (startIndex-endIndex)/2);
         
         //int pivot = intArray.get((endIndex-startIndex)/2);
         
         // Divide into two lists
         while (i <= j) {
           // If the current value from the left list is smaller then the pivot
           // element then get the next element from the left list
           while (container.get(i) < pivot) {
             i++;
           }
           // If the current value from the right list is larger then the pivot
           // element then get the next element from the right list
           while (container.get(j) > pivot) {
             j--;
           }
           
           // If we have found a values in the left list which is larger then
           // the pivot element and if we have found a value in the right list
           // which is smaller then the pivot element then we exchange the
           // values.
           // As we are done we can increase i and j
           if (i <= j) {
             swap(container, i, j);
             i++;
             j--;
           }
         }
         // Sort the two parts of the array recursively
         if (startIndex < j)
           sort(container, startIndex, j);
         if (i < endIndex)
           sort(container, i, endIndex);
         
         
     }else{
       throw new UnsupportedOperationException("Container either contains one element or is empty!");
     }
  }
  
    /**
   * This method performs a swap of two integers in the container given their indices.
   * It is used only for the internal operations of the sorting,
   * so it should be private, but I have made it public, so that I can test it
   * @param firstIndex the index of the first number to be swapped
   * @param secondIndex the index of the second number to be swapped
   * @param container the container where the swapping occurs. For the purposes of this lab,
   * this will either be an ArrayList or a LinkedList, both of which are inherited from List 
   */
  public void swap(List<Integer> container, int firstIndex, int secondIndex){
        
        //store the value in the placeHolder
        int placeHolder = container.get(firstIndex);
         
        //remove the value at the currentMaxIndex from the array
        container.set(firstIndex, container.get(secondIndex));
        
        //add the value to the back
        container.set(secondIndex, placeHolder);
  }
  
  }
  

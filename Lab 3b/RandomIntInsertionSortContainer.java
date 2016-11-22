import java.util.*;
/**
 * A class that stores the values of randomly generated integers.
 * This class is a child class of RandomIntContainer and therefore uses its fields and its constructor.
 * Additional features that are not available in the parent class are the sort method that uses
 * insertion sort to perform a sort of the container and the search method
 * that uses binary search to find a number in the container.
 * @author Ivan Evtimov
 */

public class RandomIntInsertionSortContainer extends RandomIntContainer{
  
 /** 
   * Fields
   * This class uses the fields of its parent class.
   */
  
   
 /**
   * Constructor for the RandomIntInsertionSortContainer class 
   * simply calls the constructor of the parent class. 
   */
  public RandomIntInsertionSortContainer (int sizeOfArray){
   
    super(sizeOfArray);
  
  }

  /**
   * Method to perform the insertion sorting on the intArray from the parent class.
   * 
   * The method takes no parameters and returns nothing.
   * It directly modifies the instance of the intArray field.
   * The sorting algorithm is executed in two loops.
   * The first one goes through the whole array but the last member.
   * The second loop starts at the current value of the variable of the first loop
   * and is only initiated if the last member of the sorted array is 
   * greater than the first member of the unsorted portion.
   * In the following iterations of the loop this comparison goes 
   * back a pair each time until it reaches the beginning of the array.
   * Therefore, in the second loop the members keep swapping their places
   * until each number finds its place.
   * This method sorts in ascending order.
   */
  public void sort(){

       
    //perform the insertion sort
    for (int i =0; i<intArray.size()-1; i++){
      
      //compare the end number at the current array to the first number of unsorted numbers
      //keep comparing and swapping until the new number finds its place
      for(int k = i; k>-1 && intArray.get(k) > intArray.get(k+1); k--){

         swap(k, k+1);
        
      
      }
    
    }
  
  }
  
  /**
   * This method searches through the intArray using binary search.
   * IMPORTANT: The method automatically sorts the intArray
   * no matter what (even if it is already sorted).
   * @return index of searched item if found
   * @return -1 if not is found
   * 
   */
  
  public int search(int numberToBeSearched){
    
    //sort the intArray before doing anything else
    sort();
    
    //create variables for the beginning and end of the search
    //initialize them to the beginning and the end of the intArray
    int startIndex = 0;
    int endIndex = intArray.size()-1;
    
    //initialize mid varaible
    int mid=0;
    
    while(startIndex <= endIndex){
      
      //calculate where the middle is and store its value in mid
      mid = startIndex+endIndex;
    
      //compare the number at mid and the numberToBeSearched
      if (intArray.get(mid) > numberToBeSearched){
        //continue search in the portion of the array with smaller numbers
        //(to the left of mid)
        endIndex = mid - 1;
      } else if (intArray.get(mid) < numberToBeSearched){
        //continue search in the portion of the array with bigger numbers
        //(to the right of mid)
        startIndex = mid +1;
      
      } else{
        //if both of the above cases are false,
        //then the numberToBeSearched has been found at mid
        return mid;
      
      }
      
    
    }
    
    //if the loop ends without anything having been returned
    //return -1 to indicate number has not been found
    return -1;
    
  }

  /**
   * This method performs a swap of two integers in the intArray given their indices.
   * It is used only for the internal operations of the insertion sort,
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
  
  
  
  /**
   * Main method only used to fool around with the program. Nothing useful gets done here.
   */
  public static void main(String[] args){
    
    RandomIntInsertionSortContainer riisc = new RandomIntInsertionSortContainer(1);
    
    riisc.addFromFront(60);
    riisc.addFromFront(3);
    riisc.addFromFront(1);
    riisc.addFromFront(2);
    riisc.sort();
    riisc.addFromFront(59);
    riisc.addFromFront(63);
   
//   System.out.println ("Before swapping");   
//    for (int i =0; i<6; i++){
//      System.out.print(riisc.intArray.get(i)+" ");
//    
//  }
//    
//   riisc.swap(0,1);
//   riisc.swap(3,2);
//   System.out.println ();  
//   System.out.println ("After swapping");  
//   for (int i =0; i<6; i++){
//      System.out.print(riisc.intArray.get(i)+" ");
//    
//  }

       System.out.println ("Before sorting");   
    for (int i =0; i<6; i++){
      System.out.print(riisc.intArray.get(i)+" ");
    
  }
    
   riisc.sort();
 
   System.out.println ();  
   System.out.println ("After sorting");  
   for (int i =0; i<6; i++){
      System.out.print(riisc.intArray.get(i)+" ");
    
  }
    
}
}
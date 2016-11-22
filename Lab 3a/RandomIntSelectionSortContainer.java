import java.util.*;

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

  //method to perform the selection sorting on the intArray field from the parent class  
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
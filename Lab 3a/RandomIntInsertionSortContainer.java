import java.util.*;

public class RandomIntInsertionSortContainer extends RandomIntContainer{
  
  //fields. This classes uses the fields of its parent class.
  
   
  //constructor
  //since the basic structure of the container is the same, 
  //we simply call the constructor of the parent class
  public RandomIntInsertionSortContainer (int sizeOfArray){
   
    super(sizeOfArray);
  
  }

  
  public void sort(){
     //create a place holder variable for the swap
    //int placeHolder = 0;
       
    //perform the insertion sort
    for (int i =0; i<intArray.size()-1; i++){
      for(int k = i; k>-1 && intArray.get(k) > intArray.get(k+1); k--){
        
        
         swap(k, k+1);
        
      
      }
    
    }
  
  }

  //method to perform a swap of two integers in an array given their indices
  //method is used only for the internal operations of the insertion sort,
  //so it should be private, but I have made it public, so that I can test it
  //@param firstIndex the index of the first number to be swapped
  //@param secondIndex the index of the second number to be swapped
  public void swap(int firstIndex, int secondIndex){
        
        //store the value in the placeHolder
        int placeHolder = intArray.get(firstIndex);
         
        //remove the value at the currentMaxIndex from the array
        intArray.set(firstIndex, intArray.get(secondIndex));
        
        //add the value to the back
        intArray.set(secondIndex, placeHolder);
}
  
  
  
  //main method for initial tests to the program
  public static void main(String[] args){
    
    RandomIntInsertionSortContainer riisc = new RandomIntInsertionSortContainer(1);
    
    riisc.addFromFront(60);
    riisc.addFromFront(3);
    riisc.addFromFront(1);
    riisc.addFromFront(2);
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
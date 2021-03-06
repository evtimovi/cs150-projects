import java.util.*;

public class RandomIntContainer{
  
  //constants
  
  //fields
  //they use protected keyword because 
  //they need to be able to be accessed from the subclass
  protected int sizeOfArray;
  protected ArrayList<Integer> intArray;
  
  //constructor
  public RandomIntContainer(int sizeOfArray){
    
    //assign the parameter value to the instance variable
    this.sizeOfArray = sizeOfArray;
     
    //create an ArrayList of the specified length
    intArray = new ArrayList<Integer>(this.sizeOfArray);
  
  }
  
  //empty constructor to prevent compiler errors
  public RandomIntContainer(){}
  
  //methods
  
  //method to add a number to the integer container at the beginnig of the ArrayList
  //it shifts all other numbers one index to the right
  //@param numberToBeAdded the number to be inserted at position one
  public void addFromFront (int numberToBeAdded){
    
    //use the add method from the ArrayList class to add a number at the first position
    //the method automatically shifts everything to the right
    intArray.add(0, numberToBeAdded);
    
  
  }
  
  //getter method to get the number at the desired position in the container
  //@return number at position specified in the container
  public int getNum(int position){
    return intArray.get(position);
  }
  
  //main method to test out the class
  public static void main(String[] args){
    
    RandomIntContainer r = new RandomIntContainer(2);
       
    r.addFromFront(4);
    r.addFromFront(5);
    r.addFromFront(6);
    r.addFromFront(7);
    
   // boolean test = true;
    
    for(int i = 0; i<4; i++){
    
      System.out.println(r.intArray.get(i));
     // test = r.intArray.isEmpty();
    }
    
    
  }

}
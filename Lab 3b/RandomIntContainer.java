import java.util.*;


/**
 * A class that uses the ArrayList to store a series of integers.
 * @author Ivan Evtimov
 */
public class RandomIntContainer{
  
  //constants
  
  /**
   * This field is the size of the array, which
   * changes as more items are added or removed. It is used to set the initial 
   * length of the ArrayList.
   * 
   * This field is protected as it is to be used by this class's child classes.
   */
  protected int sizeOfArray;
  
   /**
   * 
   * The second field is an ArrayList of 
   * integers that is associated with the actual functions of the class.
   * 
   * This field is protected as it is to be used by this class's child classes.
   */
  protected ArrayList<Integer> intArray;
  
  /**
   * The primary constructor takes an initial size of the array as a variable
   * and createas an ArrayList of the given size.
   * 
   */
  public RandomIntContainer(int sizeOfArray){
    
    //assign the parameter value to the instance variable
    this.sizeOfArray = sizeOfArray;
     
    //create an ArrayList of the specified length
    intArray = new ArrayList<Integer>(this.sizeOfArray);
  
  }
  
  /**
   * Empty Constructor. Does nothing interesting.
   * Used to avoid compiler errors when creating inherited classes.
   * 
   */
  public RandomIntContainer(){}
  
  //methods
  
  /**
   * This method adds a number to the integer container at the beginnig of the ArrayList.
   * It shifts all other numbers one index to the right, an automatic function of the ArrayList.
   * @param numberToBeAdded the number to be inserted at position one (index 0)
   */
  public void addFromFront (int numberToBeAdded){
    
    //use the add method from the ArrayList class to add a number at the first position
    //the method automatically shifts everything to the right
    intArray.add(0, numberToBeAdded);
    
  
  }
  
  /** This is a "getter" method to get the number at the desired position in the container.
    * It merely uses the get(int index) method from the ArrayList's methods and returns that.
    * @return number at position specified in the container
    */
  public int getNum(int position){
    return intArray.get(position);
  }
  
  /**
   * Main method used just to test out the class. 
   */
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
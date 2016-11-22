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
   * The addData makes use of the copy()
   * method in the parent class. It copies the information from the
   * parameter container to the instance variable of this class.
   * After that, it sorts the field and indents the size field by the appropriate value.
   * @param arrayToBeCopied array from which we copy
   * 
   */
  public void addData(ArrayList<Integer> arrayToBeCopied){
    
    //call the parent copy method to perform the copying to the instance variable
    super.copy(arrayToBeCopied, intArray);  
    
   //update the size of our container.
    size += arrayToBeCopied.size();
    
    //sort our container
    sort();
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
  * The method implements the sort method of the parent class
  * on the instance variable of this class.
  */
  public void sort(){
    super.sort(intArray, 0, intArray.size()-1);
  }
  
  
  /**
   * Accessor method to get the instance of the container.
   * @return the container
   */
  public ArrayList<Integer> getContainer(){
    return intArray;  
  }
  
  public static void main(String[] args){

  }
}
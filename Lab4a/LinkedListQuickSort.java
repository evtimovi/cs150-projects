import java.util.*;

/**
 * A class that will store and sort integers in an LinkedList.
 * The sorting will be performed by the quicksort algorithm.
 * 
 * The class inherits from the OrderedList abstract class,
 * which implements the SortedList interface.
 * Both of those require the method to have the addData method
 * and the OrderedList requires a sort method.
 * 
 * @author Ivan Evtimov
 */
public class LinkedListQuickSort extends OrderedList{

  /**
   * This is the actual container in the class that
   * will store the integers. 
   * 
   */
  private LinkedList<Integer> intList;
  
  
  /**
   * This field will keep track of the size of the LinkedList. 
   * 
   */
  private int size;
  
  
  /**
   * The constructor initializes an empty LinkedList
   * and sets the size field to 0.
   * Since LinkedList is dynamically expanding,
   * we do not need any information upon its creation
   * on the size of the data
   * that will be copied into it.
   * 
   */
  public LinkedListQuickSort(){
    
    //set the initial size to 0
    this.size = 0;
    
    //create the LinkedList that will hold the data
    intList = new LinkedList<Integer>();
  
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
    super.copy(arrayToBeCopied, intList);  
    
   //update the size of our container.
    size += arrayToBeCopied.size();
    
    //sort our container
    sort();
  }
  
  /**
   * Accessor method to get the integer at the specified position.
   * @param index the index in the intList that we want to access
   * @return the integer at the specified index
   */
  public Integer getInt(int index){
    return intList.get(index);  
  }
  
  /**
   * Accessor method to get the size of the container.
   * @return the size of the container
   */
  public Integer getSize(){
    return size;  
  }
  
   /**
   * Accessor method to get the instance of the container.
   * @return the container
   */
  public LinkedList<Integer> getContainer(){
    return intList;  
  }
  
  /**
  * This method sorts the array recursively using quicksort.
  * The method implements the sort method of the parent class
  * on the instance variable of this class.
  */
  public void sort(){
    super.sort(intList, 0, intList.size()-1);
  }
 
  
  
  public static void main(String[] args){

  }
}
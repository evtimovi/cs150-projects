import java.util.*;

/**
 * An interface to describe the functionalities of the OrderedList class and 
 * its child classes LinkedListQuickSort and ArrayListQuickSort.
 * 
 * @author Ivan Evtimov
 */
  

public interface SortedList{
  
  
  /**
   * The method addData should take an ArrayList passed as a parameter and 
   * then copy its contents to the field within the child classes of the OrderedList class.
   * 
   * The method should only take an ArrayList of Integer-s.
   * 
   * @param arrayToBeCopied the array whose contents will be copied 
   */

  public void addData(ArrayList<Integer> arrayToBeCopied);


}
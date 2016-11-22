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
   * The method sort should sort the corresponding data container 
   * in each class using the quicksort algorithm.
   * The parameters are needed since the sorting will be done recursively.
   * @param startIndex the index at which the sorting should start
   * @param endIndex the index at which the sorting should end
   */
  public abstract void sort(int startIndex, int endIndex);
  
}
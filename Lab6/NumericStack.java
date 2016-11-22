/**
 * A class that provides an implementation of the Stack data structure,
 * but does not extend or implement any of the API classes.
 * The class is designed to be generic and to accept only 
 * data types that are inherited from the Number class.
 * 
 * @author Ivan Evtimov
 */
public class NumericStack<E extends Number>{
  
/**
 * This field stores the data on the stack. 
 */
  private MyLinkedList<E> listCont;
  
  /**
   * This field keeps track of the stack's size.
   */
  private int size;
  
  /**
   * Constructor initializes the internal container and sets size to 0.
   */
  public NumericStack(){
    listCont = new MyLinkedList<E>();
    size = 0;
  }
  
  /**
   * Test if the stack is empty.
   * @return true if empty,
   * false if at least one element in stack
   */
  public boolean empty(){
   
    return size == 0;
  }
  
  /**
   * This method returns the object at the top of the stack without
   * removing it from the stack
   * @return object at top
   */
  public E peek(){ return listCont.getLast(); }
  
  
  /**
   * This method returns and removes the object at the top of the stack.
   * @return object at top
   */
  public E pop(){
    //update size
    size--;
    //return
    return listCont.remove(); 
  }
  

  /**
   * This method pushes the passed item on the stack
   * and returns the same item.
   * @param item item to be pushed on the stack
   * @return the item that was pushed on the stack
   */
  public E push (E item){ 
    //add item
    listCont.add(item);
    size++;//update size
    return item; 
  }
  
  /**
   * This method returns the top-based position of the passed object on the stack.
   * @param o the object to be searched for
   * @return the index (starting from the top of the stack as 1) of the parameter
   */
  public int search(Object o){
    
    //search using the indexOf method of the listCont
    int searchResult = listCont.indexOf(o);
    
    //if the search result is negative, return that
    if (searchResult == -1){
      return searchResult;
    //if its positive, calculate the one-based position from the top of the stack
    } else {
      return Math.abs(size-searchResult);
    }
    
  }
}
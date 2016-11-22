import java.util.*;


/**
 * Waiting lines will form at the entry to any staircase that is already full.
 * Hence, this class will provide the functioanlity needed for allowing
 * only one Person on the staircase at a time.
 * At the same time, it will preserve the arrival order that was determined
 * by the normal distribution.
 * In a real-world analogy, this would be the queue of people
 * waiting to access the staircase and will therefore be somewhere between the floor
 * and the staircase (in other words, at the entry/exit point).
 * Since we need quick access only to the first element in the list
 * and we also need to be able to quickly add another person to the queue (we might
 * have to do this multiple times at each time step), I am using a linked list.
 * An array list might be inefficient because of the constantly changing capacity.
 * A linked list, on the other hand, as the one this implementation of the Queue
 * uses, allows for constant add and remove complexity.
 * 
 *  
 * @author Ivan Evtimov
 */
public class WaitingLine<P> extends AbstractQueue<P>{
  
  
  /**
   * This field keeps track of the size of the queue.
   * 
   */
  private int size;
  
  
  /**
   * This field is the LinkedList container.
   * 
   */
  private LinkedList<P> pList;
  
  /**
   * Constructor: the constructor creates an empty LinkedList.
   * 
   */
  public WaitingLine(){
    
    //initialize the size variable
    this.size = 0;
    
    //create the linked list
    pList = new LinkedList<P>();
    
  }
  
  /**
   * This method adds the person at the back of the LinkedList.
   * @param p the object of type P (the generic type) that is to be added 
   * @return true if the operations is successful
   * @throws IllegalStateException never since our queue has no limits to its capacity
   * @throws ClassCastException never since this queue accepts all kinds of objects
   * @throws NullPointerException if the parameter that is passed is null
   * @throws IllegalArgumentException never
   */
  public boolean add(P p) throws IllegalStateException, ClassCastException, 
                                      NullPointerException, IllegalArgumentException{
  
    if (p == null){
      throw new NullPointerException();
    } else {
      //append the given P-object at the end of the list
      pList.add(p);
      
      //update the size field
      size++;
      
      return true;
    }
  }
  
  
  /**
   * This method adds the passed linked list to the queue.
   * I would strongly advise against using this method especially
   * when the parameter LinkedList is really big as
   * the method will first search through it for a null object 
   * and then access every single element one by one, both of
   * which will drive execution time way up.
   * @param externalPList the list of P (generic) objects that is to be added
   * @return true if the operations is successful
   * @throws NullPointerException if one of the elements in the list to be added is null.
   * @throws ClassCastException never
   * @throws IllegalStateException never
   * @throws IllegalArgumentException never
   */
  public boolean addAll(LinkedList<P> externalPList) throws IllegalStateException, ClassCastException, 
                                                                      NullPointerException, IllegalArgumentException{
  
    if (externalPList.contains(null)){
      throw new NullPointerException();
    } else {
    
      //loop through the list passed as a parameter and add every element to the queue
      for(int i = 0; i< externalPList.size(); i++){
        
        //add the element to the linked List
        pList.add(externalPList.get(i));
        
        //indent the size field
        size++;
        
      }
      
      return true;
    }
  }
  
  
  /**
   * This method clears all elements from the 
   * list using the clear() method of the LinkedList class.
   */
  public void clear(){
    
    pList.clear();
    
    //set size equal to 0
    size = 0;
  }
  
  
  /**
   * This method returns 
   * the person at the head of the queue.
   * This method throws an exception if the pList field is empty.
   * @return the Person object at the position 0 in the linked list
   * @throws NoSuchElementException if the pList is empty
   */
  public P element() throws NoSuchElementException{
  
    if (size != 0){
      //create a variable to hold the generic P object that is being accessed
      P p;
      
      //get the generic P object at position 0 in the LinkedList
      p = pList.get(0);
      
      return p;
    } else {
    
      throw new NoSuchElementException();
    }
  }
  
  /**
   * This method removes and returns the (generic) P object at the head of the queue.
   * @return the P object that has been removed
   * @throws NoSuchElementException if the pList is empty
   */
  public P remove() throws NoSuchElementException{
  
    if (size != 0){
      //store the person in a variable
      P p = pList.get(0);
      
      //remove the P object
      pList.remove(0);
      
      //modify the size field accordingly
      size--;
      
      //return the variable
      return p;
    
    } else {
    
      throw new NoSuchElementException();
    }
  }
  
  /**
   * This method returns the appropriate iterator for the LinkedList.
   * @return the iterator for the LinkedList holding the P objects
   */
  public Iterator<P> iterator(){
  
    return pList.iterator();
  }
  
  /**
   * Method to return the current size of the Queue.
   * @return the current size of the queue.
   */
  public int size(){
    return size;
  }
  
 
  /**
   * This method returns the P object at the head of the pList provided
   * that it is not empty.
   * @return null if the pList is empty
   * @return the P object at the head of the queue if it is not empty
   */
  public P peek(){
  
    if (size != 0){
      return pList.get(0);
    }else{
      return null;
    }
  }
  
   /**
   * This method returns and removes the P object at the head 
   * of the pList provided
   * that it is not empty.
   * @return null if the pList is empty
   * @return the Person object at the head of the queue (that has been removed) if it is not empty
   */
  public P poll(){
  
    if (size != 0){
      
      //store the P object in a variable
      P p = pList.get(0);
      
      //remove the P object
      pList.remove(0);
      
      //modify the size field accordingly
      size--;
      
      //return the variable
      return p;
    
    }else{
      return null;
    }
  }
  
  /**
   * This method has the same functionality as the add() method
   * and throws the exact same exceptions. I have only implemented
   * it because the compiler insisted on that.
   * @param p the P object we want to add to the pList
   * @return true if adding successful
   * @throws ClassCastException never
   * @throws NullPointerException if the passed parameter is null
   * @throws IllegalArgumentException never
   */
  public boolean offer(P p) throws ClassCastException, NullPointerException, 
                                 IllegalArgumentException{
    
    if (p == null){
      
      throw new NullPointerException();
    
    } else{
    
           
      //append the object at the end of the list
      pList.add(p);
      
      //update the size field
      size++;
      
      return true;
      
    }
 
    
  }
   

}
import java.util.*;


/**
 * A generic class that extends the AbstractQueue class.
 * In essence, this class implements a First-In, First-Out queue
 * for the generic type P. 
 * 
 * @author Ivan Evtimov
 */
public class LineQueueList<P> extends AbstractQueue<P>{
  
  
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
   * Constructor: the constructor creates an LinkedList.
   * 
   */
  public LineQueueList(){
    
    //initialize the size variable
    this.size = 0;
    
    //create the linked list
    pList = new LinkedList<P>();
    
  }
  
  /**
   * This method adds the person at the back of the LinkedList.
   * @parameter p the object of type P (the generic type) that is to be added 
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
   * @parameter externalPList the list of P (generic) objects that is to be added
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
   * @throw ClassCastException never
   * @throw NullPointerException if the passed parameter is null
   * @throw IllegalArgumentException never
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
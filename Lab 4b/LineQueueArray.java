import java.util.*;


/**
 * A class that extends the AbstractQueue class.
 * In essence, this class implements a First-In, First-Out queue
 * for the generic type P. 
 * 
 * @author Ivan Evtimov
 */
public class LineQueueArray<P> extends AbstractQueue<P>{
  
  
  /**
   * This field keeps track of the size of the queue.
   * 
   */
  private int size;
  
  
  /**
   * This field is the ArrayList container.
   * 
   */
  private ArrayList<P> pArray;
  
  /**
   * Constructor: the constructor creates an ArrayList.
   * 
   */
  public LineQueueArray(){
    
    //initialize the size variable
    this.size = 0;
    
    //create the linked array
    pArray = new ArrayList<P>();
    
  }
  
  /**
   * This method adds the person at the back of the ArrayList.
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
      //append the given P-object at the end of the array
      pArray.add(p);
      
      //update the size field
      size++;
      
      return true;
    }
  }
  
  
  /**
   * This method adds the passed ArrayList to the queue.
   * @parameter externalPArray the array of P (generic) objects that is to be added
   * @return true if the operations is successful
   */
  public boolean addAll(ArrayList<P> externalPArray) throws IllegalStateException, ClassCastException, 
                                                                      NullPointerException, IllegalArgumentException{
  
    if (externalPArray.contains(null)){
      throw new NullPointerException();
    } else {
    
      //loop throught the array passed as a parameter and add every element to the queue
      for(int i = 0; i< externalPArray.size(); i++){
        
        //add the element to the linked Array
        pArray.add(externalPArray.get(i));
        
        //indent the size field
        size++;
        
      }
      
      return true;
    }
  }
  
  
  /**
   * This method clears all elements from the 
   * array using the clear() method of the ArrayList class.
   */
  public void clear(){
    
    pArray.clear();
    
    //set size equal to 0
    size = 0;
  }
  
  
  /**
   * This method returns 
   * the person at the head of the queue.
   * This method throws an exception if the pArray field is empty.
   * @return the Person object at the position 0 in the linked array
   * @throws NoSuchElementException if the pArray is empty
   */
  public P element() throws NoSuchElementException{
  
    if (size != 0){
      //create a variable to hold the generic P object that is being accessed
      P p;
      
      //get the generic P object at position 0 in the ArrayList
      p = pArray.get(0);
      
      return p;
    } else {
    
      throw new NoSuchElementException();
    }
  }
  
  /**
   * This method removes and returns the (generic) P object at the head of the queue.
   * @return the P object that has been removed
   * @throws NoSuchElementException if the pArray is empty
   */
  public P remove() throws NoSuchElementException{
  
    if (size != 0){
      //store the person in a variable
      P p = pArray.get(0);
      
      //remove the P object
      pArray.remove(0);
      
      //modify the size field accordingly
      size--;
      
      //return the variable
      return p;
    
    } else {
    
      throw new NoSuchElementException();
    }
  }
  
  /**
   * This method returns the appropriate iterator for the ArrayList field.
   * @return the iterator for the ArrayList holding the P objects
   */
  public Iterator<P> iterator(){
  
    return pArray.iterator();
  }
  
  /**
   * Method to return the current size of the Queue.
   * @return the current size of the queue.
   */
  public int size(){
    return size;
  }
  
 
  /**
   * This method returns the P object at the head of the pArray provided
   * that it is not empty.
   * @return null if the pArray is empty
   * @return the P object at the head of the queue if it is not empty
   */
  public P peek(){
  
    if (size != 0){
      return pArray.get(0);
    }else{
      return null;
    }
  }
  
   /**
   * This method returns and removes the P object at the head 
   * of the pArray provided
   * that it is not empty.
   * @return null if the pArray is empty
   * @return the Person object at the head of the queue (that has been removed) if it is not empty
   */
  public P poll(){
  
    if (size != 0){
      
      //store the P object in a variable
      P p = pArray.get(0);
      
      //remove the P object
      pArray.remove(0);
      
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
   * @param p the P object we want to add to the pArray
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
    
           
      //append the object at the end of the array
      pArray.add(p);
      
      //update the size field
      size++;
      
      return true;
      
    }
 
    
  }
   

}
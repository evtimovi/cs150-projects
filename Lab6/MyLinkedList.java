
/**
 * A class that provides an implementation of a linked list,
 * but does not inherit from or implement any of the standard Java API
 * interfaces/classes.
 * Please, note that this class has been designed to be used in the implementation
 * of a stack, so some of its functionality has been specially tailored
 * to this purpose. For instance, the remove() method returns the element
 * at the TAIL of the list, not the head.
 * 
 * @author Ivan Evtimov
 */
public class MyLinkedList<E extends Number>{
  
  ///////////////////Inner class ///////////////////////////////
  /**
   * An inner class that is used in the implementation of 
   * a linked list used for the stack.
   */
  public class Node{
    
    /**
     * This field stores the value of the node.
     */
    private E dataValue;
    
    /**
     * This field points to the next node in the linked list.
     */
    private Node nextNode;
    
    /**
     * This field points to the previous node in the linked list.
     */
    private Node previousNode;
    
    /**
     * This method links this node with the previous one.
     * @param previousNode the previous node that will be linked with this one.
     */
    public void setPrevious (Node previousNode){
    
      //set this node's instance variable to the passed parameter
      this.previousNode = previousNode;
    }
    
    /**
     * This method links the node with the next one.
     * @param nextNode the next node that will be linked with this one.
     */
    public void setNext (Node nextNode){
    
      //set this node's instance variable to the passed parameter
      this.nextNode = nextNode;
    }
    
    /**
     * This method returns the next node in the linked list with relation
     * to this one.
     * @return the next node in the linked list
     */
    public Node getNext(){ return nextNode; }
    
    /**
     * This method returns the previous node in the linked list with relation to this one.
     * @return the previous node in the list
     */
    public Node getPrevious(){ return previousNode; }
    /**
     * This method sets the
     * node's data value to the passed parameter.
     * @param n the number that the value in the node will be set to
     */
    public void setValue(E e){ dataValue = e;}
  
    /**
     * This method gets the value of the node.
     */
    public E getValue(){ return dataValue;}
    
  }
  
  
   //////////////////////Fields for the outer class/////////////////////
   
  /**
   * This field is the head. It should have a null dataValue and 
   * only points to the next node.
   */
  private Node head;
  
  /**
   * This field is the tail. It should have a null dataValue and only point to a previous
   * node.
   */
  private Node tail;
  
  /**
   * This field keeps track of the size of the list.
   */
  private int size;
  
  /**
    * Constructor method creats an empty linked list,
    * setting the head and tail pointing to null.
    */
  public MyLinkedList(){
  
    //create head and tail
    head = new Node();
    tail = new Node();
    //System.out.println("Head points to: " + head.getNext());
    //System.out.println("Tail points to: " + tail.getPrevious());
    
    this.size = 0;
    
    
  }
  
  /**
   * Appends the passed element to the end of the list.
   * @param e the element to be added to the end of the list.
   * @return true
   */
  public boolean add(E e){
  
    //create a new node
    Node nodeToBeInserted = new Node();
    
    //set its value to the passed parameter
    nodeToBeInserted.setValue(e);
    
    //check if the list is empty
    if (size == 0){
     
      //set that node's previous and next to null
      nodeToBeInserted.setPrevious(null);
      nodeToBeInserted.setNext(null);
      
      //set the head and the tail to those values
      head = nodeToBeInserted;
      tail = nodeToBeInserted;
        
    
    } else if (size == 1){ 
    //special case if the array is 1 item long (the head and tail are the same nodes)
      tail = nodeToBeInserted;
      tail.setPrevious(head);
      head.setNext(tail);
    
      
      
    } else{
      //if it is not, set the tail's next node as the node to be inserted
      tail.setNext(nodeToBeInserted);
      
      //set that node's previous as the current tail
      nodeToBeInserted.setPrevious(tail);
      
      //update the tail
      tail = nodeToBeInserted;
     
    }
    
    //update size
    size++;
    
    return true;
  }
  
  /**
   * Returns and removes the element at the tail if the list is not empty.
   * @return the last element in the list
   * @throws NullPointerException if the list is empty
   */
  public E remove() throws NullPointerException{
    
    //check if list is empty
    if ( size == 0 ){
      
      throw new NullPointerException();//throw exception if it is empty
      
    } else if (size == 1){
      //special case if size is 1
      E valueToBeReturned = tail.getValue();
      
      
      //set both the head and the tail to null
      tail = null;
      head = null;
      
      //update size
      size--;
      
      //return
      return valueToBeReturned;
      
    }else{
      
      //get the value of the tail
      E valueToBeReturned = tail.getValue();
      
      //get the tail's previous and store it in a variable
      Node newTail = tail.getPrevious();
      
      //set the tail's previous to null
      tail.setPrevious(null);
      
      //set the new tail's next to null
      newTail.setNext(null);
      
      //update the tail
      tail = newTail;
      
      //update size
      size --;
      
      return valueToBeReturned;
     }
    
    
  }
  
  /**
   * This method returns the last element without removing it.
   * @return the element that the tail is pointing to
   * @throws NullPointerException if the list is empty
   */
  public E getLast() throws NullPointerException{
    //check if list is empty
    if ( size == 0 ){
      
      throw new NullPointerException();//throw exception if it is empty
      
    } else {
    
     //create a temporary node to access the data
      Node tempNode = tail;
      
      return tempNode.getValue();
     }
    
  }
  
  /**
   * Returns the index of the given object if it is present in the list.
   * @return the index of the object if found, -1 if not found
   * @param o the Object we are looking for
   */
  public int indexOf(Object o){
    
    //declare a variable for an index count
    int indexCount = 0;
    
    //traverse the list
    for (Node currentNode = head; 
         currentNode != null; 
         currentNode = currentNode.getNext()){
    
      
      
      //check each element if it matches the passed parameter
      if (currentNode.getValue().equals(o)){
        
        //if it does, return its "index"
        return indexCount;
         
      }
      
      //update indexCount
      indexCount++;
    }
    //if nothing is found after the traversal
    return -1;
  }
  
  /**
   * Accessor method to get the current list's size.
   * @return the linked list's size
   */
  public int size() { return size; }
  
  public static void main(String[] args){
    MyLinkedList<Double> l = new MyLinkedList<Double>();
  
    l.add(75.2);
    l.add(6.73);
    l.add(69.6);
   
    System.out.println(l.indexOf(75.2));
    System.out.println(l.indexOf(6.73));
    System.out.println(l.indexOf(69.6));
//    
//    System.out.println("Index of 75.2: " + l.indexOf(75.2));
//    System.out.println("Index of 63: " + l.indexOf(63));
//    System.out.println("Index of 69: " + l.indexOf(69));
    
//     l.remove();
//      System.out.println("Index of 69 after remove: " + l.indexOf(69));
//      
//      l.remove();
//      System.out.println("Index of 63 after remove: " + l.indexOf(63));
//      l.remove();
//      System.out.println("Index of 75 after remove: " + l.indexOf(75));
    
  }
}
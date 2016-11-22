import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class to test out the functionality
 * of the LineQueueList class. Please, note that I am using Integer
 * as the type of variables that are to be stored in the queue,
 * but the results of the test can be extended to almost any 
 * reference type.
 * @author Ivan Evtimov
 */
public class TestLineQueueList extends TestCase {
  
  /**
   * This field holds an instance of the class we are testing.
   * 
   */
  private LineQueueList<Integer> listContainer;
  
  /**
   * Set up the method by initializing the field of this test class.
   */
  public void setUp(){
    
    listContainer = new LineQueueList<Integer>();
  
  }
  
  /**
   * A test method to test the functionalities of the add() and the element() methods.
   * This method tests if the object is added at the back of the queue 
   * and the one accessed by element is the one at the head of the queue.
   */
  public void testAddAndElement() {
    
     listContainer.add(18);
     listContainer.add(35);
     listContainer.add(20);
      
            
     assertTrue("Something is wrong with element() and/or add()!", listContainer.element() == 18);

    
    }
  
  
  
  /**
   * A test method to test the functionality of the add method.
   * This method tests how the add responds to a null object.
   */
  public void testAdd() {
    
    //create a null object
    Object o = null;
    
    try{
      
      listContainer.add((Integer)o);
      
      fail("Class does not catch NullPointerException!");
      
    }catch (NullPointerException npe){
      //the test should be successful if that happens
    
    }
   
  
  }
  
  /**
   * A test method to test how element() handles an empty list.
   * Implicitly, this also tests the clear() method because
   * if the clear() method hasn't worked, the array will still be full and
   * an exception wouldn't be thrown, causing the test to fail.
   */
  public void testElementOnEmpty(){
    
    listContainer.add(35);
    listContainer.clear();
    
    try{

      listContainer.element();
      
      fail("Wow! How can you access something in an empty queue?");
    
    } catch (Exception e){
    
      //that's what we want
    }
  
  }
  
  
  /**
   * A test method to test how element() handles an empty list.
   * Implicitly, this also tests the clear() method for the same reasons described
   * in the comments for the testElementOnEmpty method.
   */
  public void testRemoveOnEmpty(){
    listContainer.add(35);
    listContainer.clear();
    
    try{
      
      listContainer.remove();
      
      fail("Wow! How can you remove something from an empty queue?");
    
    } catch (NoSuchElementException e){
    
      //that's what we want
    }
  
  }
  
    /**
   * A test method to ensure that remove()
   * removes the first element of the queue.
   */
  public void testRemove2(){
    listContainer.clear();
    listContainer.add(57);
    listContainer.add(33);
    listContainer.add(45);
    
    //now the queue should be 45, 33, 57
    
    listContainer.remove();
    
    //now the queue should be 33, 57
    
    
    assertTrue("Ooops, something went wrong. How can we fix that?", listContainer.element()==33);
    
  }
  
  
  /**
   * A test method to test the functionality of addAll() and
   * especially its response to an array with a null object.
   */
  public void testAddAll(){
  
    //initialize the linked list, but never fill it up
    LinkedList<Integer> originalList = new LinkedList<Integer>();
    originalList.add(null);
    
    try{
      
      listContainer.addAll(originalList);

      fail("Adding null objects??");
      
    } catch (Exception e){
    
      //good, the test has been passed
    }
  }
  
  /**
   * A test method to test the functionality of addAll() and,
   * implicitly, the peek() method.
   */
  public void testAddAllAndPeek(){
  
    //initialize the linked list and fill it up
    LinkedList<Integer> originalList = new LinkedList<Integer>();
    originalList.add(3);
    originalList.add(75);
    originalList.add(36);
    //the originalList should now contain 3, 75, 36 in that order
    //hence, the peek() method (after addAll(originalList) should return 3

    listContainer.clear();     
    listContainer.addAll(originalList);

    assertTrue("Something went wrong!", listContainer.peek()==3);
      
    
  }
  
  /**
   * A test method to ensure that peek()
   * returns null if the queue is empty.
   */
  public void testPeek(){
    listContainer.clear();
    
    assertTrue("Wow, shouldn't we be getting null??", listContainer.peek()==null);
    
  }
  
  /**
   * A test method to ensure that poll()
   * returns null if the queue is empty.
   */
  public void testPoll(){
    listContainer.clear();
    
    assertTrue("Wow, shouldn't we be getting null??", listContainer.poll()==null);
    
  }
  
  
  /**
   * A test method to ensure that poll()
   * removes the first element of the queue.
   */
  public void testPoll2(){
    listContainer.clear();
    listContainer.add(57);
    listContainer.add(33);
    listContainer.add(45);
    
    //now the queue should be 45, 33, 57
    
    listContainer.poll();
    
    //now the queue should be 33, 57
    
    
    assertTrue("Ooops, something went wrong. How can we fix that?", listContainer.peek()==33);
    
  }
  
  /**
   * A test method to ensure that an appropriate iterator has been
   * returned and that that iterator gives the correct values at the correct positions.
   */
  public void testIterator(){
    listContainer.clear();
    
    listContainer.add(352);
    listContainer.add(478);
    listContainer.add(365);
    //the queue should be 352, 478, 365
    
   Iterator i = listContainer.iterator();
   
   assertTrue("Something is wrong with the iterator.", (Integer)i.next()==352
                && (Integer)i.next()==478 && (Integer)i.next()==365);
    
    
  
  }
  
    

  
  
  
  
  
  
}

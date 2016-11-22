import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class to test out the functionality
 * of the LineQueueArray class. Please, note that I am using Integer
 * as the type of variables that are to be stored in the queue,
 * but the results of the test can be extended to almost any 
 * reference type.
 * @author Ivan Evtimov
 */
public class TestLineQueueArray extends TestCase {
  
  /**
   * This field holds an instance of the class we are testing.
   * 
   */
  private LineQueueArray<Integer> arrayContainer;
  
  /**
   * Set up the method by initializing the field of this test class.
   */
  public void setUp(){
    
    arrayContainer = new LineQueueArray<Integer>();
  
  }
  
  /**
   * A test method to test the functionalities of the add() and the element() methods.
   * This method tests if the object is added at the back of the queue 
   * and the one accessed by element is the one at the head of the queue.
   */
  public void testAddAndElement() {
    
     arrayContainer.add(18);
     arrayContainer.add(35);
     arrayContainer.add(20);
      
            
     assertTrue("Something is wrong with element() and/or add()!", arrayContainer.element() == 18);

    
    }
  
  
  
  /**
   * A test method to test the functionality of the add method.
   * This method tests how the add responds to a null object.
   */
  public void testAdd() {
    
    //create a null object
    Object o = null;
    
    try{
      
      arrayContainer.add((Integer)o);
      
      fail("Class does not catch NullPointerException!");
      
    }catch (NullPointerException npe){
      //the test should be successful if that happens
    
    }
   
  
  }
  
  /**
   * A test method to test how element() handles an empty array.
   * Implicitly, this also tests the clear() method because
   * if the clear() method hasn't worked, the array will still be full and
   * an exception wouldn't be thrown, causing the test to fail.
   */
  public void testElementOnEmpty(){
    
    arrayContainer.add(35);
    arrayContainer.clear();
    
    try{

      arrayContainer.element();
      
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
    arrayContainer.add(35);
    arrayContainer.clear();
    
    try{
      
      arrayContainer.remove();
      
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
    arrayContainer.clear();
    arrayContainer.add(57);
    arrayContainer.add(33);
    arrayContainer.add(45);
    
    //now the queue should be 45, 33, 57
    
    arrayContainer.remove();
    
    //now the queue should be 33, 57
    
    
    assertTrue("Ooops, something went wrong. How can we fix that?", arrayContainer.element()==33);
    
  }
  
  
  /**
   * A test method to test the functionality of addAll() and
   * especially its response to an array with a null object.
   */
  public void testAddAll(){
  
    //initialize the array list, but never fill it up
    ArrayList<Integer> originalList = new ArrayList<Integer>();
    originalList.add(null);
    
    try{
      
      arrayContainer.addAll(originalList);

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
  
    //initialize the array list and fill it up
    ArrayList<Integer> originalList = new ArrayList<Integer>();
    originalList.add(3);
    originalList.add(75);
    originalList.add(36);
    //the originalList should now contain 3, 75, 36 in that order
    //hence, the peek() method (after addAll(originalList) should return 3

    arrayContainer.clear();     
    arrayContainer.addAll(originalList);

    assertTrue("Something went wrong!", arrayContainer.peek()==3);
      
    
  }
  
  /**
   * A test method to ensure that peek()
   * returns null if the queue is empty.
   */
  public void testPeek(){
    arrayContainer.clear();
    
    assertTrue("Wow, shouldn't we be getting null??", arrayContainer.peek()==null);
    
  }
  
  /**
   * A test method to ensure that poll()
   * returns null if the queue is empty.
   */
  public void testPoll(){
    arrayContainer.clear();
    
    assertTrue("Wow, shouldn't we be getting null??", arrayContainer.poll()==null);
    
  }
  
  
  /**
   * A test method to ensure that poll()
   * removes the first element of the queue.
   */
  public void testPoll2(){
    arrayContainer.clear();
    arrayContainer.add(57);
    arrayContainer.add(33);
    arrayContainer.add(45);
    
    //now the queue should be 45, 33, 57
    
    arrayContainer.poll();
    
    //now the queue should be 33, 57
    
    
    assertTrue("Ooops, something went wrong. How can we fix that?", arrayContainer.peek()==33);
    
  }
  
  /**
   * A test method to ensure that an appropriate iterator has been
   * returned and that that iterator gives the correct values at the correct positions.
   */
  public void testIterator(){
    arrayContainer.clear();
    
    arrayContainer.add(352);
    arrayContainer.add(478);
    arrayContainer.add(365);
    //the queue should be 352, 478, 365
    
   Iterator i = arrayContainer.iterator();
   
   assertTrue("Something is wrong with the iterator.", (Integer)i.next()==352
                && (Integer)i.next()==478 && (Integer)i.next()==365);
    
    
  
  }
  
    

  
  
  
  
  
  
}

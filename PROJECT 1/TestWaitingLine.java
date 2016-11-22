import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class to test out the functionality
 * of the WaitingLine class. Please, note that I am using Integer
 * as the type of variables that are to be stored in the queue,
 * but the results of the test can be extended to almost any 
 * reference type.
 * Please also note that since I am reusing the queue implementation from lab 4b,
 * I am also reusing the test class.
 * @author Ivan Evtimov
 */
public class TestWaitingLine extends TestCase {
  
  /**
   * This field holds an instance of the class we are testing.
   * 
   */
  private WaitingLine<Person> listContainer;
  
  /**
   * Set up the method by initializing the field of this test class.
   */
  public void setUp(){
    
    listContainer = new WaitingLine<Person>();
  
  }
  
  /**
   * A test method to test the functionalities of the add() and the element() methods.
   * This method tests if the object is added at the back of the queue 
   * and the one accessed by element is the one at the head of the queue.
   */
  public void testAddAndElement() {
    
    listContainer.clear();
    
    Person personToBeAdded1 = new Person(5,1);
    Person personToBeAdded2 = new Person(3,1);
    Person personToBeAdded3 = new Person(4,1);
    
     listContainer.add(personToBeAdded1);
     listContainer.add(personToBeAdded2);
     listContainer.add(personToBeAdded3);
      
  
     
     Person elemPerson = listContainer.element();
            
     assertTrue("Something is wrong with element() and/or add()!", personToBeAdded1.equals(elemPerson));

    
    }
  
  
  
  /**
   * A test method to test the functionality of the add method.
   * This method tests how the add responds to a null object.
   */
  public void testAdd() {
    
    //create a null object
    Object o = null;
    
    try{
      
      listContainer.add((Person)o);
      
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
    
    listContainer.add(new Person(5, 1));
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
    listContainer.add(new Person(3,1));
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
    
    Person personToBeAdded1 = new Person(5,1);
    Person personToBeAdded2 = new Person(3,1);
    Person personToBeAdded3 = new Person(4,1);
    
    listContainer.add(personToBeAdded1);
    listContainer.add(personToBeAdded2);
    listContainer.add(personToBeAdded3);
    
    
    Person removedP = listContainer.remove();   
    
    assertTrue("Ooops, something went wrong. How can we fix that?", removedP.equals(personToBeAdded1));
    
  }
  
  
  /**
   * A test method to test the functionality of addAll() and
   * especially its response to an array with a null object.
   */
  public void testAddAll(){
  
    //initialize the linked list, but never fill it up
    LinkedList<Person> originalList = new LinkedList<Person>();
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
    LinkedList<Person> originalList = new LinkedList<Person>();
 
    listContainer.clear();
    
    Person personToBeAdded1 = new Person(5,1);
    Person personToBeAdded2 = new Person(3,1);
    Person personToBeAdded3 = new Person(4,1);
    
    originalList.add(personToBeAdded1);
    originalList.add(personToBeAdded2);
    originalList.add(personToBeAdded3);

    
    listContainer.addAll(originalList);
    
    Person peekP = listContainer.peek();

    assertTrue("Something went wrong!", peekP.equals(personToBeAdded1));
      
    
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
    
    Person personToBeAdded1 = new Person(5,1);
    Person personToBeAdded2 = new Person(3,1);
    Person personToBeAdded3 = new Person(4,1);
    
    listContainer.add(personToBeAdded1);
    listContainer.add(personToBeAdded2);
    listContainer.add(personToBeAdded3);
    
   
    
    Person polledP = listContainer.poll();
    
    
    
    
    assertTrue("Ooops, something went wrong. How can we fix that?", polledP.equals(personToBeAdded1));
    
  }
  
  /**
   * A test method to ensure that an appropriate iterator has been
   * returned and that that iterator gives the correct values at the correct positions.
   */
  public void testIterator(){
    listContainer.clear();
    
    Person personToBeAdded1 = new Person(5,1);
    Person personToBeAdded2 = new Person(3,1);
    Person personToBeAdded3 = new Person(4,1);
    
    listContainer.add(personToBeAdded1);
    listContainer.add(personToBeAdded2);
    listContainer.add(personToBeAdded3);
    //the queue should be 352, 478, 365
    
   Iterator i = listContainer.iterator();
   Person firstIterated = (Person) i.next();
   Person secondIterated = (Person) i.next();
   Person thirdIterated = (Person) i.next();
   
   assertTrue("Something is wrong with the iterator.", firstIterated.equals(personToBeAdded1)
                && secondIterated.equals(personToBeAdded2) && thirdIterated.equals(personToBeAdded3));
    
    
  
  }
  
    

  
  
  
  
  
  
}

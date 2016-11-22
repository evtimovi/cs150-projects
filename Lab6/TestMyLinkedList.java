import junit.framework.TestCase;

/**
 * A JUnit test case class to test the functionality of the MyLinkedList class.
 * 
 * @author Ivan Evtimov
 */
public class TestMyLinkedList extends TestCase {
  
  /**
   * The fields are test instances of the class.
   */
  private MyLinkedList<Integer> intList;
  private MyLinkedList<Double> doubleList;
  private MyLinkedList<Long> longList;
  private MyLinkedList<Short> shortList;
  private MyLinkedList<Float> floatList;
  
  /**
   * Ste up method initializes the lists.
   */
  public void setUp(){
  
    intList = new MyLinkedList<Integer>();
    doubleList = new MyLinkedList<Double>();
    longList = new MyLinkedList<Long>();
    shortList = new MyLinkedList<Short>();
    floatList = new MyLinkedList<Float>();
    
  }
  
  /**
   * A test method for the functionality of the add method.
   * It also implicitly tests the indexOf method (since we have no other way
   * of confirming what's in the linked list).
   * Finally, for more comprehensive checks
   * it also tests the remove() and size() methods.
   * The test is run with integers, doubles and floats to ensure that
   * the class is truly generic.
   * 
   */
  public void test1() {
  
    //add three numbers
    intList.add(75);
    intList.add(35);
    intList.add(64);
    
    //since add always adds in the end, they should have indices 0, 1, and 2
    assertTrue("Index in integer list wrong!", intList.indexOf(75)==0 &&
                                               intList.indexOf(35) == 1
                                               && intList.indexOf(64) == 2);
    
    
    //since add always adds in the end, three successive removes should give 64, 35, 75
    assertTrue("Index in integer list wrong!", intList.remove()==64 &&
                                               intList.remove() == 35
                                               && intList.remove() == 75);
    
    //add three doubles
    doubleList.add(75.2);
    doubleList.add(35.3);
    doubleList.add(64.6);
    
    //since we always add from the back, the indices should be 0, 1, 2
    //also, the number 67.5 which is not in the list should give us -1
    assertTrue("Indices in double list wrong!", doubleList.indexOf(64.6) == 2
                                               &&
                                               doubleList.indexOf(35.3)==1
                                               && doubleList.indexOf(75.2)==0
                                               && doubleList.indexOf(67.5) == -1);
    
    //since add always adds in the end, removal should be in opposite order of adding
    assertTrue("Remove from double list wrong!", doubleList.remove()==64.6
                                               &&
                                               doubleList.remove() == 35.3
                                               && doubleList.remove() == 75.2);

    //create three floats
    float number1 = (float) 75.345;
    float number2 = (float) 35.657;
    float number3 = (float) 64.897;
    
    //add three floats
    floatList.add(number1);
    floatList.add(number2);
    floatList.add(number3);
    
     //since add always adds in the end, indexes should be 0, 1, 2
    //also, the index of 67.3, which is not in the list, should be -1
    assertTrue("Indices in float list wrong!", floatList.indexOf(number1) == 0 &&
                                               floatList.indexOf(number2) == 1
                                               && floatList.indexOf(number3) == 2
                                               && floatList.indexOf((float) 67.3) == -1);
    
    //since add always adds in the end, removal should be in opposite order of adding
    assertTrue("Remove in float list wrong!", floatList.remove()== (float) 64.897 &&
                                               floatList.remove() == (float) 35.657
                                               && floatList.remove() == (float) 75.345);
    
    //check the sizes of the lists to make sure they are 0 after the three removals
    assertTrue("Size mismatch!", floatList.size()==0 && doubleList.size()==0 && intList.size()==0);
    
    
  }
  
  /**
   * This method tests if the method getLast throws the appropriate exception
   */
  public void testException(){
  
    try{
      
      //try to get the lst from the shortList, which should be empty
      shortList.getLast();
      
      fail("Exception went uncaught!");
      
    } catch (NullPointerException e){
    
      //test has succeeded
    }
    
  }
  
  
  /**
   * This method tests if the method remove throws the appropriate exception
   */
  public void testException2(){
  
    try{
      
      //try to get the lst from the shortList, which should be empty
      longList.remove();
      
      fail("Exception went uncaught!");
      
    } catch (NullPointerException e){
    
      //test has succeeded
    }
    
  }
  
  /**
   * This method ensures that the getlast method does not remove the element from the list.
   */
  public void testGetLast(){
  
   //create a short
    short number1 = 7865;
    short number2 = 3456;
    short number3 = 6730;
    shortList.add(number1);
    shortList.add(number2);
    shortList.add(number3);
    
    //get the last
   short tempVar = shortList.getLast();
    
   //make assertions
    assertTrue("Last is not what it was supposed to be in short list" + tempVar, tempVar==number3);
    
    assertTrue("Element missing after getLast", shortList.indexOf(number3)==2);
  }

    /**
   * This method ensures that the remove method does not remove the element from the list.
   */
  public void testRemove(){
  
   //create some longs
    long number1 = 7865;
    long number2 = 3456;
    long number3 = 6730;
    longList.add(number1);
    longList.add(number2);
    longList.add(number3);
    
    //get the last by removing it
   long tempVar = longList.remove();
    
   //make assertions
    assertTrue("Last is not what it was supposed to be in long list" + tempVar, tempVar==number3);
    
    assertTrue("Element not missing after remove", longList.indexOf(number3)==-1);
  }
  
  
}

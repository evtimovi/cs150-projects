import junit.framework.TestCase;
import java.lang.Number;

/**
 * A JUnit test case class to test the functionality of the NumericStack class.
 */
public class TestNumericStack extends TestCase {
  
  private NumericStack<Double> doubleStack;
  private NumericStack<Integer> intStack;
  
  
  /**
   * Set-up method initializes the fields.
   */
  public void setUp(){
  
    doubleStack = new NumericStack<Double>();
    intStack = new NumericStack<Integer>();
    
  }
  
  /**
   * A test method for the functionality of the search method.
   * It works both with an integer and a double stack.
   */
  public void testSearch() {
    
    //add some int's to the intStack
    intStack.push(74); //should be at index 3
    intStack.push(35); //should be at index 2
    intStack.push(66); //should be at index 1 (last in)
    
    assertTrue("Indexes of intStack wrong!", intStack.search(66)==1 &&
                                            intStack.search(35)==2 &&
                                            intStack.search(74)==3);
    
    
     //add some doubles to the intStack
    doubleStack.push(74.35); //should be at index 3
    doubleStack.push(35.67); //should be at index 2
    doubleStack.push(66.34); //should be at index 1 (last in)
    
    assertTrue("Indexes of doubleStack wrong!", doubleStack.search(66.34) == 1&&
                                            doubleStack.search(35.67)==2 &&
                                            doubleStack.search(74.35)==3);
    
  }
  
    /**
   * A test method for the functionality of the pop method.
   * It works both with an integer and a double stack.
   * It also tests the empty() method as after the popping, the stack should be empty.
   */
  public void testPop() {
    
    //add some int's to the intStack
    intStack.push(74); //should be popped last
    intStack.push(35); //should be popped second
    intStack.push(66); //should be popped first
    
    int firstPop = intStack.pop();
    int secondPop = intStack.pop();
    int thirdPop = intStack.pop();
    
    assertTrue("Pop of intStack wrong!" +" " + firstPop+" " + secondPop + " " + thirdPop, 
                                         firstPop == 66 &&
                                         secondPop == 35 &&
                                         thirdPop == 74);
    
    
     //add some doubles to the doubleStack
    doubleStack.push(74.35); //should be popped last
    doubleStack.push(35.67); //should be popped second
    doubleStack.push(66.34); //should be popped first
    
    double firstPopD = doubleStack.pop();
    double secondPopD = doubleStack.pop();
    double thirdPopD = doubleStack.pop();
    
    assertTrue("Pops of doubleStack wrong!", firstPopD == 66.34&&
                                            secondPopD ==35.67 &&
                                            thirdPopD ==74.35);
    
    //assert that after the pops, the stacks are empty
    assertTrue("Stacks not empty!", intStack.empty()&&doubleStack.empty());
    
  }
  
  /**
   * This method tests the pop method's reaction to an empty stack.
   */
  public void testExceptions(){
  
    try{
      intStack.pop();//attempt to pop an empty stack
      fail ("No Exception thrown!");
    } catch(NullPointerException e){
      //good
    }
  }
  
    /**
   * This method tests the pop method's reaction to an empty stack of doubles.
   */
  public void testExceptions2(){
  
    try{
      doubleStack.pop();//attempt to pop an empty stack
      fail ("No Exception thrown!");
    } catch(NullPointerException e){
      //good
    }
  }
  
  /**
   * This method tests the peek method.
   */
  public void testPeek(){
    
    //add some int's to the intStack
    intStack.push(74); //shouldn't be accessible just through peek
    intStack.push(35); //shouldn't be accessible just through peek
    intStack.push(66); //if only peek is called should be the only thing returned
    
    int firstPeek = intStack.peek();
    int secondPeek = intStack.peek();
    int thirdPeek = intStack.peek();
    
    assertTrue("Peek of intStack wrong!", 
                                         firstPeek == 66 &&
                                         secondPeek == 66 &&
                                         thirdPeek == 66);
    
    
     //add some doubles to the doubleStack
    doubleStack.push(74.35); //shouldn't be accessible just through peek
    doubleStack.push(35.67); //shouldn't be accessible just through peek
    doubleStack.push(66.34); //if only peek is called should be the only thing returned
    
    double firstPeekD = doubleStack.peek();
    double secondPeekD = doubleStack.peek();
    double thirdPeekD = doubleStack.peek();
    
    assertTrue("Peeks of doubleStack wrong!", firstPeekD == 66.34&&
                                            secondPeekD ==66.34 &&
                                            thirdPeekD ==66.34);
    
    //assert that after the pops, the stacks are empty
    assertTrue("Stacks are of wrong size after peek!", intStack.empty()!= true && doubleStack.empty() != true);
    
  }
  
}

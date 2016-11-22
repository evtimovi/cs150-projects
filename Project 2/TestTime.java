import junit.framework.TestCase;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * A JUnit test case class to test the functionality of the Time class.
 * @author Ivan Evtimov
 */
public class TestTime extends TestCase {
  
  /**
   * A test method to test the exception handling of the constructor.
   */
  public void testConstructor1() {
    
    try{
      
      //this should cause an exception
      Time t = new Time("");
      fail("Exception went uncaught or was not thrown!");
    } catch (Exception e){
      //test succeeds
    }
    
  }
  
    
  /**
   * A second test method to test the exception handling of the constructor.
   */
  public void testConstructor2() {
    
    try{
      
      //this should cause an exception
      Time t = new Time("2:59");
      fail("Exception went uncaught or was not thrown!");
    } catch (Exception e){
      //test succeeds
    }
    
  }
  
  
  /**
   * A third test method to test the exception handling of the constructor.
   */
  public void testConstructor3() {
    
    try{
      
      //this should cause an exception
      Time t = new Time("02:69");
      fail("Exception went uncaught or was not thrown!");
    } catch (Exception e){
      //test succeeds
    }
    
  }
  
    /**
   * A fourth test method to test the exception handling of the constructor.
   */
  public void testConstructor4() {
    
    try{
      
      //this should cause an exception
      Time t = new Time("34:39");
      fail("Exception went uncaught or was not thrown!");
    } catch (Exception e){
      //test succeeds
    }
    
  }
  
    /**
   * A fifth test method to test the exception handling of the constructor.
   */
  public void testConstructor5() {
    
    try{
      
      //this shouldn't cause an exception
      Time t = new Time("01:46");
      Time t2 = new Time("13:47");
      Time t3 = new Time("15:36");
      Time t4 = new Time("20:48");
      Time t5 = new Time("23:15");
      
    } catch (Exception e){
      //test fails
      fail("Exception was thrown when there wasn't supposed to be any!");
    }
    
  }
  
  /**
   * Test for the pattern of the toString() method's return.
   */
  public void testToString(){
  
    //create various time objects
      Time t = new Time("01:46");
      Time t1 = new Time("13:07");
      Time t3 = new Time("05:36");
      Time t4 = new Time("20:08");
      Time t5 = new Time("23:15");
      
      //compile a pattern and get the matcher
      //compile the pattern
      Pattern pat = Pattern.compile( "[0-2][0-9]:[0-5][0-9]");
      
      //get the matcher for the first time
      Matcher mat0 = pat.matcher(t.toString());
      Matcher mat1 = pat.matcher( t1.toString() );
      //Matcher mat2 = pat.matcher( t2.toString() );
      Matcher mat3 = pat.matcher ( t3.toString() );
      Matcher mat4 = pat.matcher ( t4.toString() );
      Matcher mat5 = pat.matcher ( t5.toString() );
      
      //assert the pattern was matched in each case for the test
      assertTrue(t + "wrong", mat0.find());
      assertTrue( t1 + "wrong", mat1.find());
     // assertTrue( t2 + "wrong", mat2.find() );
      assertTrue( t3 + "wrong", mat3.find() );
      assertTrue( t4 + "wrong", mat4.find() );
      assertTrue ( t5 + "Wrong", mat5.find() );
      
      
      
  }
  /**
   * This is the first in a series of methods to test whether the time class
   * correctly identifies if a given time is between two other times.
   */
  public void testIsBetween1(){
    
    //create some times
    Time t1 = new Time("17:00");
    Time t2 = new Time("18:00");
    Time t3 = new Time("17:30");
    Time t4 = new Time("18:01");
    
    //now t3 should be between t1 and t2
    assertTrue("isBetween with t3 fails!", t3.isBetween(t1, t2));
    assertFalse("reversed with t3 fails", t3.isBetween(t2, t1));//the negation of the one above
    
    //t4 should be outside t1 and t2
    assertTrue("reversed with t4 fails", t4.isBetween(t2, t1));//the negation of the one below
    assertFalse("isBetween with t4 fails", t4.isBetween(t1, t2));
    
    //also, anything should be between the time perido 17:00 to 17:00
    assertTrue("t1.isBetween(t1, t1) fails", t1.isBetween(t1, t1));
    assertTrue("t2.isBetween(t1, t1) fails", t2.isBetween(t1, t1));
  }
  
  /**
   * A second test method for the functionality of the isBetween class. 
   * This one deals with times with the same hours.
   */
  public void testIsBetween2(){
  
     //create some times (all with the same hours)
    Time t1 = new Time("17:00");
    Time t2 = new Time("17:20");
    Time t3 = new Time("17:30");
    Time t4 = new Time("17:00");
    
    assertTrue("t2 between t1 and t3", t2.isBetween(t1, t3));
    assertFalse("negation of t2 between t1 and t3", t2.isBetween(t3, t1));//negation of one above
    
    assertTrue("t4 between t1 and t3", t4.isBetween(t1, t3));
    assertTrue("t4 between t3 and t1", t4.isBetween(t3, t1));
    
    
  }
  
  /**
   * A third test method for the functionality of the isBetween method.
   */
  public void testIsBetween3(){
    
    Time t1 = new Time("08:13");
    Time t2 = new Time("09:13");//same minutes as before (shouldn't matter...)
    Time t3 = new Time("08:31");//shoud be between t1 and t2
    Time t4 = new Time("09:56");//shuld be between t2 and t1
    
    //perform the tests
    assertTrue("t3 btw t1 and t2", t3.isBetween(t1, t2));
    assertFalse("t3 btw t2 and t1", t3.isBetween(t2, t1));
    assertTrue("t4 btw t2 and t1", t4.isBetween(t2, t1));
    assertFalse("t4 btw t1 and t2", t4.isBetween(t1, t2));
  }
  
  /**
   * fourth test of isBetween
   */
  public void testIsBetween4(){
    
    //random times
    Time t1 = new Time("08:46");
    Time t2 = new Time("02:19");
    Time t3 = new Time("05:17");
    Time t4 = new Time("08:46");
    
    //perform the tests
    assertFalse("t1 btw t2 and t3", t1.isBetween(t2, t3));//(they move to the next day)
    assertTrue("t2 btw t1 and t3", t2.isBetween(t1,t3));
    assertTrue("negation of t1 btw t2 and t3", t1.isBetween(t3, t2));
    assertFalse("negation of t2 between t1 and t3", t2.isBetween(t3, t1));
    
    //tests with same times
    assertTrue("same times, t4 isBetween t1 and t2", t4.isBetween(t1, t2));
    assertTrue("same times, t4 isBetween t2 and t1", t4.isBetween(t2, t1));
    assertTrue("same times, t4 isBetween t1 and t3", t4.isBetween(t1, t3));
    assertTrue("same times, t4 isBetween t3 and t1", t4.isBetween(t3, t1));
    
    
    
               
  }
}

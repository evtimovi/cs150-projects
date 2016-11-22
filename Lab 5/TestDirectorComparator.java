import junit.framework.TestCase;

/**
 * A JUnit test case class to test the functionality of the DirectorComparator.
 * @author Ivan Evtimov
 */
public class TestDirectorComparator extends TestCase {
  
  private DirectorComparator comp1;
  private Movie m1, m2, m3, m5, m6, m7;
  private Integer integerType;
  private Double doubleType;
  
  /**
   * Set up the test so that
   * m1 has the same director as m3, but different release years.
   * m1 has different director and release year and studio from m2
   * m1 has director that should come after the director of m5
   * m1 has the same director as m6, but different release years, with 
   * m6 preceding m1
   */
  public void setUp(){
  
    m1 = new Movie("Rise Of the Titans", "Chris", 1984, "StudioX");
    m2 = new Movie("Titans", "Jack", 2010, "S Productions");
    m3 = new Movie("Fall Of the Titans", "Chris", 2006, "ThirdStudio");
    m5 = new Movie("WhatAFunnyTitle", "Ahab", 1984, "Studio15");
    m6 = new Movie("Rise Of the Titans", "Chris", 1915, "StudioX");
    
    comp1 = new DirectorComparator();
    
  }
  
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that differ in all of their elements.
   */
  public void testCompare1() {
    
    int result = comp1.compare(m1,m2);
    String resultOfComparison = String.valueOf(result);
    
    //since Chris should precede Jack, the comparison result should be negative
    assertTrue("Result of comparison was: " + resultOfComparison, result < 0);
  }
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that have the same director, but different release years.
   */
  public void testCompare2() {
    
    int result = comp1.compare(m1,m5);
    String resultOfComparison = String.valueOf(result);
    
    //since 1984 should precede 2006, the comparison result should be postivie
    assertTrue("Result of comparison was: " + resultOfComparison, result > 0);
  }
  
   /**
   * A test method to confirm that the compare method
   * properly compares movies that have different directors, where
   * the first director should come in second.
   */
  public void testCompare3() {
    
    int result = comp1.compare(m1,m3);
    String resultOfComparison = String.valueOf(result);
    
    //since Ahab should precede Chris, the comparison result should be negative
    assertTrue("Result of comparison was: " + resultOfComparison, result < 0);
  }
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that have different years and same directors
   * where the first year should come in second.
   */
  public void testCompare4() {
    
    int result = comp1.compare(m1,m6);
    String resultOfComparison = String.valueOf(result);
    
    //since 1915 should precede 2006, the comparison result should be positive
    assertTrue("Result of comparison was: " + resultOfComparison, result > 0);
  }
  
  /**
   * A test method to confirm that the compare method
   * does not attempt to compare null objects.
   */
  public void testCompareNull(){
    
    try{
      //since m7 is declared, but not initiated, this should generate an exception
      comp1.compare(m1, m7);
      
      //if no exception is thrown,  fail the test
      fail("Exception not thrown!!");
    } catch (NullPointerException e){
    
      //if we come here, the method is working
    }
    
  }
  
    /**
   * A test method to confirm that the compare method
   * does not attempt to compare objects that are not of the reference type Movie.
   */
  public void testCompareIncompatibleType(){
    
    //initialize integerType and doubleType
    integerType = 76;
    doubleType = 65.2565;
    
    try{
      //since m7 is declared, but not initiated, this should generate an exception
      comp1.compare(integerType, doubleType);
      
      //if no exception is thrown,  fail the test
      fail("Exception not thrown!!");
    } catch (ClassCastException e){
    
      //if we come here, the method is working
    }
    
  }
  
  
  
}

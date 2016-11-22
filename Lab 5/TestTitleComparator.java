import junit.framework.TestCase;

/**
 * A JUnit test case class to test the functionality of the TitleComparator.
 * @author Ivan Evtimov
 */
public class TestTitleComparator extends TestCase {
  
  private TitleComparator comp1;
  private Movie m1, m2, m3, m4, m5, m6, m7;
  private Integer integerType;
  private Double doubleType;
  
  /**
   * Set up the test so that
   * m1 has the same title as m3, but different release years.
   * m1 has the same title and release year as m4, but different studio.
   * m1 has different title, director, release year and studio from m2
   * m1 has title that should precede the title of m5
   */
  public void setUp(){
  
    m1 = new Movie("Title", "Director", 1984, "Studio");
    m2 = new Movie("DifferentTitle", "DifferentDirector", 2010, "DifferentStudio");
    m3 = new Movie("Title", "ThirdDirector", 2006, "ThirdStudio");
    m4 = new Movie("Title", "FourthDirector", 1984, "FourthStudio");
    m5 = new Movie("WhatAFunnyTitle", "Director", 1984, "Studio");
    m6 = new Movie("Title", "ThirdDirector", 1915, "ThirdStudio");
    
    comp1 = new TitleComparator();
    
  }
  
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that differ in all of their elements.
   */
  public void testCompare1() {
    
    int result = comp1.compare(m1,m2);
    String resultOfComparison = String.valueOf(result);
    
    //Since the title "Title" of m1 should come after the title "DifferenTitle" of m2
    //the value returned by this method should be positive.
    assertTrue("The result of comparison was: " + resultOfComparison, result >0);
  }
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that have the same title, but different release years.
   */
  public void testCompare2() {
    
    int result = comp1.compare(m1,m3);
    String resultOfComparison = String.valueOf(result);
    
    //Since the year 1984 of m1 should come before the year 2006 of m3
    //the value returned by this method should be negative.
    assertTrue("The result of comparison was: " + resultOfComparison, result < 0);
  }
  
    /**
   * A test method to confirm that the compare method
   * properly compares movies that have the same titles and the same years,
   * but different studios
   */
  public void testCompare3() {
    
    int result = comp1.compare(m1,m4);
    String resultOfComparison = String.valueOf(result);
    
    //Since the studio "FourthStudios" of m4 should come after the studio "Studio" of m1
    //the value returned by this method should be positive.
    assertTrue("The result of comparison was: " + resultOfComparison, result > 0);
  }
  
   /**
   * A test method to confirm that the compare method
   * properly compares movies that differ in only their titles.
   * This method asserts that the title of m1 should precede the title
   * of m5 in an alphabetical ordering.
   */
  public void testCompare4() {
    
    int result = comp1.compare(m1,m5);
    String resultOfComparison = String.valueOf(result);
    
    //Since the title "Title" of m1 should come before the title "WhatAFunnyTitle" of m5
    //the value returned by this method should be negative.
    assertTrue("The result of comparison was: " + resultOfComparison, result < 0);
  }
  
  /**
   * A test method to confirm that the compare method
   * properly compares movies that have the same title, but different release years.
   * This method checks what happens when the year of the second movie precedes the year
   * of the first movie
   */
  public void testCompare5() {
    
    int result = comp1.compare(m1,m6);
    String resultOfComparison = String.valueOf(result);
    
    //Since the year 1984 of m1 should come after the year 1915 of m6
    //the value returned by this method should be positive.
    assertTrue("The result of comparison was: " + resultOfComparison, result > 0);
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

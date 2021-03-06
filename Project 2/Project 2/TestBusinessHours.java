import junit.framework.TestCase;

/**
 * A JUnit test case class for the functionality of the BusinessHours class.
 * It performs several unit tests on the helper methods and on the major methods
 * used for storing a venue's opening and closing times.
 * Please, note that the private method 
 * setHours(int, Time, Tiem) is not tested, but
 * the method setHours (String, Time, Time) is absolutely 
 * dependent on the correct functionality of setHours(int, Time, Time) and analyzeDay() and
 * it is unit-tested.
 * Finally, there is no explicit test method for the isOpen method.
 * However, the isOpen method is used extensively to confirm the functionality
 * of the public setHours methods, so it is implicitly tested.
 * 
 * @author Ivan Evtimov
 */
public class TestBusinessHours extends TestCase {
  
  /**
   * A test method for the functionality of the analyzeDay method.
   * This method first checks if analyzeDay returns the appropriate number
   * for the approproaite day of the week if the day is spelled correctly and
   * begins with a capital letter.
   */
  public void testAnalyzeDay() {
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //perform test for each of the days
    assertTrue("Monday analysis failed!", bh.analyzeDay("Monday") == 1);
    assertTrue("Tuesday analysis failed!", bh.analyzeDay("Tuesday") == 2);
    assertTrue("Wednesday analysis failed!", bh.analyzeDay("Wednesday") ==3 );
    assertTrue("Thursday analyssis failed!", bh.analyzeDay("Thursday") == 4);
    assertTrue("Friday analysis failed!", bh.analyzeDay("Friday") == 5 );
    assertTrue("Saturday analysis failed!", bh.analyzeDay("Saturday") == 6);
    assertTrue("Sunday analysis failed!", bh.analyzeDay("Sunday") == 7);
    
  }
  
    /**
   * A test method for the functionality of the analyzeDay method.
   * This method first checks if analyzeDay returns the appropriate number
   * for the approproaite day of the week if the day is spelled with random
   * capitalization (method should ignore capitalization).
   */
  public void testAnalyzeDayFunkySpelling() {
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //perform test for each of the days, where each test uses a different strange way
    //of capitalizing the day
    assertTrue("monday analysis failed!", bh.analyzeDay("monday") == 1);
    assertTrue("tUesDay analysis failed!", bh.analyzeDay("tUesDay") == 2);
    assertTrue("WEdnesday analysis failed!", bh.analyzeDay("WEdnesday") ==3 );
    assertTrue("ThursDAY analyssis failed!", bh.analyzeDay("ThursDAY") == 4);
    assertTrue("fRiday analysis failed!", bh.analyzeDay("fRiday") == 5 );
    assertTrue("saTURday analysis failed!", bh.analyzeDay("saTURday") == 6);
    assertTrue("SUNDAY analysis failed!", bh.analyzeDay("SUNDAY") == 7);
    
  }
  
  /**
   * This test method tests the analyzeDay method's response to 
   * wrong patterns.
   */
  public void testAnalyzeDayExceptions(){
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    try{
    
      //attempt analysis with shortened name
      bh.analyzeDay("Tues");
      
      //if we reach this line of code, the test should fail because exception was not thrown
      fail("Exception was not thrown at Tues");
    } catch (Exception e){
      //good
    }
  }
  
  /**
   * This test method tests  is the next in a series of methods that
   * test the analyzeDay method's response to wrong patterns.
   */
  public void testAnalyzeDayExceptions2(){
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    try{
    
      //attempt analysis with shortened name
      bh.analyzeDay("Fri");
      
      //if we reach this line of code, the test should fail because exception was not thrown
      fail("Exception was not thrown at Fri");
      
    } catch (Exception e){
      //good
    }
  }
  
  
  /**
   * This test method tests  is the next in a series of methods that
   * test the analyzeDay method's response to wrong patterns.
   */
  public void testAnalyzeDayExceptions3(){
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    try{
    
      //attempt analysis with shortened name
      bh.analyzeDay("Mon");
      
      //if we reach this line of code, the test should fail because exception was not thrown
      fail("Exception was not thrown at Mon");
      
    } catch (Exception e){
      //good
    }
  }
  
  
  /**
   * This test method tests  is the next in a series of methods that
   * test the analyzeDay method's response to wrong patterns.
   */
  public void testAnalyzeDayExceptions4(){
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    try{
    
      //attempt analysis with shortened name
      bh.analyzeDay("random string of characters");
      
      //if we reach this line of code, the test should fail because exception was not thrown
      fail("Exception was not thrown at random string of characters");
      
    } catch (Exception e){
      //good
    }
  }
  
  
  /**
   * This test method tests  is the next in a series of methods that
   * test the analyzeDay method's response to wrong patterns.
   */
  public void testAnalyzeDayExceptions5(){
    
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    try{
    
      //attempt analysis with shortened name
      bh.analyzeDay("");
      
      //if we reach this line of code, the test should fail because exception was not thrown
      fail("Exception was not thrown at empty string");
      
    } catch (Exception e){
      //good
    }
  }
  
  /**
   * This test method tests the operation of the setHours(day, time, time) method
   * in conjunction with the isOpen method.
   */
  public void testSetByDayAndIsOpen1(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setHours("Thursday", new Time("09:45"), new Time("18:00"));
    
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Wednesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Friday", "23:59"));
  }
  
    /**
   * This test method is the next one in a series that test
   * the operation of the setHours(day, time, time) method
   * in conjunction with the isOpen method.
   */
  public void testSetByDayAndIsOpen2(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setHours("Thursday", new Time("09:45"), new Time("18:00"));
    bh.setHours("Monday", new Time("01:45"), new Time("12:00"));
    bh.setHours("Wednesday", new Time("09:45"), new Time("03:00"));
    bh.setHours("Friday", new Time("23:59"), new Time("00:00"));
    bh.setHours("Saturday", new Time("00:00"), new Time("23:59"));
    
    //test for thurs
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Tuesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Sunday", "23:59"));
    
    //test for monday
    assertFalse("isOpen for Monday, 12:30", bh.isOpen("Monday", "12:30"));
    assertTrue("isOpen for Monday, 10:01", bh.isOpen("Monday", "10:01"));
    assertTrue("isOpen for Monday, 01:45", bh.isOpen("Monday", "01:45"));
    assertTrue("isOpen for Monday, 12:00", bh.isOpen("Monday", "12:00"));
    
    //test for wednesday
    assertTrue("isOpen for Wednesday, 00:00", bh.isOpen("Wednesday", "00:00"));
    assertTrue("isOpen for Wednesday, 01:30", bh.isOpen("Wednesday", "01:30"));
    assertTrue("isOpen for Wednesday, 22:30", bh.isOpen("Wednesday", "22:30"));
    assertTrue("isOpen for Wednesday, 10:30", bh.isOpen("Wednesday", "10:30"));
    
    //is it going to be open on thrsday at 02:00 though?
    //technically, it should
//    assertTrue("isOpen for Thursday 02:00", bh.isOpen("Thursday", "02:00"));
    
  }
  
  /**
   * This test method tests the operation of the setHours(String) method
   * in conjunction with the isOpen method.
   */
  public void testSetByStringAndIsOpen2(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setHours("Thursday 09:45 18:00");
    
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Wednesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Friday", "23:59"));
  }
  
    /**
   * This test method is the next one in a series that test
   * the operation of the setHours(String) method
   * in conjunction with the isOpen method.
   */
  public void testSetByStringAndIsOpen1(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setHours("Thursday 09:45 18:00");
    bh.setHours("Monday 01:45 12:00");
    bh.setHours("Wednesday 09:45 03:00");
    bh.setHours("Friday 23:59 00:00");
    bh.setHours("Saturday 00:00 23:59");
    
    //test for thurs
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Tuesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Sunday", "23:59"));
    
    //test for monday
    assertFalse("isOpen for Monday, 12:30", bh.isOpen("Monday", "12:30"));
    assertTrue("isOpen for Monday, 10:01", bh.isOpen("Monday", "10:01"));
    assertTrue("isOpen for Monday, 01:45", bh.isOpen("Monday", "01:45"));
    assertTrue("isOpen for Monday, 12:00", bh.isOpen("Monday", "12:00"));
    
    //test for wednesday
    assertTrue("isOpen for Wednesday, 00:00", bh.isOpen("Wednesday", "00:00"));
    assertTrue("isOpen for Wednesday, 01:30", bh.isOpen("Wednesday", "01:30"));
    assertTrue("isOpen for Wednesday, 22:30", bh.isOpen("Wednesday", "22:30"));
    assertTrue("isOpen for Wednesday, 10:30", bh.isOpen("Wednesday", "10:30"));
    
    //is it going to be open on thrsday at 02:00 though?
    //technically, it should
//    assertTrue("isOpen for Thursday 02:00", bh.isOpen("Thursday", "02:00"));
    
  }
  
  
  /**
   * This test method tests the operation of the setHours(String) method
   * in conjunction with the isOpen method.
   */
  public void testSetAllHoursAndIsOpen2(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setAllHours("Thursday 09:45 18:00");
    
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Wednesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Friday", "23:59"));
  }
  
    /**
   * This test method is the next one in a series that test
   * the operation of the setHours(String) method
   * in conjunction with the isOpen method.
   */
  public void testSetAllHoursAndIsOpen1(){
  
    //create an instance of the tested class
    BusinessHours bh = new BusinessHours();
    
    //set the hours
    bh.setAllHours("Thursday 09:45 18:00,Monday 01:45 12:00,Wednesday 09:45 03:00,Friday 23:59 00:00,Saturday 00:00 23:59");
    
    //test for thurs
    assertTrue("isOpen for Thurs, 12:30", bh.isOpen("Thursday", "12:30"));
    assertFalse("isOpen for Thurs, 18:01", bh.isOpen("Thursday", "18:01"));
    assertFalse("isOpen for Wednesday, 10:00", bh.isOpen("Tuesday", "10:00"));
    assertFalse("isOpen for Friday, 23:59", bh.isOpen("Sunday", "23:59"));
    
    //test for monday
    assertFalse("isOpen for Monday, 12:30", bh.isOpen("Monday", "12:30"));
    assertTrue("isOpen for Monday, 10:01", bh.isOpen("Monday", "10:01"));
    assertTrue("isOpen for Monday, 01:45", bh.isOpen("Monday", "01:45"));
    assertTrue("isOpen for Monday, 12:00", bh.isOpen("Monday", "12:00"));
    
    //test for wednesday
    assertTrue("isOpen for Wednesday, 00:00", bh.isOpen("Wednesday", "00:00"));
    assertTrue("isOpen for Wednesday, 01:30", bh.isOpen("Wednesday", "01:30"));
    assertTrue("isOpen for Wednesday, 22:30", bh.isOpen("Wednesday", "22:30"));
    assertTrue("isOpen for Wednesday, 10:30", bh.isOpen("Wednesday", "10:30"));
    
    //is it going to be open on thrsday at 02:00 though?
    //technically, it should
//    assertTrue("isOpen for Thursday 02:00", bh.isOpen("Thursday", "02:00"));
    
  }
}

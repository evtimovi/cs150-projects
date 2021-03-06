import junit.framework.TestCase;

/**
 * A JUnit test case class to test the functionality
 * of the Store class. It checks whether the store
 * can be correctly supplied, whether its needs can be correctly
 * preset and whether we can get information about how much it needs
 * from a given resource correctly.
 * 
 * @author Ivan Evtimov
 */
public class TestStore extends TestCase {
  
  /**
   * A test method for the setting of 
   * the store's needs initially. Please, note
   * that this method implicitly tests the
   * getNeedFor method, but that method is later tested explicitly.
   */
  public void testInitialSetup() {
   
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("blueberries", 12);
    walmart.setNeed("guns", 76);
    walmart.setNeed("pepsi", 34);
    walmart.setNeed("coke", 767);
    walmart.setNeed("cheap stuff", 13);
    
    //now make assertions that when we ask the store how many units
    //it needs of each of those it returns the correct number
    assertTrue("apples", walmart.getNeedFor("apples")==35);
    assertTrue("oranges", walmart.getNeedFor("oranges")==56);
    assertTrue("blueberries", walmart.getNeedFor("blueberries")==12);
    assertTrue("guns", walmart.getNeedFor("guns")==76);
    assertTrue("pepsi", walmart.getNeedFor("pepsi")==34);
    assertTrue("coke", walmart.getNeedFor("coke")==767);
    assertTrue("cheap stuff", walmart.getNeedFor("cheap stuff")==13);
    
    
  }
  
  /**
   * This test method tests both the initial set up
   * and the getNeedFor method as it inputs negative needs
   * for a resource that should indicate that the store is oversupplied
   * (the get method should return a 0).
   */
  public void testSetupAndGetForOversupply(){
    
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with negative need (an oversupply) for some resources
    walmart.setNeed("apples", -35);
    walmart.setNeed("oranges", -56);
    walmart.setNeed("blueberries", -12);
    walmart.setNeed("guns", -76);
    walmart.setNeed("pepsi", -34);
    walmart.setNeed("coke", -767);
    walmart.setNeed("cheap stuff", -13);
    
    //now make assertions that when we ask the store how many units
    //it needs of each of those it returns 0
    assertTrue("apples", walmart.getNeedFor("apples")==0);
    assertTrue("oranges", walmart.getNeedFor("oranges")==0);
    assertTrue("blueberries", walmart.getNeedFor("blueberries")==0);
    assertTrue("guns", walmart.getNeedFor("guns")==0);
    assertTrue("pepsi", walmart.getNeedFor("pepsi")==0);
    assertTrue("coke", walmart.getNeedFor("coke")==0);
    assertTrue("cheap stuff", walmart.getNeedFor("cheap stuff")==0);
 
  }
  
  /**
   * This method checks if the setNeed method
   * allows the multiple set up of needs.
   */
  public void testDuplicationInSetup(){
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for apples
    walmart.setNeed("apples", 35);
      
    //attempt to provide it with need for apples again
    assertFalse("No problem duplicated set up of needs for resources!", 
                walmart.setNeed("apples", -56));
    
    //attempt to provide it with need for another product

    assertTrue("Problem when trying to add non-duplicated need", 
                walmart.setNeed("pickles", 33));
  
    
  }
  
  /**
   * Test for the getNeedFor method
   * when called for a resource the store doesn't need.
   */
  public void testGetNeedForUnneeded(){
    
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("blueberries", 12);
    walmart.setNeed("guns", 76);
    walmart.setNeed("pepsi", 34);
    walmart.setNeed("coke", 767);
    walmart.setNeed("cheap stuff", 13);
    
    //ask for the need for other resources
    //assert getNeedFor always returns 0
    assertTrue("not returning 0: pumpkins", walmart.getNeedFor("pumpkins") == 0);
    assertTrue("not returning 0: tomatoes", walmart.getNeedFor("tomatoes") == 0);
    assertTrue("not returning 0: cucumbers", walmart.getNeedFor("cucumbers") == 0);
    assertTrue("not returning 0: vegies", walmart.getNeedFor("vegies") == 0);
    assertTrue("not returning 0: tofu", walmart.getNeedFor("tofu") == 0);
    assertTrue("not returning 0: cranberry syrup", walmart.getNeedFor("cranberry syrup") == 0);
    assertTrue("not returning 0: sour cherries", walmart.getNeedFor("sour cherries") == 0);
  }
  
  /**
   * Test the supply method in situations where
   * store is not fully stocked yet.
   */
  public void testPartialSupply(){
   
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("guns", 76);
    walmart.setNeed("cheap stuff", 1343);
    
    //perform six supplies of appples
    //assert correct need for appples after each
    
    //declare variables for use in loop
    int remainingNeed;
    int trueRemainingNeed = walmart.getNeedFor("apples");
    
    for (int i = 1; i<6; i++){
    
      //get the remaining need after supplying i*3 apples
      remainingNeed = walmart.supply("apples", i*3);
      
      trueRemainingNeed = trueRemainingNeed - i*3;//calculate the true remaining need
      
      assertTrue("apples failed at: " + i +" " + trueRemainingNeed + " " + remainingNeed,
                 remainingNeed == trueRemainingNeed );//make assertion
      
      
    }
    
    //test again for oranges
    remainingNeed = 0;
    trueRemainingNeed = walmart.getNeedFor("oranges");
    
    for (int i = 1; i<8; i++){
    
      //get the remaining need after supplying i*3 oranges
      remainingNeed = walmart.supply("oranges", i*3);
      
      trueRemainingNeed = trueRemainingNeed - i*3;//calculate the true remaining need
      
      assertTrue("oranges failed at: " + i +" " + trueRemainingNeed + " " + remainingNeed,
                 remainingNeed == trueRemainingNeed );//make assertion
      
      
    }
    
    //test again for guns
    remainingNeed = 0;
    trueRemainingNeed = walmart.getNeedFor("cheap stuff");
    
    for (int i = 1; i<13; i++){
    
      //get the remaining need after supplying i*3 oranges
      remainingNeed = walmart.supply("cheap stuff", i*3);
      
      trueRemainingNeed = trueRemainingNeed - i*3;//calculate the true remaining need
      
      assertTrue("cheap stuff failed at: " + i +" " + trueRemainingNeed + " " + remainingNeed,
                 remainingNeed == trueRemainingNeed );//make assertion
      
      
    }
    
    //test again for cheap stuff
    remainingNeed = 0;
    trueRemainingNeed = walmart.getNeedFor("guns");
    
    for (int i = 1; i<7; i++){
    
      //get the remaining need after supplying i*3 oranges
      remainingNeed = walmart.supply("guns", i*3);
      
      trueRemainingNeed = trueRemainingNeed - i*3;//calculate the true remaining need
      
      assertTrue("guns failed at: " + i +" " + trueRemainingNeed + " " + remainingNeed,
                 remainingNeed == trueRemainingNeed );//make assertion
      
      
    }
    
  }
  
  /**
   * This method attempts supply of
   * resources that the store does not need
   * and tests if the appropriate exception is thrown/
   */
  public void testUnneededSupply(){
  
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("guns", 76);
    walmart.setNeed("cheap stuff", 1343);
    
    try{
      //attempt supplying pepsi
      walmart.supply("pepsi", 13);
      fail("Exception not thrown!");
      
    } catch (NullPointerException e){
      //test succeeds
    }
    
    
  }
  
    /**
   * This method attempts supply of
   * resources that are a negative or 0 units.
   */
  public void testRidiculousSupply(){
  
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("guns", 76);
    walmart.setNeed("cheap stuff", 1343);
    
    try{
      //attempt supplying pepsi with a negative number
      walmart.supply("pepsi", -13);
      fail("Exception not thrown!");
      
    } catch (IllegalArgumentException e){ //should be an illegal argument exception
      //test succeeds
    } catch (NullPointerException e){
      fail ("Wrong type of exception thrown");//should not be thrown because check for negatives is first
    }
    
    
  }
  
   /**
   * This method attempts supply of
   * resources that are a negative or 0 units.
   */
  public void testRidiculousSupply2(){
  
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("guns", 76);
    walmart.setNeed("cheap stuff", 1343);
    
    try{
      //attempt supplying pepsi with a negative number
      walmart.supply("apples", -13);
      fail("Exception not thrown!");
      
    } catch (IllegalArgumentException e){ //should be an illegal argument exception
      //test succeeds
    } catch (NullPointerException e){
      fail ("Wrong type of exception thrown");//should not be thrown because apples are needed
    }
    
    
  }
  
  /**
   * This method attempts supply of
   * resources that are a negative or 0 units.
   */
  public void testRidiculousSupply3(){
  
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("guns", 76);
    walmart.setNeed("cheap stuff", 1343);
    
    try{
      //attempt supplying pepsi with a 0
      walmart.supply("apples", 0);
      fail("Exception not thrown!");
      
    } catch (IllegalArgumentException e){ //should be an illegal argument exception
      //test succeeds
    } catch (NullPointerException e){
      fail ("Wrong type of exception thrown");//should not be thrown because apples are needed
    }
    
    
  }
  
  /**
   * This test assures that 0 is returned in case of oversupply
   * (test both getNeedFor and supply.
   */
  public void testOversupply(){
    
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("blueberries", 12);
    walmart.setNeed("guns", 76);
    walmart.setNeed("pepsi", 34);
    walmart.setNeed("coke", 767);
    walmart.setNeed("cheap stuff", 13);
    
    //oversupply them all
    walmart.supply("apples", 42);
    walmart.supply("oranges", 76);
    walmart.supply("blueberries", 34);
    walmart.supply("guns", 760);
    walmart.supply("pepsi", 67);
    walmart.supply("coke", 1000);
    walmart.supply("cheap stuff", 130);
    
    //asert the store no longer needs any of those
    assertTrue("apples", walmart.getNeedFor("apples")==0);
    assertTrue("oranges", walmart.getNeedFor("oranges")==0);
    assertTrue("blueberries", walmart.getNeedFor("blueberries")==0);
    assertTrue("guns", walmart.getNeedFor("guns")==0);
    assertTrue("pepsi", walmart.getNeedFor("pepsi")==0);
    assertTrue("coke", walmart.getNeedFor("coke")==0);
    assertTrue("cheap stuff", walmart.getNeedFor("cheap stuff")==0);
    
    
    
  }
  
  /**
   * Method to test exact supply of stuff.
   */
   public void testExactSupply(){
    
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
    walmart.setNeed("oranges", 56);
    walmart.setNeed("blueberries", 12);
    walmart.setNeed("guns", 76);
    walmart.setNeed("pepsi", 34);
    walmart.setNeed("coke", 767);
    walmart.setNeed("cheap stuff", 13);
    
    //oversupply them all
    walmart.supply("apples", 35);
    walmart.supply("oranges", 56);
    walmart.supply("blueberries", 12);
    walmart.supply("guns", 76);
    walmart.supply("pepsi", 34);
    walmart.supply("coke", 767);
    walmart.supply("cheap stuff", 13);
    
    //asert the store no longer needs any of those
    assertTrue("apples", walmart.getNeedFor("apples")==0);
    assertTrue("oranges", walmart.getNeedFor("oranges")==0);
    assertTrue("blueberries", walmart.getNeedFor("blueberries")==0);
    assertTrue("guns", walmart.getNeedFor("guns")==0);
    assertTrue("pepsi", walmart.getNeedFor("pepsi")==0);
    assertTrue("coke", walmart.getNeedFor("coke")==0);
    assertTrue("cheap stuff", walmart.getNeedFor("cheap stuff")==0);
    
    
    
  }
   
   /**
    * Method to test incremental exact supply.
    */
   public void testExactSupplyInSteps(){
    
    //create a test store
    Store walmart = new Store(5);
    
    //provide it with the need for some resources
    walmart.setNeed("apples", 35);
   
    //supply 7 units of apples 5 times
    for (int i = 0; i<5; i++){
      walmart.supply("apples", 7);
    
    }
    
    //assert the store no longer needs apples
    assertTrue("wow", walmart.getNeedFor("apples")==0);
    
   }
  
}

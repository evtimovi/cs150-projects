import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the functionality of the truck class.
 * @author Ivan Evtimov
 */
public class TestTruck extends TestCase {

  
  /**
   * This method sets the carrying capacity of each truck as well as its mileage.
   */
  public void setUp(){
    //reset the capacity and the mileage for the setup only
    Truck.reset();
    
    //set the static fields of the truck to the appropriate values
    Truck.setMilesCap( 150 );
    Truck.setCarryingCap( 20 );
    
    //create the map with the storage requirements for each unit
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("bananas", 1);
    map.put("free stuff", 1);
    map.put("guns", 1);
    map.put("tiny bugs", 1);
    map.put("giant strawberries", 1);
    map.put("enormous caterpillars", 1);
    map.put("firearms", 1);
    map.put("wow", 1);
    map.put("happiness pills", 1);
    map.put("cheap stuff", 1);
    
    //now add some resources that require more than one unit of storage for each of its
    //units on the truck
    map.put("shampoo", 2);
    map.put("laptops", 2);
    map.put("macbooks", 2);
    map.put("tanks", 3);
    map.put("books", 4);
    map.put("tables", 5);
    map.put("furniture", 10);
    map.put("couches", 20);
    map.put("sofas", 30);//there should be no way to add that
    
    //set the truck's capacity needs
    Truck.setCapacityNeeds( map );

  }
  
  /**
   * A test method for multiple calls of the setMilesCap
   * static method (it should throw an exception upon second call).
   * Tests with uniform storage requirements for units.
   */
  public void testMultipleSetMilesCap() {
    
    try{
      //attempt modification of miles cap 
      Truck.setMilesCap(32);
      //exception should be thrown
      fail("Exception not thrown!");
    } catch (IllegalStateException e){
      //test succeeds
    }
                                              
  }
  
    /**
   * A test method for multiple calls of the setMilesCap
   * static method (it should throw an exception upon second call).
   * Tests with uniform storage requirements for units.
   */
  public void testMultipleSetCarryingCap() {
    
    try{
      //attempt modification of miles cap
      Truck.setCarryingCap( 32 );
      //exception should be thrown
      fail("Exception not thrown!");
    } catch (IllegalStateException e){
      //test succeeds
      
    }
                                              
  }
  
  /**
   * Tests travel() method and getRemainingMiles() methods for no special cases.
   */
  public void testTravel(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //helper, start with 150
    int trueRemainingMiles = 150;
    
    //keep advancing the truck by 10 miles until it reaches its mileage
    //check for the appropriate number of remaining miles at each iteration
    while (bigTruck.getRemainingMiles() > 0 ){
      
      //indent the trueRemainingMiles
      trueRemainingMiles = trueRemainingMiles - 10;
      
      //travel ten miles and assume it was possible
      assertTrue("impossible travel with trueRemainingmiles= " + trueRemainingMiles, bigTruck.travel(10));
      
      //assume the remaining miles equal the true remaining miles
      assertTrue("mismatch between remaining miles " + bigTruck.getRemainingMiles() 
                   + " and true " + trueRemainingMiles,
                 bigTruck.getRemainingMiles() == trueRemainingMiles);
      
      
      
    }
  }
  
   /**
   * Tests travel() method and getRemainingMiles() methods for 
   * the special case whenn travelling would result in overshooting the mileage.
   */
  public void testTravel2(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //helper, start with 150
    int trueRemainingMiles = 150;
    
    //keep advancing the truck by 10 miles until it reaches its mileage
    while (bigTruck.getRemainingMiles() > 10 ){
      
      //indent the trueRemainingMiles and make the truck go
      trueRemainingMiles = trueRemainingMiles - 10;
      bigTruck.travel(10);
    }
    
    //now, the bigTruck should have 10 remaining miles
    assertTrue("remaining miles wrong", bigTruck.getRemainingMiles()==10);
    
    //it should also not be able to travel 11 miles
    assertFalse("wow", bigTruck.travel(11));
    
    //finally, it shouldn't have moved after the previous command
    assertTrue("remaining miles wrong after command", bigTruck.getRemainingMiles()== 10);
    
    
  }
  
  /**
   * Tests the getFree, getLoadOf, and most importantly, load methods.
   * No special case tested.
   * Tests with uniform storage requirements for units.
   */
  public void testLoading(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load some resources onto it
    bigTruck.loadResource("bananas", 5);
    bigTruck.loadResource("free stuff", 13);
    
    //the truck should have 2 free slots
    assertEquals("free slots problem", bigTruck.getFree(), 2);
    
    //the truck should have 13 units of bananas
    assertEquals("bananas, problem!", bigTruck.getLoadOf("bananas"), 5);
    
    //the truck should have 5 units of free stuff
    assertEquals("free stuff, problem!", bigTruck.getLoadOf("free stuff"), 13);
    
  }
  
    
  /**
   * Tests the getFree, getLoadOf, and most importantly, load methods.
   * Special case of overload tested.
   * Tests with uniform storage requirements for units.
   */
  public void testLoading2(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load some resources onto it
    bigTruck.loadResource("bananas", 5);
    bigTruck.loadResource("free stuff", 13);
    
   //attempt to add 8 units of bananas
    try{
      bigTruck.loadResource("bananas", 8);
      fail ("Exception was not thrown!");
    } catch (UnsupportedOperationException e){
      //exceptions work
    }
    
    
  }
  
  /**
   * Tests multiple additions of same resource.
   * Tests with uniform storage requirements for units.
   */
  public void testLoading3(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load bananas in multiple steps, each time asserting the number of returned bananas is 
    //just as many as were added
    assertTrue("banana first load", 5 == bigTruck.loadResource("bananas", 5));
    assertTrue("banana getLoadOf, firstload", 5 == bigTruck.getLoadOf("bananas"));
    
    assertTrue("banana second load", 7 == bigTruck.loadResource("bananas", 2));
    assertTrue("banana getLoadOf, second", 7 == bigTruck.getLoadOf("bananas"));
    
    assertTrue("banana third load", 20 == bigTruck.loadResource("bananas", 13));
    assertTrue("banana getLoadOf, third", 20 == bigTruck.getLoadOf("bananas"));
               
    
  }
  
   /**
   * Tests multiple additions of same resource
   *  with addition of another resource in between.
   * Tests with uniform storage requirements for units.
   */
  public void testLoading4(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //add bananas, then firearms, then bananas again
    assertTrue("banana first load", 5 == bigTruck.loadResource("bananas", 5));
    assertTrue("banana getLoadOf, firstload", 5 == bigTruck.getLoadOf("bananas"));
    assertEquals("wow", bigTruck.getFree(),15);
    
    assertTrue("firearms first load", 6 == bigTruck.loadResource("firearms", 6));
    assertTrue("firearms getLoadOf, first", 6 == bigTruck.getLoadOf("firearms"));
    assertEquals("wow2", bigTruck.getFree(),9);
    
    assertTrue("banana second load", 7 == bigTruck.loadResource("bananas", 2));
    assertTrue("banana getLoadOf, second", 7 == bigTruck.getLoadOf("bananas"));
    assertEquals("wow3", bigTruck.getFree(),7);
    
    //attempt overload
    try{
      bigTruck.loadResource("guns, guns, guns!", 8);
      
      fail("Exception?????");
      
    } catch (UnsupportedOperationException e){
      ///success!!!
    }
  }
  
   /**
   * Tests multiple additions of same resource
   *  with addition of another resource in between.
   * Tests with uniform storage requirements for units.
   */
  public void testLoading5(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //attempt to get load of stuff that isn't there
    assertEquals(bigTruck.getLoadOf("brownies"), 0);
    assertEquals(bigTruck.getLoadOf("bananas"), 0);
    assertEquals(bigTruck.getLoadOf("firearms"), 0);
  }
  
  /**
   * Method to test the isFull method.
   */
  public void testIsFull(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load 19 units of stuff
    //at each point, assert the truck is still not full
    for( int i = 0; i < 19; i++){
      bigTruck.loadResource("wow", 1);
      assertFalse("truck full? really?", bigTruck.isFull());
    }
    
    //now add the extra 20th unit that fills it up
    bigTruck.loadResource("happiness pills", 1);
    assertTrue("not full??", bigTruck.isFull());
    
  }
  
  /**
   * Test the unload method of the truck.
   * No special case yet.
   * Tests with uniform storage requirements for units.
   */
  public void testUnload(){
  
    //create a truck and fill it up with random stuff
    Truck miniTruck = new Truck();
    
    miniTruck.loadResource("cheap stuff", 3);
    miniTruck.loadResource("giant strawberries", 4);
    miniTruck.loadResource("enormous caterpillars", 10);
    miniTruck.loadResource("tiny bugs", 3);
    
    //unload some of each of the stuff
    assertEquals("unloading the cheap stuff!", miniTruck.unload("cheap stuff", 2), 2);
    assertEquals("cheap stuff rem", miniTruck.getLoadOf("cheap stuff"), 1);
    assertEquals("total rem", miniTruck.getFree(), 2);
    
    assertEquals("unloading the giant strawberries!", miniTruck.unload("giant strawberries", 2), 2);
    assertEquals("cheap stuff rem", miniTruck.getLoadOf("giant strawberries"), 2);
    assertEquals("total rem", miniTruck.getFree(), 4);
    
    
    assertEquals("unloading the enormous caterpillars! eww...", miniTruck.unload("enormous caterpillars", 6), 6);
    assertEquals("cheap stuff rem", miniTruck.getLoadOf("enormous caterpillars"), 4);
    assertEquals("total rem", miniTruck.getFree(), 10);
    
    
    assertEquals("unloading the tiny bugs.", miniTruck.unload("tiny bugs", 3), 3);
    assertEquals("cheap stuff rem", miniTruck.getLoadOf("tiny bugs"), 0);
    assertEquals("total rem", miniTruck.getFree(), 13);
    
    
  }
  
  /**
   * Test the unload method of the truck.
   * Special case for unloading more than the truck has
   * Tests with uniform storage requirements for units.
   */
  public void testUnload2(){
  
    //create a truck and fill it up with random stuff
    Truck miniTruck = new Truck();
    
    miniTruck.loadResource("cheap stuff", 3);
    miniTruck.loadResource("giant strawberries", 4);
    miniTruck.loadResource("enormous caterpillars", 10);
    miniTruck.loadResource("tiny bugs", 3);
    
    //attempt to unload more of each resource
    assertEquals("cheap stuff", 0, miniTruck.unload("cheap stuff",4));
    assertEquals("cheap stuff rem", 3, miniTruck.getLoadOf("cheap stuff"));
    assertEquals("free spots", 0, miniTruck.getFree());
    
    
    assertEquals("giant strawberries", 0, miniTruck.unload("giant strawberries", 10));
    assertEquals("giant strawberries rem", 4, miniTruck.getLoadOf("giant strawberries"));
    assertEquals("free spots", 0, miniTruck.getFree());
    
    assertEquals("enormous caterpillars", 0, miniTruck.unload("enormous caterpillars", 13));
    assertEquals("enormous caterpillars rem", 10, miniTruck.getLoadOf("enormous caterpillars"));
    assertEquals("free spots", 0, miniTruck.getFree());
    
     assertEquals("tiny bugs", 0, miniTruck.unload("tiny bugs", 13));
    assertEquals("riny bugs rem", 3, miniTruck.getLoadOf("tiny bugs"));
    assertEquals("free spots", 0, miniTruck.getFree());
  }
  
    /**
   * Test the unload method of the truck.
   * Special case for unloading resources the truck doesn't have.
   * Tests with uniform storage requirements for units.
   */
  public void testUnload3(){
  
    //create a truck and fill it up with random stuff
    Truck miniTruck = new Truck();
    
    miniTruck.loadResource("cheap stuff", 3);
    miniTruck.loadResource("giant strawberries", 4);
    miniTruck.loadResource("enormous caterpillars", 10);
    miniTruck.loadResource("tiny bugs", 3);
    
    assertEquals("unload bananas", 0, miniTruck.unload("bananas", 1));
    assertEquals("free spots", 0, miniTruck.getFree());
    
    assertEquals("unload guns", 0, miniTruck.unload("guns", 1));
    assertEquals("free spots", 0, miniTruck.getFree());
    
    assertEquals("unload bugs", 0, miniTruck.unload("bugs", 5));
    assertEquals("free spots", 0, miniTruck.getFree());
    
  }
  
  /**
   * Test method for the counters and unique numbers.
   * Creates a 1000 trucks and for each,
   * checks if its number matches the total number of trucks created.
   */
  public void testCounters(){
    
    //declare a temp truck var
    Truck tempTruck;
    
    //create a 1000 trucks
    for (int i = 1; i < 1001; i++){
    
      tempTruck = new Truck();
      
      //test the unique numbers
      assertEquals("failed at iteration: " + i, Truck.getCounter(), tempTruck.getTruckNum()); 
      
      //test the size
      assertEquals("size failsed at: " + i, i, Truck.getCounter());
      
    }
  }
  
  /**
   * Tests overload with one resource
   * (a resource that never should be able to be loaded on the truck).
   */
  public void testOverloadWithSingle(){
  
    //create a test truck
    Truck fancyTruck = new Truck();
    
    //attempt to put sofas, which should cause the truck to overload
    try{
      fancyTruck.loadResource("sofas",1);
      fail("Exception not thrown!");
    } catch (UnsupportedOperationException e){
      //good!
    }
  }
  
  /**
   * Tests attempt of loading units that are not
   * specified in the map for capacity needs.
   */
  public void testUnSpecfified(){
  
    //create a test truck
    Truck fancyTruck = new Truck();
    
    try{
      fancyTruck.loadResource("abc", 3);
      fail("Exception not thrown!");
      
    } catch (UnsupportedOperationException e){
      //good
    }
  }
  
  /**
   * Tests loading without special case of resources that take up
   * more than one unit of storage for each of its units.
   */
  public void testLoadingWeighted(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load some resources onto it
    //each of those should take up two units of storage for each of its own units
    bigTruck.loadResource("shampoo", 5);
    bigTruck.loadResource("laptops", 3);
    
    //the truck should have 2 free slots
    assertEquals("free slots problem", bigTruck.getFree(), 4);
    
    //the truck should have 5 units of shampoo
    assertEquals("bananas, problem!", bigTruck.getLoadOf("shampoo"), 5);
    
    //the truck should have 3 units of laptops
    assertEquals("free stuff, problem!", bigTruck.getLoadOf("laptops"), 3);
  }
   
  /**
   * Tests loading in special case of overload for resources that take up
   * more than one unit of storage for each of its units.
   */
  public void testLoadingWeighted2(){
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load some resources onto it
    bigTruck.loadResource("macbooks", 5);
    bigTruck.loadResource("tanks", 3);
    
   //attempt to add 1 more units of tanks, should be impossible
    try{
      bigTruck.loadResource("tanks", 1);
      fail ("Exception was not thrown!");
    } catch (UnsupportedOperationException e){
      //exceptions work
    }
  }
  
   /**
   * Tests multiple additions of same resource for weighted.
   *
   */
  public void testLoadingWeighted3(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //load bananas in multiple steps, each time asserting the number of returned bananas is 
    //just as many as were added
    assertEquals("books first load", 2, bigTruck.loadResource("books", 2));
    assertEquals("books getLoadOf, firstload", 2, bigTruck.getLoadOf("books"));
    assertEquals("free space?", 12, bigTruck.getFree());
    
    assertEquals("books second load", 3, bigTruck.loadResource("books", 1));
    assertEquals("books getLoadOf, firstload", 3, bigTruck.getLoadOf("books"));
    assertEquals("free space?", 8, bigTruck.getFree());      
    
    assertTrue("macbooks first load", 3 == bigTruck.loadResource("macbooks", 3));
    assertTrue("books getLoadOf, firstload", 3 == bigTruck.getLoadOf("macbooks"));
    assertEquals("free space?", 2, bigTruck.getFree());     

  }
  
   
   /**
   * Tests multiple additions of same resource
   *  with addition of another resource in between.
   * Tests with uniform storage requirements for units.
   */
  public void testLoadingWeighted4(){
  
    //create a new truck
    Truck bigTruck = new Truck();
    
    //add bananas, then firearms, then bananas again
    assertTrue("furniture first load", 1 == bigTruck.loadResource("furniture", 1));
    assertTrue("banana getLoadOf, firstload", 1 == bigTruck.getLoadOf("furniture"));
    assertEquals("wow", bigTruck.getFree(),10);
    
    assertTrue("books first load", 1 == bigTruck.loadResource("books", 1));
    assertTrue("firearms getLoadOf, first", 1 == bigTruck.getLoadOf("books"));
    assertEquals("wow2", bigTruck.getFree(),6);
    
    assertTrue("books second load", 2 == bigTruck.loadResource("books", 1));
    assertTrue("books getLoadOf, second", 2 == bigTruck.getLoadOf("books"));
    assertEquals("wow3", bigTruck.getFree(),2);
    
    //attempt overload
    try{
      bigTruck.loadResource("guns, guns, guns!", 8);
      
      fail("Exception?????");
      
    } catch (UnsupportedOperationException e){
      ///success!!!
    }
  }
  
  /**
   * Tests unload of weighted stuff.
   */
  public void testUnloadWeighted(){
  
    //create a new truck
    Truck funkyTruck = new Truck();
    
    //add couches
    assertEquals("adding couches", 1, funkyTruck.loadResource("couches", 1));
    assertEquals("free space?", 0, funkyTruck.getFree());
    assertEquals("load of couches (1)", 1, funkyTruck.getLoadOf("couches"));
    
    //unload
    assertEquals("unloading couches", 1, funkyTruck.unload("couches", 1));
    assertEquals("free space?", 20, funkyTruck.getFree());
    assertEquals("load of couches", 0, funkyTruck.getLoadOf("couhces"));
  }
}

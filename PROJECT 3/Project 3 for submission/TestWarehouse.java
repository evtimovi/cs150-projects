import junit.framework.TestCase;

/**
 * A JUnit test case class for the functionality of the warehouse class.
 * @author Ivan Evtimov
 */
public class TestWarehouse extends TestCase {
  
  /**
   * A test method for the functionality of the sendTruck method.
   * It also checks the get-methods.
   */
  public void testSend() {
    
    //create a new warehouse with 20 trucks
    Warehouse w = new Warehouse(20, 5);
    
    //assert remaining trucks 20, out 0, returned 0
    assertEquals("rem", 20, w.getRemainingTrucks());
    assertEquals("out", 0, w.getTrucksOut());
    assertEquals("ret", 0, w.getTrucksReturned());
    
    //send a truck
    Truck sentTruck = w.sendTruck();
    
    //make the assertions again
    assertEquals("rem", 19, w.getRemainingTrucks());
    assertEquals("out", 1, w.getTrucksOut());
    assertEquals("ret", 0, w.getTrucksReturned());
    assertTrue("null", sentTruck != null );
    
  
  }
  
  /**
   * A test method for the functionality of the receiveTruck method.
   * It also checks the get-methods.
   */
  public void testReceive() {
    
    //create a new warehouse with 20 trucks
    Warehouse w = new Warehouse(20, 5);
    
    //assert remaining trucks 20, out 0, returned 0
    assertEquals("rem", 20, w.getRemainingTrucks());
    assertEquals("out", 0, w.getTrucksOut());
    assertEquals("ret", 0, w.getTrucksReturned());
    
    //send a truck
    Truck sentTruck = w.sendTruck();
    
    //make the assertions again
    assertEquals("rem", 19, w.getRemainingTrucks());
    assertEquals("out", 1, w.getTrucksOut());
    assertEquals("ret", 0, w.getTrucksReturned());
    assertTrue("null", sentTruck != null );
    
    //receive a truck
    assertEquals("received", 1, w.receiveTruck());
    assertEquals("out", 0, w.getTrucksOut());
    assertEquals("ret", 1, w.getTrucksReturned());
    assertEquals("rem", 19, w.getRemainingTrucks());
    
  
  }
  
}

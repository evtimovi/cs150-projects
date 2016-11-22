/**
 * This class is designed to store information about
 * a warehouse, such as how many trucks it has,
 * how many have gone out and how many have returned.
 * 
 * @author Ivan Evtimov
 */
public class Warehouse extends DistributionNetworkMember{
  
  /**
   * This field holds the number of trucks the warehouse initially has.
   */
  private int initialNumOfTrucks;
  
  /**
   * Holds the number of trucks that have gone out.
   */
  private int trucksOut;
  
  /**
   * Holds the number of trucks that have returned.
   */
  private int trucksReturned;
  
  /**
   * The constructor initializes the warehouse with the given number of trucks.
   * It sets the key that will be used for the mapping to the specified value.
   * @param trucks the number of trucks the warehouse has initially
   * @param key the key used for mapping this warehouse
   */
  public Warehouse( int trucks, int key ){
    
    //set the initial number of trucks
    this.initialNumOfTrucks = trucks;
    
    //no trucks have gone out yet
    trucksOut = 0;
    
    //no trucks have returned yet
    trucksReturned = 0;
    
    //set the key, using the parent's setKey method
    this.setKey( key );
  }
  
  /**
   * Accessor method to determine how many trucks have left this warehouse.
   * @return the number of trucks out on delivery that have not returned yet
   */
  public int getTrucksOut(){ return trucksOut; }
  
  /**
   * Accessor method to determine how many trucks have returned from delivery.
   * @return trucks returned from delivery
   */
  public int getTrucksReturned() { return trucksReturned; }
  
  /**
   * This method returns how many truckss the warehouse still has at its disposal.
   * It performs a simple calculation in that it subtracts all trucks that have
   * gone out or returned fromt the initial number of trucks.
   * @return the number of trucks that can be still used for supply
   */
  public int getRemainingTrucks(){ return initialNumOfTrucks - trucksOut - trucksReturned; }
  
  /**
   * This method "sends out" a new truck.
   * It creates a new instance of the truck class
   * and returns it to go supply the network.
   * HOWEVER, at the time of the creation, this truck is empty. 
   * It is loaded outside of the warehouse.
   * This implementation is necessary to
   * 1) allow freedom in loading the truck (loading it through the warehouse
   * limits the number of resources that can be efficiently "loaded" on the truck);
   * 2) keep track of how many trucks the warehouse has sent out (too difficult
   * without Warehouse class).
   * If there are no more trucks to send out, it returns a null.
   * @return a new instance of the Truck class if there are still trucks in the warehouse
   */
  public Truck sendTruck( ){
    
    if( this.getRemainingTrucks() == 0 ){
      return null;//no remaining trucks
    }
    
    //there are more trucks to send out, so do so
    
    //update the counter
    trucksOut++;
    
    //create a new truck and tell it where it started from
    Truck t = new Truck();
    t.startedFrom( this.key );
    
    //return the new truck
    return t;
    
  }
  
  /**
   * This method receives a new truck and thus
   * updates the number of returned trucks.
   * @return the new number of returned trucks
   */
  public int receiveTruck(){
    //calculate how many trucks have now been received
    trucksReturned = trucksReturned + 1; //one came in
    trucksOut--;//the same one came back
    
    return trucksReturned;//return how many trucks have made it back
  }
  
  
}
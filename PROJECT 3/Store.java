import java.util.*;

/**
 * This method represents another member of the distribution network, namely a store.
 * It has attributes that contain information about the store's unsatisfied needs.
 * 
 * The class extends DistributionNetworkMember because the stores are members
 * of the distribution network, but have different functionality than warehouses.
 * They all share the x and y positions.
 * 
 * @author Ivan Evtimov
 */
public class Store extends DistributionNetworkMember{
  
  /**
   * This container stores the needs for the store.
   * The key is the resource that the store needs and the
   * value mapped to each resource is the units of this resource
   * that the store needs.
   * Since we need quick access for easy modification,
   * this container is a HashMap with String (resource) as a key
   * and an Integer (units needed) as a value.
   */
  private HashMap<String, Integer> needsMap;
  
  
  /**
   * The constructor initializes the class's internal container.
   * and sets the key that will be used to map this store to the
   * appropriate vertex in the graph.
   * @param key the key for mapping
   */
  public Store( int key ){
    needsMap = new HashMap<String, Integer>();//initialize the map that will store the needs
    
    this.setKey( key );//use parent class's method
    
    
  }
  
 
  /**
   * This method adds another need of the store.
   * The method is designed to add needs only initially. It should not be used
   * to update the store's needs after a successful delivery. If a need for a resource is
   * already specified, it won't be modified and the method will return false.
   * @param resource the resource that the store will have a need of
   * @param units the units of that resource that the store will need
   * @return true if this is the first time specifying a need for a given resource, false
   * if the need for a resource has already been specified
   */
  public boolean setNeed( String resource, int units ){
  
    //check if the need for a resource has already been set
    Integer currNeed = needsMap.get( resource ); //get the value mapped to that resource
    
    if( currNeed != null ){ //perform the check
      //something is already mapped, so return false
      return false;
    }
    
    //the check has been passed
    //put this new need in the map
    needsMap.put( resource, units );
    
    return true;//to indicate success
  }
  
  /**
   * This method is designed to update the needs of 
   * a store based on how much has been delivered to the store already.
   * Please, note that this method allows an oversupply of resources. In
   * such a case, the need for a given resource will be set to a negative value.
   * @param resource the resource we are supplying
   * @param units the units of that resource we are supplying
   * @return the new need for that resource of the store
   * @throws NullPointerException if the store doesn't need this resource
   * @throws IllegalArgumentException if attempts to supply negative or 0 units
   */
  public int supply( String resource, int units ) throws NullPointerException, IllegalArgumentException{
  
    //first check if the units being supplied are 0 or negative
    if( units <= 0 ){
      throw new IllegalArgumentException("The process of supply involves adding elements to a store.");
    }
    
    //get the current need for that resource (before the update)
    Integer currNeed = needsMap.get( resource );
    
    //if nothing is mapped to that resource, throw an error
    if( currNeed == null ){
      throw new NullPointerException("The store doesn't need this resource!");
    }
    
    //calculate the new need after the supply
    Integer newNeed = currNeed - units;
    
    //put that new need in the map and return the updated need
    needsMap.put( resource, newNeed );
    
    return needsMap.get( resource );
    
  }
  
  /**
   * This method is designed to provide information 
   * for the need for a certain resource of the store.
   * @param resource the resource we want to find out about
   * @return the need for the resource if there is any (a positive number); 
   * 0 if the store is oversupplied, supplied exactly as much as it needed, or
   * does not need that resource at all
   */
  public int getNeedFor( String resource ){
  
    //get the need for a resource
    Integer currNeed = needsMap.get( resource );
    
    //check if there is no need for that resource
    if( currNeed == null ){
      return 0;//the store doesn't need anything for that resource
    }
    
    //check if oversupplied or supplied enough
    if( currNeed <= 0 ){
      return 0;//the store needs nothing more from that resource
    }
    
    //if this line of code is reached,
    //the store still needs some more units of the given resource
    return currNeed;
  }
  
  /**
   * This method is designed to return the resources that the store needs.
   * @return a set with the resources that the store needs
   */
  public Set<String> getNeededResources(){
    return needsMap.keySet();//the key set represents the resources needed
  }
  
  /**
   * Designed to check if it needs anything more.
   */
  public boolean hasMoreNeeds(){
  
    int next;
    for( Iterator it = needsMap.values().iterator(); it.hasNext(); ){
      next = (Integer) it.next();
      if( next != 0 ){
       // System.out.println("Store 150: "+ next);
        return true;
      }
    }
    return false;
  }
  
}
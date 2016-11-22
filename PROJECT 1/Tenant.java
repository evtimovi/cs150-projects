/**
 * This class inherits all functionality from its parent class.
 * It implements no methods and simply calls the parent constructor in order to
 * avoid compiler errors.
 * @author Ivan Evtimov
 */
public class Tenant extends Person{
  
  /**
   * Constructor simply calls the parent class's constructor.
   * @param floorNumber the floor the Visitor is created on
   * @param timeToArrive the time to arrive the person will be assigned
   */
  public Tenant(int floorNumber, int timeToArrive){
    super(floorNumber, timeToArrive);
  }
  
  public static void main(String[] args){
  
    Tenant t = new Tenant(5, 13);
    
    System.out.println(t);
    System.out.println("ID: "+ t.getID());
   
    Visitor v = new Visitor(5, 64);
    System.out.println("Visitor: "+v);
    System.out.println("Visitor's ID: " +v.getID());
    
  }
}
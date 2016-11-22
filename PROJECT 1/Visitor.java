/**
 * This class inherits all functionality from its parent class.
 * It implements no methods and simply calls the parent constructor in order to
 * avoid compiler errors.
 */
public class Visitor extends Person{
  
  /**
   * Constructor simply calls the parent class's constructor.
   * @param floorNumber the floor the Visitor is created on
   * @param timeToArrive the time to arrive the person will be assigned
   */
  public Visitor(int floorNumber, int timeToArrive){
    super(floorNumber, timeToArrive);
  }
}
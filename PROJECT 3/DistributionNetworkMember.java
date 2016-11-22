/**
 * This abstract class is designed to provide the possibility
 * for a store or a warehouse to be stored in the same field.
 * 
 * It also defines common functionality of the classes, 
 * such as ints that store the x and y locations of this node.
 * 
 * @author Ivan Evtimov
 */
public class DistributionNetworkMember{
  
  /**
   * This field holds the key that is used to associate
   * this member of the distribution network with a vertex in the graph.
   */
  protected int key;
  
  /**
   * This field is this member's x position in the coordinate system
   * as specified in location.txt
   * Protected because child classes will need access.
   */
  protected int xPos;
  
  /**
   * This field is this member's y position in the coordinate system
   * as specified in location.txt
   * Protected because child classes will need access.
   */
  protected int yPos;
 
  /**
   * Accessor method to get this distribution network member's 
   * x location.
   * @return this's x position
   */
  public int getX(){ return xPos; }
  
 /**
   * Accessor method to get this distribution network member's 
   * u location.
   * @return this's y position
   */
  public int getY(){ return yPos; }
  
  /**
   * Modifier method to set this distribution network member's
   * x position.
   * @param xPos the x position of this
   */
  public void setX( int xPos ){
    this.xPos = xPos; 
  }
  
  
  /**
   * Modifier method to set this distribution network member's
   * y position.
   * @param yPos the y position of this
   */
  public void setY( int yPos ){
    this.yPos = yPos; 
  }
  
  /**
   * Accessor method to access the distribution network member's 
   * key used for mapping in the graph.
   * @return the key that is used to map this object in the map
   */
  public int getKey(){ return key; }
  
  
  /**
   * Modifier method to set this distribution network member's
   * key that will be used to map it in the graph.
   * Protected since only child classes should be able to modify that.
   * @param newKey the new key that we are setting
   */
  protected void setKey( int newKey ){
    this.key = newKey; 
  }
}
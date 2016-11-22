import junit.framework.TestCase;
import java.io.*;
import java.util.*;

/**
 * A JUnit test case class for the database.
 * It uses a testDatabase.txt as input.
 * 
 * @author Ivan Evtimov
 */
public class TestDatabase extends TestCase {
  
  /**
   * This is going to be the test instance that is going to be used 
   * throughout the test.
   * 
   */
  private Database testDatabase;
  
  /**
   * This field holds the restaurants from the test database file.
   */
  private ArrayList<Restaurant> restaurantsArr;
  
  /**
   * The setUp method creates the test database based on the testDatabase.txt input file.
   * 
   */
  public void setUp(){
    //create the database
    testDatabase = new Database();
    
    //fill it up
    testDatabase.createDatabaseFromFile( "testDatabase.txt" );
    
    /**
     * Create a bunch of restaurants independently and based on the same input database.
     */
    Scanner sc = null;
    try{
      //create a scanner
       sc = new Scanner( new BufferedReader( new FileReader( "testDatabase.txt" )));
       
       //use the double new-line character delimiter
       sc.useDelimiter("\n\n");
       
       
       //create an array list to hold those restaurants
       restaurantsArr = new ArrayList<Restaurant>();
       
       //scan the restaurants in
       while( sc.hasNext() ){
       
         //create a new restaurant based on the block of text and add it to the array.
         restaurantsArr.add( new Restaurant( sc.next() ) );
       }
       
    } 
    
    catch (IOException e) {
      System.out.println( e );
    }
    
    finally{
    
      //always close the scanner if it exists
      if( sc != null ){
        sc.close();
      }
    }
  }
  
  /**
   * A test method for the addNewRestaurant() method.
   */
  public void testGetAll() {
    
    //get all the restaurants from the database
    ArrayList<Restaurant> allFromDatabase = testDatabase.getAll();
    
    
    //now remove all of the restaurants that are in the independently created array
    allFromDatabase.removeAll( restaurantsArr );
    
    //size of the allFromDatabase array should be 0 if they all match up (all have been removed)
    assertTrue( "getAll wrong " + restaurantsArr.size() , allFromDatabase.size() == 0);
  }
  
}

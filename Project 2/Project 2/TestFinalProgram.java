import junit.framework.TestCase;

/**
 * A JUnit test case class for the functionality of the FinalProgram class.
 * Please, note that this program uses the testDatabase.txt file, which has a limited
 * amount of entries, but presents the various situations that can happen in the restaurants.txt
 * file.
 * @author Ivan Evtimov
 */
public class TestFinalProgram extends TestCase {
  
  /**
   * This variable is the FinalProgram instance that will be used throughout the tests.
   * The database is created based on the testDatabase.txt file.
   */
  FinalProgram testFP = new FinalProgram( "testDatabase.txt" );
  
  /**
   * A test method for the functionality of the search method.
   */
  public void testX() {
  }
  
}

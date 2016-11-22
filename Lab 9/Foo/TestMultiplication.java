import junit.framework.TestCase;

/**
 * A JUnit test case class to test the functions of the Multiplication class.
 */
public class TestMultiplication extends TestCase {
  
  /**
   * This filed will hold the instance of the tested class.
   */
  Multiplication a;
  
  /**
   * The set up method just generates an instance of the Addition class.
   */
  public void setUp(){
  
    a = new Multiplication();
  }
  
  /**
   * A test method for multiply.
   */
  public void testMultiply() {
    
    //declare variables for the multiplication
    int num1 = 2;
    int num2 = 3;    
    String firstString = num1 + "*" + num2;
        
    int num3 = 45;
    int num4 = 87;    
    String secondString = num3 + "*" + num4;
    
    
    int num5 = 132;
    int num6 = 98632;    
    String thirdString = num5 + "*" + num6;
    
    int num7 = 5353;
    int num8 = 1119;
    int num9 = 1425521;
    int num10 = 1;    
    String fourthString = num7 + "*" + num8 + "*" + num9 + "*" + num10;
    
    //perform the multiplications
    String firstAdd = a.multiply(firstString);
    String secondAdd = a.multiply(secondString);
    String thirdAdd = a.multiply(thirdString);
    String fourthAdd = a.multiply(fourthString);
    
    //perform real multiplyition
    int result1 = num1 * num2;
    int result2 = num3 * num4;
    int result3 = num5 * num6;
    int result4 = num7 * num8 * num9 * num10;
    
    //convert the results to strings
    String result1String = String.valueOf(result1);
    String result2String = String.valueOf(result2);
    String result3String = String.valueOf(result3);
    String result4String = String.valueOf(result4);
    
    //perform the testing
    assertTrue("1. Addition result: " + firstAdd + "\nActual result: " + result1String, 
               firstAdd.equals(result1String));
    
    assertTrue("2. Addition result: " + secondAdd + "\nActual result: " + result2String, 
               secondAdd.equals(result2String));
    
    assertTrue("3. Addition result: " + thirdAdd + "\nActual result: " + result3String, 
               thirdAdd.equals(result3String));
    
    assertTrue("4. Addition result: " + fourthAdd + "\nActual result: " + result4String, 
               fourthAdd.equals(result4String));
    
  }
  
}

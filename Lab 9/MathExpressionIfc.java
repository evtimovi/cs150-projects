/**
 * This interface provides the definition of the functionalities of the classes
 * that will perform the calculations. 
 * Each class should be able to detect what function to perform,
 * convert the input string into a substring, 
 * and compute the result of the contained math expression.
 * 
 * @author Ivan Evtimov
 */
public interface MathExpressionIfc{
  
  /**
   * This method should be able to detect if the operation that needs to be
   * performed is addition, subtraction, multiplication, or division.
   * It might also detect if there are parantheses in the input string.
   */
  public AbstractMathExpression detectInput();
  
  /**
   * This method should be able to split the input string
   * into appropriate subexpressions.
   */
  public void splitInput();
  
  /**
   * This method performs the operation on the contained math expression and returns the result.
   */
  public int calculate(String inputString);
}
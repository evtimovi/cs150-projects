import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a calculator class. It doesn't use the crap described in the lab assignment.
 */
public class Multiplication extends AbstractMathExpression{
 
 
  /**
   * This method performs recursive multiplication in a tree-like manner.
   */
  public String multiply(String inputString){
    
    /**
     * Store the input string into the class's instance variable.
     */
    this.inputString = inputString;
        
   
    //split the input into a current expression to be processed here (a number and an operator)
    //and a remaining expression
    splitInput();
    
//    //if the remaining expression is just a number (no operator), we are ready to evaluate
//    if( Pattern.matches("\\d+", rightSide) ){
//
//      //get the values of hte two expressions
//      Integer leftNum = Integer.parseInt(leftSide);
//      Integer rightNum = Integer.parseInt(rightSide);
//      
//      //sum them (perform addition)
//      int sumResult = leftNum * rightNum;
//      
//      //return the output as a string
//      return String.valueOf(sumResult);
//
//    } else {
//      
//      //pass down for calculation
//      int rightNum = Integer.parseInt(calculate(rightSide));
//      
//      //since the subexpression has an operator, pass it down the tree
//      int product = Integer.parseInt(leftSide) * rightNum;
//            
//      return String.valueOf(product);
//      
//    }
    
    Integer leftNum = Integer.parseInt(leftSide);
    
    int result = leftNum * Integer.parseInt(calculate(rightSide));
    return String.valueOf(result);
  }
}
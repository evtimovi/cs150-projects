import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class partially implements the MathExpression Interface.
 */
public class AbstractMathExpression {
 

  /**
   * This field stores the left-hand side of 
   * the operation (a number).
   */
  protected String leftSide;
  
  /**
   * This field stores the right-hand side of the operation
   * (a number or another operation).
   */
  protected String rightSide;
  
  /**
   * This field holds the operator itself:
   * +, -, \, or *
   */
  protected char operator;
  
  /**
   * This field holds the inputString that is used
   * to extract the math expression from.
   */
  protected String inputString;

  
  /**
   * This performs operations on the input string,
   * splitting it into a string to be processed at the current level
   * of the tree and a substring to be passed down
   * for further processing.
   */
  public void splitInput(){
   
    //create temp variables for the current expression
    //and the expression that is to be passed down the tree
    String currentExpr;
    String remainingExpr = "";
    
    //compile a pattern
    Pattern pat = Pattern.compile("(\\d+[\\Q+\\E|\\Q-\\E|\\Q*\\E|\\Q/\\E]?)");
    
    //match the result to it
    Matcher result = pat.matcher(inputString);
   
    //find a match
    result.find();
      
    //get the first group (this should be the expressing we need)
    currentExpr = result.group();
    
    System.out.println("Current expression from split: " + currentExpr);
    
    //get the other groups and put them into the remainingExpr var
    //that will be passed for further processing down the tree
    while (result.find()){
    
      remainingExpr = remainingExpr + result.group();
    }
    
    //store the number only in the left hand side
    leftSide = currentExpr.replaceAll("[\\Q+\\E|\\Q*\\E|\\Q-\\E|\\Q/\\E]?","");
    
    //store the operator
    operator = currentExpr.charAt(currentExpr.length()-1);
    
    //store the remaining expression in the right-hand side
    rightSide = remainingExpr;
    
    System.out.println("Left side: " + leftSide + "\n" + "operator: " + operator +
                       "\n" + "right side: " + rightSide);
   
  }
  
  /**
   * This method checks the operator and calls the appropriate class for it.
   */
  public String calculate(String inputString){
    
    this.inputString = inputString;
    
    if( Pattern.matches("\\d+", inputString) ){
      return inputString;
    }
    
    splitInput();
  
    String op = String.valueOf(operator);
      
    if (op.equals("+")){
      //if the operator is plus, create and instance of the addition class and start the addition
      Addition a = new Addition();
      return a.add(inputString);
      
    } else if (op.equals("*")){
      
      //if the operator is the multiplication op, create and instance of the multiplication class and start the m
      Multiplication m = new Multiplication();
      return m.multiply(inputString);
    }
    
    return "";
  }
  
}
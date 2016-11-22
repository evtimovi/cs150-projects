import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a calculator class. It doesn't use the crap described in the lab assignment.
 */
public class Calculator {
 
  /**
   * This field holds the current expression on which we will
   * perform operations at this level of the tree.
   */
  protected String currentExpr = null;
  
  /**
   * This field holds the remaining expression
   * after the current expression has been removed.
   * It will be passed down the tree for further processing.
   */
  protected String remainingExpr = "";
  
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
  private void splitInput(){
   
    //compile a pattern
    Pattern pat = Pattern.compile("(\\d[\\Q+\\E|\\Q-\\E|\\Q*\\E|\\Q/\\E]?)");
    
    //match the result to it
    Matcher result = pat.matcher(inputString);
   
    //find a match
    result.find();
      
    //get the first group (this should be the expressing we need)
    currentExpr = result.group();
      
    //get the other groups and put them into the remainingExpr var
    //that will be passed for further processing down the tree
    while (result.find()){
    
      remainingExpr = remainingExpr + result.group();
    }
    
    System.out.println("Current: " + currentExpr);
    
    System.out.println("Remaining: " + remainingExpr);
  }
  
  /**
   * This method performs recursive addition in a tree-like manner.
   */
  private String add(String inputString){
    
    Calculator tempCalc = new Calculator();
    
    splitInput();
    
    String leftExpr = currentExpr.substring(0,currentExpr.length()-2);
    
    String rightExpr = remainingExpr;
    
    Integer rightNum;
    Integer leftNum;
    
    //if the right hand side is just a number
    if( Pattern.matches("\\d+", rightExpr) ){
    
      //then just add
     
      leftNum = Integer.parseInt(leftExpr);
      rightNum = Integer.parseInt(rightExpr);
      
      int sumResult = leftNum + rightNum;
      
      return String.valueOf(sumResult);

    } else {
    
      //if the right expression has an operator, pass down the tree
      rightNum = Integer.parseInt( add(remainingExpr));
      
    }
    
  
  
  }
  

  
  
}
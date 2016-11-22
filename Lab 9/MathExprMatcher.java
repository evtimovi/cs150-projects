import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

public class MathExprMatcher{

  private static String currentExpr = null;
  private static String remainingExpr = "";
  
  public static void parseExpr(String inputString){
    
        
    Pattern pat = Pattern.compile("(\\d[\\Q+\\E|\\Q-\\E|\\Q*\\E|\\Q/\\E]?)");
    
    Matcher result = pat.matcher(inputString);
    
 
    
    while(result.find()){
    
      System.out.println(result.group());
    }
    
    
 
  }
  
  public static void splitExpr(String inputString){
  
      Pattern pat = Pattern.compile("(\\d[\\Q+\\E|\\Q-\\E|\\Q*\\E|\\Q/\\E]?)");
    
    Matcher result = pat.matcher(inputString);

    
    result.find();
      
    currentExpr = result.group();
      
    while (result.find()){
    
      remainingExpr = remainingExpr + result.group();
    }
    
    System.out.println("Current: " + currentExpr);
    
    System.out.println("Remaining: " + remainingExpr);
  }
  
  public static void detectInput(){
  
    String addRegEx = "\\d+\\Q+\\E";
    String subtractRegEx = "\\d+\\Q-\\E";
    String mulRegEx = "\\d+\\Q*\\E";
    String divRegEx = "\\d+\\Q/\\E";
    
    if (Pattern.matches (addRegEx, currentExpr)){
      System.out.println("Add");
    } else if (Pattern.matches (subtractRegEx, currentExpr)){
      System.out.println("Subtract");
    } else if (Pattern.matches (mulRegEx, currentExpr)){
      System.out.println("Multiply");
    } else if (Pattern.matches (divRegEx, currentExpr)){
      System.out.println("Divide");
    }
  }
 
  public static void main(String[] args){
    
    MathExprMatcher.splitExpr("2-5*2/6");
    MathExprMatcher.detectInput();
  }
}

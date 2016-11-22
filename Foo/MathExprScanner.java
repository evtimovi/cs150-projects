import java.util.*;
import java.util.regex.*;

public class MathExprScanner{

  public static void parseExpr(String input){
  
    Scanner inputScanner = new Scanner(input);
    
    inputScanner.findInLine("\\d+[+|-|*|\\]?");
    
    MatchResult result = inputScanner.match();
    
    for (int i = 1; i < result.groupCount()+1; i++){
      System.out.println(result.group(i));
    }
    
    inputScanner.close();
  }
  
  public static void main(String[] args){
  
    MathExprScanner.parseExpr("4+3-6/7*9");
  }
}
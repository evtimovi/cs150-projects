public class Addition extends AbstractMathExpression{
  
  public int calculate(String inputString){
  
    this.inputString = inputString;
    splitInput();
    
    AbstractMathExpression temp = detectInput();
    //check the pattern of the current expression
    //if it has no operation at the end, we have reached the end of the tree
    if (temp == null){
      
      //get the value of the string
      Integer rightSide = String.valueOf(inputString);
      
      //sum
      
    }
  }
}
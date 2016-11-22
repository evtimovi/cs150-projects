public class CalculatorForPointlessApproximations{

  public static double F13(double tValue){
    
    return Math.exp(Math.sqrt(tValue));
  
  }
  
  public static void main(String[] args){
  
    System.out.println(F13(.5));
  }

}
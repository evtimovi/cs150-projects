  import java.util.*;
  import java.io.*;
  
  
    
/**
 * This method is designed to evaluate the performance
 * of the search and update methods with different queries.
 * 
 * @author Ivan Evtimov
 */
public class Analysis{

  /**
   * This field holds the FinalProgram that we are evaluating
   */
  private FinalProgram fp;
  
  /**
   * The constructor initializes the FinalProgram with the restaurants.txt file.
   * 
   */
  public Analysis(){
    //initialize the program to create the txt file
    fp = new FinalProgram("restaurants.txt");
  }
  
  /**
   * This method times the performance for search on restaurants different restaurants.
   * @param inputStringForSearch the input string with the user parameters separated
   * by semicolons
   */
  public long timeSearch( String inputStringForSearch ){
 
    
    //time the beginning
    long startTime = System.nanoTime();
    
    //perform the search
    ArrayList<Restaurant> searchOutput = fp.search( inputStringForSearch );
    
    //time the end
    long endTime = System.nanoTime();
    
    //calculate how long it took
    long totalTime = endTime - startTime;
    
    //output appropriate values
    
    if( searchOutput != null){
      System.out.println(searchOutput.size() + "\t" + totalTime);
    } else {
      System.out.println( "0\t" + totalTime); 
    }
    //return how long it took
    return totalTime;
    
  }
  
  
  /**
   * The main method reads in the parameters from a file where we have a list of
   * user input parameters.
   */
  public static void main ( String[] args ){
    
    //create an instance of this class
    Analysis a = new Analysis();
    
    //get the file with the parameters
    String fileWithParametersForSearch = args[0];
    
    //declare the reader and the scanner, so that they can be closed in finally if somethign goes wrong
    BufferedReader br = null;
    
    Scanner sc = null;
    
    try{
      
      //create teh file reader and the corresponding scanner
      br = new BufferedReader( new FileReader( fileWithParametersForSearch ));
      sc = new Scanner( br );
      
       
      //print out what search we are performing
      System.out.println("Search with different number of matches//////// ");
      System.out.println("Matches: \t" + "Time for search: ");
      
      //the parameters will be entered on separate lines
      while ( sc.hasNextLine() ){
      
        //time the search fo each set of parameters
        a.timeSearch( sc.nextLine() );
      }
    }
    catch (IOException e){
      e.printStackTrace();
    }
    finally{
      
      //always close the input stream
      if( br != null ){
      
        try{
          br.close();
        } catch (IOException e){
          System.out.println("Reader was not closed due to exception: " + e);
        }
      }
      
      //also, close the scanner
      if (sc != null ){
        sc.close();
      }
    }
  }
}
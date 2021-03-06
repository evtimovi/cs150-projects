import java.util.*;
import java.io.*;

/**
 * This class reads in a text file, counts the words
 * in it (the occurences of each word) and outputs that data.
 * 
 * @author Ivan Evtimov
 */
public class WordCounter{
  
  /**
   * This is the database that holds all of the words counted.
   * 
   */
  WordCount wc;
  
  /**
   * The constructor initializes the class's internal database.
   * 
   */
  public WordCounter(){
  
    wc = new WordCount();
  }
  
  /**
   * This method takes care of the reading of the input text file.
   * @param inputFilePath the path to the input file we are reading
   */
  public void readFile (String inputFilePath){
    
    //the scanner or one of the readers might throw an exception
    try{
      
      //create a "file" from the input path
      File inputFile = new File( inputFilePath );
      
      //create an unbuffered reader based on that file
      FileReader fr = new FileReader( inputFile );
      
      //create a buffered reader based on that reader
      BufferedReader br = new BufferedReader( fr );
      
      //create a scanner based on all of that
      Scanner sc = new Scanner ( br );
    
      //begin the actual database creation
      while ( sc.hasNext() ){
      
        //get the next word
        String nextWord = sc.next();
        
        //check if it is present in the wordcount database
        if ( wc.present (nextWord) ){
        
          //if it is, increment its count
          wc.incrementCount( nextWord );
          
        } else {
        
          //it is not present, add it
          wc.add( nextWord );
        }
      }
      
      //close the input stream
      fr.close();
      br.close();
      sc.close();
      
    } catch (Exception e){
      
      System.out.println("Something went wrong: " + e);
    
    } 
//    finally {
//      //in all cases, close the scanner and the readers if they exist
//      if ( fr != null ){
//        fr.close();
//      }
//      
//      if ( br != null ){
//        br.close();
//      }
//      
//      if ( sc != null ){
//        sc.close();
//      }
//    }

  }
  
  /**
   * The method returns the WordCount database (used for debugging).
   * @return wordCount databse of words and their occurences
   */
  public WordCount getData(){ return wc; }
  
  /**
   * This method writes the data in the word count object
   * @outputFilePath the path to the output file
   */
  public void writeFile( String outputFilePath ){
    
    //writers might throw exceptions
    try {
    
      //create an output stream
      FileWriter fw = new FileWriter( outputFilePath );
      BufferedWriter bw = new BufferedWriter( fw );
      
      //get the words array from the WordCount database
      ArrayList<String> wordsArr = wc.getWords();
      
      //declare a variable for the count of the current word
      int currentWordCount = 0;
      
      //go through that array
      for ( String currentWord : wordsArr ){
        
        //get the current word's count
        currentWordCount = wc.getCount( currentWord );
        
        bw.write( currentWord + "\t" + currentWordCount );
        bw.newLine();
        
      }
      
      //close the writers
      bw.close();
      fw.close();
      
    
    } catch (Exception e){
    
      System.out.println("Exception happened! ");
      e.printStackTrace();
    }
    
    
  
  }
  
  /**
   * The main method takes in two arguments.
   * The first one is the inputFile's path.
   * The second one is the outputFile's path.
   */
  public static void main( String[] args ){
    
    //create an instance of the class
    WordCounter wc = new WordCounter();
    
    //read 
    wc.readFile( args[0] );
    
    //write
    wc.writeFile ( args[1] );
    
  }
  
  
}
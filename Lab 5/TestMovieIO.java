import junit.framework.TestCase;
import java.io.*;
import java.util.*;


/**
 * A JUnit test case class to test the functionality of the 
 * Movie Input/Output class.
 * @author Ivan Evtimov
 */
public class TestMovieIO extends TestCase {
  
  /**
   * The field of this class is just a variable for
   * the tested class.
   */
  private MovieIO movieIOTest;
  private MovieIO movieIOTest2;
  
  /**
   * The set up method initializes the needed variables.
   */
  public void setUp(){
    movieIOTest = new MovieIO();
    movieIOTest2 = new MovieIO();
  }
  
  /**
   * A test method to test the functionality of the read method.
   * This one just gives the method a file with the correct pattern and
   * expects it to read in and return true.
   */
  public void testRead() {
    
    
   boolean result = movieIOTest.readMovies("inputDatabase.txt");
    
   assertTrue("Something went wrong when reading a file with the correct pattern",
              result);  
    
  }
  
  /**
   * A test method to test the functionality of the read method.
   * This one gives the method a file with the wrong pattern
   * and expects the method to "catch" the exception
   * and return false.
   */
  public void testReadEmpty(){
    
   boolean result = movieIOTest2.readMovies("emptyFile.txt");
    
   assertTrue("Something went wrong when reading an empty file.",
              result==false);  
  }
  
   /**
   * A test method to test the functionality of the read method.
   * This one gives the method a file with two blank lines after
   * the first movie. Please, note that it is impossible 
   * to test whether the title and the director have been switched
   * since implementing a method that checks if the first string 
   * is really a title and the next one a director requires much more than this lab.
   */
  public void testReadWrongPattern(){
  
    boolean result = movieIOTest2.readMovies("wrongPattern.txt");
    
   assertTrue("Something went wrong when reading an empty file.",
              result==false);  
    
  }
  
  /**
   * This test method tests the writing of the method writeByTitle.
   * Please, note that this test just creates an empty file
   * since we have not added anything to the tree.
   */
  public void testWriteByTitle(){
        boolean resultWrite1 = movieIOTest.writeByTitle("outputOfTest_correct.txt");
        assertTrue("ERROR!!!!", resultWrite1);
  }
  
   /**
   * This test method tests the writing of the method writeByDirector.
   * Please, note that this test just creates an empty file
   * since we have not added anything to the tree.
   */
  public void testWriteByDirector(){
        boolean resultWrite1 = movieIOTest.writeByDirector("outputOfTest2_correct.txt");
        assertTrue("ERROR!!!!", resultWrite1);
  }
  
    /**
   * This test method tests the writing of the method writeByDirector.
   * This time, the correct input has been read in.
   * I scan the file back in and check if it has the correct pattern.
   * If it does not, some exception can be expected to be thrown, causing the test
   * to fail.
   */
  public void testWriteByDirector2(){
    
    movieIOTest.readMovies("inputDatabase.txt");
    boolean resultWrite1 = movieIOTest.writeByDirector("outputOfTest3.txt");
    
    //the test should fail here if something goes wrong during the writing
    assertTrue("Error during writing!", resultWrite1);
    
    try{
    
      //set up for rescanning the outputed file
      File resultOfWrite = new File("outputOfTest3.txt");
      Scanner testScanner = new Scanner(resultOfWrite);
      
     //create variables for the the inputs
      String title, director, studio;
      int year;
      
      //the way the output is structured, we first have an empty line
      //so read that one in first
      testScanner.nextLine();
      
      while(testScanner.hasNextLine()){
      
        //read the data according to the pattern we know 
        //if, for instance, the year is not where it is supposed to be,
        //then an exception will be thrown causing the test to fail.
        title = testScanner.nextLine();
        director = testScanner.nextLine();
        year = Integer.parseInt(testScanner.nextLine()); //parse the String that comes in to an int
        studio = testScanner.nextLine();
       //System.out.println(studio);
        
        if(testScanner.hasNextLine()){
        
          testScanner.nextLine();
        }
      }
      
      
    }catch (Exception e){
      //if an exception is caught, fail the test
      fail("The following exception was thrown, causing the test to fail: " + e);
    }
  }
  
    /**
   * This test method tests the writing of the method writeByDirector.
   * This time, the correct input has been read in.
   * I scan the file back in and check if it has the correct pattern.
   * If it does not, some exception can be expected to be thrown, causing the test
   * to fail.
   */
  public void testWriteByTitle2(){
    
    movieIOTest.readMovies("inputDatabase.txt");
    boolean resultWrite1 = movieIOTest.writeByTitle("outputOfTest3.txt");
    
    //the test should fail here if something goes wrong during the writing
    assertTrue("Error during writing!", resultWrite1);
    
    try{
    
      //set up for rescanning the outputed file
      File resultOfWrite = new File("outputOfTest3.txt");
      Scanner testScanner = new Scanner(resultOfWrite);
      
     //create variables for the the inputs
      String title, director, studio;
      int year;
      
      //the way the output is structured, we first have an empty line
      //so read that one in first
      testScanner.nextLine();
      
      while(testScanner.hasNextLine()){
      
        //read the data according to the pattern we know 
        //if, for instance, the year is not where it is supposed to be,
        //then an exception will be thrown causing the test to fail.
        title = testScanner.nextLine();
        director = testScanner.nextLine();
        year = Integer.parseInt(testScanner.nextLine()); //parse the String that comes in to an int
        studio = testScanner.nextLine();
       //System.out.println(studio);
        
        if(testScanner.hasNextLine()){
        
          testScanner.nextLine();
        }
      }
      
      
    }catch (Exception e){
      //if an exception is caught, fail the test
      fail("The following exception was thrown, causing the test to fail: " + e);
    }
  }
  
  
}

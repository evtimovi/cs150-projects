import java.io.*;
import java.util.*;


/**
 * This class runs the program.
 * It takes in the input data file as a parameter.
 * Then, it reads the data from that file
 * and stores the data in the two tree set containers
 * that implement the TitleComparator and DirectorComparator.
 * 
 * Finally, it outputs the data stored in the containers to two new files.
 * In one of them, the movies are ordered by Title, Director and then Release Year.
 * In another one, the movies are ordered by Director and Release Year.
 * 
 * @author Ivan Evtimov
 */
public class MovieIO{
  
  /**
   * This field is the tree set container that implements
   * the TitleComparator.
   */
  private TitleComparatorTreeSet titleContainer;
  
  /**
   * This field is the tree set container that implements
   * the DirectorContainer.
   */
  private DirectorComparatorTreeSet directorContainer;
  
  /**
   * The constructor method initializes the containers and the file readers.
   *
   *
   * @param outputFileDirectorLocation this is the location of the output file that will have the director ordering
   * This will be received by the main method as an argument.
   */
  public MovieIO(){
  
    //initialize our containers
    titleContainer = new TitleComparatorTreeSet();
    directorContainer = new DirectorComparatorTreeSet();
    
    //initialize the scanner
    
  }
  
  /**
   * This method reads the file that has been passed as an input and stores its data in the two containers. 
   * @param inputFileLocation this is the location of the input file that will be passed as the first argument
   */
  public boolean readMovies(String inputFileLocation){
    
    //try and catch the exception in case the input file is not found
    try{
      
      //create the new file that we can read from based on the location that was passed
      File incomingFile = new File(inputFileLocation);
      
      //create a scanner based on that file
      Scanner inputScanner = new Scanner(incomingFile);
     
      //if the file is empty or starts with an empty string, return false
      //to indicate failure of the reading and print out an error message.
      if (inputScanner.next()==null){
        System.out.println("Empty file or wrong pattern.");
        
        return false;
        
      }else{
        
      //reset the scanner
      inputScanner.reset();
      
      //create variables for the the inputs
      String title, director, studio;
      int year;
      
      //create a tempMovie variable that will temporarily hold the movie before adding it to the containers
      Movie tempMovie;
      
      //read the information
      while(inputScanner.hasNextLine()){
      
        //read the data according to the pattern we know (debugger statements have been commented out)
        title = inputScanner.nextLine();
      System.out.println(title);
        director = inputScanner.nextLine();
       System.out.println(director);
        year = Integer.parseInt(inputScanner.nextLine()); //parse the String that comes in to an int
       System.out.println(year);
        studio = inputScanner.nextLine();
       System.out.println(studio);
        
        if(inputScanner.hasNextLine()){
          //read the empty line
          inputScanner.nextLine();
        }
        
        //create a Movie object
        tempMovie = new Movie(title, director, year, studio);
        
        //add the movie to both containers
        titleContainer.addMovie(tempMovie);
        directorContainer.addMovie(tempMovie);
                      
      }
      
      //close the scanner
      inputScanner.close();
      
     //if all of the adding was successful, return true
      return true;
      }
      
    } catch (FileNotFoundException e){
    
      //print out an error message
      System.out.println("Input File has not been found. Please check command line arguments.");
      
      //return false to indicate the adding was not successful
      return false;
      
    } catch (NoSuchElementException e){
    
      //print out an error message
      System.out.println("Pattern does not match or other problem with input file.");
      
      //return false to indicate the adding was not successful
      return false;
      
    } catch (Exception e){
      //print out an error message
      System.out.println("Something else went wrong. Here is the exception message: " + e);

      //return false to indicate the adding was not successful
      return false;
    
    }
    
  }
  
  /**
   * This method writes to an output file the movies in a sorted by title order.
   * @param outputFileTitleLocation this is the location of the output file that will have the title ordering
   * This will be received by the main method as an argument.
   */
  public boolean writeByTitle( String outputFileTitleLocation){
  
    try{
      //create a FileWriter and a BufferedWriter
      FileWriter outputFileWriter = new FileWriter (outputFileTitleLocation);
      BufferedWriter bw = new BufferedWriter(outputFileWriter);
      
      //get the iterator for the tree set that sorts by title, etc.
      Iterator titleIterator = titleContainer.iterator();
      
      //string with the output to be written
      String outputToBeWritten = "\n";
      
      //temp movie variable to hold whatever we get from the tree set container
      Movie tempMovie;
      
      while(titleIterator.hasNext()){
        
        tempMovie = (Movie) titleIterator.next();
        
        //save the provided output to a String variable
        outputToBeWritten = outputToBeWritten + tempMovie.toString() + "\n";
      }
      
      //once we are done reading from the container, write to the file the collected output
      bw.write(outputToBeWritten);
      
      //close the BufferedWriter
      bw.close();
    
      //return true to say that the operation has been successful
      return true;
      
    } catch (IOException e){
    
      System.out.println("Error during output. " + e);
      
      return false;
    }
  
    
  }
  
  /**
   * This method writes to an output file the movies in a sorted by director order.
   * @param outputFileDirectorLocation this is the location of the output file that will have the title ordering
   * This will be received by the main method as an argument.
   */
  public boolean writeByDirector(String outputFileDirectorLocation){
  
    try{
      //create a FileWriter and a BufferedWriter
      FileWriter outputFileWriter = new FileWriter (outputFileDirectorLocation);
      BufferedWriter bw = new BufferedWriter(outputFileWriter);
      
      //get the iterator for the tree set that sorts by title, etc.
      Iterator titleIterator = directorContainer.iterator();
      
      //string with the output to be written
      String outputToBeWritten = "\n";
      
      //temp movie variable to hold whatever we get from the tree set container
      Movie tempMovie;
      
      while(titleIterator.hasNext()){
        
        tempMovie = (Movie) titleIterator.next();
        
        //save the provided output to a String variable
        outputToBeWritten = outputToBeWritten + tempMovie.toString() + "\n";
      }
      
      //once we are done reading from the container, write to the file the collected output
      bw.write(outputToBeWritten);
      
      //close the BufferedWriter
      bw.close();
    
      //return true to say that the operation has been successful
      return true;
      
    } catch (IOException e){
    
      System.out.println("Error during output. " + e);
      
      return false;
    } catch (Exception e){
    
      System.out.println("UNKNOWN Error during output. " + e);
      
      return false;
    }
  
    
  }
  
  
  
  public static void main(String[] args){
    MovieIO movieIOTest = new MovieIO();
  
    boolean resultRead = movieIOTest.readMovies(args[0]);
    
   // boolean resultWrite1 = movieIOTest.writeByTitle(args[1]);
    
   // System.out.println(resultWrite1);

   // boolean resultWrite2 = movieIOTest.writeByDirector(args[2]);
    
    //System.out.println(resultWrite2);
    
//    MovieIO movieIOTest2=new MovieIO();
//    
//    boolean result = movieIOTest2.readMovies("emptyFile.txt");
//    System.out.println("/////Result: " + result);
  }
  
  
  
  
  
     
}
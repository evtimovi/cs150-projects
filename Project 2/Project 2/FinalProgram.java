import java.util.*;
import java.io.*;


/**
 * This class provides the final console program that simulates the functions of 
 * YELP. It allows queries through the command line and outputs the desired results.
 * If the queries are wrong, the program outputs the appropriate error message.
 * 
 * @author Ivan Evtimov
 */
public class FinalProgram{

  /**
   * This field is the database that stores all the data.
   */
  private Database data;
  
  /**
   * This field is the scanner for the user's input.
   */
  private Scanner userInputScanner;
  
  /**
   * This keeps track of whether we have exited and closed the scanner
   * 
   */
  private boolean closed;
  
  /**
   * The constructor of the program initializes the data field and creates the database
   * from the specified file (a file path as a string).
   * If an exception for the format of the input database is thrown, the
   * program will terminate there and print out an error message.
   * @param inputFilePath the path to the restaurants.txt file
   */
  public FinalProgram( String inputFilePath ){
    
    try{
      //create the database
      data = new Database();
      data.createDatabaseFromFile ( inputFilePath );
      
      //initialize the input scanner
      userInputScanner = new Scanner ( System.in );
      
      //open the scanner according to the variable
      closed = false;
    }
    catch (IllegalStateException e){
    
      System.out.println("Exception in database creation! FIX INPUT FILE!!!!!!!!!! " + e);
      
      this.close();
     
    }
  }
  
  /**
   * This method is designed to ask the user for a city, category, cost and time
   * and perform the search based on the user's input.
   * @param inputString the list of user parameters in a string, separated by a semicolong
   * @return the output of the search in the database.
   */
  public ArrayList<Restaurant> search( String inputString ){
    
    //create a scanner to split the input
    Scanner inputSc = new Scanner( inputString );
    
    //set the delimiter to be the semicolong
    inputSc.useDelimiter(";");
    
    //first one should be city
    String city = inputSc.next();
    
    //then categories
    String categories = inputSc.next();
    
    //then cost
    String cost = inputSc.next();
    
    //then day
    String day = inputSc.next();
    
    //then time
    String time = inputSc.next();
    
    //now, check if there is more than one category
    if ( categories.contains(",") ){ //if there is a comma, there is more than one category
    
      //create an array list to hold the categories
      ArrayList<String> categoriesArr = new ArrayList<String>();
      
      //create a scanner for the categories string
      Scanner sc = new Scanner ( categories );
      
      //use the comma as a delimiter
      sc.useDelimiter (",");
      
      //scan all categories into the array lsit
      while (sc.hasNext()){
        categoriesArr.add( sc.next() );
      }
      
      //System.out.println("83 Categories in array passed for search: " + categoriesArr);
      
      //call the appropriate search
      ArrayList<Restaurant> searchOutputArr = data.search( city, categoriesArr, cost, day, time );
      
      return searchOutputArr;

    }
    
    else { //only one category
      
      //call the appropriate search
      ArrayList<Restaurant> searchOutputArr = data.search( city, categories, cost, day, time );
      
      return searchOutputArr;
    }
  }
  
  /**
   * This method is designed to print out the result of the search
   * given the array that the search method in the Database class returns.
   * @param searchOutput the output of the search method (an arrayList)
   */
  public void printSearchResult( ArrayList<Restaurant> searchOutput ){
    
    //create a TreeMap that will enforce ordering by rank
    TreeMap<Double, ArrayList<Restaurant>> orderedRest = new TreeMap<Double, ArrayList<Restaurant>>();
    
    //create a temp variable for ther rank
    Double tempRank;
    
    //go through the restaurants in the array and put them in a treemap with rank = key
    for ( Restaurant curr : searchOutput ){
    
      tempRank = curr.getRank();
      
      //check if the tree map contains something mapped to that rank
      if ( orderedRest.containsKey ( tempRank )){
        
        //get the appropriate container and put the restaurant in there
        orderedRest.get( tempRank ).add( curr );
      } 
          
      else { //if the key is not present
        
        //create a new ArrayList of restaurants
        ArrayList<Restaurant> newArr = new ArrayList<Restaurant>();
        
        //add the curr to it
        newArr.add ( curr );
        
        //put that in the map
        orderedRest.put( tempRank, newArr );
          
      }
      
    }
    
    //get the keys in a set (descending as we want the highest rank first)
    Set<Double> keySet = orderedRest.descendingKeySet();
    
    //declare a temporary array list
    ArrayList<Restaurant> tempArr;
    
    //iterate through that set
    for (Iterator it = keySet.iterator(); it.hasNext(); ){
    
      //go through each array list and print out everything in it
      //get the arrayList mapped to that key
      tempArr = orderedRest.get( (Double) it.next() );
      
      //go through the array list
      for ( Restaurant curr : tempArr ){
        
        //print it out
        System.out.println(curr + "\n");
      }
    }
    
  }
  
  /**
   * This method adds a review from the user's input.
   * Again, this method is designed to be used in conjunction with the
   * scanInForReview() method, which returns a string with the user's parametres
   * separated by semicolons.
   * @param inputString the input string of the user's parameters separated by a semicolon
   */
  public void review( String inputString ){
    
     //create a scanner to split the input
    Scanner inputSc = new Scanner( inputString );
    
    //set the delimiter to be the semicolong
    inputSc.useDelimiter(";");
    
    //first one should be city
    String name = inputSc.next();
    
    //then categories
    String city = inputSc.next();
    
    //then cost
    String score = inputSc.next();
   
    
    //get the double of the score
    Double newRank = Double.parseDouble( score );
    
    //review and get the review result
    ArrayList<Restaurant> reviewResult = data.addReview ( name, city, newRank );
    
    //go through that array list and print out what's needed
    for ( Restaurant curr : reviewResult ){
      
      
      System.out.println("Restaurant reviewed: \n");
      
      //name
      System.out.println("name: " + curr.getName() );
      
      //city
      System.out.println( "city: " +  curr.getCity() );
      
      //updated score
      System.out.println( "new score: " +  curr.getRank() );
      
      //number of reviewers
      System.out.println( "reviewers: " + curr.getNumOfReviewers() );
      
      //empty line separator
      System.out.println();
    }
    
    
  }
  
  /**
   * This method scans in the user input for review
   * @return the string with the user input, separare parametrs separed by semicolong
   */
  public String scanInForReview(){

    //get the name
    System.out.println("Please specify name: ");
    String name = userInputScanner.nextLine();
    
    //get the city
    System.out.println("Please, specify city: ");
    String city = userInputScanner.nextLine();
    
    //get the cost
    System.out.println("Enter new score (0-5): ");
    String score = userInputScanner.nextLine();
    
    //build the final string
    String finalString = name + ";" + city  + ";" + score;
    
    return finalString;
  }
  
  /**
   * This method handles the add command based on the user's parameters.
   * It is designed to be used in conjunction with the scanInForAdd method,
   * which returns the user's parameters separated by semicolons.
   * @param inputString the user's parameters for the add, separated by semicolons
   */
  public void add( String inputString ){

    //create a scanner to split the input
    Scanner inputSc = new Scanner( inputString );
    
    //set the delimiter to be the semicolong
    inputSc.useDelimiter(";");
    
    //first one should be city
    String name = inputSc.next();
    
    //then city
    String city = inputSc.next();
    
    //then categories
    String categories = inputSc.next();
    
    //then cost
    String cost = inputSc.next();
    
    //then open
    String open = inputSc.next();

    //add it to the database
    data.addNewRestaurant( name, city, categories, open, cost );
    
  }
  
  /**
   * This method performs the scan of user commands necessary for the add method.
   * @return a string of the user's parameters, separated by a semicolon
   */
  public String scanInForAdd(){
  
    //get the name
    System.out.println("Please, enter restaurant name: ");
    String name = userInputScanner.nextLine();
    
    //get the city
    System.out.println("Please specify city: ");
    String city = userInputScanner.nextLine();
    
    //get the category (or categories separated by commas)
    System.out.println("Enter categories, separated by commas: ");
    String categories = userInputScanner.nextLine();
    
    //get the cost
    System.out.println("Enter cost ($, $$, or $$$): ");
    String cost = userInputScanner.nextLine();
    
    //get the current day and time
    //get the day
    System.out.println("Enter opening hours in the format <day> <open> <close>,<day>... : ");
    String open = userInputScanner.nextLine();
    
    //build the report string
    String finalString = name + ";" + city + ";" + categories + ";" + cost + ";" + open;
    
    return finalString;
  }
  
  /**
   * This takes care of the quit command.
   * It uses a BufferedWriter to write to the output file and then
   */
  public void quit(){
    
    //so that it can be closed in the finally block
    BufferedWriter bw = null;
    
    try{
      //create a file writer
      bw = new BufferedWriter( new FileWriter( "new-restaurants.txt" ));
      
      //get all restaurants in the database
      ArrayList<Restaurant> allRestArr = data.getAll();
      //go through the arrayList we have in the database
      for ( Restaurant curr : allRestArr ){
        
        //write each one to the file
        bw.write( curr.toString() +"\n");
      }
    }
    
    catch (Exception e){
      System.out.println("Something went wrong! " + e);
    }
    
    finally{
      
      //close the inputScanner
      close();
      
      //close the writer if it exists
      if(bw != null ){
       
        try{
          //close the file writer and the buffered writer
          bw.close();
          
        } catch (IOException e){
          
          System.out.println("writer was not closed due to exception: " + e);
        }
      }
    }
  }

  
  /**
   * This method is designed to identify the command that the user has given
   * and call the appropriate method.
   */
  public void identifyCommand( String userInput ){
    
    try{
    
      //check if the user is searching
      if (userInput.equals("search")){
        
        //let search handle the rest of the operations
        ArrayList<Restaurant> searchOutputArr = search( scanInForSearch() );
        
       
        //perform the printing of the results
        //check if anything was found
        if( searchOutputArr != null ){
          
          //print the search result
          printSearchResult( searchOutputArr );
          
          
        } else {
          //if not print out appropriate message
          System.out.println("Nothing found with specified search criteria!");

        }
      } 
      
      //check if the user is adding a review
      else if (userInput.equals("review")){
        
        //let review handle the rest
        review( scanInForReview() );
      }
      
      //check if the user is adding a restaurant
      else if (userInput.equals("add")){
        
        //let add handle the rest
        add( scanInForAdd() );
      }
      
      //check if the user wants to quit the program
      else if (userInput.equals("quit")){
        
        //call quit
        quit();
      }
      else {
        
        System.out.println("Command not recognized! Possible commands are: add, review, search, quit.");
        identifyCommand ( userInputScanner.nextLine() );
      }
    }
    catch (Exception e){
    
      //print out the error message
      System.out.println("Please, reexamine commands and input file. The following exception occured: " + e);
      
      //close the scanner
      close();
    }
         
  }
  
  /**
   * This method scans the user's next command.
   * @return the next command of the user.
   */
  public String scanCommand(){
    //prompt for command
    System.out.println("Please enter command: search, add, review, or quit");
    return userInputScanner.nextLine();
  }
  
  /**
   * This method checks if the quit method has been called.
   * @return true if the quit method hasn't been called, false otherwise
   */
  public boolean isOpen(){
    return closed == false;
  }
  
  /**
   * Method to read in the data for the search.
   * @return a string with the user's parameter inputs, separated by a semicolong
   */
  public String scanInForSearch(){
  
    //get the city
    System.out.println("Please specify city: ");
    String city = userInputScanner.nextLine();
    
    //get the category (or categories separated by commas)
    System.out.println("Enter categories, separated by commas: ");
    String categories = userInputScanner.nextLine();
    
    //get the cost
    System.out.println("Enter cost ($, $$, or $$$): ");
    String cost = userInputScanner.nextLine();
    
    //get the current day and time
    //get the day
    System.out.println("Enter current day of the week (full word): ");
    String day = userInputScanner.nextLine();

    //get the time
    System.out.println("Enter current time in 24-hour format: ");
    String time = userInputScanner.nextLine();
    
    //build the final output string
    String finalOutputString = city + ";" + categories + ";" + cost + ";" + day + ";" + time;
    
    return finalOutputString;
  }
  
  
  /**
   * This method closes the user input scanner.
   */
  public void close(){
    
    //if the user input scanner is open, close it
    if( userInputScanner != null){
      userInputScanner.close();
    }
    //set the boolean to true
    closed = true;
  }
  
  public static void main(String[] args){
  
    //create an instance of the program based on the restaurants.txt
    FinalProgram fp = new FinalProgram("restaurants.txt");
     
    //as long as the scanner is open, keep prompting the user for input
     while (fp.isOpen() ){
       
        //identify the appropriate command from the scanner
        fp.identifyCommand( fp.scanCommand() );
      }
   
  }
}
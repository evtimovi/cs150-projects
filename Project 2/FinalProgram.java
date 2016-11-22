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
  Database data;
  
  /**
   * This field is the scanner for the user's input.
   */
  Scanner userInputScanner;
  
  /**
   * This keeps track of whether we have exited and closed the scanner
   * 
   */
  private boolean closed;
  
  /**
   * The constructor of the program initializes the data field and creates the database
   * from the specified file (a file path as a string).
   * @param inputFilePath the path to the restaurants.txt file
   */
  public FinalProgram( String inputFilePath ){
    
    //create the database
    data = new Database();
    data.createDatabaseFromFile ( inputFilePath );
    
    //initialize the input scanner
    userInputScanner = new Scanner ( System.in );
    
    //open the scanner according to the variable
    closed = false;
  }
  
  /**
   * This method is designed to ask the user for a city, category, cost and time
   * and perform the search based on the user's input.
   * 
   */
  public ArrayList<Restaurant> search(){
    
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

      //check if anything was found
      if( searchOutputArr != null ){
        
         //print the search result
         printSearchResult( searchOutputArr );
        
         //return the result
         return searchOutputArr;
      
      } else {
        //if not print out appropriate message
        System.out.println("Nothing found with specified search criteria!");
        return null;
      }
    }
    
    else { //only one category
      
      //call the appropriate search
      ArrayList<Restaurant> searchOutputArr = data.search( city, categories, cost, day, time );
      
      //check if something was found
      if( searchOutputArr != null ){
        
        //print the search result
        printSearchResult( searchOutputArr );
        
        //return the result
        return searchOutputArr;
        
      } else {
        System.out.println("Nothing found with specified search criteria!");
        return null;
      }
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
    
    //get the keys in a set
    Set<Double> keySet = orderedRest.keySet();
    
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
   */
  public void review(){
    //get the city
    System.out.println("Please specify name: ");
    String name = userInputScanner.nextLine();
    
    //get the city
    System.out.println("Please, specify city: ");
    String city = userInputScanner.nextLine();
    
    //get the cost
    System.out.println("Enter new score (0-5): ");
    String score = userInputScanner.nextLine();
    
    //get the double of the score
    Double newRank = Double.parseDouble( score );
    
    //review and get the review result
    ArrayList<Restaurant> reviewResult = data.addReview ( name, city, newRank );
    
    //go through that array list and print out what's needed
    for ( Restaurant curr : reviewResult ){
      
      
      System.out.println("Restaurant reviewed: \n");
      
      //name
      System.out.println( curr.getName() );
      
      //city
      System.out.println( curr.getCity() );
      
      //updated score
      System.out.println( curr.getRank() );
      
      //number of reviewers
      System.out.println( curr.getNumOfReviewers() );
      
      //empty line separator
      System.out.println();
    }
    
    
  }
  
  /**
   * This method handles the add command.
   */
  public void add(){
  
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

    //add it to the database
    data.addNewRestaurant( name, city, categories, open, cost );
    
  }
  
  /**
   * This takes care of the quit command.
   */
  public void quit(){
  
    //set the closed boolean to true
    closed = true;
    
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
    
    //check if the user is searching
    if (userInput.equals("search")){
      
      //let search handle the rest of the operations
      search();
    } 
    
//    //check if the user is adding a review
    else if (userInput.equals("review")){
      
      //let review handle the rest
      review();
    }
   
    //check if the user is adding a restaurant
    else if (userInput.equals("add")){
      
      //let add handle the rest
      add();
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
  
  /**
   * This method scans the user's next command.
   * @return the next command of the user.
   */
  public String scanCommand(){
    return userInputScanner.nextLine();
  }
  
  /**
   * This method checks if the quit method has been called.
   * @return true if the quit method hasn't been called, false otherwise
   */
  public boolean isOpen(){
    return closed == false;
  }
  
  public static void main(String[] args){
  
    
    FinalProgram fp = new FinalProgram("restaurants3.txt");
   
    while (fp.isOpen() ){
      fp.identifyCommand( fp.scanCommand() );
    }
  }
}
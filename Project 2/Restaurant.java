import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class provides the functionality for storing the information about each restaurant.
 * The name and the city where the restaurants are located are stored as String-s.
 * The categoires are stored in an ArrayList as we need quick access to them. Due to their
 * small number, we don't care if they are sorted. (sorting according to category
 * will not be done by the class's comparator though.
 * To store the hours of operation of this venue, the class makes use of the BusinessHours class
 * which allows input in several different forms and queries on whether the restaurant is open.
 * The cost is a simple String (see field description on why it is not a class).
 * The rank is a simple double that is an average of the number of people
 * who have reviewed the restaurant and its total score. These last two variables
 * can be set based on the data in the text file.
 * Finally, the class implements Comparable and its natural ordering
 * is by name, then city. If two restaurant objects have the same name
 * and are located in the same city, they are considered the same.
 * 
 * The class provides publicly an empty constructor and a constructor
 * that creates the restaurant based on the block of code for a restaurant in the text file.
 * 
 * @author Ivan Evtimov
 */
public class Restaurant implements Comparable<Restaurant>{
  
  /**
   * This field holds the restauran's name. It is a simple string.
   */
  private String name;
  
  /**
   * This field holds the restaurant's city. It is a simple string.
   */
  private String city;
  
  /**
   * This field holds the restaurant's category/categories. Since there could be more than one,
   * but not a huge number, it is an ArrayList.
   */
  private ArrayList<String> categoriesArr;
  
  /**
   * This field holds the business hours of the restaurant.
   * It makes use of the BusinessHours implementation.
   */
  private BusinessHours hours;
  
  /**
   * This field holds the restaurant's current ranking.
   * First, it is set by whatever comes in from the database,
   * but if a review method is called, it is changed accordingly.
   * 
   */
  private double rank;
  
  /**
   * This field holds the restaurant's total number of reviewers. 
   * It is an int variable and is taken from the database initially.
   * If the review method is called, it is incremented by one.
   */
  private int numOfReviewers;
  
  /**
   * This field holds the restaurant's cost, in dollar values.
   * Since the compareTo method of the String class appropriately ranks
   * the $ as the smallest (cheapest), $$ as the middle, and $$ as the greatest,
   * there is no need to implement a special class for that.
   */
  private String cost;
  
  /**
   * This field holds the number of non-accessed categories of a restaurant.
   * It starts out as the size of the ArrayList holding them, but each time
   * the getCategory is called, it is incremented down by 1.
   */
  private int nonaccessedCats;
  
  /**
   * This field holds the total score (not average, but sum) of all reviews.
   */
  private double totalScore;
  
  /**
   * This constructor method takes in the whole block of text for a restaurant
   * as found in the text file and creates a restaurant object
   * with the appropriate field values based on it.
   * @param restaurantBlock the block of text as found in the text file
   */
  public Restaurant ( String restaurantBlock ){
  
    //compile the patterns for the different keywords
    Pattern namePat = Pattern.compile ( "name:\\s(.+)" ); //for the name
    Pattern cityPat = Pattern.compile ( "city:\\s(.+)" ); //for the city
    Pattern rankPat = Pattern.compile ( "rank:\\s([0-5]\\.?\\d?)" ); //for the rank
    Pattern revPat = Pattern.compile ( "reviewers:\\s(\\d+)" ); //for the reviewers
    Pattern costPat = Pattern.compile ( "cost:\\s(\\${1,3})" ); // for the cost
    Pattern catPat = Pattern.compile ( "category:\\s(.+)" ); // for the category
    
    //build the regex for the hours
    String hoursRegex = "open:\\s((\\w+\\s\\d\\d:\\d\\d\\s\\d\\d:\\d\\d),?"; //first day
    
    //any consequent days
    for (int i = 0; i < 6; i++){
      hoursRegex = hoursRegex + "(\\w+\\s\\d\\d:\\d\\d\\s\\d\\d:\\d\\d)?,?";
    }
    
    //add the closing paranthesis
    hoursRegex = hoursRegex + ")";
   
    
   // System.out.println("Hours regex: " + hoursRegex);
    
    //compile the hours pattern
    Pattern hoursPat = Pattern.compile ( hoursRegex );
    
    //get the matcher for the namePat
    Matcher nameMat = namePat.matcher ( restaurantBlock );
    
    //match and get the group
    nameMat.find();
    String name = nameMat.group(1);
    
    //get the matcher for the citypat
    Matcher cityMat = cityPat.matcher ( restaurantBlock );
     
    //match and get the group
    cityMat.find();
    String city = cityMat.group(1);
    
    //get the matcher for the rankPat
    Matcher rankMat = rankPat.matcher( restaurantBlock );
    
    //match and get the group
    rankMat.find();
    double rank = Double.parseDouble( rankMat.group(1) );
    
     //get the matcher for the rankPat
    Matcher revMat = revPat.matcher( restaurantBlock );
    
    //match and get the group
    revMat.find();
    int reviewers = Integer.parseInt( revMat.group(1) );
    
    //get the matcher for the costPat
    Matcher costMat = costPat.matcher ( restaurantBlock );
    
    //match and get the group
    costMat.find();
    String cost = costMat.group(1);
    
    //get the matcher for the catPat
    Matcher catMat = catPat.matcher ( restaurantBlock );
    
    //match and get the group
    catMat.find();
    String categories = catMat.group(1);
    
    
     //get the matcher for the hoursPat
    Matcher hoursMat = hoursPat.matcher ( restaurantBlock );
    
    //match and get the group
    hoursMat.find();
    
    //set all the hours
    setAllHours( hoursMat.group(1) );
 
     //call the appropriate set methods 
    setName ( name );
    setCity ( city );
    setCategories ( categories );
    //setAllHours ( hours );
    setRank ( rank );
    setCost ( cost );
    setReviewers ( reviewers );
    
    //set the number of unaccessed categories (it should be as big as the arraylist of categories)
    nonaccessedCats = categoriesArr.size();
    
    //calculate the total score
    totalScore = rank * numOfReviewers;

    
  }
  
  
  
  /**
   * Simple accessor method for the restaurant's rank.
   * @return the restaurant's rank as a double
   */
  public double getRank(){ return this.rank; }
  
  /**
   * Simple accessor method for the restaurant's name.
   * @return the restaurant's name as a String
   */
  public String getName(){ return this.name; }
  
  /**
   * Simple accessor for ther restaurant's city.
   * @return the restaurant's city as a String
   */
  public String getCity() { return this.city; }
  
  /**
   * Simple accessor for the restaurant's number of reviewers.
   * @return the restaurant's number of reviewers as an int
   */
  public int getNumOfReviewers() { return this.numOfReviewers; }
  
  /**
   * Simple accessor for the restaurant's cost.
   * @return the restaurant's cost (1, 2, or 3 dollar signs) as a string
   */
  public String getCost() { return this.cost; }
  
  /**
   * This method tells an outside object using the Restaurant object
   * whether the object has more categories that haven't previously been accessed.
   * @return true if the restaurant has a category that it has never returned, 
   * false if all categories have been given to outside objects
   */
  public boolean hasNextCategory() {
    return nonaccessedCats > 0;
  }
  
  /**
   * This method provides access to the restaurant's categories ONE AT A TIME.
   * @return the next category that hasn't been accessed as a String
   * @throws UnsupportedOperationException if all the categories have already been accessed
   */
  public String getCategory(){
  
    if ( hasNextCategory() ){
      
      //calculate what index we should be in the ArrayList
      int arrPos = nonaccessedCats - 1;
      
      //increment down the nonaccessedCats counter
      nonaccessedCats--;
      
      return categoriesArr.get(arrPos);
      
    } else {
      
      //to indicate all categories have been accessed
      throw new UnsupportedOperationException ("All categories have already been accessed!");
    }
  }
  
  /**
   * Method to reset the counter of non-accessed categories. 
   * (might be helpful for testing, hmm)
   * Please, note that accessing the categories will have to start from the first one again.
   */
  public void resetAccessCounter(){
    nonaccessedCats = 0;
  }
  
  /**
   * This is the closes thing to an accessor method for the BusinessHours class.
   * It allows queries on whether the restaurant is open at the specified time,
   * returning the appropriate boolean (true for open and false for closed)
   * Please, note the format of the time must be "09:00" or "17:23",
   * otherwise an exception will be thrown by classes down the hierarchy.
   * @param day the day we want the restaurant to be open on
   * @param time the time of that day we want the restaurant to be open on
   * @return true if the restaurant is open at the specified day and time,
   * false otherwise
   */
  public boolean isOpen(String day, String time){
    return hours.isOpen(day, time);
  }
  
  /**
   * This method will set the restaurant's name to whatever has been specified.
   * @param name the specified name we want the restaurant to have
   */
  private void setName( String name ){
  
    //set this object's name to the passed name
    this.name = name;
  }
  
 /**
   * This method will set the restaurant's city to whatever has been specified.
   * @param city the specified city we want the restaurant to have
   */
  private void setCity( String city ){
  
    //set this object's city to the passed name
    this.city = city;
  }
 
  /**
   * This is a private method that is used for internal operations of the class.
   * It overrides the old value of the rank and replaces it with a new one.
   * @param newRank the newRank we want the restaurant to have
   * @throws IllegalArgumentException if the rank is outside of the bounds 0 to 5
   */
  private void setRank( double newRank ) throws IllegalArgumentException{
    
    if( newRank < 0.0 || newRank > 5.0 ){
      throw new IllegalArgumentException("Rank must be between 0 and 5");
    }
    
    this.rank = newRank;
  }
  
  /**
   * This method overrides the number of reviewers for each restaurnt.
   * This is another private method that is used only for internal operations of the class.
   * @param newNumOfReviewers the new number of reviewers we want the restaurant to have
   */
  private void setReviewers( int newNumOfReviewers ){
    this.numOfReviewers = newNumOfReviewers;
  }
  
  /**
   * This method overrides the categories of the restaurant.
   * This is another private method that is used only for internal operations of the class.
   * Please, note that it accepts the categories in any data structure that implements
   * the Collection and has its values stored in a String.
   * @param newCats the new categories, stored in a data structure that implements Collection
   */
  private<T extends Collection<String>> void setCategories(T catColl){
    
    //initialize the categories array
    categoriesArr = new ArrayList<String>();
    
    //get the iterator of the collection and store everything from it in our arraylist
    for (Iterator it = catColl.iterator(); it.hasNext(); ){
      categoriesArr.add( (String) it.next() ); //since we have ensured that
                                               //the collection has Strings,
                                               //we can safely do this cast
    }
  }
  
  /**
   * This method takes in the categories as a string as found in the text file.
   * Used for internal operations, so private.
   * @param inputCategories the string of categories as found in the text file
   */
  private void setCategories ( String inputCategories ){
  
    //create a scanner based on the input string
    Scanner sc = new Scanner( inputCategories );
    sc.useDelimiter(",");//set the delimiter to our well-known comma
    
    //get a TreeSet to store the categories
    TreeSet<String> catCont = new TreeSet<String>();
    
    //scan the categories and put them in the set
    while( sc.hasNext() ){
      catCont.add( sc.next() );
    }
    
    //finally, pass the collection of category strings to the other setCategories method
    setCategories ( catCont );
  }
  
  /**
   * This method sets the working hours of the restaurant ONLY FOR A PARTICULAR DAY!
   * It is private and used only for internal operations of the class.
   * @param hoursOfADay the String that contains the day and the opening and closing hours of each day
   */
  private void setHoursForDay( String hoursOfADay ){
    hours = new BusinessHours();
    hours.setHours( hoursOfADay );
  }
  
  /**
   * This method sets the working hours of the restaurant for all days
   * from the string that was found in the source file.
   * @param allDays the string containing the opening hours for each day with a comma delimiter
   * 
   */
  private void setAllHours( String allDays ){
    //initialize the business hours var
    hours = new BusinessHours();
    
    hours.setAllHours ( allDays );//call the appropriate method from the BusinessHours class
  }
  
  /**
   * Internal method to override the cost with the passed string. 
   * Ensures cost matches format using a regular expression.
   * @param cost the cost of the restaurant (1, 2, or 3 dollar signs)
   * @throws IllegalArgumentException if the argument does not match the pattern of the dollar signs
   */
  private void setCost( String cost ) throws IllegalArgumentException{
    
    //use the pattern class and a regex to check if the passed string matches the format
    if ( Pattern.matches ( "\\${1,3}", cost ) ){
      
      //if the pattern matches, everything is okay
      this.cost = cost;
    
    } else {
      
      //but if the pattern doesn't match, we need an exception...
      throw new IllegalArgumentException("Input must be one of these three strings: $, $$, $$$.");
    }
    
  }
  
  /**
   * This method allows the user to add a new review of the restaurant, it only accepts doubles
   * between 0 and 5.
   * @param newScore the new score we want to add to the total
   * @return the new average rank of the restaurant
   * @throws IllegalArgumentException if score is not between 0 or 5
   */
  public double addReview( double newScore ){
  
    //check if newScore is between 0 and 5
    if( newScore > 0 && newScore < 5 ){
      
      //first update the total score
      totalScore = totalScore + newScore;
      
      //then increment the total number of reviews
      numOfReviewers++;
      
      //finally, calculate the new rank
      rank = totalScore/numOfReviewers;
      
      return rank;
    } else {
      throw new IllegalArgumentException("Score must be between 0 and 5, exclusive");
    }
  }
  
  
  
  /**
   * This method is implemented as specified in the Comparable interface.
   * It enforces a natural ordering of the Restaurants by name and then city.
   * If two restaurants have the same name and city, they are considered to be identical
   * objects.
   * @param r the Restaurant object we are comparing the current restaurant to
   * 
   */
  public int compareTo( Restaurant r ){
    
    //first check if the two have the same names
    if ( this.name.equals( r.name ) == false ){
      
      //let the compareTo method in the String class handle the comparision here
      return this.name.compareTo( r.name );
    
    } else if ( this.city.equals( r.city ) == false ) { //then check if they are in the same city
      
      //again, let the string compareTo handle everything
      return this.city.compareTo( r.city );
    
    } else { //if they have the same name and city
    
      return 0; //to indicate they are the same object
    }

  }
  
  /**
   * Standard toString method to output the data as it appears in teh text file.
   * @return a string as in the text file
   */
  public String toString(){
    
    //declare the outputString var
    String outputString;
    
    //begin building the outputString 
    outputString = "name: " + name + "\n";
    outputString = outputString + "city: " + city + "\n";
   
    
    //build the category string
    String categ = "";
    
    for ( String curr : categoriesArr ){
      categ = categ + curr + ",";
    }
    
    //continue building the outputString
    outputString = outputString + "category: " + categ + "\n";
    outputString = outputString + "open: " + hours + "\n";
    outputString = outputString + "cost: " + cost + "\n";
    outputString = outputString + "rank: " + rank + "\n";
    outputString = outputString + "reviewers: " + numOfReviewers + "\n";
    
    //return the outputString
    return outputString;
  }
  
  /**
   * Provides access to all categories at once in no particular order.
   * @return an ArrayList of all categories of the restaurant
   */
  public ArrayList<String> getAllCategories(){
    return categoriesArr;
  }
}
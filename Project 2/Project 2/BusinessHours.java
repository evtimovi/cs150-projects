import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.*;


/**
 * This class provides storage and queries for the business hours of a venue.
 * Any instance of the BusinessHours class has entries for the opening and closing
 * times for each day of the week. 
 * Moreover, each instance can be queried whether the venue that the BusinessHours 
 * pertain to is open at a given day and time.
 * 
 * @author Ivan Evtimov
 */
public class BusinessHours{
  
  /**
   * This field is a two-dimensional array of times.
   * The first dimension indicates which day of the week
   * the times in question are. 
   * At the same time, the first row indicates the opening time
   * and the second row indicates the closing time.
   * Therefore, the entry at [0][0] indicates the opening
   * time for Monday, [0][1] indicates the clsoing time
   * for Monday and so on.
   * I am not using an ArrayList on purpose because I need 
   * a data structure that does not automatically readjust its size.
   * The reason behind this is that if a certain entry is null,
   * it will indicate that the restaurant is closed at this day.
   */
  private Time[][] timesArr;
  
  /**
   * This constructor initializes a table of
   * empty opening and closing times, indicating that 
   * without any further additions, the venue is closed at all
   * times.
   */
  public BusinessHours(){
  
    //initialize the two-dimensional array
    timesArr = new Time[7][2];
  }
  
  
  /**
   * This is a private helper method to set the opening and closing times for a given day.
   * The days here are indicated numerically with Monday being day 1 and Sunday being day 7.
   * @param day the day of the week, from 1 to 7 (Monday to Sunday)
   * @param openingTime the time the restaurant opens on that day
   * @param closingTime the time the restaurant closes on that day
   * @return true if successfully modified
   */
  private boolean setHours(int day, Time openingTime, Time closingTime){
    
    //calculate the position in the two-dimensional array
    int actualPos = day - 1;//to account for displacement in array indexing
    
    //System.out.println("line 60: dayNum: " + actualPos);
    
    //set the opening time
    timesArr[actualPos][0] = openingTime;
    
    
   // System.out.println("line 65: openingTime in arr: " + timesArr[actualPos][0]);
    
    //set the closing time
    timesArr[actualPos][1] = closingTime;
  
//    System.out.println("line 71: closingTime in arr: " + timesArr[actualPos][1]);
//    System.out.println();
    
    return true;
  }
  
  
  /**
   * This is a private helper method to analyze which day of the week
   * the input string corresponds to.
   * @param inputDay the day of the week we want to associate with a number
   * @throws IllegalArgumentException if the input string is not the name of the day
   * or if it has been misspelled or not typed out fully
   */
  public int analyzeDay(String inputDay) throws IllegalArgumentException{
  
    //declare the regexes for the matching
    String monday = "Monday";
    String tuesday = "Tuesday";
    String wed = "Wednesday";
    String thurs = "Thursday";
    String fri = "Friday";
    String sat = "Saturday";
    String sun = "Sunday";
    
    //compile CASE INSENSITIVE patterns
    Pattern monPat = Pattern.compile (monday, Pattern.CASE_INSENSITIVE);
    Pattern tuesPat = Pattern.compile (tuesday, Pattern.CASE_INSENSITIVE);
    Pattern wedPat = Pattern.compile (wed, Pattern.CASE_INSENSITIVE);
    Pattern thursPat = Pattern.compile (thurs, Pattern.CASE_INSENSITIVE);
    Pattern friPat = Pattern.compile (fri,Pattern.CASE_INSENSITIVE);
    Pattern satPat = Pattern.compile (sat, Pattern.CASE_INSENSITIVE);
    Pattern sunPat = Pattern.compile (sun, Pattern.CASE_INSENSITIVE);
    
    //check which one the input string matches
    //return the corresponding number for eachd day
    if ( monPat.matcher(inputDay).matches() ){
      return 1;
    } else if ( tuesPat.matcher(inputDay).matches() ){
      return 2;
    } else if ( wedPat.matcher(inputDay).matches() ){
      return 3;
    } else if ( thursPat.matcher(inputDay).matches() ){
      return 4;
    } else if ( friPat.matcher(inputDay).matches() ){
      return 5;
    } else if ( satPat.matcher(inputDay).matches() ){
      return 6;
    } else if (sunPat.matcher(inputDay).matches() ){
      return 7;
    } else {
      //if none of the days was matched, throw an exception
      throw new IllegalArgumentException("Name of day wrong, please note it must be typed out fully! " + inputDay);
    }
  }
  
  /**
   * This method takes in a day of the week and opening and closing times
   * and assigns the opening and closing times for that day of the week 
   * @param day the day of the week input as a string (written out fully)
   * @param openingTime the time the venue opens
   * @param closingTime the time the venue closes
   * @return true to indicate adding succeeded
   */
  public boolean setHours(String day, Time openingTime, Time closingTime){
  
    return setHours( analyzeDay(day), openingTime, closingTime );
  }
  
  /**
   * This method takes in an input string in the format
   * <day> <open> <close>
   * and sets the opening and closing times of the venue accordingly.
   * PLEASE NOTE: The regular expression has been set up so that
   * it is acceptable both to have and not ot have the comma at the end of the format.
   * @param inputString the string that contains a day with business hours
   * @return true when adding succeeds
   */
  public boolean setHours(String inputString){
  
    //compile a regular expression that the input string should match
    Pattern pat = Pattern.compile("([a-zA-Z]{6,9})\\s(\\d\\d:\\d\\d)\\s(\\d\\d:\\d\\d),?");
    
    //get the matcher
    Matcher mat = pat.matcher(inputString);
    
    //perform the matchin
    mat.matches();
    
    //first group should be the day
    //second group should be the opening time
    //third group should be the closing time
    
    String day = mat.group(1);
    String open = mat.group(2);
    String close = mat.group(3);
    
    //System.out.println("line 156 day: " + day + " open: " + open + " close: " + close);
   // System.out.println(day);
    
    //generate the times
    Time openingTime = new Time(open);
    Time closingTime = new Time(close);
    
   // System.out.println("line 166: openingTime: " + openingTime + " closingTime: " + closingTime);
    
    //set the hours accordingly and returnt the result
    return setHours(day, openingTime, closingTime);
  }
  
  /**
   * This method taakes in the whole string of opening and closing hours
   * for each day as found in the text file, analyzes it and then sets
   * the opening and closing hours for each day accordingly.
   * @param inputFromFile the String with the opening and closing information as found in the source file
   */
  public void setAllHours( String inputFromFile ){
  
    //System.out.println("BusinessHours: 189: " + inputFromFile);
    
    //create a scanner for the input string from the file
    Scanner sc = new Scanner ( inputFromFile );
    sc.useDelimiter(",");//use the comma delimiter
    
    //get everything from the input and set the hours for each day accordingly
    while ( sc.hasNext() ){
      setHours( sc.next() );
    }
    
  }
  
    
  /**
   * A standard toString method that prints out the table of the opening and closing times.
   * @return the table of opening and closing times, with first row Monday, second Tueday and so on
   */
  public String toString(){
    
    //create the output string
    String outputString = "";
//    
//    //go through the first dimension
//    for (int i = 0; i < 7; i++){
//    
//      outputString = outputString + "opens: " + timesArr[i][0] + " closes: " + timesArr[i][1] +"\n";
//    }
    
    ArrayList<String> daysArr = new ArrayList<String>(7);
    daysArr.add("Monday");
    daysArr.add("Tuesday");
    daysArr.add("Wednesday");
    daysArr.add("Thursday");
    daysArr.add("Friday");
    daysArr.add("Saturday");
    daysArr.add("Sunday");
    
   // int index = 0;
    
    String curr = "";
    
   // System.out.println(daysArr);
 //   System.out.println("timesArr before toString loop (230): " + timesArr);
    
    for (int index = 0; index < daysArr.size(); index = index +1){
      
      curr = daysArr.get(index);
//      System.out.println(index);
//      System.out.println(curr);
   
   //   System.out.println(timesArr[index][0]);
      
      if(timesArr[index][0] != null ){
        outputString = outputString + curr + " "+ timesArr[index][0] +" " + timesArr[index][1] + ",";
        //System.out.println("BusinessHours says: outputString at index " + index +": " + outputString + " curr: " 
          //+ curr);
    }
      //index++;
    }
    

    
    
    return outputString;
  }
  
  /**
   * This method is used to check if the restaurant is open at the specified day and time.
   * @param day the day we want the restaurant to be open
   * @param time the time we want the restaurant to be open
   * @return true if ther restaurant is open, false if it is closed
   * 
   */
  public boolean isOpen(String day, String time){
  
    //analyze the day and get its position in the two-dimensional array
    int dayPos = analyzeDay(day) - 1;
    
    //create an instance of the time class for the passed time
    Time currentTime = new Time(time);
    
    //check if the restaurant is open on that day at all 
    //(if it is not, the position for it in the array would be null)
    if(timesArr[dayPos][0] != null && timesArr[dayPos][1] != null){
    
      //find the day and check if the current time is between its opening and closing hours
      return currentTime.isBetween(timesArr[dayPos][0],timesArr[dayPos][1]);
    } else {
      //if the position for that day is null,
      //then no opening time has been assigned to the restaurant for that day,
      //so it is assumed it's closed
      return false;
    }
  }
}
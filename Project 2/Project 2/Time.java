import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;


/**
 * This class should provide the functionality for storing time values in a 24-hour format.
 * Furthermore, it provides a method to determine if a given time is between two other times.
 * It makes use of regular expressions when constructing the Time object
 * to make sure that the string passed is in an acceptable format.
 * 
 * @author Ivan Evtimov
 */
public class Time{
  
  /**
   * This field will hold the hours once they are converted to an int.
   */
  private int hours;
  
  /**
   * This field will hold the minutes once they are converted to an int.
   */
  private int minutes;
  
  /**
   * This constant holds the regular expression that is used to match 
   * the string that has been passed as a parameter to the time format.
   */
  private static final String REGEX = "([0-2][0-9]):([0-5][0-9])";
  
  
  
  /**
   * The constructor takes in a string that contains a time expression 
   * as input and creates a corresponding Time object.
   * 
   * @param inputString the string that has the time expression in a 24-hour format
   * @throws IllegalArgumentException if the time is not in a 24-hour format
   */
  public Time(String inputString) throws IllegalArgumentException{
    try{
      
      //compile the pattern
      Pattern pat = Pattern.compile(REGEX);
      
      //get the matcher
      Matcher mat = pat.matcher(inputString);
      
      //match the input
      mat.find();
      
      //first group should be the hours
      hours = Integer.parseInt(mat.group(1));
      
      //second group should be the minutes
      minutes = Integer.parseInt(mat.group(2));
      
      
    } catch (RuntimeException e){
      
      throw new IllegalArgumentException("Wrong format of input string! Please enter a 24-hour time!");
    }
    
  }
  
  /**
   * The toString method prints out the minutes and the hours individually
   * to allow for debugging.
   * @return a string indicating what the hour field is and what the minute field is
   */
  public String toString(){
  
    //create a string for the hours
    String hoursString = "";
    
    //create a string for the minutes
    String minutesString = "";
    
    //check if hours are less than 10
    if ( hours < 10 ){
      hoursString = "0" + hours; //if so, we need to add the missing zero
    } else {
      hoursString = String.valueOf(hours); //it's already in the two-digit format
    }
    
    //do the same for the minutes
    if ( minutes < 10 ){
      
      minutesString = "0" + minutes;//add the zero
      
    } else {
      
      minutesString = String.valueOf(minutes);//no need to add zero (number is between 10 and 59)
    }
    
    return  hoursString + ":" + minutesString;//return the string
  }
  
  
  /**
   * This method determines whether the current time (object) indicates a time
   * that is between the times that the other two objects indicate.
   * Please, note that if the endTime is less than the startTime, 
   * the method will automatically add 24 hours to the endTime to reflect
   * the fact that the second time is on the following day.
   * This "comparison" method assumes that if a time matches 
   * either the endTime or the startTime completely, it is within the period.
   * @param startTime the beginning of the period
   * @param endTime the end of the period
   * @return true if the current Time objects is in the specified period, false otherwise
   */
  public boolean isBetween ( Time startTime, Time endTime ){
    
    //before everything, check if the startTime's hours match endTime's hours
    if (startTime.hours == endTime.hours ){
      
      //check if we have moved to the next day, e.g. if the times are 17:35 to 17:00
      if(startTime.minutes > endTime.minutes){
        
        //so the time has to be outside of the given period, say it is not between 17:35 and 17:00
        return this.hours != startTime.hours 
               || this.minutes == startTime.minutes
               || this.minutes == endTime.minutes ||
               (this.minutes > startTime.minutes && this.minutes < endTime.minutes);
        
      } else if (startTime.minutes == endTime.minutes ){
      
        return true;//in this case, this is a 24-hour time period, so a time is always between the start and end
      
      } else {
        
        //in this case, the time is within the period only if this is true
        //it has to be between the two times
        return this.hours == startTime.hours && this.minutes >= startTime.minutes && this.minutes <= endTime.minutes;
      }
    } else {
      
      //now we know the hours of the start and endTime are different
      //so, first check if the hours of the startTime and this are the same
      if (startTime.hours == this.hours){
      
        //then since we have already covered the case where the hours of the start and end are the same,
        //just check if the startTime's minutes are smaller than the current time's minutes
        return this.minutes >= startTime.minutes;
      
      } else if (endTime.hours == this.hours) {
      
        //this case is the exact opposite of the previous one
        return this.minutes <= endTime.minutes;
        
      } else {
      
        //in this case, the hours of the startTime, the endTime and the this time are all different
        //so first check if we've went to another day, e.g. if the period is 17:00 to 01:00
        if (startTime.hours > endTime.hours){
        
          //if so, our time needs to be outside of the negation of the period, or it must be outside of 01:00 to 17:00
          return !(this.hours > endTime.hours && this.hours < startTime.hours);
        } else {
        
          //this is FINALLY the trivial case where the hours and minutes of the period are all within the same day
          return this.hours < endTime.hours && this.hours > startTime.hours;
        }
      }
    }
    
  }
}
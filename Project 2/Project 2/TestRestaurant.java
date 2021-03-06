import junit.framework.TestCase;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
    


/**
 * A JUnit test case class for the functionality of the Restaurant class.
 * This class performs a series of unit tests on the constructor and the public methods
 * of the class. Please, note that since the public method rely heavily on the
 * correct functioning of the private methods, the private methods' functionality
 * is implicitly tested.
 * @author Ivan Evtimov
 */
public class TestRestaurant extends TestCase {
  
  /**
   * A test method for the constructor.
   * This is the first in a series of three tests that provbide different blocks
   * of text data for a restaurant and check if the object was appropriately constructed.
   */
  public void testConstructor() {
    
    //build a string with information as found in the text file    
    String textBlock = "rank: 3.4";
    textBlock = textBlock + "\n" + "name: Butcher and Singer";
    textBlock = textBlock + "\n" + "reviewers: 1776";
    textBlock = textBlock + "\n" + "cost: $$$";
    textBlock = textBlock + "\n" 
      + "open: Monday 11:30 22:00,Tuesday 11:30 22:00,Wednesday 11:30 22:00,Thursday 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
    textBlock = textBlock + "\n" + "category: American (new),Steakhouses";
    textBlock = textBlock + "\n" + "city: Philadelphia";
    
    //create a new Restaurant object
    Restaurant testRest = new Restaurant( textBlock );
    
    //perform the tests
    //name should be "Butcher and Singer"
    assertTrue ("Name wrong", testRest.getName().equals ( "Butcher and Singer" ));
    
     //number of reviewers
    assertTrue ("NumOfReviewers wrong!", testRest.getNumOfReviewers() == 1776 );
    
    //and so on
    assertTrue ("Cost wrong!", testRest.getCost().equals("$$$"));
    
    //get the categories that were stored in this restaurant
    String cat1 = testRest.getCategory();
    String cat2 = testRest.getCategory();
    
    //skip the opening hours for now
    assertTrue ("Categories wrong! " + cat1 + " " + cat2, 
                (cat1.equals ("American (new)") || cat2.equals("American (new)")) && //ensures both categories are present
                (cat1.equals("Steakhouses") || cat2.equals("Steakhouses")));
    
    //test for city
    assertTrue ("City wrong!", testRest.getCity().equals( "Philadelphia" ));
    
    
    //perform tests for when the restaurant is open
    assertTrue("Monday opening problem", testRest.isOpen("Monday", "14:31"));
    assertTrue("Tuesday opening problem", testRest.isOpen("Tuesday", "11:39"));
    assertTrue("Wednesday opening problem", testRest.isOpen("Wednesday", "17:31"));
    assertTrue("Thursday opening problem", testRest.isOpen("Thursday", "19:31"));
    assertTrue("Friday opening problem", testRest.isOpen("Friday", "16:17"));
    assertTrue("Saturday opening problem", testRest.isOpen("Saturday", "13:45"));
    assertTrue("Sunday opening problem", testRest.isOpen("Sunday", "11:36"));
    
    //perform tests for when the restaurant is closed
    assertFalse("Monday closing problem", testRest.isOpen("Monday", "23:30"));
    assertFalse("Tuesday closing problem", testRest.isOpen("Tuesday", "00:15"));
    assertFalse("Wednesday closing problem", testRest.isOpen("Wednesday", "07:16"));
    assertFalse("Thursday closing problem", testRest.isOpen("Thursday", "11:29"));
    assertFalse("Friday closing problem", testRest.isOpen("Friday", "06:34"));
    assertFalse("Saturday closing problem", testRest.isOpen("Saturday", "07:37"));
    assertFalse("Sunday closing problem", testRest.isOpen("Sunday", "01:43"));

  }
  
  /**
   * This test method of the constructor takes a more generalized approach as it
   * directly scans a sample database and check if each object has been correctly 
   * created.
   */
  public void testConstructor2(){
    
    //create a scanner
    Scanner sc = null;
    Scanner catScan = null;
    Restaurant testRest = null;
    
    //to take care of I/O exceptions
    try{
      
      //create the scanner (this time, actually)
      //based on the restaurants3.txt (the database)
      sc = new Scanner( new BufferedReader (new FileReader ("restaurants3.txt")));
      
      //use a delimiter that is two consecutive new line objects (separate blocks of code)
      sc.useDelimiter("\n\n");
      
      //declare a tempString that will hold the next scan
      String tempString = "";
      
      //declare variables to be used in the loop
      String name, hoursRegex, city, cost, categories, currCat;
      Pattern hoursPat, cityPat, namePat, rankPat, revPat, costPat, catPat;
      Matcher nameMat, hoursMat, cityMat, rankMat, revMat, costMat, catMat;
      double rank;
      int reviewers;
      BusinessHours hours;
      ArrayList<String> categoriesArr, currCategoriesArr;
      
      //as ling as the database is not exhausted
      while ( sc.hasNext() ) {
      
        //////begin scanning and storing in variables///////
        //get the next block of text
        tempString = sc.next();
        
        //compile the patterns for the different keywords
        namePat = Pattern.compile ( "name:\\s(.+)" ); //for the name
        cityPat = Pattern.compile ( "city:\\s(.+)" ); //for the city
        rankPat = Pattern.compile ( "rank:\\s([0-5]\\.?\\d?)" ); //for the rank
        revPat = Pattern.compile ( "reviewers:\\s(\\d+)" ); //for the reviewers
        costPat = Pattern.compile ( "cost:\\s(\\${1,3})" ); // for the cost
        catPat = Pattern.compile ( "category:\\s(.+)" ); // for the category
        
        //build the regex for the hours
        hoursRegex = "open:\\s((\\w+\\s\\d\\d:\\d\\d\\s\\d\\d:\\d\\d),?"; //first day
        
        //any consequent days
        for (int i = 0; i < 6; i++){
          hoursRegex = hoursRegex + "(\\w+\\s\\d\\d:\\d\\d\\s\\d\\d:\\d\\d)?,?";
        }
        
        //add the closing paranthesis
        hoursRegex = hoursRegex + ")";
        
        //compile the hours pattern
        hoursPat = Pattern.compile ( hoursRegex );
        
        //get the matcher for the namePat
        nameMat = namePat.matcher ( tempString );
        
        //match and get the group
        nameMat.find();
        name = nameMat.group(1);
        
        //get the matcher for the citypat
        cityMat = cityPat.matcher ( tempString );
        
        //match and get the group
        cityMat.find();
        city = cityMat.group(1);
        
        //get the matcher for the rankPat
        rankMat = rankPat.matcher( tempString );
        
        //match and get the group
        rankMat.find();
        rank = Double.parseDouble( rankMat.group(1) );
        
        //get the matcher for the rankPat
        revMat = revPat.matcher( tempString );
        
        //match and get the group
        revMat.find();
        reviewers = Integer.parseInt( revMat.group(1) );
        
        //get the matcher for the costPat
        costMat = costPat.matcher ( tempString );
        
        //match and get the group
        costMat.find();
        cost = costMat.group(1);
        
        //get the matcher for the catPat
        catMat = catPat.matcher ( tempString );
        
        //match and get the group
        catMat.find();
        categories = catMat.group(1);
        
        //get the categories in an array
        catScan = new Scanner ( categories );//open a scanner for that string
        
        catScan.useDelimiter(",");//use the commas to split it up
        
        categoriesArr = new ArrayList<String>();//store everything in this array
        
        //get every category in the array
        while ( catScan.hasNext()){
        
          categoriesArr.add( catScan.next() );
        }
        
        //get the matcher for the hoursPat
        hoursMat = hoursPat.matcher ( tempString );
        
        //match and get the group
        hoursMat.find();
        
        //set all the hours
         hours = new BusinessHours();
         hours.setAllHours(hoursMat.group(1));
        ////////////end of scanning and storing in variables///////////////////
         
         //////////////////actual testing////////////////////
         //create a new restaurant based on the block of text
         testRest = new Restaurant( tempString );
         
         //perform the tests
         assertTrue("Name wrong, name: " + name + "restaurant: " + testRest, testRest.getName().equals( name ));
         assertTrue("City wrong, city: " + city  + " restaurant: " + testRest, testRest.getCity().equals( city ));
         assertTrue("Rank wrong, rank: " + rank  + " restaurant: " + testRest, testRest.getRank() == rank );
         assertTrue("Reviewers wrong, reviewers: " + reviewers  + " restaurant: " + testRest, 
                    testRest.getNumOfReviewers() == reviewers );
         assertTrue("Cost wrong, cost: " + cost  + " restaurant: " + testRest, testRest.getCost().equals( cost ));
         
         //initialize the currCategoriesArr to hold all the categories of the restaurn
         currCategoriesArr = new ArrayList<String>();
         
         //go through all the categories in the restaurant
         while ( testRest.hasNextCategory() ){
           
           //get the current category
           currCat = testRest.getCategory();
           currCategoriesArr.add( currCat );
           
           //check if the categories array from the text file has that category
           assertTrue("This category popped up wrongfully form the Restaurant: " + currCat + " restaurant: " + testRest,
                      categoriesArr.contains( currCat ));
           
           
         }
         
         //go through the categories that the restaurant has and check that they are the same as found in the text file
        for ( String textFileCat : categoriesArr ){
        
          //check if a category that was in the text file is not found in the restaurant
          assertTrue("This category was not found: " + textFileCat + " for restaurant: " + testRest,
                     currCategoriesArr.contains( textFileCat ));
        }
        
        //test opening for several days and times, the hours from the text file (hours)
        //should match the result from the isOpen method of the Restaurant class
        assertTrue("Monday 13:59 mismatch\n" + testRest,
                   testRest.isOpen("Monday", "13:59") == hours.isOpen("Monday", "13:59"));
        
        assertTrue("Tuesday 00:59 mismatch\n" + testRest,
                   testRest.isOpen("Tuesday", "00:59") == hours.isOpen("Tuesday", "00:59"));
        
        assertTrue("Wed 23:59 mismatch\n" + testRest,
                   testRest.isOpen("Wednesday", "23:59") == hours.isOpen("Wednesday", "23:59"));
        
        assertTrue("Thursday 08:17 mismatch\n" + testRest, 
                   testRest.isOpen("Thursday", "08:17") == hours.isOpen("Thursday", "08:17"));
      
        assertTrue("Friday 01:34 mismatch\n" + testRest, 
                   testRest.isOpen("Friday", "01:34") == hours.isOpen("Friday", "01:34"));
        
        assertTrue("Saturday 12:00 mismatch\n" + testRest,
                   testRest.isOpen("Saturday", "12:00") == hours.isOpen("Saturday", "12:00"));
        
        assertTrue("Sunday 00:00 mismatch\n" + testRest, 
                   testRest.isOpen("Sunday", "00:00") == hours.isOpen("Sunday", "00:00"));
        
        
      }
    }
    catch (IOException e){
      System.out.println( testRest );
    }
    catch (IllegalArgumentException e){
      
      fail(e + " for restaurant after: " + testRest);
       //for mismatches in rank in the database
      System.out.println(e + "\n" + testRest );
    }
    finally{
    
      //always close the scanner if it was created at all
      if(sc != null)
        sc.close();
      
      if(catScan != null)
        catScan.close();
      
    
    }
  }
  
  /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor31(){
  
    try{
      //build a string with information as found in the text file  
      //but delibaretly make the rank greater than 5
      String textBlock = "rank: 5.1";
      textBlock = textBlock + "\n" + "name: Butcher and Singer";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Monday 11:30 22:00,Tuesday 11:30 22:00,Wednesday 11:30 22:00,Thursday 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
      textBlock = textBlock + "\n" + "category: American (new),Steakhouses";
      textBlock = textBlock + "\n" + "city: Philadelphia";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
    /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor32(){
  
    try{
      //build a string with information as found in the text file  
      //but delibaretly omit the name
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Monday 11:30 22:00,Tuesday 11:30 22:00,Wednesday 11:30 22:00,Thursday 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
      textBlock = textBlock + "\n" + "category: American (new),Steakhouses";
      textBlock = textBlock + "\n" + "city: Philadelphia";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
      /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor33(){
  
    try{
      //build a string with information as found in the text file  
      //but delibaretly omit the city
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Monday 11:30 22:00,Tuesday 11:30 22:00,Wednesday 11:30 22:00,Thursday 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
      textBlock = textBlock + "\n" + "category: American (new),Steakhouses";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
       /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor34(){
  
    try{
      //build a string with information as found in the text file  
      //but delibaretly omit the categories
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Monday 11:30 22:00,Tuesday 11:30 22:00,Wednesday 11:30 22:00,Thursday 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
      //textBlock = textBlock + "\n" + "category:";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
         /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor35(){
  
    try{
      //build a string with information as found in the text file  
      //but make the hours format wrong
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Monday 1:30 22:00,Tuesday 1:30 22:00,Wednesday 11:30 2:00,Thurs 11:30 22:00,Friday 11:30 22:00,Saturday 11:30 22:00,Sunday 11:30 22:00";
      textBlock = textBlock + "\n" + "category: pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
          /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor36(){
  
    try{
      //build a string with information as found in the text file  
      //but make the days format wrong
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$$";
      textBlock = textBlock + "\n" 
        + "open: Mon 21:30 22:00,Tues 21:30 22:00,Wed 11:30 2:00,Thurs 11:30 22:00,Fri 11:30 22:00,Sat 11:30 22:00,Sun 11:30 22:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
  /**
   * The test methods of the group 3* test 
   * the constructor's handling of wrong input.
   */
  public void testConstructor37(){
  
    try{
      //build a string with information as found in the text file  
      //but change the cost to something else (not a dollar sign)
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: 9";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
      //attempt to create  the test restaurant
      Restaurant r = new Restaurant( textBlock );
      
      //if we reach this line of code, the test has failed
     fail("WRONG");
    }
    catch (IllegalStateException e){
    
      //good the test, succeeds
  
    } catch (IllegalArgumentException e ){
    
      //this is also a valid exception
    }
    
  }
  
  /**
   * This test method tests the functionality of the compareTo method.
   */
  public void testCompare(){
  
     //build a string with information as found in the text file  
     //for one restaurant
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
       //build a string with information as found in the text file  
     //for another restaurant from the same city, but with different names
      String textBlock2 = "rank: 1.1";
      textBlock2 = textBlock2 + "\n" + "reviewers: 1776";
      textBlock2 = textBlock2 + "\n" + "cost: $$";
      textBlock2 = textBlock2 + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock2 = textBlock2 + "\n" + "category: Pure awesomeness";
      textBlock2 = textBlock2 + "\n" + "name: Black tree";
      textBlock2 = textBlock2 + "\n" + "city: New York";
      
      //create the two restaurants based on these textBlocks
      Restaurant r1 = new Restaurant ( textBlock );
      Restaurant r2 = new Restaurant ( textBlock2 );
      
      //these should be different
      assertTrue("restaurants with different name are the same!", r1.compareTo(r2) != 0 &&
                 r2.compareTo(r1) != 0);
      
      
      
  }
  
    
  /**
   * This test method tests the functionality of the compareTo method.
   */
  public void testCompare2(){
  
     //build a string with information as found in the text file  
     //for one restaurant
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
       //build a string with information as found in the text file  
     //for another restaurant from the same city and with the same name, but different everything else
      String textBlock2 = "rank: 3.4";
      textBlock2 = textBlock2 + "\n" + "reviewers: 19";
      textBlock2 = textBlock2 + "\n" + "cost: $";
      textBlock2 = textBlock2 + "\n" 
        + "open: Monday 22:30 22:00,Tuesday 01:30 22:00,Thursday 23:30 02:00";
      textBlock2 = textBlock2 + "\n" + "category: random";
      textBlock2 = textBlock2 + "\n" + "name: Awesome Rest";
      textBlock2 = textBlock2 + "\n" + "city: New York";
      
      //create the two restaurants based on these textBlocks
      Restaurant r1 = new Restaurant ( textBlock );
      Restaurant r2 = new Restaurant ( textBlock2 );
      
      //these should be different
      assertTrue("restaurants with same name and city are the different!", r1.compareTo(r2) == 0 &&
                 r2.compareTo(r1) == 0);
      
      
      
  }
  
    /**
   * This test method tests the functionality of the compareTo method.
   */
  public void testCompare3(){
  
     //build a string with information as found in the text file  
     //for one restaurant
      String textBlock = "rank: 1.1";
      textBlock = textBlock + "\n" + "reviewers: 1776";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
       //build a string with information as found in the text file  
     //for another restaurant with the same name, but in a different city
      String textBlock2 = "rank: 3.4";
      textBlock2 = textBlock2 + "\n" + "reviewers: 19";
      textBlock2 = textBlock2 + "\n" + "cost: $";
      textBlock2 = textBlock2 + "\n" 
        + "open: Monday 22:30 22:00,Tuesday 01:30 22:00,Thursday 23:30 02:00";
      textBlock2 = textBlock2 + "\n" + "category: random";
      textBlock2 = textBlock2 + "\n" + "name: Awesome Rest";
      textBlock2 = textBlock2 + "\n" + "city: Philadelphia";
      
      //create the two restaurants based on these textBlocks
      Restaurant r1 = new Restaurant ( textBlock );
      Restaurant r2 = new Restaurant ( textBlock2 );
      
      //these should be different
      assertTrue("restaurants with same name but different city are the same!", r1.compareTo(r2) != 0 &&
                 r2.compareTo(r1) != 0);
      
      
      
  }
  
  /**
   * This method checks the functionality of the addReview method.
   */
  public void testReview(){
    
    //build a string with information as found in the text file  
      String textBlock = "rank: 3.4";
      textBlock = textBlock + "\n" + "reviewers: 100";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
    
      //create the restaurant
      Restaurant r = new Restaurant(textBlock);
      
      //now the summ of all scores should be: 776*1.1
      //double scoreSum = 776*1.1;
     
      //declare variables to use inside the loop
      double newScoreSum = 0;
      double newAverage = 0;
      double reviewOutput = 0;
      
      
      //test with adding different averages
      for (double i = 0.1; i < 5.0; i = i + 0.1 ){

        //when we add the review of i,...
        newScoreSum = r.getNumOfReviewers()*r.getRank() + i;
        
        //...and divide by the number of reviewers plus the one we are adding, we should get the new average
        newAverage = newScoreSum / (r.getNumOfReviewers() + 1);
        
        //get the review output
        reviewOutput = r.addReview(i);
        
        //assert that the addReview method retunrs the same thing (within a very small margin of error)
        assertTrue("Average mismatch at " + i + " review output: " + reviewOutput +
                   " newAverage: " + newAverage, reviewOutput - newAverage < 0.00000001); 
      }
  }
  
  /**
   * This is the same as the testReview method, but tests for when ther restaurant starts
   * with 0 revieweers and a score of 0.
   */
  public void testReview2(){
  
     //build a string with information as found in the text file  
      String textBlock = "rank: 0.0";
      textBlock = textBlock + "\n" + "reviewers: 0";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" + "category: Pure awesomeness";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
    
      //create the restaurant
      Restaurant r = new Restaurant(textBlock);
     
      //declare variables to use inside the loop
      double newScoreSum = 0;
      double newAverage = 0;
      double reviewOutput = 0;
      
      
      //test with adding different averages
      for (double i = 0.1; i < 5.0; i = i + 0.1 ){

        //when we add the review of i,...
        newScoreSum = r.getNumOfReviewers()*r.getRank() + i;
        
        //...and divide by the number of reviewers plus the one we are adding, we should get the new average
        newAverage = newScoreSum / (r.getNumOfReviewers() + 1);
        
        //get the review output
        reviewOutput = r.addReview(i);
        
        //assert that the addReview method retunrs the same thing (within a very small margin of error)
        assertTrue("Average mismatch at " + i + " review output: " + reviewOutput +
                   " newAverage: " + newAverage, reviewOutput - newAverage < 0.00000001); 
      }
  }
  
  /**
   * Although category access is tested for every restaurant in the database,
   * this method tests the category accessor methods again.
   */
  public void testCategoryAccess(){
  
    //build a string with information as found in the text file  
      String textBlock = "rank: 0.0";
      textBlock = textBlock + "\n" + "reviewers: 0";
      textBlock = textBlock + "\n" + "cost: $$";
      textBlock = textBlock + "\n" 
        + "open: Monday 21:30 22:00,Tuesday 21:30 22:00,Wednesday 11:30 02:00";
      textBlock = textBlock + "\n" 
        + "category: Pure awesomeness,Random,Great,Brittish,Scottish,Asian Fusion,Italian,American (new)";
      textBlock = textBlock + "\n" + "name: Awesome Rest";
      textBlock = textBlock + "\n" + "city: New York";
      
      //create a restaurant based on that
      Restaurant r = new Restaurant(textBlock);
      
      ArrayList<String> categoriesArr = new ArrayList<String>();
      
      //access all of the categories, store them in an array list
      while ( r.hasNextCategory() ){
        categoriesArr.add( r.getCategory() );
      }
      
      //check on that array's size
      assertTrue("Wrong number of categories accessed", categoriesArr.size() == 8);
      
      //get the original categories in an array
      ArrayList<String> origCats = new ArrayList<String>(8);
      
      origCats.add("Pure awesomeness");
      origCats.add("Random");
      origCats.add("Great");
      origCats.add("Brittish");
      origCats.add("Scottish");
      origCats.add("Asian Fusion");
      origCats.add("Italian");
      origCats.add("American (new)");
      
      //go through it and make sure that everything in that is contained within the categoriesArr
      for ( String curr : origCats ){
    
        assertTrue("Something not found in categoriesArr " + curr, categoriesArr.contains(curr));
     }
      
      //do the same the other way around
      //go through it and make sure that everything in that is contained within the categoriesArr
      for ( String curr : categoriesArr ){
    
        assertTrue("Something not found", origCats.contains(curr));
     }
      
      
      
  }
}
  


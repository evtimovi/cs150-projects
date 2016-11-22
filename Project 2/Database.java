import java.util.*;
import java.io.*;


/**
 * This class provides the functionality for storing and accessing
 * the entries for the different restaurants. It uses several different
 * containers to allow access to the restaurant objects by
 * name, city, category, rank, and cost.
 * The class also provides a method to scan in the database from a file
 * and to write the database to a file.
 * Finally, the class allows restricted modifications of the database 
 * for adding a review or adding a new entry.
 * @author Ivan Evtimov
 */
public class Database{
  
  /**
   * This field holds the objects for access by rank.
   * Hence, the key of this Map container is the rank of the Restaurant object,
   * which is a double and the value is another container of restaurant objects.
   * Since ordering for the rank is important, I use a TreeMap.
   */
  private TreeMap<Double, ArrayList<Restaurant>> rankCont;
  
   /**
   * This field holds the objects for access by cost.
   * Hence, the key of this Map container is the cost of the Restaurant object,
   * which is a string and the value is another container of restaurant objects.
   * Since ordering for the cost is important, I use a TreeMap.
   */
  private TreeMap<String, ArrayList<Restaurant>> costCont;
  
  
  
   /**
   * This field holds the objects for access by category.
   * Hence, the key of this Map container is the category of the Restaurant object,
   * which is a string, and the value is another container of restaurant objects.
   * Since ordering for the category doesn't matter, I use a HashMap.
   */
  private HashMap<String, ArrayList<Restaurant>> catCont;
  
  
  /**
   * This field holds the objects for access by city.
   * Hence, the key of the Map is the city of the Restaurant object,
   * which is a String, and the value is another container of restaurant objects.
   */
  private TreeMap<String, ArrayList<Restaurant>> cityCont;
  
  /**
   * This is the standard container that has the restaurants ordered by name.
   */
  private HashMap<String, ArrayList<Restaurant>> nameCont;
  
  /**
   * This field stores all restaurants in no particular order, for the purposes
   * of quick access of the whole database.
   */
  private ArrayList<Restaurant> allCont;
  
  /**
   * The constructor initializes all of our containers and does nothing else that is interesting.
   */
  public Database(){
  
    rankCont = new TreeMap<Double, ArrayList<Restaurant>>();
    costCont = new TreeMap<String, ArrayList<Restaurant>>();
    catCont = new HashMap<String, ArrayList<Restaurant>>();
    nameCont = new HashMap<String, ArrayList<Restaurant>>();
    cityCont = new TreeMap<String, ArrayList<Restaurant>>();
    allCont = new ArrayList<Restaurant>();
  }
  
  /**
   * This method creates the database from the file.
   * It first scans the file in blocks of code.
   * Then it passes each block of code to the internal containers, each
   * of which has a different ordering.
   * @param inputFilePath the path to the input file in a String
   */
  public void createDatabaseFromFile( String inputFilePath ){
  
    //declare the scanner (allows access to it from the finall block
    Scanner sc = null;
    
    //scanner might cause an exceptin
    try{
    
      //create a new scanner from a buffered reader based on the file path specified
      sc = new Scanner ( new BufferedReader( new FileReader ( inputFilePath ) ) ) ;
      
      //set the scanner's delimiter so that it reads one block of restaurant at a time
      sc.useDelimiter("\n\n");
      
      //initialize a string var for the block of restaurant data
      String block = "";
      
      //int debuggerCounter = 0;
        
      //as long as there are more tokens in the scanner
      while ( sc.hasNext() ){
      
        //get the block of data for the restaurant
        block = sc.next();
        
        //create a new temp restaurant based on that data
        Restaurant tempRestaurant = new Restaurant(block);
        
        //add it to the rank container
        addToRankCont( tempRestaurant );

        //add it to the cost container
        addToCostCont( tempRestaurant );
        
     //   debuggerCounter++;
        
        //add it to the category container
        addToCatCont( tempRestaurant );
        
        //add it to the name container
        addToNameCont( tempRestaurant );
        
        //add to the city container
        addToCityCont( tempRestaurant );
        
        //add it to the generic container
        addToAllCont( tempRestaurant );
        
        //System.out.println(r);
      }
      
   //   System.out.println("database 124: restaurants added: " + debuggerCounter);
    }
    catch (Exception e){
        
        //print out the exception and its stack trace
        System.out.println(e);
        e.printStackTrace();
    }
    finally {
      //close the scanner 
      if (sc != null)
        sc.close();
    }
    
  }
  
  /**
   * This method adds a restaurant to the allContainer.
   * @param rest the restaurant we want added
   */
  private void addToAllCont( Restaurant rest ){
  
    //add to allCont
   boolean addingResult = allCont.add( rest );
   
   if( addingResult == false ){
     System.out.println("Database 158: " + addingResult + " for restaurant: " + rest);
   }
  }
  
  /**
   * This is a private method to perform insertion in the container that 
   * stores values ordered according to what city they are from.
   * @param currRestaurant the restaurant we are adding
   */
  private void addToCityCont( Restaurant currRestaurant ){
  
    //get the restaurant's city
    String currCity = currRestaurant.getCity();
    
    //check if a key (that city) with that value is present
    if ( cityCont.containsKey( currCity ) ){
    
      //if so, add the restaurant to the container associated with that key
      cityCont.get( currCity ).add( currRestaurant );
    } else {
    
      //if it is not present, create a value (container) to associate with that key
      ArrayList<Restaurant> newCont = new ArrayList<Restaurant>();
      
      //add the restaurant to that container
      newCont.add( currRestaurant );
      
      //add the container to the map
      cityCont.put( currCity, newCont );
    }
  }
  
  /**
   * This is a private helper method to add the restaurant to 
   * the standard contaienr where they are sorted by name and then city.
   */
  private void addToNameCont( Restaurant currRest ){
  
    //get the current restaurant's name
    String currName = currRest.getName();
    
     //check if this name is already present in the hash map
    if ( nameCont.containsKey( currName ) ){
    
      //if so, get the container associate with that key and add the restaurant to it
      nameCont.get( currName ).add( currRest );
      
    } else {
    
      //if the key doesn't already exist, create a new container to be associated with it
      ArrayList<Restaurant> newCont = new ArrayList<Restaurant>();
      
      //add the restaurant to the new container
      newCont.add( currRest );
      
      nameCont.put( currName, newCont );
    }
  }
  
  /**
   * This is a private helper method to perform the addition in the
   * tree map by rank.
   * @param currRest the Restaurant object we want to add
   */
  private void addToRankCont( Restaurant currRest ){
    
    //get the object's rank
    Double currRank = currRest.getRank();
    
    //check if this rank is already present in the tree map
    if ( rankCont.containsKey( currRank ) ){
    
      //if so, get the container associate with that key and add the restaurant to it
      rankCont.get( currRank ).add( currRest );
      
    } else {
    
      //if the key doesn't already exist, create a new container to be associated with it
      ArrayList<Restaurant> newCont = new ArrayList<Restaurant>();
      
      //add the restaurant to the new container
      newCont.add( currRest );
      
      rankCont.put( currRank, newCont );
    }
  }
  

  
  
  
  /**
   * This is a private helper method to perform the addition in the
   * tree map by rank.
   * @param currRest the Restaurant object we want to add
   */
  private void addToCostCont( Restaurant currRest ){
    
    //get the object's cost
    String currCost = currRest.getCost();

    
    //check if this cost is already present in the tree map
    if ( costCont.containsKey( currCost ) ){
    
      //if so, get the container associate with that key and add the restaurant to it
      boolean addRes = costCont.get( currCost ).add( currRest );
      
      if( addRes == false ){
        System.out.println( currRest );
      }
    
   } else {
    
      //if the key doesn't already exist, create a new container to be associated with it
      ArrayList<Restaurant> newCont = new ArrayList<Restaurant>();
      
      //add the restaurant to the new container
      newCont.add( currRest );
      
      costCont.put( currCost, newCont );
      
    }
  }
  
  
  /**
   * This method returns an array of all objects with a given cost.
   * @param cost the cost we want to have the objets with in the dollar sign format!
   * @return an arraylist of all objects with that cost currently in the database,
   * null if no objects are with this cost
   */
  public ArrayList<Restaurant> getAllWithCost( String cost ){
  
    //get the container associated with that key
    ArrayList<Restaurant> keyCont = costCont.get( cost );//by the way, this would throw a NullPointer
                                                      //if there is no key like the cost
  
    //if key container is null, then there are no restaurants with this cost, so return null
    if ( keyCont == null ){
      return null;
    }

    //return the array list of restaurants
    return keyCont;
  }
  
  /**
   * This method returns an array of all objects with a given rank.
   * @param rank the rank we want to have the objets with
   * @return an arraylist of all objects with that rank currently in the database
   */
  public ArrayList<Restaurant> getAllWithRank( Double rank ){
  
    //get the container associated with that key
    ArrayList<Restaurant> keyCont = rankCont.get( rank );
  
    //return the array list of restaurants
    return keyCont;
  }
  
  /**
   * This method adds to the category container.
   */
  private void addToCatCont( Restaurant currRest ){
 
    //declare a string for the current category
    String currCat = "";
    
    //multiple repetitions needed for each category
    while ( currRest.hasNextCategory() ){
      
      //associate the currCat with the next non-accessed category
      currCat = currRest.getCategory();
      
      //the usual procedure
      //check if this category is already present in the tree map
      if ( catCont.containsKey( currCat ) ){
        
        //if so, get the container associate with that key and add the restaurant to it
        catCont.get( currCat ).add( currRest );
        
      } else {
        
        //if the key doesn't already exist, create a new container to be associated with it
        ArrayList<Restaurant> newCont = new ArrayList<Restaurant>();
        
        //add the restaurant to the new container
        newCont.add( currRest );
        
        catCont.put( currCat, newCont );
      }
      
    }
  }

    /**
   * This method returns an array of all objects with a given category.
   * @param category the category we want to have the objets with
   * @return an arraylist of all objects with that category currently in the database
   * @throws IllegalArgumentException if the category is not present in the database
   */
  public ArrayList<Restaurant> getAllWithCategory( String category ) throws IllegalArgumentException{
  
    //check if the category exists at all
    if ( catCont.containsKey( category ) ){
      
      //get the container associated with that key
      ArrayList<Restaurant> keyCont = catCont.get( category );
          
      return keyCont;
      
    } else {
    
      //if the category is not found in the database, throw an exception to indicated that
      throw new IllegalArgumentException("category not found in database!" );
    }
  }
  
  /**
   * This method returns the restaurant(s) with the given name and city in an ArrayList.
   * @param city the city the restaurant is located in
   * @param name the name of the restaurant
   * @return the Restaurants with that name and city (if present) in an ArrayList, null if not present
   */
  public ArrayList<Restaurant> getByNameAndCity( String name, String city ){
  
    //get the tree set with the restaurants associated with the given name
    ArrayList<Restaurant> finalCont = nameCont.get( name );
    
    //return null if no restaurant is present with this name
    if (finalCont == null){
      return null;
    }
    
    //get the restaurants from the given city
    ArrayList<Restaurant> restFromCity = cityCont.get( city ); 

    //if no restaurants exist in that city in the database
    if ( restFromCity == null ){
      return null;
    }
    
    //throw out those restaurants that are not in the specified city
    finalCont.retainAll( restFromCity );
    
    //if nothing is left in that container, return null
    if ( finalCont.size() == 0 ){
      return null;
    }
    
    //return whatever is left, this would be null 
    return finalCont; 
  }
  
  /**
   * This method returns all restaurants in a given city.
   * @param city the city we ware looking for restaurants in
   * @return all restaurants in the specified city in an ArrayList
   */
  public ArrayList<Restaurant> getFromCity( String city ){
  
    //check if the city exists at all
    if ( cityCont.containsKey( city ) ){
      
      //get the container associated with that key
      ArrayList<Restaurant> keyCont = cityCont.get( city );
      
      //create the array list that we will return
      ArrayList<Restaurant> restArr = new ArrayList<Restaurant>();
      
      //iterate over tall of the restaurants in that tree set to the array list
      for ( Iterator it = keyCont.iterator(); it.hasNext(); ){
        
        //add 
        restArr.add( (Restaurant) it.next() );
      }
      
      return restArr;
      
    } else {
    
      //if the category is not found in the database, throw an exception to indicated that
      throw new IllegalArgumentException("city not found in database!" );
    }
  }
  
  /**
   * This method is designed to return all restaurants
   * with the given search criteria (only one category allowed!).
   * Please, note that the wildcard (*) can be used on all
   * parameters with the exception of city. 
   * For the time, as long as a wildcard is passed either for the day or the time,
   * the search method assumes that both the day and time are irrelevant
   * (since we cannot determine whether the restaurant is open without one of them).
   * @param city the city we want to eat in
   * @param category the category we are looking for
   * @param cost the cost we are looking for
   * @param currDay the day and day we want to eat on, written out fully
   * @param currTime the current time, given in a 24-hour format with leading zero
   * @return an ArrayList with all the restaurants that match this criteria, null if no restaurant matches all
   * of the criteria
   */
  public ArrayList<Restaurant> search( String city, String category, String cost, String currDay, String currTime ){
  
    try{
      //first get the all the restaurants from this city
      ArrayList<Restaurant> finalArr = getFromCity (city);
      
      //check for wild card and only remove if there is none
      
      if ( category.equals("*") == false ){
        
       // System.out.println("Entered 449 in Database");
        
        //get the restaurants from this category
        ArrayList<Restaurant> catArr = getAllWithCategory( category );
        
        //remove all that do not match the category
        //prune the final list to get it rid of all that don;t match the category
        finalArr.retainAll ( catArr );
      }
      
      //check for wildcard in cost (if there is one, this step will be skipped)
      if( cost.equals("*") == false ){
        //get the restaurants with a given cost
        ArrayList<Restaurant> costArr = getAllWithCost( cost );
      
        //get rid of the restaurants that don't match the cost
        finalArr.retainAll( costArr );
      
      }
      
      //check for wildcard with time
      if( currTime.equals("*") == false && currDay.equals("*") == false ){
        //get the open restaurants
        ArrayList<Restaurant> openArr = new ArrayList<Restaurant>();
        
        //go through the array
        for ( Restaurant curr : finalArr ){
          
          //add everything that is open to the openArr
          if (curr.isOpen(currDay, currTime) ){
            
            openArr.add( curr );
          }
        }
     
         //get rid of all that are closed
         finalArr.retainAll( openArr );
      }
      
     // System.out.println("Final array before returning: " + finalArr );
      
      //return what's left if the array is not empty
      if (finalArr.size() > 0){
        
        return finalArr;
      } 
      
      else {
        
        return null;//if the array is empty
      }
      

    } catch (IllegalArgumentException e){
      
      System.out.println("Nasty exception says hey! (line 524 in Database) " + e);
      
      //if such an exception was thrown, a category or a cost was not found
      return null;
    
    }
  }
  
   /**
   * This method is designed to return all restaurants
   * that match the search criteria exactly. 
   * If there is more than one category specified in the ArrayList for categories,
   * then the method only returns all restaurants that have all of those categories.
   * If the restauarant is missing one category from the specified list, it will not be returned.
   * If a restaurant has additional categories, however (besides the ones in the array list, 
   * it will be returned because it matches the search criteria.
   * Please, note that the wildcard (*) can be used on all
   * parameters with the exception of category. 
   * For the time, as long as a wildcard is passed either for the day or the time,
   * the search method assumes that both the day and time are irrelevant
   * (since we cannot determine whether the restaurant is open without one of them).
   * @param city the city we want to eat in
   * @param categories an array list of the categories we are looking for
   * @param cost the cost we are looking for
   * @param currDay the day and day we want to eat on, written out fully
   * @param currTime the current time, given in a 24-hour format with leading zero
   * @return an ArrayList with all the restaurants that match this criteria, null if no restaurant matches all
   * of the criteria
   */
  public ArrayList<Restaurant> search
    ( String city, ArrayList<String> categories, String cost, String currDay, String currTime ){
  
    try{
      //first get the all the restaurants from this city
      ArrayList<Restaurant> finalArr = getFromCity (city);
      
     
      //check for wildcard in cost (if there is one, this step will be skipped)
      if( cost.equals("*") == false ){
        //get the restaurants with a given cost
        ArrayList<Restaurant> costArr = getAllWithCost( cost );
      
        //get rid of the restaurants that don't match the cost
        finalArr.retainAll( costArr );
      
      }
      
      //check for wildcard with time
      if( currTime.equals("*") == false && currDay.equals("*") == false ){
        //get the open restaurants
        ArrayList<Restaurant> openArr = new ArrayList<Restaurant>();
        
        //go through the array
        for ( Restaurant curr : finalArr ){
          
          //add everything that is open to the openArr
          if (curr.isOpen(currDay, currTime) ){
            
            openArr.add( curr );
          }
        }
     
         //get rid of all that are closed
         finalArr.retainAll( openArr );
      }
 
      //this variable temporarily stores all of the current restaurant's categories
      ArrayList<String> currCatArr;
      
      //this will hold all of the restaurants that are to be removed from the final array
      ArrayList<Restaurant> toBeRemovedArr = new ArrayList<Restaurant>();
      
      //go through the parameter categories
      for ( String currParamCat : categories ){
        
        //go through the final array
        for ( Restaurant curr : finalArr ){
          
          //get the current restaurant's categories
          currCatArr = curr.getAllCategories();
                      
            //check if the parameter array contains the current category in the parameter
            if ( currCatArr.contains ( currParamCat ) == false ){
              
              //add the current restaurant to the removal list
              toBeRemovedArr.add( curr );
              
            }
            
          }
        }

      //System.out.println("databsae 624: toberemovedarr: " + toBeRemovedArr);
      
      //finally, remove all of the restaurants slated for removal
      finalArr.removeAll( toBeRemovedArr );
      
      //System.out.println("database 627 finalArr: " + finalArr);
     // System.out.println("Final array before returning: " + finalArr );
      
      //return what's left if the array is not empty
      if (finalArr.size() > 0){
        
        return finalArr;
      } 
      
      else {
        
        return null;//if the array is empty
      }
      

    } catch (IllegalArgumentException e){
      
      System.out.println("Nasty exception says hey! (line 524 in Database) " + e);
      
      //if such an exception was thrown, a category or a cost was not found
      return null;
    
    }
  }
  
   /**
   * This method provides a more generic search possibility
   * where multiple categories can be entered in an ArrayList.
   * The method returns all restaurants that match at least one of those categories.
   * No wildcard allowed for the categories as they are entered as an arrayList.
   * @param city the city we want to eat in
   * @param categoriesArr the categories we are looking for in an ArrayList
   * @param cost the cost we are looking for
   * @param currDay the day and day we want to eat on, written out fully
   * @param currTime the current time, given in a 24-hour format with leading zero
   * @return an ArrayList with all the restaurants that match this criteria, null if no restaurant matches all
   * of the criteria
   */
  public ArrayList<Restaurant> searchWithLessMatchingCategories
    ( String city, ArrayList<String> categoriesArr, String cost, String currDay, String currTime ){
  
    
    try{
      //declare some variables to be used in the loop
      ArrayList<ArrayList<Restaurant>> twoDimensionalArr = new ArrayList<ArrayList<Restaurant>>(categoriesArr.size());

      //declare a temp variable for the array of categories
      ArrayList<Restaurant> catArr;
      
      //declare a counter to move across the dimensions
      int counter = 0;
      
      for ( String currCat : categoriesArr ){ 
      
        
        //get the restaurants from this category
         catArr = getAllWithCategory( currCat );
        
        //add them in the appropriate column of the two-dimensional array
        twoDimensionalArr.add( catArr );
        
        //indent the counter
        counter++;
        
        
      }
      
      //get rid of all the restaurants that are not in the same city in all of the categories
      ArrayList<Restaurant> cityArr = getFromCity( city );
      
      //go through the two-dimensional array and get rid of those restaurants that are not in this city for all cats
      for( ArrayList<Restaurant> curr : twoDimensionalArr ){
          curr.retainAll ( cityArr );
      }
      
      //check for wildcard in cost (if there is one, this step will be skipped)
      if( cost.equals("*") == false ){
        //get the restaurants with a given cost
        ArrayList<Restaurant> costArr = getAllWithCost( cost );
      
        //go through the two-dimensional array and get rid of those restaurants that do not match the cost
        for( ArrayList<Restaurant> curr : twoDimensionalArr ){
          curr.retainAll ( costArr );
        }
      
      }
      
      //check for wildcard with time
      if( currTime.equals("*") == false && currDay.equals("*") == false ){
        //get the open restaurants
        ArrayList<Restaurant> openArr = new ArrayList<Restaurant>();

       //go through the two-dimensional array and get rid of those restaurants that are closed for all categories
        for( ArrayList<Restaurant> curr : twoDimensionalArr ){
          
          //go through the array for the current category
          for ( Restaurant currRest : curr ){
            //add everything that is open to the openArr
            if (currRest.isOpen(currDay, currTime) ){
              openArr.add( currRest );
          }
        }
        
          //get rid of the closed ones for this category
          curr.retainAll ( openArr );
        }
      }
      
      //prepare the final array for returning
      ArrayList<Restaurant> finalArr = new ArrayList<Restaurant>();
      
      //put all the categories into it
      for ( ArrayList<Restaurant> curr : twoDimensionalArr ){
      
        //this will add restaurants that have more than one category twice
        finalArr.addAll ( curr );
      }
      
      //return what's left if the array is not empty
      if (finalArr.size() > 0){
        
        return finalArr;
      } 
      
      else {
        
        return null;//if the array is empty
      }
      

    } catch (IllegalArgumentException e){
      
      
      //if such an exception was thrown, a category or a cost was not found
      return null;
    
    }
  }
  
  /**
   * This method adds a new review for (all) restaurants with the specified
   * name and city. 
   * @param city the city where the restaurants are located in
   * @param name the name of the restaurants
   * @param score the new score of the restaurant
   * @return the ArrayList of Restaurants that were updated, null if the restaurant with that name
   * and city was not found
   */
  public ArrayList<Restaurant> addReview ( String name, String city, double score ){
  
    //get all restaurants with the name and city
    ArrayList<Restaurant> restArr = this.getByNameAndCity( name, city );
    
    //if the restaurant with that name and city was not found
    if ( restArr == null ){
      return null;
    }
    
    //go throuhg the array list
    for ( Restaurant curr : restArr ){
    
      //update every restaurant in it
      curr.addReview( score );
    }
    
    //return the restaurants as found in the database
    return this.getByNameAndCity( name, city );
  }
  
  /**
   * This method provides the functionality needed to add a new restaurant to 
   * the knowledge base. Since all new restaurants start out with a rank of 0 and no
   * reviewers, it only accepts name, city, category, days when its open, and cost as parameters.
   * @param name the name of the new restaurant
   * @param city the city of the new restaurant
   * @param category the category/ies of the new restaurant
   * @param open the opening hours of the new restaurant, as a string
   * @param cost the cost of the new restaurant ($$$)
   */
  public void addNewRestaurant( String name, String city, String category, String open, String cost ){
    
    //build the text block the restaurant object is expecting
    String block = "name: " + name +"\n";
    block = block + "city: " + city + "\n";
    block = block + "category: " + category + "\n";
    block = block + "open: " + open + "\n";
    block = block + "cost: " + cost + "\n";
    block = block + "reviewers: " + "0" + "\n";
    block = block + "rank: " + "0.0" + "\n";
    
    
     //create a new temp restaurant based on that block
     Restaurant tempRestaurant = new Restaurant( block );
        
        //add it to the rank container
     addToRankCont( tempRestaurant );

        //add it to the cost container
     addToCostCont( tempRestaurant );
        
        //add it to the category container
     addToCatCont( tempRestaurant );
        
        //add it to the name container
     addToNameCont( tempRestaurant );
        
        //add to the city container
     addToCityCont( tempRestaurant );
     
     //add it to the generic container
     addToAllCont( tempRestaurant );
  }
  
  /**
   * This method returns all restaurants in no particluar order.
   * @return all restaurants in the database in no particular order.
   */
  public ArrayList<Restaurant> getAll(){
    return allCont;
  }
}
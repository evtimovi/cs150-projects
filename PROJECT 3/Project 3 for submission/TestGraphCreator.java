import junit.framework.TestCase;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

/**
 * A JUnit test case class for the functionality of the GraphCreator class.
 * Please, note that since the names of the files are preset in the GraphCreator class
 * and can never be modified by an external user of the class, these tests also work
 * with the same files by scanning them independently and comparing the results
 * with those of the graph returned by the GraphCreator class.
 * 
 * @author Ivan Evtimov
 */
public class TestGraphCreator extends TestCase {
  
  /**
   * Field that stores the universal graph creator used throughout the test.
   */
  private GraphCreator testGC;
  
  /**
   * Field that stores the graph created by the testGC.
   */
  private Graph testGraph;
  
  /**
   * Setup method to initialize everything as needed.
   */
  public void setUp(){
  
    testGC = new GraphCreator();//initialize the creator
    
    testGraph = testGC.createGraph();//get the graph it created
  }
  
  /**
   * A test method to test the connectivity of the graph.
   * This method takes a ridiculous amount of time to complete since
   * I need to account for the possibility that a connection between two edges is 
   * specified more than once in the file. I do that by keeping a record
   * of which connections were already created, so that I can ignore
   * the second one. This is what the GraphCreator should be doing
   * and it accomplishes it in a fair amount of time since it only checks
   * the graph for possible duplication before creating the edge.
   * However, in this test, I keep the record of which connections
   * occured in a TreeSet and have to search through it every time
   * I want to test for a connection. Even though that's just logn, it might
   * be a big n because I have to keep a record of both directions.
   */
  public void testConnectivity() {
    
    //declare a data structure that will store all the connections that have already appeared once
    //ArrayList<String> connectionsStringArr = new ArrayList<String>();
    TreeSet<String> conenctionsStringSet = new TreeSet<String>();//faster than array
    
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "connectivity.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(\\d+)\\s-\\s(\\d+)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //string for temporary keys
      String tempKeyString1 = "";
      String tempKeyString2 = "";
      
      int key1, key2, weight, indexOfPreviousOccurence1, indexOfPreviousOccurence2;
      boolean previousOccurence1, previousOccurence2;
      
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();

        //the weight of the edge should match the one specified by the text file
        //get the matcher
        mat = pat.matcher(tempString);
        System.out.println(tempString);
        //find everything
        mat.find();
          
        //get the keys and weight
        key1 = Integer.parseInt( mat.group(1) );
        key2 = Integer.parseInt( mat.group(2) );
        weight = Integer.parseInt( mat.group(3) );
        
        //build a temporary string of keys
        tempKeyString1 = key1 + " - " + key2;
        tempKeyString2 = key2 + " - " + key1;//for reverse since unweighted
        
        //check if that connection was already added
       // indexOfPreviousOccurence1 = connectionsStringArr.indexOf( tempKeyString1 );
        //indexOfPreviousOccurence2 = connectionsStringArr.indexOf( tempKeyString2 );//for reverese since unweighted
        previousOccurence1 = conenctionsStringSet.contains( tempKeyString1 );
        previousOccurence2 = conenctionsStringSet.contains( tempKeyString2 );
        
        if( previousOccurence1 == false || previousOccurence2 == false){
          //no problem here, 
          //the connection wasn't already present, 
          //just go on, but add the tempKeyString-s to the set
          conenctionsStringSet.add( tempKeyString1 );
          conenctionsStringSet.add( tempKeyString2 );
          
        } else {
          //if the connection was already scanned in,
          //then do not test 
          //(skip the test since the connection 
          //will have a different weight than the one currently specified)
          continue;
        }
        //now! assert that the weight between those two vertices 
        //in the graph is the same as the one from the file
        //note that getWeight would return 0
        //if one vertex or the edge doesn't exist
        assertEquals("btw " + key1 + " and " + key2, weight, testGraph.getWeight(key1, key2) );
        
         
        
      }
     
      
    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  }
  
  /**
   * Tests whether the needs of each store have been properly set
   * by comparing them against an independent scan of needs.txt
   */
  public void testNeeds(){
  
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "needs.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(\\d+)\\s(\\w+)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int key, need;
      String resource = "";
      Store tempStore = null;
      
      
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //find everything
        mat.find();
        
        //get the key, resource and need
        key = Integer.parseInt( mat.group(1) );
        resource =  mat.group(2);
        need = Integer.parseInt( mat.group(3) );
        
        tempStore = (Store) testGraph.get( key );
        
        //now! assert that the need of a store
        //in the graph is the same as the one from the file
        assertEquals("problem at key: " + key, need, tempStore.getNeedFor(resource) );

        
      }
     
      
    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  }
  
  /**
   * Tests locations of stores and warehouses against an independent
   * scan of the locations.txt
   */
  public void testStoreAndWarehouseLocations(){
    
     //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "locations.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(\\w+)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int key;
      String type = "";
      
      
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //find everything
        mat.find();
        
        //get the key
        key = Integer.parseInt( mat.group(2) );
        
        //assert the corresponding thing in the graph is not null
        assertFalse("Problem with key: " + key + " " + mat.group(2) , testGraph.get( key ) == null );
        
      }
     
      
    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
  /**
   * More precise est for locations of stores through an independent
   * scan of the locations.txt
   */
  public void testStoreLocations(){
    
     //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "locations.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(store)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int key;
      String type = "";
      Store tempStore;
      
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //if a store found
        if( mat.find() ){
        
          //get the key
          key = Integer.parseInt( mat.group(2) );
          
          try{
            //attempt to cast this object into a store,
            tempStore = (Store) testGraph.get( key );
            
            tempStore.getNeedFor("apples");//attempt to do smtg with the store to generate a null-pointer if not there
            
          } catch (ClassCastException e){
            fail("Object is not a store!");//if a class cast exception is thrown, then this is definitely not a store
         
          } catch (NullPointerException e){
            fail("Object missing!");//there should be a store there!
          }
       
        } else {
          
          continue;//we are not dealing with a store at this location to begin with, so skip it
        }
          
      }

    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
   /**
   * More precise est for locations of stores through an independent
   * scan of the locations.txt
   * While we are at it, why not test for the appropriate number of trucks?
   */
  public void testWarehouseLocationsAndNumOfTrucks(){
    
     //declare the Scanners
    Scanner sc = null;
    Scanner sc2 = null;
    
    try{
      
      //initialize the scanner and open the input stream
      sc2 = new Scanner( new BufferedReader( new FileReader( "resources.txt" ) ));
      
      //compile a pattern
      Pattern pat1 = Pattern.compile("(warehouse):\\s(\\d+)");
      
      //declare the matcher
      Matcher mat1 = null;
      
      //declare the tempString
      String tempString1 = "";
      
      //declare some vars to be used in the loop
      int trueNumOfTrucks = 0;
      boolean findResult;

      while ( sc2.hasNextLine() ){
      
        //scan in the next line
        tempString1 = sc2.nextLine();
        
        //get the matcher
        mat1 = pat1.matcher(tempString1);
        
        //find to capture groups
        findResult = mat1.find();
 
        if( findResult == false ){
          continue;//not a warehouse
        }
        
        //get the mileage that's supposed to be
        trueNumOfTrucks = Integer.parseInt( mat1.group(2) );
      }
      
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "locations.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(warehouse)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int key;
      String type = "";
      Warehouse tempWarehouse;
      
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //if a store found
        if( mat.find() ){
        
          //get the key
          key = Integer.parseInt( mat.group(2) );
          
          try{
            //attempt to cast this object into a store,
            tempWarehouse = (Warehouse) testGraph.get( key );
            
            //attempt to do smtg with the store to generate a null-pointer if not there
            //...and test the num of trucks at the same time
            assertEquals("wow at remaining trucks", trueNumOfTrucks, tempWarehouse.getRemainingTrucks());
            
          } catch (ClassCastException e){
            fail("Object is not a warehouse!");//if a class cast exception is thrown, then this is definitely not a store
         
          } catch (NullPointerException e){
            fail("Object missing!");//there should be a store there!
          }
       
        } else {
          
          continue;//we are not dealing with a store at this location to begin with, so skip it
        }
          
      }

    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
          
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
      
       //check if the reader was initialized
      if( sc2 != null ){
        //closure might throw exception
        try{
          sc2.close();//always close the input stream
          
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
    
   /**
   * Test the capacity needs through an independent scan of the resources.txt
   */
  public void testCapacityNeeds(){
    
    //get the needs that have been scanned in
    HashMap<String, Integer> needs = testGC.getCapacityNeeds();
    
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "resources.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(\\w+):\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int need, storedNeed;
      String resource = "";
      boolean findResult;
      
   
      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //find to capture groups
        findResult = mat.find();
          
        if(findResult == false ){
          continue;//skipp, it's probably a truck
        }
        
        //check if it is a truck or a warehouse
        if( mat.group(1).equals("truck") || mat.group(1).equals("warehouse")){
          continue;//skip this, it's not a resource
        }
        
        //get the resource
        resource = mat.group(1);
        
        //get the need that's supposed to be
        need = Integer.parseInt( mat.group(2) );
        
        //get the stored need
         storedNeed = needs.get(resource);
         
        //asert same capacity need
        assertEquals("Wow at resource: " + resource, need, storedNeed);

      }

    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
      fail("!");
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
       fail("2");
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
  /**
   * Test mileage and maximum capacity of truck.
   */
  public void testMileageAndCapacity(){
    
    //get the needs that have been scanned in
    int mileage = testGC.getMileage();
    int capacity = testGC.getMaxCapacity();
    
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "resources.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(truck)\\s(\\d+)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int trueMileage, trueCapacity;
      boolean findResult;

      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //find to capture groups
        findResult = mat.find();
 
        if( findResult == false ){
          continue;//not a truck
        }
        
        //get the mileage that's supposed to be
        trueMileage = Integer.parseInt( mat.group(3) );
        
        //get the capacity that's supposed to be
        trueCapacity = Integer.parseInt(mat.group(2));
        
        //assertions
        assertEquals("wow at capacity", trueCapacity, capacity);
        assertEquals("Wow at mileage", trueMileage, mileage);

      }

    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
      fail("!");
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
       fail("2");
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
 /**
   * Test the number of trucks.
   */
  public void testNumOfTrucks(){
    
    //get the needs that have been scanned in
    int storedNumOfTrucks = testGC.getNumOfTrucks();
    
    //declare the Scanner
    Scanner sc = null;
    
    try{
      //initialize the scanner and open the input stream
      sc = new Scanner( new BufferedReader( new FileReader( "resources.txt" ) ));
      
      //compile a pattern
      Pattern pat = Pattern.compile("(warehouse)\\s(\\d+)");
      
      //declare the matcher
      Matcher mat = null;
      
      //declare the tempString
      String tempString = "";
      
      //declare some vars to be used in the loop
      int trueNumOfTrucks;
      boolean findResult;

      while ( sc.hasNextLine() ){
      
        //scan in the next line
        tempString = sc.nextLine();
        
        //get the matcher
        mat = pat.matcher(tempString);
        
        //find to capture groups
        findResult = mat.find();
 
        if( findResult == false ){
          continue;//not a warehouse
        }
        
        //get the mileage that's supposed to be
        trueNumOfTrucks = Integer.parseInt( mat.group(2) );
       
        
        //assertions
        assertEquals("wow at num of trucks", trueNumOfTrucks, storedNumOfTrucks);

      }

    } 
    catch (IOException e){
      System.out.println("Input not scanned in due to exception: " + e);
      fail("!");
    } 
    catch (IllegalStateException e){
       System.out.println("Input not scanned in due to exception: " + e);
       fail("2");
    }
    
    //^all other exceptions are fair game (should make the test fail)
    
    finally{
      
      //check if the reader was initialized
      if( sc != null ){
        //closure might throw exception
        try{
          sc.close();//always close the input stream
        } catch (Exception e){
          System.out.println("Input not closed due to exception (connectivity): " + e);
          
        }
      }
    }
  
  }
  
  
}

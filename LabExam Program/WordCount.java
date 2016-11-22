import java.util.*;

/**
 * This class implements the counting interface and thus provides the functionality needed
 * to store the number of occurences of a word in a given file.
 * @author Ivan Evtimov
 */
public class WordCount implements Counting{
  
  /**
   * This field should be the actual container of 
   * the occurences of the words. 
   * The key of the tree map should be the word itself,
   * the value is the number of the word's occurences.
   */
  private TreeMap<String, Integer> data;
  
  /**
   * The constructor creates the database of word counts.
   * Since we have no information about the words or what the database will contain,
   * it takes no parameters.
   * It just initializes the class's internal container
   */
  public WordCount(){
  
    //initialize the TreeMap
    data = new TreeMap<String, Integer>();
  }
  
    /**
     * Assuming that a word is not alreayd in the container, 
     * add it to the container. Set its number of occurences to 1.
     * @param word the word we want to add (first occurence of it)
     * @throws UnsupportedOperationException if the word is already present
     */
  public void add( String word ) throws UnsupportedOperationException{
    
    //first, check if the word is already present
    if ( present(word) == false ){
      
      //if it is not, associate it with 1 occurence
      data.put (word, 1);
    
    } else {
    
      //since the word is already present, throw an exception to indicate
      //that a word cannot be added twice
      throw new UnsupportedOperationException("Word cannot be added twice! Consider the increment() method.");
    }
    
  }
  
  
  /**
   * This method checks if the word that has been passed as a parameter is
   * already in the container. It returns the appropriate boolean expression.
   * @param word the word that we want to know if it is present
   * @return true if the word is in the container, false otherwise
   */
  public boolean present( String word ){
  
    //since the words are the keys, 
    //just check if there is a key present 
    //with the value of the passed word
    return data.containsKey(word);
  }
  
  /**
   * This method assumes that the word that it has been passed is already in the container.
   * If so, it increments its count.
   * @param word the word we want to check for
   * @throws UnsupportedOperationException if the word is not present in the database
   */
  
  public void incrementCount( String word ) throws UnsupportedOperationException{
  
    //check if the word is present
    if ( present (word) ){
      
      //if it is, get its current count
      int currentCount = data.get(word);
      
      //increment the count
      int newCount = currentCount + 1;
      
      //map the new count to the same word
      data.put( word, newCount );
    } else {
      
      //if the word is not present, 
      //throw an exception to indicate that something that is not present
      //cannot be incremented
      throw new UnsupportedOperationException ("Word is not present. Consider the add method.");
    }
    
    
    
  }
  
  /**
   * This method returns the number of occurences of a word
   * in the text file according to the database.
   * If the word is not present in our database, it will return a count of 0.
   * @param word the word we want to know for how many times it occurs
   * @return the number of occurences of the word
   */
  public int getCount( String word ){
  
    //check if the word is present
    if ( present (word) ){
      
      //if it is, get its current count and return it (the value mapped to the key "word")
      return data.get(word);
      
    } else {
      
      //if the word is not present, 
      //return a count of 0
      return 0;
    }
  }
  
 /**
  * This method returns an ArrayList of all the words 
  * that are in the database in an alphabetically sorted order.
  * @return an ArrayList of all words
  */
  public ArrayList<String> getWords(){
  
    //create the ArrayList that we will return, its size is going to be the size of the TreeMap
    ArrayList<String> wordsArr = new ArrayList<String>( data.size() );
    
    //get a TreeSet of the words (keys) in the container
    Set<String> dataTree = data.keySet();
    
    //get the iterator for that set and store the values we get from it in an array
    for ( Iterator it = dataTree.iterator(); it.hasNext(); ){
      
      wordsArr.add( (String) it.next() );
    
    }
    
   // return the new ArrayList
    return wordsArr;
  }
}
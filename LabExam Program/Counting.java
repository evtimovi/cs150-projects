/*
 * The Interface to be implemented
 */
import java.util.*;
 
public interface Counting {
 
 	// add a word to the container, assumes word is
 	// not already in the container
 	public void add( String word );
 	
 	// is this word in the container?
 	public boolean present( String word );
 	
 	// increment the count for the word; assumes word is in the container
 	public void incrementCount( String word );
 	
 	// get the current count of the word
 	public int getCount( String word );
 	
 	// get the list of Words in the container
 	public ArrayList<String> getWords();
 	
}

import java.util.*;

/**
 * This class has a tree set container
 * that implements the DirectorComparator class.
 * It allows adding to the container
 * and access to the entries in it through an iterator.
 * @author Ivan Evtimov
 */
public class DirectorComparatorTreeSet{

  /**
   * The fields of this class are the director comparator and
   * the tree set container.
   */
  private Comparator comp;
  private TreeSet<Movie> treeContainer;
  
  /**
   * The constructor method of this class
   * initializes a tree set container which
   * uses the DirectorComparator.
   * It needs no parameters.
   */
  public DirectorComparatorTreeSet(){
    
    comp = new DirectorComparator();
    treeContainer = new TreeSet<Movie>(comp);
  }
  
  /**
   * Accessor method for the tree set's iterator.
   * @return the tree set's iterator
   */
  public Iterator iterator(){
  
    return treeContainer.iterator();
  }
  
  /**
   * Modifier method to add an object of type movie
   * to the tree container.
   * @param the movie to be added
   */
  public void addMovie(Movie movieToBeAdded){
  
    treeContainer.add(movieToBeAdded);
    
  }
  
}
import java.util.*;

/**
 * This class implements 
 * the Comparator interface and imposes the ordering of
 * the movie database by Director.
 * If the directors are exactly the same,
 * it then looks at the release year.
 *  
 * @author Ivan Evtimov
 * 
 */
public class DirectorComparator implements Comparator{

  /**
   * method to compare two movie objects by director and release year
   * As per assignment, this method assumes that at the very least, one of those
   * three parameters of the movies will be different.
   * @param movie1 the first movie to be compared
   * @param movie2 the second movie to be compared
   * @throws ClassCastException if the objects that are passed to the method are not
   * of type Movie
   * @throws NullPointerException if the objects that are to be compared are null
   * @return negative value if the second movie should precede the first movie; 
   * positive value if the first movie should precede the second movie
   */
  public int compare (Object m1, Object m2) throws ClassCastException{
  
    
    if (m1==null || m2 == null){
    
      //throw a NullPointerException if the objects being compared are null
      throw new NullPointerException();
    }
     else if (!(m1 instanceof Movie && m2 instanceof Movie)){
    
      //throw a ClassCastException if the objects being compared are not Movie-s
      throw new ClassCastException("The objects being compared are not movies!");
    }
    
    //now that we have made sure that the objects are movies and are not null,
    //1. cast the objects into movies
    Movie movie1 = (Movie) m1;
    Movie movie2 = (Movie) m2;
    
    //2. get their information
    String director1 = movie1.getDir();
    String director2 = movie2.getDir();
    int year1 = movie1.getYear();
    int year2 = movie2.getYear();
   
    
    //store comparison values in variables
    int comparisonOfDirectors = director1.compareTo(director2);
    int comparisonOfYears = year1 - year2;
  
    
    //if the titles are not lexicographically the same
    if (comparisonOfDirectors != 0){
    
      //return the value of their comparison given by the compareTo method in String
      return comparisonOfDirectors;
      
    //otherwise, if the years are not exactly the same
    } else {
    
      //return their difference,
      //note that this will be negative 
      //if the first year comes after the second year
      return comparisonOfYears;
    
     }
        

  }
  
  

}
import java.util.*;

/**
 * This class implements 
 * the Comparator interface and imposes the ordering of
 * the movie database by Title.
 * If the titles are exactly the same,
 * it then looks at the release year.
 * Fianlly, if the release years match, the comparator 
 * looks at the studio that released the movie.
 * 
 * @author Ivan Evtimov
 * 
 */
public class TitleComparator implements Comparator{

  /**
   * method to compare two movie objects by title, release year and then studio
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
    String title1 = movie1.getTitle();
    String title2 = movie2.getTitle();
    int year1 = movie1.getYear();
    int year2 = movie2.getYear();
    String studio1 = movie1.getStudio();
    String studio2 = movie2.getStudio();
    
    //store comparison values in variables
    int comparisonOfTitles = title1.compareTo(title2);
    int comparisonOfYears = year1 - year2;
    int comparisonOfStudios = studio1.compareTo(studio2);
    
    //if the titles are not lexicographically the same
    if (comparisonOfTitles != 0){
    
      //return the value of their comparison given by the compareTo method in String
      return comparisonOfTitles;
      
    //otherwise, if the years are not exactly the same
    } else if(comparisonOfYears != 0){
    
      //return their difference,
      //note that this will be negative 
      //if the first year comes after the second year
      return comparisonOfYears;
    
    //otherwise, or when both year and title are the same,
    //compare the directors
    }else {
    
      //return the value of the comparison of the directors
      return comparisonOfStudios;
    }
    

  }
  
  

}
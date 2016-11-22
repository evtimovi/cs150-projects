/**
 * This class stores information about movies
 * that is read from the inputDatabase data file.
 * 
 * Each instance of the class has a title, a director,
 * a release year, and a studio.
 * 
 * @author Ivan Evtimov
 */

public class Movie{
  
  /**
   * The fields of the class store the data
   * about the movie:
   * title, director and studio are obviously Strings.
   * ReleaseYear is an int.
   * 
   */
  private String title;
  private String director;
  private int releaseYear;
  private String studio;
  
  /**
   * The constructor for this class
   * takes in the information about the movie as parameters.
   * @param title the title of the movie
   * @param director the director of the movie
   * @param releaseYear the year the movie was released
   * @param studio the production company
   */
  public Movie (String title, String director, int releaseYear, String studio){
  
    //set the fields to the passed parameters
    this.title = title;
    this.director = director;
    this.releaseYear = releaseYear;
    this.studio = studio;
    
  
  }
  
  /**
   * Accessor method to access the movie's title.
   * @return the instance of the title for that movie
   */
  public String getTitle() {return title; }
  
   /**
   * Accessor method to access the movie's director.
   * @return the instance of the director for that movie
   */
  public String getDir() {return director; }
  
   /**
   * Accessor method to access the movie's studio.
   * @return the instance of the studio for that movie
   */
  public String getStudio() {return studio; }
  
   /**
   * Accessor method to access the movie's release year.
   * @return the instance of the release year for that movie
   */
  public int getYear() {return releaseYear; }
  
  /**
   * Standard toString method to print out the information about the movie.
   * @return String with info on the movie
   */
  public String toString(){
  
    return title+"\n"+director+"\n"+releaseYear+"\n"+studio+"\n";
  }

  
}
import java.util.*;

/**
 * A class that represents a person waiting in a queue.
 * Each person has a unique ID number and a unique time to 
 * stay in the queue it has been designated to stay in.
 * These values range from 1-4 and are generated randomly.
 * The ID-s are in the form of 1**** and are assigned based
 * on when the Person object has been created, so that 
 * the first one will have an ID of 10001, the second one
 * and ID of 10002 and so on.
 * 
 * @author Ivan Evtimov
 */
public class Person{
  
  /**
   * This field keeps track of the ID numbers that have already been generated.
   * It is a static variable as each instance of the class should have its own unique ID.
   * 
   */
  private static int IDNumberCount=10000;
  
  /**
   * This field holds the ID number for each individual person. 
   * There should be no modifier methods for this field
   * as this ID number should be unique to everyone and immutable.
   */
  private int currentPersonID;
  
  /**
   * This field holds the time to complete
   * the standing in the assigned queue.
   * 
   */
  private int timeToComplete;
  
  /**
   * This field indicates how much the person has waited at the front of the queue.
   */
  private int waitingTime;
  
  /**
   * The constructor for the class creates a person with a unique ID number and 
   * generates a random time to compelte.
   * 
   * It first generates the timeToComplete using 
   * an instance of the Random class with
   * a seed of 12345678.
   * Then the constructor indents the IDNumberCount.
   * Finally, it assigns the current IDNumberCount to this
   * person's currentPersonID.
   * 
   * The constructor needs no parameters.
   * 
   */
  public Person(){
    
    //create a random number generator
    Random rand = new Random(11);
    
    //generate a number between 1 and 4 to determine the time to stay
    this.timeToComplete = rand.nextInt(4)+1;
    
    //indent the static field
    IDNumberCount++;
    
    //assign the current value of the static field to this person's ID number
    currentPersonID = IDNumberCount;
    
    //initialize the waiting time to 0
    waitingTime = 0;
  }
  
  
  /**
   * This is a standard toString method.
   * It returns the relevant information about the instance of the Person class.
   */
  public String toString(){
    return "Current person has an assigned time to complete of: " + timeToComplete +
           "\nCurrent person's ID: " + currentPersonID + "\nThe ID number count is: "
           +IDNumberCount;
  
  }
  
  /**
   * Accessor method to get the current person's ID.
   * @return the current person's ID
   */
  public int getCurrentPersonID(){
    return currentPersonID;
  }
  
  /**
   * Accessor method to get the ID count.
   * @return the ID count for the Person class
   */
  public int getIDNumberCount(){
    return IDNumberCount;
  }
  
  
  /**
   * Accessor method to get the current persons's assigned time to complete.
   * @return the time to complete that has been generated for this person
   */
  public int getTimeToStay(){
    return timeToComplete;
  }
  
  /**
   * This method indents the field waitingTime by 1.
   */
  public void indentWaitingTime(){
    waitingTime++;
  
  }
  
  /**
   * This method returns the waiting time.
   */
  public int getWait(){
    return waitingTime;
  }
  
  
  public static void main(String[] args){
    Person p1 = new Person();
    
    System.out.println(p1);
    
    Person p2 = new Person();
    
    System.out.println(p2);
  
  }
  
  
  
  
}
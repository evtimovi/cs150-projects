import java.io.*;
import java.util.*;

public class Foo{

  public static void main(String[] args){
//  
//    String cheap = "$";
//    String middle = "$$";
//    String expensive = "$$$";
//    
//    System.out.println("Compare cheap and middle: " + cheap.compareTo(middle));
//    System.out.println("Compare cheap and expensive: " + cheap.compareTo(expensive));
//    System.out.println("Compare middle and expensive: " + middle.compareTo(expensive));
//    System.out.println("Compare expensive and cheap: " + expensive.compareTo(cheap));
//    System.out.println("Compare expensive and middle: " + expensive.compareTo(middle));
//    System.out.println("Compare middle and cheap: " + middle.compareTo(cheap));
    
//    Restaurant r = new Restaurant ("MidCity Diner", 
//                                   "New York",
//                                   "German,unknown",
//                                   "Monday 13:00 14:00,Tuesday 08:00 19:00",
//                                   3.5,
//                                   "$$",
//                                  12);
//    try{
//      File restaurants = new File("restaurants3.txt");
//      Scanner sc = new Scanner (restaurants);
//      sc.useDelimiter("\n\n");
//      
//      String block = "";
//      
//      
//      String nextLine;
//      while ( sc.hasNext() ){
//      
//        block = sc.next();
//        
//        Restaurant r = new Restaurant(block);
//        System.out.println(r);
//      }
//    
//      //r.toString();
//    } catch (Exception e){
//      System.out.println(e);
//      e.printStackTrace();
//    }
//    
//    BusinessHours bh = new BusinessHours();
//    
//    bh.setAllHours("Sunday 08:00 16:00,Monday 06:00 20:00,Tuesday 06:00 20:00,Wednesday 06:00 20:00,Thursday 06:00 20:00,Friday 06:00 20:00,Saturday 06:00 01:00");
//    System.out.println(bh);
  
    Database d = new Database();
    d.createDatabaseFromFile("restaurants3.txt");
    ArrayList<Restaurant> outputArr = d.addReview("The Leatherwing Cat", "Boston", 4.5);
    System.out.println( outputArr );
//    System.out.println("$: " + d.counterGlobal1);
//    System.out.println("$$: " + d.counterGlobal2);
//    System.out.println("$$$: " + d.counterGlobal3);
//    System.out.println("$$ (added): " + d.counterGlobal4);
    //ArrayList<Restaurant> outputArr = d.getAllWithCategory("Soup");
    //System.out.println( outputArr + "Total with this category: " + outputArr.size() );
//    ArrayList<Restaurant> outputArr = d.getFromCity("New York");
//    System.out.println( outputArr + "Total from New York: " + outputArr.size() );
//    //System.out.println(d.getFromCity("New York"));
//    
//     Database d = new Database();
//     d.createDatabaseFromFile("restaurants3.txt");
//     
//     System.out.println(d.search("New York", "Italian", "$$$", "Thursday", "12:05"));
  }
}
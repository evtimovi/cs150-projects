import java.util.*;

public class Foo{
  public static void main(String[] args){
    HashMap<String, String> map = new HashMap<String, String>();
    
    map.put("resources", null);
    
    System.out.println(map.keySet());
    System.out.println(map.get("resources"));
    System.out.println(map.get("r"));
  }
}
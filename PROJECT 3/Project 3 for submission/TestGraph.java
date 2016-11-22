import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the functionality of the
 * Graph Class that performs additional tests on the Graph class 
 * (on top of the tests performed for lab 10).
 * Please, note, however, that the inner classes only contain a constructor
 * and one or two simple accessor methods, so for the most
 * part, they are not directly unit tested.
 * 
 * @author Ivan Evtimov
 */
public class TestGraph extends TestCase {
  
  /**
   * A test method for the general construction of a simple graph
   * with no edges and an attempt to access anyone of them.
   */
  public void testNoEdges() {
    
    //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //at this point, an exception should be thrown by the getAdj method (no vertices yet)
    try{
      
      graph.getAdj(2);//should cause an exception
      
      fail("Exception not thrown!");//should not be reached due to exception
      
    } catch (NoSuchElementException e){
      //test succeeds
    }
  }
  
  /**
   * A test method for the general construction of a simple graph
   * with 5 vertices and no connection between them. Method tests
   * getAdj, addNode and, impilciltly, getWeight.
   * 
   */
  public void testNoEdges2() {
    
    //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
    
    //declare the array to store the adjacents for each current node
    ArrayList<Graph.UnDirectedGraphNode> currAdj;
    
    //try to get the adjacents of each one of them, should be empty
    for( int i = 1; i < 6; i++){
      
      //get the array of the current's adjacents
      currAdj = graph.getAdj( i );
      
      assertTrue("failed at: " + i, currAdj.size() == 0 );//there should be no adjacents
    }
 
  }
  
  
 /**
   * A test method for the general construction of a simple graph
   * with 5 vertices and some connection between them. Method tests
   * getAdj, addNode and addEdge.
   * 
   */
  public void testSomeEdges() {
    
    //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
    
    //add some edges between them
    graph.addEdge(5, 4, 35);
    graph.addEdge(4, 3, 134);
    graph.addEdge(3, 2, 12);
    graph.addEdge(2, 1, 150);
    graph.addEdge(1, 5, 10); //now it's circular
    graph.addEdge(1, 4, 50); //more interesting
    graph.addEdge(4, 2, 110);
    
    //test for the missing connections first
    assertEquals("2 to 5 should be missing", 0, graph.getWeight( 2, 5 ));
    assertTrue("5 to 2 should be missing", graph.getWeight( 5, 2 )==0);
    assertTrue("3 to 5 should be missing", graph.getWeight( 3, 5 )==0);
    assertTrue("5 to 3 should be missing", graph.getWeight( 5, 3 )==0);
    
    //test the connections that should be there
    assertTrue("5 to 4 should be 35", graph.getWeight( 5, 4 ) == 35 );
    assertTrue("4 to 5 should be 35", graph.getWeight( 4, 5 )== 35 );//test for undirected
    
    assertTrue("3 to 4 should be 35", graph.getWeight( 3, 4 ) == 134 );
    assertTrue("4 to 3 should be 34", graph.getWeight( 4, 3 )== 134 );//test for undirected
    
    assertTrue("3 to 2 should be 12", graph.getWeight( 3, 2 ) == 12 );
    assertTrue("2 to 3 should be 12", graph.getWeight( 2, 3 )== 12 );//test for undirected
    
    assertTrue("1 to 2 should be 150", graph.getWeight( 1, 2 ) == 150 );
    assertTrue("2 to 1 should be 150", graph.getWeight( 2, 1 )== 150 );//test for undirected
    
    assertTrue("1 to 5 should be 10", graph.getWeight( 1, 5 ) == 10 );
    assertTrue("5 to 1 should be 12", graph.getWeight( 5, 1 )== 10 );//test for undirected
    
    assertTrue("1 to 4 should be 10", graph.getWeight( 1, 4 ) == 50 );
    assertTrue("4 to 1 should be 12", graph.getWeight( 4, 1 )== 50 );//test for undirected
    
    assertTrue("2 to 4 should be 110", graph.getWeight( 2, 4 ) == 110 );
    assertTrue("4 to 2 should be 110", graph.getWeight( 4, 2 )== 110 );//test for undirected

  }
  
  /**
   * Tests the method for the shortest path.
   */
  public void testShortestPath(){
  
     //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
    
    //add some edges between them
    graph.addEdge(5, 4, 35);
    graph.addEdge(4, 3, 134);
    graph.addEdge(3, 2, 12);
    graph.addEdge(2, 1, 150);
    graph.addEdge(1, 5, 10); //now it's circular
    graph.addEdge(1, 4, 50); //more interesting
    graph.addEdge(4, 2, 110);
    
    
    //make assertions as follows:
    /*
     * Shortest between:
     * 1 and 2 is {1, 2}
     * 1 and 3 is {1, 2, 3}
     * 1 and 4 is {1, 5, 4}
     * 1 and 5 is {1, 5}
     * 
     * The algorithm should work when called multiple times on the same graph.
     */
    
    //get the shortest path to 2
    ArrayList<Integer> pathTo2 = graph.findShortestPath( 1, 2 );
    
    //make the assertions
    assertTrue("pathTo2 first is not 1!", pathTo2.get(0) == 1);
    assertTrue("pathTo2 second is not 2!", pathTo2.get(1) == 2);
    
    
    //get the shortest path to 3
    ArrayList<Integer> pathTo3 = graph.findShortestPath( 1, 3 );
    
    //make the assertions
    assertTrue("pathTo3 first is not 1!", pathTo3.get(0) == 1);
    assertTrue("pathTo3 second is not 2!", pathTo3.get(1) == 2);
    assertTrue("pathTo3 third is not 3!", pathTo3.get(2) == 3);
    
    //get the shortest path to 4
    ArrayList<Integer> pathTo4 = graph.findShortestPath( 1, 4 );
    
    //make the assertions
    assertTrue("pathTo4 first is not 1!", pathTo4.get(0) == 1);
    assertTrue("pathTo4 second is not 5! "+ pathTo4.get(1), pathTo4.get(1) == 5);
    assertTrue("pathTo4 third is not 4!", pathTo4.get(2) == 4);
    
     //get the shortest path to 5
    ArrayList<Integer> pathTo5 = graph.findShortestPath( 1, 5 );
    
    //make the assertions
    assertTrue("pathTo5 first is not 1!", pathTo5.get(0) == 1);
    assertTrue("pathTo5 second is not 5!", pathTo5.get(1) == 5);

  }
  
  /**
   * This method tests the effectiveness of the compareTo
   * method of the inner node class.
   */
  public void testCompareTo(){
    
     //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes, but no edges
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
  
    
    //at this point, all nodes should be equal because djikstra's bfs hasn't been called yet
    assertEquals("1 and 2", graph.getNode( 1 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("2 and 1", graph.getNode( 2 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 3", graph.getNode( 1 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("3 and 1", graph.getNode( 3 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 4", graph.getNode( 1 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("4 and 1", graph.getNode( 4 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 5", graph.getNode( 1 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 1", graph.getNode( 5 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("3 and 2", graph.getNode( 3 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("2 and 3", graph.getNode( 2 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("2 and 4", graph.getNode( 2 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("2 and 5", graph.getNode( 2 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("4 and 2", graph.getNode( 4 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("5 and 2", graph.getNode( 5 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("3 and 4", graph.getNode( 3 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("3 and 5", graph.getNode( 3 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 3", graph.getNode( 5 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 3", graph.getNode( 4 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 5", graph.getNode( 4 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 4", graph.getNode( 5 ).compareTo( graph.getNode( 4 ) ), 0);
    
    //now check for the same nodes being equal
    assertEquals("5 and 5", graph.getNode( 5 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("3 and 3", graph.getNode( 3 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 4", graph.getNode( 4 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("1 and 1", graph.getNode( 1 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("2 and 2", graph.getNode( 2 ).compareTo( graph.getNode( 2 ) ), 0);    
    
    
  }
  
    /**
   * This method tests the effectiveness of the compareTo
   * method of the inner node class.
   */
  public void testCompareToBeforeDjikstra(){
    
     //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes, but no edges
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
  
     //add some edges between them
    graph.addEdge(5, 4, 35);
    graph.addEdge(4, 3, 134);
    graph.addEdge(3, 2, 12);
    graph.addEdge(2, 1, 150);
    graph.addEdge(1, 5, 10); //now it's circular
    graph.addEdge(1, 4, 50); //more interesting
    graph.addEdge(4, 2, 110);
    
    //at this point, all nodes should be equal because djikstra's bfs hasn't been called yet
    assertEquals("1 and 2", graph.getNode( 1 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("2 and 1", graph.getNode( 2 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 3", graph.getNode( 1 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("3 and 1", graph.getNode( 3 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 4", graph.getNode( 1 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("4 and 1", graph.getNode( 4 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("1 and 5", graph.getNode( 1 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 1", graph.getNode( 5 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("3 and 2", graph.getNode( 3 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("2 and 3", graph.getNode( 2 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("2 and 4", graph.getNode( 2 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("2 and 5", graph.getNode( 2 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("4 and 2", graph.getNode( 4 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("5 and 2", graph.getNode( 5 ).compareTo( graph.getNode( 2 ) ), 0);
    assertEquals("3 and 4", graph.getNode( 3 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("3 and 5", graph.getNode( 3 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 3", graph.getNode( 5 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 3", graph.getNode( 4 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 5", graph.getNode( 4 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("5 and 4", graph.getNode( 5 ).compareTo( graph.getNode( 4 ) ), 0);
    
    //now check for the same nodes being equal
    assertEquals("5 and 5", graph.getNode( 5 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("3 and 3", graph.getNode( 3 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 4", graph.getNode( 4 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("1 and 1", graph.getNode( 1 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("2 and 2", graph.getNode( 2 ).compareTo( graph.getNode( 2 ) ), 0);    
    
    
  }
  
    /**
   * This method tests the effectiveness of the compareTo
   * method of the inner node class AFTER Djikstr'a BFS has been called from 1.
   */
  public void testCompareToAftterDjikstra(){
    
     //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes, but no edges
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
  
    //add some edges between them
    graph.addEdge(5, 4, 35);
    graph.addEdge(4, 3, 134);
    graph.addEdge(3, 2, 12);
    graph.addEdge(2, 1, 150);
    graph.addEdge(1, 5, 10); //now it's circular
    graph.addEdge(1, 4, 50); //more interesting
    graph.addEdge(4, 2, 110);
    
    //call djikstra! fun, fun, fun
    graph.bfs(1);
    
     /*
     * Shortest between:
     * 1 and 2 is {1, 2} = 150
     * 1 and 3 is {1, 2, 3} = 162
     * 1 and 4 is {1, 5, 4} = 45
     * 1 and 5 is {1, 5} = 10
     * 
     */
    
    //at this point, compareTo should return the differences between shortest paths
    assertEquals("1 and 2", graph.getNode( 1 ).compareTo( graph.getNode( 2 ) ), -150);
    assertEquals("2 and 1", graph.getNode( 2 ).compareTo( graph.getNode( 1 ) ), 150);
    assertEquals("1 and 3", graph.getNode( 1 ).compareTo( graph.getNode( 3 ) ), -162);
    assertEquals("3 and 1", graph.getNode( 3 ).compareTo( graph.getNode( 1 ) ), 162);
    assertEquals("1 and 4", graph.getNode( 1 ).compareTo( graph.getNode( 4 ) ), -45);
    assertEquals("4 and 1", graph.getNode( 4 ).compareTo( graph.getNode( 1 ) ), 45);
    assertEquals("1 and 5", graph.getNode( 1 ).compareTo( graph.getNode( 5 ) ), -10);
    assertEquals("5 and 1", graph.getNode( 5 ).compareTo( graph.getNode( 1 ) ), 10);
    assertEquals("3 and 2", graph.getNode( 3 ).compareTo( graph.getNode( 2 ) ), 12);
    assertEquals("2 and 3", graph.getNode( 2 ).compareTo( graph.getNode( 3 ) ), -12);
    assertEquals("2 and 4", graph.getNode( 2 ).compareTo( graph.getNode( 4 ) ), 105);
    assertEquals("2 and 5", graph.getNode( 2 ).compareTo( graph.getNode( 5 ) ), 140);
    assertEquals("4 and 2", graph.getNode( 4 ).compareTo( graph.getNode( 2 ) ), -105);
    assertEquals("5 and 2", graph.getNode( 5 ).compareTo( graph.getNode( 2 ) ), -140);
    assertEquals("3 and 4", graph.getNode( 3 ).compareTo( graph.getNode( 4 ) ), 117);
    assertEquals("3 and 5", graph.getNode( 3 ).compareTo( graph.getNode( 5 ) ), 152);
    assertEquals("5 and 3", graph.getNode( 5 ).compareTo( graph.getNode( 3 ) ), -152);
    assertEquals("4 and 3", graph.getNode( 4 ).compareTo( graph.getNode( 3 ) ), -117);
    assertEquals("4 and 5", graph.getNode( 4 ).compareTo( graph.getNode( 5 ) ), 35);
    assertEquals("5 and 4", graph.getNode( 5 ).compareTo( graph.getNode( 4 ) ), -35);
    
    //now check for the same nodes being equal
    assertEquals("5 and 5", graph.getNode( 5 ).compareTo( graph.getNode( 5 ) ), 0);
    assertEquals("3 and 3", graph.getNode( 3 ).compareTo( graph.getNode( 3 ) ), 0);
    assertEquals("4 and 4", graph.getNode( 4 ).compareTo( graph.getNode( 4 ) ), 0);
    assertEquals("1 and 1", graph.getNode( 1 ).compareTo( graph.getNode( 1 ) ), 0);
    assertEquals("2 and 2", graph.getNode( 2 ).compareTo( graph.getNode( 2 ) ), 0);    
    
    
  }
  
  /**
   * tests the radius-finding method
   */
  public void testRadius(){
  
       //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    Graph < Integer, String > graph = new Graph < Integer, String >();
    
    //add 5 nodes, but no edges
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
  
    //add some edges between them
    graph.addEdge(5, 4, 35);
    graph.addEdge(4, 3, 134);
    graph.addEdge(3, 2, 12);
    graph.addEdge(2, 1, 150);
    graph.addEdge(1, 5, 10); //now it's circular
    graph.addEdge(1, 4, 50); //more interesting
    graph.addEdge(4, 2, 110);
    
    //call djikstra! fun, fun, fun
    graph.bfs(1);
    
     /*
     * Shortest between:
     * 1 and 2 is {1, 2} = 150
     * 1 and 3 is {1, 2, 3} = 162
     * 1 and 4 is {1, 5, 4} = 45
     * 1 and 5 is {1, 5} = 10
     * 
     */
    ArrayList<Integer> withinRadiusArr = graph.radius( 1, 151 );
    
    //make the assertions
    assertFalse("wow, why is that damn bugger here?", withinRadiusArr.contains(3));//3 should be left out, out of radius
    
    //they should also be shorted from shortest to longest
    assertEquals("remember 0 should be the center itself", 1, (int) withinRadiusArr.get(0));
    assertEquals("first in radius arr", 5, (int) withinRadiusArr.get(1));
    assertEquals("second in radius arr", 4, (int) withinRadiusArr.get(2));
    assertEquals("third in radius arr", 2, (int) withinRadiusArr.get(3));
    
    
  }
}

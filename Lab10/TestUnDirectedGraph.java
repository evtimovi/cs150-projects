import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class for the functionality of the
 * UnDirectedGraph Class and (if needed) its inner classes.
 * Please, note, however, that the inner classes only contain a constructor
 * and one or two simple accessor methods, so for the most
 * part, they are not unit tested.
 * 
 * These unit-tests ensure basic functionality. More in-depth unit tests could be performed.
 * @author Ivan Evtimov
 */
public class TestUnDirectedGraph extends TestCase {
  
  /**
   * A test method for the general construction of a simple graph
   * with no edges and an attempt to access anyone of them.
   */
  public void testNoEdges() {
    
    //first create a new graph
    //for simplicity, I am using int as the key and string as the element
    UnDirectedGraph < Integer, String > graph = new UnDirectedGraph < Integer, String >();
    
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
    UnDirectedGraph < Integer, String > graph = new UnDirectedGraph < Integer, String >();
    
    //add 5 nodes
    graph.addNode(1, "New York");
    graph.addNode(2, "San Francisco");
    graph.addNode(3, "Los Angeles");
    graph.addNode(4, "Washington, D.C.");
    graph.addNode(5, "Philadelphia");
    
    //declare the array to store the adjacents for each current node
    ArrayList<UnDirectedGraph.UnDirectedGraphNode> currAdj;
    
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
    UnDirectedGraph < Integer, String > graph = new UnDirectedGraph < Integer, String >();
    
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
    assertTrue("2 to 5 should be missing", graph.getWeight( 2, 5 )==0);
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
    UnDirectedGraph < Integer, String > graph = new UnDirectedGraph < Integer, String >();
    
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
    ArrayList<UnDirectedGraph.UnDirectedGraphNode> pathTo2 = graph.findShortestPath( 1, 2 );
    
    //make the assertions
    assertTrue("pathTo2 first is not 1!", pathTo2.get(0).getKey() == 1);
    assertTrue("pathTo2 second is not 2!", pathTo2.get(1).getKey() == 2);
    
    
    //get the shortest path to 3
    ArrayList<UnDirectedGraph.UnDirectedGraphNode> pathTo3 = graph.findShortestPath( 1, 3 );
    
    //make the assertions
    assertTrue("pathTo3 first is not 1!", pathTo3.get(0).getKey() == 1);
    assertTrue("pathTo3 second is not 2!", pathTo3.get(1).getKey() == 2);
    assertTrue("pathTo3 third is not 3!", pathTo3.get(2).getKey() == 3);
    
    //get the shortest path to 4
    ArrayList<UnDirectedGraph.UnDirectedGraphNode> pathTo4 = graph.findShortestPath( 1, 4 );
    
    //make the assertions
    assertTrue("pathTo4 first is not 1!", pathTo4.get(0).getKey() == 1);
    assertTrue("pathTo4 second is not 5! "+ pathTo4.get(1), pathTo4.get(1).getKey() == 5);
    assertTrue("pathTo4 third is not 4!", pathTo4.get(2).getKey() == 4);
    
     //get the shortest path to 5
    ArrayList<UnDirectedGraph.UnDirectedGraphNode> pathTo5 = graph.findShortestPath( 1, 5 );
    
    //make the assertions
    assertTrue("pathTo5 first is not 1!", pathTo5.get(0).getKey() == 1);
    assertTrue("pathTo5 second is not 5!", pathTo5.get(1).getKey() == 5);

  }
}

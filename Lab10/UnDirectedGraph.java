import java.util.*;

/**
 * This class is my own implementation of an Undirected Graph completed according
 * to the assignment for lab 10. It implements a generic undirected graph where
 * the type of the key and the value of the element related to that key can be
 * specified as type parameters. 
 * The class makes use of an inner node class that stores the different
 * elements and is characterized by a key. No repetition of the keys is allowed,
 * but this ensures that the elements stored in the graph can be repeated.
 * The graph implements the addNode and addEdge methods and the constructor initializes
 * an empty graph.
 * 
 * Moreover, the class has two inner classes - UnDirectedGraphNode and UnDirectedGraphEdge
 * that help in the construction of the graph.
 * 
 * Important note: Code heavily based on book's implementation of the Graph and shortest 
 * path algorithm.
 * 
 * @author Ivan Evtimov
 * @param K the type of the key
 * @param E the type of the element stored in the node
 */
public class UnDirectedGraph<K, E>{
  
   /////////////////////////// Inner Node Class ////////////////////////////////////
  /**
   * This inner class provides the functionality needed for storing
   * elements mapped to given keys.
   * It has references to the adjacent nodes of each node stored in a linked list.
   * The class is generic and uses the same type parameters as the outer class as those
   * represent the key and the element being stored.
   * @author Ivan Evtimov
   */
  public class UnDirectedGraphNode<K,E>{
    
    /**
     * Constant to mark unprocessed nodes (nodes that still haven't had their shortest distance set).
     */
    protected static final int INFINITY = Integer.MAX_VALUE;
    
    /**
     * Represents the value of the node.
     */
    protected E value;
    
    /**
     * Represents the key of the node
     */
    protected K key;
    
    /**
     * Represents the shortest distance from this node to
     * the start node as computed by the shortest path algorithm.
     */
    protected int shortestDist;
    
    /**
     * Reference to the previous node on the shortest
     * path to the start node as defined by the shortest path alg.
     */
    protected UnDirectedGraphNode previous;
    
    /**
     * References to adjacent nodes.
     */
    protected LinkedList<UnDirectedGraphEdge> adjList;
    
    /**
     * This variable holds the information of whether the note was visited.
     * True for visited, false for not visited
     */
    protected boolean visited ;
    
    /**
     * The constructor method creates a node based on the given name and key.
     * It initializes its reference adjacency list to an empty linked list.
     * @param value the value that the current node should hold
     * @param key the key that is used to access this node
     */
    public UnDirectedGraphNode( K paramKey, E paramValue ){
      
      key = paramKey;//set this instance's key to what has been passed
      value = paramValue; //same as for key
      
      //initializes the linked list
      adjList = new LinkedList<UnDirectedGraphEdge>();
      
      //set visited to false
      visited = false;
      
      //set shortest distance to infinity (still unprocessed)
      shortestDist = INFINITY;
    }
  
    /**
     * This method returns the weight of the edge from the current node 
     *  to a given node.
     * @param node the node we are looking for the connection to
     * @return the weight of the edge or 0 if no connection exists
     */
    public int getWeight( UnDirectedGraphNode node ){
      
      //get this instance's adjacency list
      LinkedList<UnDirectedGraphEdge> edges = adjList;
      
      //go throuhg the edges
      for( UnDirectedGraphEdge curr :  edges ){
        
        //for each connection, check if the second node is the one we are looking for
        if ( curr.secondNode.equals( node ) ){
          return curr.weight;//if so, return the weight of that connection
        }
        
      }
      
      //if we reach this line, nothing has been returned/found, so return 0 (no connection)
      return 0;
    }
      
    
    /**
     * For debugging, prints out this node's key.
     * @return this node's key as a string
     */
    public String toString(){
      return this.key.toString();
    }
    
     /**
     * For testing, retunrs out this node's key.
     * @return this node's key as a string
     */
    public K getKey(){
      return this.key;
    }
    
  }
  /////////////////////////// End Of Inner Node Class /////////////////////////////
  
  
 /////////////////////////// Inner Edge Class ////////////////////////////////////
  /**
   * This inner class provides the functionality needed for storing
   * references to the node's adjacent nodes (it represents an edge in the graph).
   * Since it is designed to be stored in the adjacency list of the nodes, 
   * this class only contains a reference to the second node that it is connecting
   * and a weight for the connection.
   * @author Ivan Evtimov
   */
  public class UnDirectedGraphEdge{
    
    /**
     * This field stores the node that the edge connects to.
     */
    protected UnDirectedGraphNode secondNode;
    
    /**
     * This field stores the edge's weight.
     */
    protected int weight;
    
    /**
     * The constructor creates the connection between the 
     * @param n the second node in the connection
     * @param w the weight of the connection
     */
    public UnDirectedGraphEdge( UnDirectedGraphNode n, int w ){
      
      secondNode = n;//point this edge's second node to the given param
      weight = w; //assign the given param for the weight to this edge's weight
    }
  }
  
  /////////////////////////// End Of Inner Edge Class /////////////////////////////
  
    
 /////////////////////////// Inner Path Class ////////////////////////////////////
  /**
   * This inner class is a "helper" class used in the implementation
   * of Djikstra's shortest path algorithm. It extends Comparable so that
   * it can be used in the priority queue of the algorithm.
   * @author Ivan Evtimov
   */
  public class UnDirectedGraphPath implements Comparable<UnDirectedGraphPath>{
    
    /**
     * This field stores the node that the edge connects to.
     */
    protected UnDirectedGraphNode secondNode;
    
    /**
     * This field stores the edge's weight.
     */
    protected int weight;
    
    /**
     * The constructor creates the connection between the 
     * @param n the second node in the connection
     * @param w the weight of the connection
     */
    public UnDirectedGraphPath( UnDirectedGraphNode n, int w ){
      
      secondNode = n;//point this edge's second node to the given param
      weight = w; //assign the given param for the weight to this edge's weight
    }
    
    /**
     * The compareTo method needed for the priority queue.
     * @param p2 the second path we are comparing to
     * @return positive if this path's weight is greater, negative if it is smaller, 0 if equal weights
     */
    public int compareTo( UnDirectedGraphPath p2 ){
    
      //get the other path's weight
      int secondWeight = p2.weight;

      //compare and return the appropriate value
      if ( this.weight < secondWeight){
        return -1;
      } else if (this.weight > secondWeight) {
        return 1;
      } else {
        return 0;
      }
    }
  }
  
  /////////////////////////// End Of Inner Edge Class /////////////////////////////
  
  /**
   * This field holds the vertices in the graph
   * (all instances of the UnDirectedGraphNode class)
   * for quick access by key
   */
  private HashMap<K, UnDirectedGraphNode> vertCont;
  

    
  /**
   * The constructor create an empty graph with no nodes.
   */
  public UnDirectedGraph( ){
    
    //initialize the hash map of nodes
    vertCont = new HashMap<K, UnDirectedGraphNode>();

  }

  /**
   * This method adds a new node to the graph.
   * Please, note that at this point, this node is not connected with anything 
   * in the graph. It adds the node based on its key and the value it is supposed to containe.
   * However, if there is something already mapped to this key, it will return false and 
   * the node will not be added to the graph.
   * @param key the key of the node
   * @param val the value we want the node to have
   * @return true if node was successfully added, false otherwise
   */
  public boolean addNode ( K key, E val ){
  
    //if we already have something mapped to this key,
    //we return false and make no further computations
    if ( vertCont.containsKey( key ) ){
      return false;
    }
    
    //if here, nothing is mapped to this key
    //create the node
    UnDirectedGraphNode<K, E> tempNode = new UnDirectedGraphNode<K,E>( key, val );
    
    //add it to the container
    vertCont.put( key, tempNode );
    
    return true;// to indicate correct insertion
  }
  
  /**
   * This method adds an edge (a connection) between two nodes with the given keys.
   * @param k1 the element of the first key we want to connect
   * @param k2 the element of the second key we want to connect
   * @param weight the weight of the connection
   * @return true if connection successful; false if one of the keys has nothing mapped to it
   */
  public boolean addEdge( K k1, K k2, int weight ){
    
    //get the elements mapped to those keys
    UnDirectedGraphNode node1 = vertCont.get( k1 );
    UnDirectedGraphNode node2 = vertCont.get( k2 );
    
    //check if node1 and node2 are associated with anything in this map
    if( node1 == null || node2 == null ){
      return false;//to indicate that insertion was a failure
    }
    
    //if here, both keys have something mapped to them
    //add the edge to both nodes' internal containers of connections (their adjacency lists)
    node1.adjList.add( new UnDirectedGraphEdge( node2, weight ) );//this connects node1 to node2
    node2.adjList.add( new UnDirectedGraphEdge( node1, weight ) );//connects node2 to node1
    
    return true;
  }
  
  /**
   * This method is designed to return the adjacent nodes of a given node
   * in an array list.
   * @param key the key for the node whose adjacents are going to be returned
   * @return an array list of nodes adjacent to the one with the given key
   * @throws NoSuchElementException if nothing is mapped to this key
   */
  public ArrayList<UnDirectedGraphNode> getAdj( K key ) throws NoSuchElementException{
 
    //get the node associated with that key
    UnDirectedGraphNode tempNode = vertCont.get( key );
    
    //throw an exception if nothing is found
    if( tempNode == null ){
      throw new NoSuchElementException("Nothing mapped to this key!");
    }
    
    //create the array that will be returned with the size of the linked list of adjacent edges
    ArrayList<UnDirectedGraphNode> adjacents = new ArrayList<UnDirectedGraphNode>( tempNode.adjList.size() );
    
    //get this node's linked list of edges
    LinkedList<UnDirectedGraphEdge> edgeList = tempNode.adjList;
    
    //iterate over the list of edges
    for( UnDirectedGraphEdge currEdge : edgeList){
      
      //add the node that this edge connects to
      adjacents.add( currEdge.secondNode );
    }
  
    //after we are done getting all the adjacents, return the array
    return adjacents;
  }
  
  /**
   * This method is designed to return the adjacent nodes of a given node
   * in a string.
   * @param key the key for the node whose adjacents are going to be returned
   * @return a string of all adjacent nodes of the node with the given key or an error
   * message string if nothing is mapped to this key
   */
  public String printAdj( K key ) throws NoSuchElementException{
 
    //get the node associated with that key
    UnDirectedGraphNode tempNode = vertCont.get( key );
    
    //throw an exception if nothing is found
    if( tempNode == null ){
      return "Nothing mapped to this key in the graph!";
    }
    
    //initialize the string that will be returned
    String adjacents = "";
    
    //get this node's linked list of edges
    LinkedList<UnDirectedGraphEdge> edgeList = tempNode.adjList;
    
    //iterate over the list of edges
    for( UnDirectedGraphEdge currEdge : edgeList){
      
      //add information about the node
      adjacents = adjacents + "connection to: " + currEdge.secondNode + "weight: " + currEdge.weight + "\n" ;
    }
  
    //after we are done getting all the adjacents, return the string
    return adjacents;
  }
  
  /**
   * This method is meant to return the weight
   * of the edge between two vertices with the given keys.
   * @param key1 the key of the first vertex
   * @param key2 the key of the second vertex
   * @return weight of edge between vertex1 and vertex2; 0 if one of the two keys is not present 
   * in the graph or if the edge betwen these vertices doesn't exist
   */
  public int getWeight( K key1, K key2 ){
  
    //get those vertices
    UnDirectedGraphNode vertex1 = vertCont.get( key1 );
    UnDirectedGraphNode vertex2 = vertCont.get( key2 );
    
    //check if they both exist
    if( vertex1 == null || vertex2 == null ){
      return 0; //if one of them is not present in the graph
    }
    
    return vertex1.getWeight( vertex2 );//use the node's getWeight method
  }
  
  /**
   * This method is designed to find the shortest path 
   * between two nodes and return the path of nodes in an array list.
   * @param k1 the key of the first node
   * @param k2 the key of the second node
   * @return the shortest path between nodes with keys k1 and k2
   * @throws NoSuchElementException if either k1 or k2 is not present in the graph
   */
  public ArrayList<UnDirectedGraphNode> findShortestPath( K k1, K k2 ) throws NoSuchElementException{
  
    //get the start and end nodes
    UnDirectedGraphNode startNode = vertCont.get( k1 );
    UnDirectedGraphNode endNode = vertCont.get( k2 );
    
    //check for exception
    if( startNode == null || endNode == null ){
      throw new NoSuchElementException( "Nothing mapped to either k1 or k2!" );
    }
    
    //declare a curr for the iteration throught the nodes
    UnDirectedGraphNode curr;
    
    //reset all the nodes' visited variables and shortestDist variables
    for( Iterator it = vertCont.keySet().iterator(); it.hasNext(); ){
    
      //get the node with the current key
      curr = vertCont.get( it.next() );
      
      //set the curr's visited to false
      curr.visited = false;
      
      //set its shortest distance to 0
      curr.shortestDist = UnDirectedGraphNode.INFINITY;
      
    }
    
    //create the priority queue
    PriorityQueue<UnDirectedGraphPath> queue = new PriorityQueue<UnDirectedGraphPath>();
    
    //add the starting node's path to itself to the priority queue
    queue.add( new UnDirectedGraphPath( startNode, 0 ) );
    
    //set the start node's distance to itself to 0
    startNode.shortestDist = 0;
    
    //declare a counter of the seen nodes
    int seenCounter = 0;
    
    //iterate through the graph using the algorithm
    while( queue.isEmpty() == false && seenCounter < vertCont.size() ){
      
      //get the first path in the priority queue
      UnDirectedGraphPath path = queue.remove();
      
      //get the node that this path connects to
      UnDirectedGraphNode secondNodeInPath = path.secondNode;
      
      //check if visited
      if( secondNodeInPath.visited == true ){
        continue;//skip over the visited ones
      }
      
      //set this node's visited to true and update the seenCounter
      secondNodeInPath.visited = true;
      seenCounter++;
      
      //get the adjacency list of the second node
      LinkedList<UnDirectedGraphEdge> adjacencyList = secondNodeInPath.adjList;
      
      //go through the edges going out of the second node
      for( UnDirectedGraphEdge currEdge : adjacencyList ){
        
        //get the node that this current edge connects to and the weight of the connection
        UnDirectedGraphNode connectingNode = currEdge.secondNode;
        int weightToConnectingNode = currEdge.weight;
        
        //check if this node's shortest distance is greater
        //than the current way to reach it
        if( connectingNode.shortestDist > secondNodeInPath.shortestDist + weightToConnectingNode ){
          
          //if so, update its shortest distance
          connectingNode.shortestDist = secondNodeInPath.shortestDist + weightToConnectingNode;
          
          //then set its previous in the graph to the secondNodeInPath
          connectingNode.previous = secondNodeInPath;
          
          //finally add this path to the priority queue for processing
          queue.add( new UnDirectedGraphPath( connectingNode, connectingNode.shortestDist ) );
        }
        
      }
    }
      
      //finally iterate through the path and add each node on it to the arrayList of nodes
      //declare tha array that will store the shortest path
      ArrayList<UnDirectedGraphNode> shortestPathReverseArr = new ArrayList<UnDirectedGraphNode>();
     
      
      //add the end node to it
     // shortestPathReverseArr.add( endNode );
      
      //initialize a currNode for the iteartion and set it to endNode
      UnDirectedGraphNode currNode = endNode;
      
      //do the itearation
      while( currNode.equals( startNode ) == false ){
      
        //add the currNode to the path
        shortestPathReverseArr.add( currNode );
        
        //update currNode to point to the previous one (previous was determined by shortest path algorithm)
        currNode = currNode.previous;
      }
      
      //add the starting node as well
      shortestPathReverseArr.add(currNode);
      
    
    //declare the array in the correct order
    ArrayList<UnDirectedGraphNode> correctOrderArr = new ArrayList<UnDirectedGraphNode>();
    
    //finally get the array in the correct order
    for( int i = shortestPathReverseArr.size() - 1; i > -1; i--){
    
      correctOrderArr.add( shortestPathReverseArr.get( i ) );
    }
    
    //return the array in the correct order
    return correctOrderArr;
 
  }
  
  /**
   * Returns requested node.
   * @param key the key of the node
   * @return the node, if present, null if not
   */
  public UnDirectedGraphNode getNode( K key ){
    return vertCont.get( key );
  }
  
  public static void main(String[] args){
  
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
    System.out.println(pathTo2); 
  }
  
}
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
     * This field is the linked list that stores this nodes' adjacent nodes.
     * 
     */
    private LinkedList<UnDirectedGraphNode> adjList;
    
    /**
     * This field holds the actual value of the node.
     */
    private E value;
    
    /**
     * This field holds the key of the node.
     */
    private K key;
    
    /**
     * This field stores the node's weight (distance) to a particular
     * other node. Please, note that this weight is dependent on which
     * node this one is adjacent to.
     */
    private int weight;
    
    
    /**
     * The constructor initializes a node with the given value and key 
     * BUT does not link that node to any other nodes in the graph.
     * The constructor merely initializes the linked list container
     * used to hold the references that will be added later and
     * assings the given key and element to that node.
     * 
     * @param element the element we want to be added to the graph
     * @param paramKey the key of the node we are creating
     */
    public UnDirectedGraphNode( K paramKey, E element ) {
         
      //initialize the references list
      adjList = new LinkedList<UnDirectedGraphNode>();
      
      //set this node's value to the passed element
      this.value = element;
      
      //set the  key
      this.key = paramKey;
      
     
    }
    
    /**
     * This is an accessor method to allow external access to the
     * node's value.
     * @return the element stored in this node
     */
    public E getValue(){
      return value;
    }
    
    /**
     * Standard accessor method that returns this node's key.
     * @return this node's key
     */
    public K getKey(){
      return key;
    }
    
    /**
     * This method allows a user of this Node class
     * to add an adjacent node to this one (creates a connection
     * between this node and another one in the graph).
     * @param adjNode the new adjacent node
     * @return true if the operation is successful
     */
    public boolean addAdjacent( UnDirectedGraphNode adjNode, int weightToAdjNode ){
    
      //set this node's distance  to the adjacent node
      this.weight = weightToAdjNode;
      
      //set the adjacent node's distance to this node
      adjNode.weight = weightToAdjNode;
      
      //add this node to the passed node's adjacency list
      boolean res1 = adjNode.adjList.add( this );
      
      //add the passed node to this node's adjacency list
      boolean res2 = this.adjList.add( adjNode );
      
      //if one of the additions didn't work, this would be false
      return res1 && res2;
    }

  }
  
  /////////////////////////// End Of Inner Node Class /////////////////////////////
  
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
   * This method adds a new vertex to the graph. Please, node that at this point,
   * this node is NOT connected to any other node, but merely stored in this class's
   * internal container.
   * @param key the key of the node we want to add
   * @param value the value of the node we want to add
   * @return true if the node was successfully created, false if the key was already present
   */
  public boolean addNode( K key, E value ){
    
    //check if we already have this key
    if( vertCont.containsKey( key ) ){
      //if so, return false
      return false;
    }
    
    //if the key is not already present
     
    //create a new node with that key and value
    UnDirectedGraphNode tempNode = new UnDirectedGraphNode( key, value );
    
    //add it to the internal container
    vertCont.put( key, tempNode );
    
    // add it to this class's container and return the result of the addition
    return true;
  
  }
  
  /**
   * This method adds a connection between two nodes.
   * Please, note that since this is an undirected graph, 
   * the order of key1 and key2 may be switched around without
   * affecting the outcome.
   * @param key1 the first node to be connected
   * @param key2 the second node to be connected
   * @param weight the weight of the edge to be created
   * @return the result of the connection
   */
  public boolean addEdge( K key1, K key2, int weight){
  
    //get the first node associated with key1
    UnDirectedGraphNode tempNode1 = vertCont.get( key1 );
    
    //get the second node associated with key2
    UnDirectedGraphNode tempNode2 = vertCont.get( key2 );
    
    //link those two with the appropraite weight
    //note that this method creates a link in both directions with the same weight
    tempNode1.addAdjacent( tempNode2, weight );
  
    //return true????
    return true;
  }
}
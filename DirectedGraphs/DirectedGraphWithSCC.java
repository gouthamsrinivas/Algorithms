import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * Base class for Directed Graph.
 * Also computes Strongly connected components 
 */

public class DirectedGraphWithSCC {
	
	/*
	 * Set of variables and DS that operate on or during dfs on the original graph and during finding scc for the original grpah
	 * Scope changed to public so that other packages can access it.
	 */
	
    public long vertices ;

    public HashMap<Long,ArrayList<Long>> adjList = new HashMap<>();
	
    public Set<Long> visitedVertices = new HashSet<>();
	
	public HashMap<Long,Long> ccList = new HashMap<>() ;    //  Maintains a key -value to track connected components. Vertices with same value means they are connected
	
    public long ccIndex=1;
	
	
	
	/*
	 *  Set of variables and DS that operate on or during dfs on reversed Graph
	 */
	
     public long prePostCount=1;
	
     public Set<Long> visitedVerticesRev = new HashSet<>();
	 
	 public  HashMap<Long,ArrayList<Long>> reversedAdjList= new HashMap<>();
	
	 public  HashMap<Long,Long> preVisitOrderMap=new HashMap<>();  // just populate this map during pre processing 
	
	 public HashMap<Long,Long> postVisitOrderMap= new HashMap<>(); // Just populate this map during post order visit 
	
     public Map<Long,Long> invertedIndexPostOrderMap = new TreeMap<>(Collections.reverseOrder());
	


	



	
	DirectedGraphWithSCC(long vertices){
		this.vertices =vertices;
		
	}
	
	public long getNumberOfVertices(){
		return this.vertices;
	}
	
	
	
	public void findSCC(){
		doAdvDFSonReversedGraph();
		formStronglyConnectedComponents();
		
	}
	
	public void printSCCs(){
		
	}
	
	/*
	 * Note : One way to avoid the linear iteration is to use instead of map to use vertex --> ccIndex
	 * we can populate a map with ccIndex --> ArrrayList<Long> vertices;
	 * But for that map to be used, we need the ccIndex of the passed vertex..
	 * 
	 * So,if we use a map<ccIndex,ArrayList<Long>> along with the ccList map that we are using, we can get the connected components in constant time
	 * Also,in that way ,it will be easier to group and print all connected components
	 */
	
	 public ArrayList<Long> getConnectedComponets(long vertex){
	    	
	    	ArrayList<Long> ccComponents = new ArrayList<Long>();
	    	
	    	long ccIndexValue =ccList.get(vertex) ;  // The ccIndexValue for the given vertex
	    	
	    	// Now all the vertices that are connected to this vertex must have the same ccIndexValue in the map
	    	
	    	Set<Long> keyset =ccList.keySet();
	    	
	    	for(long currVertex : keyset){
	    		
	    		
	    		if(ccList.get(currVertex) == ccIndexValue && currVertex !=vertex){
	    			ccComponents.add(currVertex);
	    		}
	    	}
	    	
	    	return ccComponents;
	    	
	    	
	    }
	
	 /*
     * returns the number of connectedComponents in the graph
     */
    
    public long getNumberOfConnectedComponents(){
    	return ccIndex-1;
    }
    
    
    /*
     * Adds a directed Edge : Meaning from - fromVertex to ToVertex
     */
    public void addDirectedEdge(long fromVertex,long toVertex){
    	
    	
		ArrayList<Long> existingList =  adjList.get(fromVertex) ;
		
		if(existingList == null){
			existingList = new ArrayList<Long>();
		}
		
		existingList.add(toVertex) ;
		
		adjList.put(fromVertex, existingList) ;
		
		
		formAdjListForReversedGraph(toVertex, fromVertex); // Note How from and toVertex interchanged to form the reverse Graph
		
		
    }
    
	
	
	private void doAdvDFSonReversedGraph(){
		for(int i=1;i<=this.vertices ;i++){  	
			  if(!isVisitedRev(i)){
	        	processVertexWithPrePostForRevGraph(i);
	        }
		 }
	}
	
	
    
    
    /*
     * This method does the dfs and also forms connected components along the way
     * @params vertex - vertex which should be processed
     *         isReverseGraph - boolean flag which denotes whether it is done as part of forward dfs or dfs on reversed Grapn
     *         if true ,use reversedAdjList to get the adjacent vertices
     */
    
    private void processVertexWithPrePostForRevGraph(long vertex){ // make sure this method operates only on variables of the reverse Graph and utilizes method intended for reverse Graph
    	
    	if(isVisitedRev(vertex))
    		return;
    	
    	ArrayList<Long> adjVertices=reversedAdjList.get(vertex);
    	
    	
    	visitedVerticesRev.add(vertex) ;	
    	
    	
    	preVisitOrderMap.put(vertex, prePostCount);
    	prePostCount++;
    	
    	if(adjVertices !=null)         	        
    	  for(long currVertex : adjVertices)  // DO this only if adjvertices are there..
    		processVertexWithPrePostForRevGraph(currVertex); 
    
    	
    	
    	
    	postVisitOrderMap.put(vertex, prePostCount);
    	invertedIndexPostOrderMap.put(prePostCount, vertex) ; // Actually this map is enough for our purpose
    	prePostCount++;
    	
    }
    
    
    
    /*
     * This method does the dfs and also forms strongly connected components along the way
     */
    
    public void processVertexAndFormSCC(long vertex){
    	
    	if(isVisited(vertex))
    		return;
    	
    	ArrayList<Long> adjVertices = adjList.get(vertex) ;
    	
    	
    	visitedVertices.add(vertex) ;	
    	ccList.put(vertex,ccIndex) ;    // add it to the componentComponets
    	
    	
    	if(adjVertices==null)          // Means there is no neighbour to this graph
    		return;                    // NOTE : THis return is fine if we dont do post Order number..Else it work wrong as for the vertices with no adjVertices,postOrderNumbering will miss out 
    	
    	for(long currVertex : adjVertices) {   		
    		processVertexAndFormSCC(currVertex);
    	}
    	
    	
    	
    }
    
    
    /*
	 *  Checks if the given vertex is already visited
	 *  A vertex becomes visited if it is already Explored or processed
	 *  @param vertex - The vertex to be checked
	 *  @returns boolean if it is already visited .Else false
	 *  
	 */
	
	protected boolean isVisited(long vertex){
		return visitedVertices.contains(vertex);
	}
	
	protected boolean isVisitedRev(long vertex){
		return visitedVerticesRev.contains(vertex) ;
	}
    
    
    private void formAdjListForReversedGraph(long fromVertex,long toVertex){
    	
    	
		ArrayList<Long> existingList =  reversedAdjList.get(fromVertex) ;
		
		if(existingList == null){
			existingList = new ArrayList<Long>();
		}
		
		existingList.add(toVertex) ;
		
		reversedAdjList.put(fromVertex, existingList) ;
    	
    }
    
    /*
     * forms stronglyConnectedComponents based on the post order..
     * This method does nothing but explores individual vertices but the order is pased on the reversed post order
     */
    
    private void formStronglyConnectedComponents(){
    	
    	Collection<Long >postOrder = invertedIndexPostOrderMap.values();
    	
    	for(long v : postOrder){
    		if(!isVisited(v)){
    		  processVertexAndFormSCC(v);
    	  	  ccIndex++;
    		}
    	}
    	
    }

}



/*
 * Idea for computing Strongly Connected Component
 * 
 * 0) If V is in a sinc SCC,explore(v) finds vertices reachable from V. This is nothing but SCC of Vertex v
 * 1) We need to find sink component
 * 2) The vertex with the largest post order number is a source component
 * 3) The trick idea : Get the reversed graph and find the largest post order in it. It will be its source component.
 *        SO, the source in the reversed component will be the sink in the original Graph
 *        
 *        
 *        
 *   So, basically start with DFS on Reversed Graph and do post order numberings
 *   Based on descending order of post order numberings,
 *      do DFS on the forward Graph;     
 *        
 * 
 * 
 * 
 */

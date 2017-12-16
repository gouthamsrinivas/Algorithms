package com.graph.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.graph.construction.Graph;


/*
 *  Breadth-first search.
 *  Depth-first search finds some path from a source vertex s to a target vertex v. 
 *  We are often interested in finding the shortest such path (one with a minimal number of edges).
 *  Breadth-first search is a classic method based on this goal. 
 *  To find a shortest path from s to v, we start at s and check for v among all the vertices that we can reach by following one edge, then we check for v among all the vertices that we can reach from s by following two edges, and so forth.
  
    To implement this strategy, we maintain a queue of all vertices that have been marked but whose adjacency lists have not been checked. We put the source vertex on the queue, then perform the following steps until the queue is empty:
         Remove the next vertex v from the queue.
         Put onto the queue all unmarked vertices that are adjacent to v and mark them.
         
     TO DO : doest not work correctly for nodes 1 to N
             array distTo is from 0 to N-1 
             Change later.
             
 */

public class BreadthFirstPaths {

	ArrayList<Integer> bfsPath = new ArrayList<Integer>();
	private HashSet<Integer> visitedVertices= new HashSet<Integer>();
	Queue<Integer> vertexQueue = new LinkedList<Integer>();
	
	private static final int INFINITY = Integer.MAX_VALUE;
	
	HashMap<Integer,ArrayList<Integer>> adjListMap ;
	HashMap<Integer,ArrayList<Integer>> pathMap =new HashMap<>();
	
	int distTo[] ;
	
	public BreadthFirstPaths(Graph G, int S){
		
		this.adjListMap = G.getAdjacencyListMap();
		distTo=new int[G.getVerticesCount()];
		
		visitedVertices.add(S);  // Mark the source element as visited
		vertexQueue.add(S);      // Added the source to the queue
		
	    ArrayList<Integer> pathList=new ArrayList<Integer>();
	    pathList.add(S);
	    pathMap.put(S,pathList) ;
		
		getPaths(G,S) ;
	}
	
	// find paths in Graph G from source S
	public void getPaths(Graph G, int S){
		
		   for (int v = 0; v < G.getVerticesCount(); v++)
	            distTo[v] = INFINITY;
		
		distTo[S]=0; // Distance from source to source =0;
		
		
		while(vertexQueue!=null && vertexQueue.size() > 0){
			
			int topElement = vertexQueue.poll(); // Remove the firstElement
			
			System.out.println(topElement); //print the element. NOTE : The element in the queue will be marked before adding them ..SO should be true..
			
		//	System.out.println(topElement);  // Process the top Element
			
			ArrayList<Integer> currAdjList= adjListMap.get(topElement) ; // get the adjacency list for the top element
			
		
		
			ArrayList<Integer> pathListTillNow =  pathMap.get(topElement);
		
		   for(int i : currAdjList){
			   
			  if(isAlreadyVisited(i)) 
				  continue;
			  visitedVertices.add(i);                  // marking the elements before adding them to the queue 
			  distTo[i]=distTo[topElement]+1;  
			  
			  ArrayList<Integer> pathList = new ArrayList<Integer>(pathListTillNow);  // Get the traversed path till now say, 0 -4 -3
			  pathList.add(i);                                                        // Add the current node to the path .Ex : If currNode is 6, 0 4 3 6
			  pathMap.put(i, pathList);                                               // Put it to the pathMap
			  
			  vertexQueue.add(i);                                                     // Put the current element in the queue
			  
		   }
		   
		  
		   
		   // Note, here First the elements are marked and then added..
		   // So, all the elements which are added in the queue will be marked
		   
	}
	
		
		
	}
	
	//is there a path from Source S to V 
	public boolean hasPathTo(int v){
		return false;
	}
	
	// Gets the path from Source S to V
	public ArrayList<Integer> pathTo(int v){
		return pathMap.get(v);
		
	}
	
	/*
	 * returns the minimum distance from the source to the Vertex V
	 * The value which is already precomputed will be returned..
	 */
	
	/*
	 * EXPLANATION : How BFS works is, here from source NODE, we add neighbour nodes to the queue.
	 * {Everytime we add something to the queue, it is marked as visited and distance is computed }
	 * After LEVEl I neighbours are done, we add its neighbours to the queue {LEVEL 2 } .
	 * 
	 * So, In this way, if some node is Level 2 neighbour through say node A and level 3 neighbour thorugh say node B ,
	 * since level 2 neighbours are the ones visited first, that is marked and distance is noted down as 2
	 */
	public int minPathTo(int v){
		return distTo[v];
	}
	
	
	public boolean isAlreadyVisited(int v){
		return visitedVertices.contains(v);
	}
	
	
}

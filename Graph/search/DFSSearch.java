package com.graph.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.graph.construction.Graph;

/*
 * Modeled after robert Sedwevicks .
 * Has the same methods as his code 
 * Implementation is my own 
 * @author Goutham Srinivas
 * @date   23/7/2016
 */

public class DFSSearch {
	
	private HashSet<Integer> visitedVertices= new HashSet<Integer>();
	private int count=0;

	/*
	 * @param  Graph G
	 * @param  v- the vertex to which we have to find the connected vertices
	 * @return void
	 * @updates the count variable 
	 */
	public void search(Graph G, int V){
		
		HashMap<Integer,ArrayList<Integer>> adjListMap =  G.getAdjacencyListMap();
		ArrayList<Integer> currAdjList= adjListMap.get(V) ;
		
		visitedVertices.add(V); // Mark the starting Node.. THis code should be invoked only during first time ,ie when it is not a recursive invocation
		                        // I havent added isAlreadyVisited above,because by default visitedVertices.add wont add if it is already present
		                       
		
	
		//checkAndProcessAdjList(G, currAdjList);
		
          for(int i : currAdjList){
			
			if(isAlreadyVisited(i))      // If the vertex is already visited, continue..(skip to the next vertex ) Dont do anything
				continue;
			
		 	visitedVertices.add(i);   // Mark the current vertex as 'visited '
		 	System.out.println(i);  
		 	count++; // Increment the count
		 	
		 	search(G,i) ;
          }
		
		
	}

	// Not used..Code is directly subsituted as inline method ..
	private void checkAndProcessAdjList(Graph G, ArrayList<Integer> currAdjList) {
		
		for(int i : currAdjList){
			
			if(isAlreadyVisited(i))      // If the vertex is already visited, continue..(skip to the next vertex ) Dont do anything
				continue;
			
		 	visitedVertices.add(i);   // Mark the current vertex as 'visited '
		 	System.out.println(i);  
		 	count++; // Increment the count
		 	
		 	search(G,i) ;
			
		}
	}

	// Not used...And not needed
	private void checkAndProcessCurrentVertex(int V) {
		
		if(isAlreadyVisited(V))
			return;
		
		visitedVertices.add(V) ; // Mark the currect vertex as 'visited'
		System.out.println(V);  // Print the current vertex
	}
	
	
	/*
	 * if It is already Visited, then it also means, vertex v is connected to the source S {passed as a param to the search Method }
	 */
	public boolean isAlreadyVisited(int v){
		return visitedVertices.contains(v);
	}
	
	
	
	/*
	 * @return  the number of vertices connected to the given vertex
	 */
	public int count(){
		return count;
	}
	
	public int searchAndGetCount(Graph G,int V){
		search(G, V);
		return count;
		
	}
}

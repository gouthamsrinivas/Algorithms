package com.graph.construction;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	private int no_of_vertices=0;
	private int no_of_edges=0;
	private HashMap<Integer,ArrayList<Integer>> adjListMap= new HashMap<>();  // Maintains an adjacency List for the given vertex

	/*
	 * Initializes a Graph with given number of vertices
	 */
	public Graph(int no_of_vertices){
		this.no_of_vertices=no_of_vertices;
		for(int i=1; i<=no_of_vertices ;i++){
			initializeAdjList(i);
		}
		
		
	}

	private void initializeAdjList(int i) {
		ArrayList<Integer> adjListTemp= new ArrayList<Integer>();
		adjListMap.put(i,adjListTemp);
	}
	
	/*
	 * returns the number of vertices in the given graph
	 */
	public int getVerticesCount(){
		return no_of_vertices;
	
	}
	
	public HashMap<Integer, ArrayList<Integer>> getAdjacencyListMap(){
		return adjListMap;
	}
	
	/*
	 * returns the number of edges in the given graph
	 */
	public int getEdgesCount(){
		return no_of_edges ;
	}
	
	/*
	 * Adds a new edge 
	 * @param  fromVertex - starting vertex
	 * @param  toVertex - ending vertex
	 * @return True if addition of the edge is successful, else false
	 * 
	 */
	public boolean addEdge(int fromVertex,int toVertex){
		
		if(!isValidVertex(fromVertex) || !isValidVertex(toVertex))
			return false;
		
	   // if(validateVertex(fromVertex))
		
		// What if the list that is returned is null..Ideally should not be , as during the graph initiazation itself, we initialize the adjacency list
		ArrayList<Integer> currList = adjListMap.get(fromVertex);   // Alternatively, we can do lazy initialization here..
		currList.add(toVertex);
		adjListMap.put(fromVertex, currList);  // Note : We have to update again in the hashmap.. when we get a list using adjList.get , it makes a copy and return..
		
		
		currList=adjListMap.get(toVertex);  // add in the adjacency list of both..
		currList.add(fromVertex);
		adjListMap.put(toVertex, currList);
		no_of_edges++;
		
		return true;
	}
	
	
	public ArrayList<Integer> adj(int vertex){		
		return adjListMap.get(vertex);
	}
	
	
	/*
	 * vertex should be from 1 to no_vertex  1 <= vertex <= no_of_vertices
	 * @param    vertex which should be validated
	 * @return   True, if it is a valid vertex 
	 *                else returns false;
	 *                
	 *  UPDATE : Removing edge cases. so that both 0 to N-1 and 1 to N are supported              
	 * 
	 */
	private boolean isValidVertex(int vertex){
		return !(vertex < 0  || vertex > no_of_vertices);
	}
	

}

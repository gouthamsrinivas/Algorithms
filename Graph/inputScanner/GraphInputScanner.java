package com.graph.inputScanner;

import java.util.Scanner;

import com.graph.construction.Graph;

public class GraphInputScanner {

	
	/**
	 * reads input and then constructs a graph 
	 * {This method is intended to be used when nodes are numbered 1 to N }
	 * 
	 * @return the constructed Graph from scanned input
	 */
	public static Graph scanInputAndConstructGraph(){
		Scanner inp=new Scanner(System.in);  
        int v=inp.nextInt();
        int e=inp.nextInt();
        
         Graph graph= new Graph(v);
        for(int i =0 ; i<e ;i++){
       	 int startVertex = inp.nextInt() ;
       	 int endVertex =inp.nextInt();
       	 graph.addEdge(startVertex, endVertex);
        }
		return graph;
        
	}
	
	/**
	 * reads input and then constructs a graph 
	 * {This method is intended to be used when nodes are numbered 0 to N-1 }
	 * 
	 * @return the constructed Graph from scanned input
	 */
	public static Graph scanInputAndConstructGraph_zeroVertex(){
		Scanner inp=new Scanner(System.in);  
        int v=inp.nextInt();
        int e=inp.nextInt();
        
         Graph graph= new Graph(v);
        for(int i =0 ; i<e ;i++){
       	 int startVertex = inp.nextInt() ;
       	 int endVertex =inp.nextInt();
       	 graph.addEdge(startVertex+1, endVertex+1);
        }
		return graph;
        
	}
	
	/**
	 * scans input for T test cases.
	 * Internally constructs graph using  #scanInputAndConstructGraph method
	 */
	public static void scanInputForNtestCases(){
		
		Scanner inp=new Scanner(System.in);  
		
		int t= inp.nextInt();
		while(t-- > 0){
			Graph graph = scanInputAndConstructGraph();
		     int startingNode= inp.nextInt();                // scan for 'starting vertex' if needed. Comment and Uncomment line as required.
		}
	}
}

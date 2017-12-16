

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * Pass a graph with just adjList formed to this class and it will detect the cycles
 */

public class AcyclicDetector {

	DirectedGraphWithSCC dg ;
	
	private Set<Long> isVisitedDuringCurrExplore = new HashSet<Long>();  // maintains a set of vertices visited during current explore..Clear it at the start of next explore
	
	AcyclicDetector(DirectedGraphWithSCC dg){
		this.dg=dg;
		
	}
	
	
	public boolean hasCycle(){
		boolean hasCycle =false;
		for(int i=1;i<=this.dg.getNumberOfVertices();i++){
			this.isVisitedDuringCurrExplore.clear();
			if(!this.dg.isVisited(i)){
				hasCycle=checkForCycles(i);
				if(hasCycle==true)
					break;
			}
		

	}
		return hasCycle;
}


	private boolean checkForCycles(long vertex) {
		// TODO Auto-generated method stub
		
		boolean hasCycleDetected=false;
		
		if(this.isVisitedDuringCurrExplore(vertex))
			return true;
		
		ArrayList<Long> adjVertices = this.dg.adjList.get(vertex);
		System.out.println("Processing vertex "+vertex);
		this.dg.visitedVertices.add(vertex);
		this.isVisitedDuringCurrExplore.add(vertex);
		
		if(adjVertices==null)
			return false;
		
		for(long v: adjVertices){
			hasCycleDetected = hasCycleDetected |checkForCycles(v);
		}
		
		return hasCycleDetected;
		
		
		
	}
	
	private boolean isVisitedDuringCurrExplore(long vertex){
		return this.isVisitedDuringCurrExplore.contains(vertex);
	}
}

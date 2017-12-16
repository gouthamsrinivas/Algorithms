
import java.util.Scanner;

import week1.Graph;

public class DirectedGraphInputScanner {
	
public static DirectedGraphWithSCC scanInput(){
		
		Scanner scanner = new Scanner(System.in);
		
		int vertices = scanner.nextInt();
		int edges = scanner.nextInt();
		
		DirectedGraphWithSCC g= new DirectedGraphWithSCC(vertices);
		
		for(int i=0;i<edges ;i++) {
		
			int fromVertex = scanner.nextInt();
			int toVertex = scanner.nextInt();
			
			g.addDirectedEdge(fromVertex, toVertex);
			
		}
		
		scanner.close();
		
		return g;
	}

}

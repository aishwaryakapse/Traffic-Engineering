import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {
	int sizeOfMatrix = 37;
	private Map<String, LinkedHashSet<String>> hm = new HashMap<String, LinkedHashSet<String>>();
	double[][] capacityMatrix = new double[sizeOfMatrix][sizeOfMatrix];
	int[][] latencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

	Graph() {
		System.out.println("Graph Formed");
		for (int i = 0; i < sizeOfMatrix; i++) {
			for (int j = 0; j < sizeOfMatrix; j++) {
				capacityMatrix[i][j] = 0.0;
				latencyMatrix[i][j] = 999; //infinity
			}
		}
	}

	public void addVertex(String n1, String n2, String n3, String n4) {
		addEdge(n1, n2, n3, n4);
		addEdge(n2, n1, n3, n4);
	}
	
	public void addEdge(String n1, String n2, String n3, String n4) {
		int a3 = Integer.parseInt(n3);
		int a4 = Integer.parseInt(n4);
		LinkedHashSet<String> adj = hm.get(n1);
		if (adj == null) {
			adj = new LinkedHashSet<String>();
			hm.put(n1, adj);
		}
		adj.add(n2);
		int a1 = Integer.parseInt(n1);
		int a2 = Integer.parseInt(n2);
		capacityMatrix[a1][a2] = a3;
		latencyMatrix[a1][a2] = a4;
	}

	public LinkedList<String> adjNodes(String last) {
		LinkedHashSet<String> adj = hm.get(last);
		if (adj == null) {
			//Empty
			return new LinkedList<String>();
		}
		return new LinkedList<String>(adj);
	}

	public boolean isConnected(String n1, String n2) {
		Set<String> adj = hm.get(n1);
		if (adj == null) {
			return false;
		}
		return adj.contains(n2);
	}
	
	public int[][] getLatencyMatrix() {
		return latencyMatrix;
	}
	
	public double[][] getCapacityMatrix() {
		return capacityMatrix;
	}

}

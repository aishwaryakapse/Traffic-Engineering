import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FlowGraph {
	int sizeOfMatrix = 37;
	int[][] fgLatencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];
	double demandF;
	double demandR;
	int isValid = 1;

	ArrayList<String> sortedTunnelList = new ArrayList<String>();

	FlowGraph(Graph graph) {
		demandF = 0;
		System.out.println("Enter Bandwidth Demand : ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		demandR = sc.nextInt();
		ArrayList<String> tunnelList = new ArrayList<String>();
		PathSearch a = new PathSearch();
		a.bfs(graph);
		tunnelList = a.getTunnelList();

		fgLatencyMatrix = graph.getLatencyMatrix();

		Map<String, Integer> uMap = new HashMap<String, Integer>();
		for (int i = 0; i < tunnelList.size(); i++) {
			uMap.put(tunnelList.get(i), getLatency(tunnelList.get(i)));
		}

		Map<String, Integer> sortedMapAsc = sortingComparator(uMap, true);

		for (String key : sortedMapAsc.keySet()) {
			sortedTunnelList.add(key);
		}
		
		System.out.println("The number of routes from Source to Destination 6 are : "+sortedTunnelListSize());
		System.out.println("The mappings are : \n"+sortedTunnelList);
		
		//Do not close the scanner if you don't the program to loop
		//sc.close(); 
	}

	

	private static Map<String, Integer> sortingComparator(Map<String, Integer> uMap, final boolean sequence) {

		List<Entry<String, Integer>> lList = new LinkedList<Entry<String, Integer>>(uMap.entrySet());

		// Sorting the linked list based on values
		//Use the Comparator interface provide in java
		Collections.sort(lList, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (sequence) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order using LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : lList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public double getBottleneck(double[][] maincapacitymatrix) {
		double bnValue = 999;
		double[][] fgcapacitymatrix = new double[sizeOfMatrix][sizeOfMatrix];
		fgcapacitymatrix = maincapacitymatrix;
		String tempPath = sortedTunnelList.get(0);
		String[] bnarr = tempPath.split("-");
		for (int i = 0; i <= bnarr.length - 2; i++) {
			int bnR = Integer.parseInt(bnarr[i]);
			int bnC = Integer.parseInt(bnarr[i + 1]);
			double edgeCapacity = fgcapacitymatrix[bnR][bnC];
			// Finding the least capacity (bottleneck) edge
			if (edgeCapacity < bnValue) {
				bnValue = edgeCapacity;
			}
		}
		return bnValue;
	}
	
	public int getLatency(String s) {
		int lat = 0;
		String[] arr = s.split("-");
		for (int i = 0; i <= arr.length - 2; i++) {
			int r = Integer.parseInt(arr[i]);
			int c = Integer.parseInt(arr[i + 1]);
			lat = lat + fgLatencyMatrix[r][c];
		}
		return lat;
	}

	public double getFS(int flowGrphId, double limitValue) {
		double x = 0.0;
		double y = limitValue;
		if (flowGrphId == 0) {
			x = (y / 11);
		} else if (flowGrphId == 1) {
			x = (y / 0.5);
		} else {
			System.out.println("Fair Share Function Not Defined");
		}
		return x;
	}

	public int sortedTunnelListSize() {
		return sortedTunnelList.size();
	}
	
	public void updateTunnelList() {
		sortedTunnelList.remove(0);
	}
	
	public double getLimits(int flowGrphId, double fsValue) {
		double x = fsValue;
		double y = 0.0;
		if (flowGrphId == 0) {
			// -----------------------------Sample Problem from class Fair Share
			// Equations-----------------------------
			// if (x<=1.5)
			// {
			// y = (11*x);
			// }
			// else if(x>1.5 && x<5)
			// {
			// y = 15+x;
			// }
			// else
			// {
			// y = 20;
			// }
			// --------------------------------Fat Tree & DCell--------------------------------------------
			if (x <= 1) {
				y = (12 * x);
			} else if (x > 1 && x <= 5) {
				y = 10 + (2 * x);
			} else {
				y = 20;
			}
			// ------------------------------------------------------------------------------------------
		} else if (flowGrphId == 1) {
			// ------------------------------Sample Problem from class Fair Share
			// Equations----------------------------
			// if (x < 10)
			// {
			// y = (0.5*x);
			// }
			// else
			// {
			// y = 5;
			// }
			// ------------------------------- Fat Tree & Dell ----------------------------------------
			if (x < 2) {
				y = (5 * x);
			} else {
				y = 10;
			}
			// ----------------------------------- Flow Graph Not Defined  ----------------------------------------------------
		} else {
			System.out.println("Fair Share Function Undefined");
		}
		return y;
	}

	public double[][] updateThePath(double flow, double[][] maincapacitymatrix) {
		double[][] fgCapacityMatrix = new double[sizeOfMatrix][sizeOfMatrix];
		fgCapacityMatrix = maincapacitymatrix;
		String s = sortedTunnelList.get(0);
		String[] arr = s.split("-");
		int i = 0;
		while (i <= arr.length - 2) {
			int r = Integer.parseInt(arr[i]);
			int c = Integer.parseInt(arr[i + 1]);
			if (fgCapacityMatrix[r][c] - flow <= 0.1) {
				fgCapacityMatrix[r][c] = 0;
			} else {
				fgCapacityMatrix[r][c] = fgCapacityMatrix[r][c] - flow;
			}
			fgCapacityMatrix[r][c] = Math.round(fgCapacityMatrix[r][c] * 100);
			fgCapacityMatrix[r][c] = fgCapacityMatrix[r][c] / 100;
			i++;
		}
		return fgCapacityMatrix;
	}

	

	

}

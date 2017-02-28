import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PathSearch{

	String start;
	String stop;
	LinkedList<String> visited = new LinkedList<String>();
	ArrayList<String> tunnelList = new ArrayList<String>();
	
	PathSearch(){
	    System.out.println("Enter Source Node : ");
	    @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    start = sc.nextLine();
	    System.out.println("Enter Sink Node : ");
	    stop = sc.nextLine();
	    //sc.close();
	    visited.add(start);
	}
	
    public void bfs(Graph graph) 
    {
        LinkedList<String> nodes = graph.adjNodes(visited.getLast());
        
        for (String node : nodes) 
        {
            if (visited.contains(node)) 
            {
                continue;
            }
            if (node.equals(stop)) 
            {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        
        for (String node : nodes) 
        {
            if (visited.contains(node) || node.equals(stop)) 
            {
                continue;
            }
            visited.addLast(node);
            bfs(graph);
            visited.removeLast();
        }
    }

    public void printPath(LinkedList<String> visited){
    	String path = "";
        for (String node : visited) 
        {
            path = path +node + "-";
        }
        int length = path.length();
        path = path.substring(0, length-1);
        tunnelList.add(path);
        //System.out.println(path);
    }
    
    public ArrayList<String> getTunnelList(){
    	return(tunnelList);
    }
}
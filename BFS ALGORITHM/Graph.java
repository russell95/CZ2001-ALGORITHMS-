
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList; 
	 
class Neighbor {
    public int vertexNum;
    public Neighbor next;
    
    public Neighbor(int vnum, Neighbor nbr) {
            this.vertexNum = vnum;
            next = nbr;
    }
}
	 
class Vertex {
    String name;
    Neighbor adjList;
    Boolean visited;
    
    Vertex(String name, Neighbor neighbors) {
            this.name = name;
            this.adjList = neighbors;
            this.visited = false;
    }
    
    void setVisited() {
    	visited = true;
    }
    
}
	 
public class Graph {
 
    Vertex[] adjLists;
    Vertex[] parentLists;
    Queue<Integer> q = new LinkedList<>();
    ArrayList<String> stack = new ArrayList<String>();
     
    public Graph(String file) throws FileNotFoundException {
         
        Scanner sc = new Scanner(new File(file));
         
        //Create a table size to store each vertex
        int length = sc.nextInt();
        adjLists = new Vertex[length];
        parentLists = new Vertex[length];
 
        // read vertices
        //populate vertex into adjacency lists
        for (int v=0; v < adjLists.length; v++) {
            adjLists[v] = new Vertex(sc.next(), null);
        }
 
        // read edges (vertex and its neighbors)
        while (sc.hasNext()) {
             
            // read vertex names and translate to vertex numbers
            int parent = indexForName(sc.next());
            int child = indexForName(sc.next());
             
            // add child to front of parent's adjacency list and
            // add parent to front of child's adjacency list
            
        	if(adjLists[parent].adjList!=null) {
        		for (Neighbor nbr=adjLists[parent].adjList; nbr != null;nbr=nbr.next) {
        			if((adjLists[nbr.vertexNum].name).equals(adjLists[child].name)) {
        				break;
        			}else if(nbr.next==null) {
        				adjLists[parent].adjList = new Neighbor(child, adjLists[parent].adjList);
                        adjLists[child].adjList = new Neighbor(parent, adjLists[child].adjList);
                        break;
        			}
                }
        	}else {
        		adjLists[parent].adjList = new Neighbor(child, adjLists[parent].adjList);
        		adjLists[child].adjList = new Neighbor(parent, adjLists[child].adjList);
                
    		}
            
        }
    }
     
    //assigning index to vertex
    int indexForName(String name) {
        for (int v=0; v < adjLists.length; v++) {
            if (adjLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }   
     
    public void print() {
        System.out.println();
        for (int v=0; v < adjLists.length; v++) {
            System.out.print(adjLists[v].name);
            for (Neighbor nbr=adjLists[v].adjList; nbr != null;nbr=nbr.next) {
                System.out.print(" --> " + adjLists[nbr.vertexNum].name);
            }
            System.out.println("\n");
        }
    }
    
    public void printParentList() {
    	System.out.println();
    	for (int v=0; v < parentLists.length; v++) {
    			System.out.print(adjLists[v].name);
            System.out.print(" --> " +parentLists[v].name);
            System.out.println("\n");
        }
    }
    
    public void shortestPath(String src, String dst) {
    	
    	
	    	for (int v=0; v < parentLists.length; v++) {
			if((adjLists[v].name).equals(dst)) {
				stack.add(parentLists[v].name );
				dst = parentLists[v].name;
			} 
	    }
	    	if(dst.equals(src) == false) {
	    		shortestPath(src,dst);
	    	}
	    	else {
	    		for(int i=stack.size()-1; i>=0; i--) {
	    			System.out.print(stack.get(i) + "->");
	    		}
	    		System.out.print("end");
	    	}
    }
    
    public void setParents(int parent, int child) {
    		parentLists[child] = new Vertex(adjLists[parent].name, null);
    	
    }
    
    
    public void BFSTraversal(Neighbor nbr, int head, String dst) {
    		
    		while(!(q.isEmpty())) {
    			head = q.poll();
    			for (nbr=adjLists[head].adjList; nbr != null;nbr=nbr.next) {
        			
        			if(adjLists[nbr.vertexNum].visited==false) {
        				q.add(nbr.vertexNum);
        				adjLists[nbr.vertexNum].setVisited();
                		setParents(head, nbr.vertexNum);
        			}
             }
    			
    		}
    	
    }
    
    
    public void BFS(String src, String dst) {
    	int routeIndex=0;
    	
    	for (int v=0; v < adjLists.length; v++) {
            if(adjLists[v].name.equals(src)) {
            	routeIndex = v;
            	q.add(routeIndex);
            	break;
            }
        }

    	adjLists[routeIndex].setVisited();
    	Neighbor nbr=adjLists[routeIndex].adjList;
    	parentLists[routeIndex] = new Vertex("src", null);
    	BFSTraversal(nbr,routeIndex ,dst);
    	
    }
     
    /**
     * @param args
     */
    public static void main(String[] args) 
    throws IOException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String file = "src/Cities.txt";
        Graph graph = new Graph(file);
        graph.print();
        
        System.out.println("Choose SRC and DST: ");
		System.out.println("SRC: ");
		String src = sc.nextLine();
		System.out.println("DST: ");
		String dst = sc.nextLine();
		long startTimer = System.nanoTime();
		graph.BFS(src, dst);
		System.out.println();
		System.out.println("BFS Traversal Complete");
		System.out.println("Printing Parent Lists....");
		graph.printParentList();
		System.out.println("Print Path");
		graph.stack.add(dst);
		graph.shortestPath(src,dst);
		long endTimer = System.nanoTime();
		System.out.print("\n"+(endTimer-startTimer)+" ns");
    }
}


package graph;

import java.util.LinkedList;
import java.util.Stack;

/*
 * 
 * Basic Undirected (or Directed) Graph Implementation
 * 
*/
public class Graph {
    int n; // Number of Nodes
    LinkedList<Edge>[] adj; // Adjacency List

    public Graph(int size) { // constructor for a Graph with `size` nodes
        n = size;
        
        // initialise adjacency list
        adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    int size() { return n; }

    public Edge addEdge(int u, int v) { // Wrapper, adds unweighed Edge in one direction
        return addEdge(u, v, 0);
    }

    Edge addEdge(int u, int v, int w) { // Adds weighed Edge
        Edge e = new Edge(u, v, w);
        adj[u].add(e);
        return e;
    }

    public void addUndirectedEdge(int u, int v, int w) { // Adds two unweighed edges in both directions
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    public void addUndirectedEdge(int u, int v) { // adds an undirected edge between `u` and `v`
        addUndirectedEdge(u, v,0);
    }

    Edge getEdge(int u, int v) {
        for(Edge e : adj[u]) {
            if(e.target == v) {
                return e;
            }
        }
        return null;
    }

    public void DFS(int src) { // Depth First Search
    	Stack<Integer> s = new Stack<Integer>(); // initialise stack
        boolean[] visited = new boolean[n]; // initialise boolean array
        
    	s.push(src); // add source to the stack
        visited[src] = true; // set source to visited
        
    	while(!s.isEmpty()) {
            int v = s.pop();
            System.out.print(v + " "); 

    		for(Edge e : adj[v]) { // add unvisited neighbours of v to the stack
    			if(!visited[e.target]) {
    				s.push(e.target);
    			}
    		}
    	}
    	System.out.println();
    }
    
    public void BFS(int src) { // Breadth First Search
    	LinkedList<Integer> l = new LinkedList<Integer>(); // initialise FIFO-Queue
        boolean[] visited = new boolean[n]; // initialise boolean array
        
    	l.add(src); // add source to the list
        visited[src] = true;
        
        while(!l.isEmpty()) {

    		int v = l.poll(); // pop oldest vertex off stack
            System.out.print(v + " ");
                        
    		for(Edge e : adj[v]) { // add unvisited neighbours of v to the list
    			if(!visited[e.target]) {
                    visited[e.target] = true;
    				l.add(e.target);
    			}
    		}
    	}
    	System.out.println();
    }
    public static class Edge { // Internal Edge Class
        public int source, target, weight;
        Edge(int u, int v, int w) {
            this.source = u;
            this.target = v;
            this.weight = w;
        }
    }
}
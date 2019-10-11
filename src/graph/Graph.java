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

    public Graph(int size) {
        n = size;
        adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    int size() { return n; }

    public Edge addEdge(int u, int v) { // Wrapper, adds weighed Edge
        return addEdge(u, v, 0);
    }

    Edge addEdge(int u, int v, int w) { // Adds weighed Edge
        Edge e = new Edge(u, v, w);
        adj[u].add(e);
        return e;
    }

    public void addUndirectedEdge(int u, int v, int w) {
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    public void addUndirectedEdge(int u, int v) {
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
    	Stack<Integer> s = new Stack<Integer>();
        boolean[] visited = new boolean[n];
        
    	s.push(src);
        visited[src] = true;
        
    	while(!s.isEmpty()) {
            int v = s.pop();
            System.out.print(v + " ");
            visited[v] = true;
    		for(Edge e : adj[v]) {
    			if(!visited[e.target]) {
    				visited[e.target] = true;
    				s.push(e.target);
    			}
    		}
    	}
    	System.out.println();
    }
    
    public void BFS(int src) { // Breadth First Search
    	LinkedList<Integer> l = new LinkedList<Integer>();
        boolean[] visited = new boolean[n];
        
    	l.add(src);
        visited[src] = true;
        
    	while(!l.isEmpty()) {
    		int v = l.poll();
    		System.out.print(v + " ");
    		visited[v] = true;
    		for(Edge e : adj[v]) {
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
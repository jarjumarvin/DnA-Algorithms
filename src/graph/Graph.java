package graph;

import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    int n;
    LinkedList<Edge>[] adj;

    public Graph(int size) {
        n = size;
        adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    int size() { return n; }

    public Edge addEdge(int u, int v) {
        return addEdge(u, v, 0);
    }

    Edge addEdge(int u, int v, int w) {
        Edge e = new Edge(u, v, w);
        adj[u].add(e);
        return e;
    }

    public void addUndirectedEdge(int u, int v, int w) {
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    void addUndirectedEdge(int u, int v) {
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

    public void DFS(int src) {
        boolean[] visited = new boolean[n];
        Stack<Integer> s = new Stack<>();
        s.push(src);
        visited[src] = true;
        while(!s.isEmpty()) {
            int v = s.pop();
            System.out.print(v + " ");
            for(Edge e : adj[v]) {
                int neighbour = e.target;
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    s.push(neighbour);
                }
            }
        }
        System.out.println();
    }

    public void BFS(int src) {
        boolean[] visited = new boolean[n];
        LinkedList<Integer> q = new LinkedList<>();
        q.push(src);
        visited[src] = true;
        while(!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for(Edge e : adj[v]) {
                int neighbour = e.target;
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    public static class Edge {
        public int source, target, weight;
        Edge(int u, int v, int w) {
            this.source = u;
            this.target = v;
            this.weight = w;
        }
    }
}
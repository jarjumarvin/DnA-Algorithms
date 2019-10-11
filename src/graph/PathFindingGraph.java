package graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * 
 * Extension to Graph.java that implements Path Finding Algorithms
 * - Dijkstra
 * -> Boolean Array
 * -> Priority Queue
 * 
 * - Bellman Ford
 * - Floyd Warshall
 */
public class PathFindingGraph extends Graph {
    static LinkedList<Edge> edges;

    public PathFindingGraph(int size) {
        super(size);
        edges = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    @Override
    Edge addEdge(int u, int v, int w) { // This Implementation uses a list of Edges for Bellman Ford / Floyd Warshall
        Edge e = super.addEdge(u, v, w);
        edges.add(e);
        return e;
    }

    int[] booleanArrayDijkstra(int src, boolean getDistance) {
        int dist[] = new int[n];
        int parent[] = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            visited[i] = false;
        }
        dist[src] = 0;
        for(int i = 0; i < n - 1; i++) {
            int u = minDist(dist, visited);
            visited[u] = true;
            for(int v = 0; v < n; v++) {
                Edge e = getEdge(u, v);
                if(!visited[v] && e != null && dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight;
                    parent[v] = u;
                }
            }
        }
        return getDistance ? dist : parent;
    }
    
    int[] priorityQueueDijkstra(int src, boolean getDistance) {
        int dist[] = new int[n];
        int parent[] = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        dist[src] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(n, Comparator.comparingInt(a -> dist[a]));
        pq.add(src);
        while(!pq.isEmpty()) {
            int u = pq.poll();
            for (Edge e : adj[u]) {
                if (dist[e.source] + e.weight < dist[e.target]) {
                    dist[e.target] = dist[e.source] + e.weight;
                    parent[e.target] = e.source;
                    pq.add(e.target);
                }
            }
        }
        return getDistance ? dist : parent;
    }

    int minDist(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE, minNode = -1;
        for(int v = 0; v < n; v++) {
            if(!visited[v] && dist[v] <= minDist) {
                minDist = dist[v];
                minNode = v;
            }
        }
        return minNode;
    }

    public LinkedList shortestPathBooleanDijkstra(int src, int target) {
        int[] parent = booleanArrayDijkstra(src, false);
        return shortestPath(parent, src, target);
    }

    public LinkedList shortestPathPriorityQueueDijkstra(int src, int target) {
        int[] parent = priorityQueueDijkstra(src, false);
        return shortestPath(parent, src, target);
    }

    LinkedList shortestPath(int[] parent, int src, int target) {
        if(src > n - 1 || src < 0 || target > n-1 || target < 0) return null;
        LinkedList path = new LinkedList();
        path.addFirst(target);
        int current = parent[target];
        while(current != src) {
            if(current == -1) return null;
            path.addFirst(current);
            current = parent[current];
        }
        path.addFirst(src);
        return path;
    }

    int[] bellmanFord(int src, boolean getDistance) throws Exception {
        int[] dist = new int[n];
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        dist[src]  = 0;

        for(int i = 0; i < n - 1; i++) {
            for(Edge e : edges) {
                int u = e.source;
                int v = e.target;
                int w = e.weight;

                if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                }
            }
        }
        for(Edge e : edges) {
            int u = e.source;
            int v = e.target;
            int w = e.weight;

            if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                throw new Exception("Negative Cycle Detected");
            }
        }
        return getDistance ? dist : parent;
    }

    public LinkedList shortestPathBellmanFord(int src, int target) {
        try {
            int[] parent = bellmanFord(src, false);
            return shortestPath(parent, src, target);
        } catch (Exception e) {
            System.out.println("Negative Cycle Detected in Graph");
            return null;
        }
    }

    int[][] floydWarshall(boolean getDistances) {
        int[][] dist = new int[n][n];
        int[][] parent = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                parent[i][j] = -1;
            }
        }
        for (Edge e : edges) {
            dist[e.source][e.target] = e.weight;
            parent[e.source][e.target] = e.source;
        }
        for (int i = 0; i < n; i++) {
            for (int v = 0; v < n; v++) {
                if(parent[v][i] == -1) continue;
                for (int w = 0; w < n; w++) {
                    if(dist[i][w] != Integer.MAX_VALUE && dist[v][i] != Integer.MAX_VALUE) {
                        if (dist[v][w] > dist[v][i] + dist[i][w]) {
                            dist[v][w] = dist[v][i] + dist[i][w];
                            parent[v][w] = parent[i][w];
                        }
                    }
                }
            }
        }
        return getDistances ? dist : parent;
    }

    public LinkedList shortestPathFloydWarshall(int src, int target) {
        if(src > n - 1 || src < 0 || target > n-1 || target < 0) return null;
        int[][] parent = floydWarshall(false);
        if(parent[src][target] == Integer.MIN_VALUE) return null;
        return shortestPath(parent[src], src, target);
    }
}

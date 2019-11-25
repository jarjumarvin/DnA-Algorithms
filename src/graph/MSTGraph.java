package graph;

import datastructures.DisjointSet;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 
 * Extension to Graph.java that implements Kruskal (using DisjointSet) and Prim MST Algorithms
 * 
 */
public class MSTGraph extends Graph {
    public PriorityQueue<Edge> edges;

    public MSTGraph(int size) {
        super(size);
        edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    }

    @Override
    Edge addEdge(int u, int v, int w) { // This Implementation uses a PriorityQueue of Edges for Kruskal
        Edge e = super.addEdge(u, v, w);

        edges.add(e); // for kruskal, add all edges into a priority queue

        return e;
    }

    // Kruskal implementation using the Disjoin Set data structure, assumes the graph is connected
    public MSTGraph kruskal() { 
        DisjointSet disjointSet = new DisjointSet();
        disjointSet.make(n);

        MSTGraph g = new MSTGraph(n);
        while (!edges.isEmpty()) { // as long as there are edges left

            if(disjointSet.getSetCount() == 1) break; // if the disjointSet contains all nodes, break

            Edge e = edges.poll(); // find lowest weight edge

            if(disjointSet.find(e.source) != disjointSet.find(e.target)) { // if the endpoints of the edge belong to different sets, union the two sets
                int v = disjointSet.find(e.source);
                int w = disjointSet.find(e.target);

                disjointSet.union(v, w);

                g.addEdge(e.source, e.target, e.weight); // add the edge to the MST graph
            }
        }
        return g;
    }

    // Prims algorithm implementation using a priority queue of edges, assumes the graph is connected
    public MSTGraph prim(int src) {
        PriorityQueue<Edge> set = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight)); // initialise edge set, sorted by weight
        boolean[] included = new boolean[n]; // initialise boolean array

        included[src] = true; // set source to be included
        set.addAll(adj[src]); // add all nodes reachable from src to the edge list

        MSTGraph g = new MSTGraph(n); // initialise the MST graph

        while(!set.isEmpty() || g.edges.size() != n - 1) { // while there are still reachable nodes, or the MST graph doesn't contain all nodes
            Edge lowest = set.poll(); // find the lowest weight edge

            if(!included[lowest.target]) {  // if this edge points from our MST to a not-included vertex x, add it to the MST
                included[lowest.target] = true; 
                g.addEdge(lowest.source, lowest.target, lowest.weight);
                for(Edge e : adj[lowest.target]) { // add all edges that point to not-included nodes from x
                    if(!included[e.target]) set.add(e);
                }
            }
        }
        return g;
    }
}
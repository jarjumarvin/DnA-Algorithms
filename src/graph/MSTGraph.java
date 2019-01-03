package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MSTGraph extends Graph {
    public PriorityQueue<Edge> edges;

    public MSTGraph(int size) {
        super(size);
        edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    }

    @Override
    Edge addEdge(int u, int v, int w) { // This Implementation uses a PriorityQueue of Edges for Kruskal
        Edge e = super.addEdge(u, v, w);
        edges.add(e);
        return e;
    }

    public MSTGraph kruskal() {
        DisjointSet disjointSet = new DisjointSet();
        disjointSet.make(n);

        MSTGraph g = new MSTGraph(n);
        while (!edges.isEmpty()) {
            if(disjointSet.getSetCount() == 1) break;
            Edge e = edges.poll();
            if(disjointSet.find(e.source) != disjointSet.find(e.target)) {
                int v = disjointSet.find(e.source);
                int w = disjointSet.find(e.target);
                disjointSet.union(v, w);
                g.addEdge(e.source, e.target, e.weight);
            }
        }
        return g;
    }
}
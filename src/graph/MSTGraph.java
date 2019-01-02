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
            if(disjointSet.setCount == 1) break;
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

    static class DisjointSet {
        int[] p;
        int[] size;
        int setCount = 0;

        void make(int n) {
            p = new int[n];
            size = new int[n];
            this.setCount = n;
            for(int i = 0; i < n; i++) {
                p[i] = i;
                size[i] = 1;
            }
        }

        int find(int a) {
            while(p[a] != a) {
                a = p[a];
            }
            return a;
        }

        void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if(size[root_a] < size[root_b]) {
                p[root_a] = p[root_b];
                size[root_b] += size[root_a];
            } else {
                p[root_b] = p[root_a];
                size[root_a] += size[root_b];
            }
            setCount--;
        }
    }
}
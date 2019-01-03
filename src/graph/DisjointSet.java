package graph;

class DisjointSet {
    private int[] p;
    private int[] size;
    private int setCount = 0;

    int getSetCount() { return setCount; }

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
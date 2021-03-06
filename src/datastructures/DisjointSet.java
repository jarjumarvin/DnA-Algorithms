package datastructures;

public class DisjointSet {
    private int[] p;
    private int[] size;
    private int setCount = 0;

    public int getSetCount() { return setCount; }

    public void make(int n) { // Creates a Disjoint Set data structure with n sets
        p = new int[n];
        size = new int[n];
        this.setCount = n;
        for(int i = 0; i < n; i++) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a) { // finds the root of the tree beloning to a
        while(p[a] != a) {
            a = p[a];
        }
        return a;
    }

    public void union(int a, int b) { // combines two sets into one, appends the smaller tree to the larger
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
import graph.Graph;
import graph.MSTGraph;
import graph.PathFindingGraph;

public class Main {
    public static void main(String[] args) {
        searchAlgorithms();
        sortingAlgorithms();
        graphAlgorithms();
        System.out.println();
    }

    static void searchAlgorithms() {
        System.out.println("=========Search Algorithms=========");
        Search search = new Search();
        int[] list = search.getOrderedList(50);
        System.out.println("List: {0,....,49}");
        System.out.println("Search Values: {12, 15, 13, 2, 9, 27, 54, 37}");
        int[] v = new int[] {12, 15, 13, 2, 9, 27, 54, 37};
        System.out.println("-----------Linear Search-----------");
        for(int i : v) {
            System.out.print(search.linearSearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("-----------Binary Search-----------");
        for(int i : v) {
            System.out.print(search.binarySearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("--------Interpolation Search-------");
        for(int i : v) {
            System.out.print(search.interpolationSearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("===================================\n");
    }

    static void sortingAlgorithms() {
        System.out.println("========Sorting Algorithms=========");
        Sort sort = new Sort();
        System.out.println("Inputs: randomized arrays of length 20");
        System.out.println("------------Bubble Sort------------");
        int[] bubble = sort.getRandomList();
        sort.bubbleSort(bubble);
        printArray(bubble);
        System.out.println("-----------Selection Sort----------");
        int[] selection = sort.getRandomList();
        sort.selectionSort(selection);
        printArray(selection);
        System.out.println("-----------Insertion Sort----------");
        int[] insertion = sort.getRandomList();
        sort.insertionSort(insertion);
        printArray(insertion);
        System.out.println("-------------Merge Sort------------");
        System.out.println(">> TODO <<");
        System.out.println("-------------Quick Sort------------");
        int[] quick = sort.getRandomList();
        sort.quickSort(quick);
        printArray(quick);
        System.out.println("===================================\n");
    }

    static void printArray(int[] A) {
        for(int i : A) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void graphAlgorithms() {
        System.out.println("==========Graph Algorithms=========");
        System.out.println("------------DFS and BFS------------ (simple.png)");
        DFSandBFS();
        System.out.println();
        System.out.println("------------Path Finding----------- (path.png)");
        pathFinding();
        System.out.println();
        System.out.println("---------------Kruskal------------- (path.png)");
        mst();
        System.out.println("===================================\n");
    }

    static void DFSandBFS() {
        // Simple Graph (unweighed) for BFS / DFS
        Graph u = new Graph(7);
        u.addEdge(0, 1);
        u.addEdge(0, 2);
        u.addEdge(1, 3);
        u.addEdge(1, 4);
        u.addEdge(2, 5);
        u.addEdge(2, 6);

        u.DFS(0);
        u.BFS(0);
    }

    static void pathFinding() {
        // Weighed Graph with dijkstra (priority queue and standard), bellmanford, floyd warshall
        PathFindingGraph g = new PathFindingGraph(7);
        g.addUndirectedEdge(0, 1, 9);
        g.addUndirectedEdge(0, 4, 10);
        g.addUndirectedEdge(1, 6, 8);
        g.addUndirectedEdge(6, 4, 4);
        g.addUndirectedEdge(1, 2, 7);
        g.addUndirectedEdge(6,5,6);
        g.addUndirectedEdge(4,5,5);
        g.addUndirectedEdge(2, 3, 3);
        g.addUndirectedEdge(2, 5, 1);
        g.addUndirectedEdge(5, 3, 2);

        System.out.println("Boolean Array Dijkstra - 0 to 3");
        System.out.println(g.shortestPathBooleanDijkstra(0, 3));
        System.out.println("Priority Queue Dijkstra - 0 to 6");
        System.out.println(g.shortestPathPriorityQueueDijkstra(0, 6));
        System.out.println("Bellman Ford - 0 to 3");
        System.out.println(g.shortestPathBellmanFord(0, 3));
        System.out.println("Bellman Ford - 0 to 6");
        System.out.println(g.shortestPathBellmanFord(0, 6));
        System.out.println("Floyd Warshall - 0 to 3");
        System.out.println(g.shortestPathFloydWarshall(0, 3));
        System.out.println("Floyd Warshall - 0 to 6");
        System.out.println(g.shortestPathFloydWarshall(0, 6));
        System.out.println("Floyd Warshall - 3 to 0");
        System.out.println(g.shortestPathFloydWarshall(3, 0));
    }

    static void mst() {
        //Graph that implements prims algorithm (for connected graphs)
        MSTGraph g = new MSTGraph(7);
        g.addUndirectedEdge(0, 1, 9);
        g.addUndirectedEdge(0, 4, 10);
        g.addUndirectedEdge(1, 6, 8);
        g.addUndirectedEdge(6, 4, 4);
        g.addUndirectedEdge(1, 2, 7);
        g.addUndirectedEdge(6,5,6);
        g.addUndirectedEdge(4,5,5);
        g.addUndirectedEdge(2, 3, 3);
        g.addUndirectedEdge(2, 5, 1);
        g.addUndirectedEdge(5, 3, 2);

        MSTGraph mst = g.kruskal();
        System.out.println("Edges in the MST:");
        int weight = 0;
        for(Graph.Edge e : mst.edges) {
            weight += e.weight;
            System.out.println(e.source + " to " + e.target + ", weight: " + e.weight);
        }
        System.out.println("Total Weight: " + weight);
        System.out.println("BFS traversal to check if connected: ");
        mst.BFS(0);
    }

}

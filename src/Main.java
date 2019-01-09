import graph.Graph;
import graph.MSTGraph;
import graph.PathFindingGraph;
import search_sort.Search;
import search_sort.Sort;
import trees.AVLTree;
import trees.BinaryTree;
import trees.MultipleTree;

public class Main {
    public static void main(String[] args) {
        searchAlgorithms();
        sortingAlgorithms();
        graphAlgorithms();
        treeAlgorithms();
    }

    static void searchAlgorithms() {
        System.out.println("===============Search Algorithms==============");
        Search search = new Search();
        int[] list = ListUtil.getOrderedList(50);
        System.out.println("Input: {0,....,49}");
        System.out.println("Search Values: {12, 15, 13, 2, 9, 27, 54, 37}");
        int[] v = new int[] {12, 15, 13, 2, 9, 27, 54, 37};
        System.out.println("----------------Linear Search------------------");
        for(int i : v) {
            System.out.print(search.linearSearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("----------------Binary Search-----------------");
        for(int i : v) {
            System.out.print(search.binarySearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("---------------Interpolation Search-----------");
        for(int i : v) {
            System.out.print(search.interpolationSearch(list, i) + ", ");
        }
        System.out.println();
        System.out.println("===============================================\n");
    }

    static void sortingAlgorithms() {
        System.out.println("==============Sorting Algorithms===============");
        Sort sort = new Sort();
        System.out.println("Inputs: randomized arrays of length 20");
        System.out.println("------------------Bubble Sort------------------");
        int[] bubble = ListUtil.getRandomList();
        sort.bubbleSort(bubble);
        printArray(bubble);
        System.out.println("-----------------Selection Sort----------------");
        int[] selection = ListUtil.getRandomList();
        sort.selectionSort(selection);
        printArray(selection);
        System.out.println("-----------------Insertion Sort----------------");
        int[] insertion = ListUtil.getRandomList();
        sort.insertionSort(insertion);
        printArray(insertion);
        System.out.println("--------------------Heap Sort-----------------");
        int[] heap = ListUtil.getRandomList();
        sort.heapSort(heap);
        printArray(heap);
        System.out.println("--------------------Merge Sort-----------------");
        int[] merge = ListUtil.getRandomList();
        sort.mergeSort(merge);
        printArray(merge);
        System.out.println("--------------------Quick Sort-----------------");
        int[] quick = ListUtil.getRandomList();
        sort.quickSort(quick);
        printArray(quick);
        System.out.println("===============================================\n");
    }

    static void printArray(int[] A) {
        for(int i : A) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void graphAlgorithms() {
        System.out.println("===============Graph Algorithms================");
        System.out.println("-----------------DFS and BFS------- (simple.png)");
        DFSandBFS();
        System.out.println();
        System.out.println("-----------------Path Finding------- (path.png)");
        pathFinding();
        System.out.println();
        System.out.println("--------------------Kruskal--------- (path.png)");
        mst();
        System.out.println();
        System.out.println("----------------------Prim---------- (path.png)");
        prim();
        System.out.println();
        System.out.println("===============================================\n");
    }

    static void treeAlgorithms() {
        System.out.println("=============Tree Data Structures==============");
        System.out.println("---------------Standard Binary Tree------------");
        binaryTree();
        System.out.println();
        System.out.println("------------------Divisor Tree-----------------");
        divisorTree();
        System.out.println();
        System.out.println("---------------------AVL Tree------------------");
        avlTree();
        System.out.println("===============================================\n");
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

    static void prim() {
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

        MSTGraph mst = g.prim(0);
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

    static void binaryTree() {
        BinaryTree tree = new BinaryTree();
        System.out.println("Insert(1....20)");
        for(int i = 1; i <= 20; i++) {
            tree.insert(i);
        }
        System.out.println("Find(25) : " + tree.find(25));
        System.out.println("Rank(14) : " + tree.rank(14));
        System.out.println("Rank(16) : " + tree.rank(16));
        System.out.println("Delete(15)");
        tree.delete(15);
        System.out.println("Rank(16) : " + tree.rank(16));
        System.out.println("Delete(9)");
        tree.delete(9);
        System.out.println("Delete(12)");
        tree.delete(12);
        System.out.println("Rank(16) : " + tree.rank(16));
        System.out.println("Find(9) : " + tree.find(9));
    }

    static void divisorTree() {
        System.out.println("The divisor is 3. Ranges [a:b] are strict. Tree starts off with (1, 3, 7, 4, 5, 8, 6, 2)");
        MultipleTree tree = new MultipleTree(3); // setting 3 as the divisor
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(4);
        tree.insert(5);
        tree.insert(8);
        tree.insert(6);
        tree.insert(2);

        System.out.println("TotalDivisors: " + tree.divisors());
        System.out.println("Divisors in [2:5]: " + tree.divisorsBetween(2, 5));
        System.out.println("Delete(3) => (1, 7, 4, 5, 8, 6, 2)");
        tree.delete(3);
        System.out.println("Divisors in [2:5]: " + tree.divisorsBetween(2, 5));
        System.out.println("Insert(3) => (1, 3, 7, 4, 5, 8, 6, 2)");
        tree.insert(3);
        System.out.println("Divisors in [2:7]: " + tree.divisorsBetween(2, 7));
        System.out.println("Divisors in [1: ]: " + tree.divisorsGreaterThan(1));
        System.out.println("Insert(9) => (1, 3, 7, 4, 5, 8, 6, 9)");
        tree.insert(9);
        System.out.println("Divisors [1: ]:" + tree.divisorsGreaterThan(1));
    }

    static void avlTree() {
        AVLTree tree = new AVLTree();
        System.out.println("Ranges [a:b] are strict. Tree starts off with (1, 3, 7, 4, 5, 8, 6, 2)");
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(4);
        tree.insert(5);
        tree.insert(8);
        tree.insert(6);
        tree.insert(2);

        System.out.println("AVLCondition: " + tree.isAVL());
        System.out.println("Select Smallest: " + tree.select(0).getVal());
        System.out.println("Select Largest: " + tree.select(tree.size() - 1).getVal());
        System.out.println("Nodes in [0:5], rank(5): " + tree.rank(5));
        System.out.println("Contains 3: " + tree.contains(3));
        System.out.println("delete(3) => (1, 7, 4, 5, 8, 6, 2)");
        tree.delete(3);
        System.out.println("Contains 3: " + tree.contains(3));
        System.out.println("Nodes in [0:5], rank(5): " + tree.rank(5));
        System.out.println("Height of node with value 7: " + tree.find(7).getHeight());
        System.out.println("Nodes in [1:8]: " + tree.nodesBetween(1, 8));
        System.out.println("insert(3) twice => (1, 3(2), 7, 4, 5, 8, 6, 2)");
        tree.insert(3);
        tree.insert(3);
        System.out.println("multiplicity of 3: " + tree.multiplicity(3));
        System.out.println("Nodes in [1:8]: " + tree.nodesBetween(1, 8));
        System.out.println("delete(3) => (1, 3, 7, 4, 5, 8, 6, 2)");
        tree.delete(3);
        System.out.println("Nodes in [1:8]: " + tree.nodesBetween(1, 8));
        System.out.println("AVLCondition: " + tree.isAVL());
        System.out.println("isEmpty: " + tree.isEmpty());
    }
}

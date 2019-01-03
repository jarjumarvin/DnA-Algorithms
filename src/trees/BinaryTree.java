package trees;

public class BinaryTree {
    private Node root;

    // Public functions
    public Node find(int k) { // return node with value k or null
        return find(root, k);
    }

    public void insert(int k) {
        root = insert(root, k);
    }

    public void delete(int k) {
        root = delete(root, k);
    }

    public int rank(int k) { // return the rank of integer k, or 0 if value is not in the tree
        return rank(root, k);
    }

    //utility functions
    private int size(Node n) { return n == null ? 0 : n.size; } // for rank

    private Node find(Node n, int k) { // O(h) Binary Search
        if (n == null) return null;
        if (n.val == k) return n;
        else if (k < n.val) return find(n.left, k);
        else return find(n.right, k);
    }
    private Node insert(Node n, int k) { // O(h)
        if (n == null) return new Node(k); // Base Case, create new Node
        else if (k < n.val) n.left = insert(n.left, k); // Move to left Subtree
        else n.right = insert(n.right, k); // Move to Right Subtree
        // update size
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    private int rank(Node n, int k) { // number of nodes lesser than k
        if (n == null) return 0; // Base Case = 0
        if (k < n.val) return rank(n.left, k); // val > k, ignore right subtree
        else if (k > n.val) return 1 + size(n.left) + rank(n.right, k); // val < k, return count + size of left and call rank on right subtree
        else return size(n.left); // val = k, size of left
    }

    private Node delete(Node n, int k) {
        if (n == null) return null; // Base Case
        if (n.val > k) n.left = delete(n.left, k); // Move to left Subtree
        else if (n.val < k) n.right = delete(n.right, k); // Move to right Subtree
        else { // Node found, perform recursive delete using inorder successor
            if (n.left == null) return n.right;
            else if (n.right == null) return n.left;
            n.val = successor(n.right); // lowest value in the right subtree
            n.right = delete(n.right, n.val);
        }
        // update size
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    private int successor(Node n) {
        int v = n.val;
        while (n.left != null) {
            v = n.left.val;
            n = n.left;
        }
        return v;
    }

    class Node {
        int val;
        int size;
        Node left, right;
        Node(int k) {
            val = k;
            left = null;
            right = null;
            size = 1;
        }
    }
}
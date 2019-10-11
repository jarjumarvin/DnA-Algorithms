package trees;

/*
 * 
 * My Implementation of a balanced AVL Tree
 * 
*/
public class AVLTree {
    private Node root;

    // Public functions
    public int size() { return root == null ? 0 : root.size; }
    public boolean isEmpty() { return (size() == 0); }
    public boolean contains(int k) { return find(root, k) != null; }
    public boolean isAVL() { return checkAVL(root); }
    public int multiplicity(int k) {
        Node n = find(k);
        return n == null ? 0 : n.count;
    }

    public int nodesBetween(int a, int b) {
        if(root == null) return 0;
        return rank(root, b) - rank(root, a + 1);
    }

    public Node find(int k) { return find(root, k); } // return node with value k or null
    public Node select(int k) { return select(root, k); } // return node with the k'th largest value

    public int rank(int k) { return rank(root, k);} // number of nodes with value < k in the tree

    public void insert(int k) { root = insert(root, k); }
    public void delete(int k) { root = delete(root, k); }

    //Utility
    private int height(Node n) { return n == null ? -1 : n.height; }
    private int balanceFactor(Node n) { return height(n.left) - height(n.right); }
    private int size(Node n) { return n == null ? 0 : n.size; }

    // Private Functions
    private Node find(Node n, int k) { // binary search
        if(n == null) return null;
        if(n.val == k) return n;
        else if(k < n.val) return find(n.left, k);
        else return find(n.right, k);
    }

    private int rank(Node n, int k) { // number of nodes lesser than k
        if (n == null) return 0; // Base Case = 0
        if (k < n.val) return rank(n.left, k); // val > k, ignore right subtree
        else if (k > n.val) return n.count + size(n.left) + rank(n.right, k); // val < k, return count + size of left and call rank on right subtree
        else return size(n.left); // val = k, size of left
    }

    private Node select(Node n, int k) { // return the node with the k'th largest value
        if (n == null) return null;
        int t = size(n.left);
        if (t > k)
            return select(n.left, k);
        else if (t < k)
            return select(n.right, k - t - 1);
        else return n;
    }

    private Node rotateRight(Node n) {
        Node x = n.left;
        Node x_r = x.right;

        // rotate right
        x.right = n; // n to x.right
        n.left = x_r; // x_r to n.left

            /*
                    n                                x
                   / \                             /   \
                  x   4  --> rotateRight(n) -->  y      n
                 / \                            / \    / \
                y   x_r                        1   2  x_r 4
               / \
              1  2
             */

        // fix sizes and heights after rotating
        x.size = n.size;
        n.size = n.count + size(n.left) + size(n.right);

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }


    private Node rotateLeft(Node n) {
        Node x = n.right;
        Node x_l = x.left;

        // rotate left
        x.left = n; // n to x.keft
        n.right = x_l; // x_l to n.right

            /*
                n                                     x
               / \                                  /   \
              1   x      --> rotateLeft(n) -->    n      y
                 / \                             / \    / \
               x_l  y                           1  x_l 3   4d
                   / \
                  3   4
             */

        // fix sizes and heights after rotating
        x.size = n.size;
        n.size = n.count + size(n.left) + size(n.right);

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }


    private Node insert(Node n, int val) {
        if (n == null) return new Node(val); // add root if non-existent
        if(n.val == val) {
            n.count++;
            n.size++;
            return n;
        }

        if (val < n.val) n.left = insert(n.left, val);
        else n.right = insert(n.right, val);

        n.size = n.count + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));

        int b = balanceFactor(n);

        // left left case -> rotate right
        if (b > 1 && val < n.left.val) { return rotateRight(n); }
        // right right case -> rotate left
        if (b < -1 && val > n.right.val) { return rotateLeft(n); }
        // left right case -> rotate left on the left child of n, then rotate right on n
        if (b > 1 && val > n.left.val) {
                /*
                       n                                      n                                        x
                      / \                                    / \                                     /   \
                    n.l  4                                  x   4                                 n.l     n
                    / \         --> rotateLeft(n.l) ->     / \      --> rotateRight(n) ->        /  \    / \
                   1   x                                 n.l  3                                 1  x_l  3   4
                      / \                               /  \
                    x_l  3                             1   x_l

                */
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        // right left case -> rotate right on right child of n, then rotate left on n
        if (b < -1 && val < n.right.val) {
                /*
                       n                                     n                                  x
                      / \                                   / \                               /   \
                    1    n.r                               1   x                             n     n.r
                         / \    --> rightRotate(n.r) ->       / \  --> rotateLeft(n) ->    /  \    /  \
                        x   4                               2   n.r                       1    2 x_r  4
                       / \                                      / \
                      2  x_r                                  x_r  4

                */
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    private Node delete(Node n, int k) {
        if (n == null) return null; // Base Case
        if (n.val > k) n.left = delete(n.left, k); // Move to left Subtree
        else if (n.val < k) n.right = delete(n.right, k); // Move to right Subtree
        else { // Node find, perform recursive delete using inorder successor
            if(n.count > 1) { // Reduce multiplicity
                n.count--;
            } else {
                if (n.left == null) return n.right;
                else if (n.right == null) return n.left;
                else {
                    n.val = successor(n.right); // lowest value in the right subtree
                    n.right = delete(n.right, n.val);
                }
            }
        }

        // update height
        n.size = n.count + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));

        // Balance
        int b = balanceFactor(n);
        if (b > 1 && k < n.left.val) { return rotateRight(n); }
        if (b < -1 && k > n.right.val) { return rotateLeft(n); }
        if (b > 1 && k > n.left.val) {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (b < -1 && k < n.right.val) {
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    private int successor(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n.val;
    }


    private boolean checkAVL(Node n) {
        if(n == null) return true;
        int b = balanceFactor(n);
        if(b < -1 || b > 1) return false;
        return checkAVL(n.left) && checkAVL(n.right);
    }

    public class Node {
        private int val, height, size, count;
        private Node left, right;

        private Node(int v) {
            val = v;
            height = 0;
            size = 1;
            count = 1;
        }
        public int getVal() { return val; }
        public int getHeight() { return height; }
    }
}

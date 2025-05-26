public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public void setLeft(Node node) {
        if (left == null)
            left = node;
    }

    public void setRight(Node node) {
        if (right == null)
            right = node;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int Fibonacci(int x) {
        if (x < 2) {
            return 1;
        } else {
            return Fibonacci(x - 1) + Fibonacci(x - 2);
        }
    }

    void printPreorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    void printPostorder(Node node) {
        if (node == null)
            return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.data + " ");
    }

    void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public String print() {
        return this.print("", true, "");
    }

    public String print(String prefix, boolean isTail, String sb) {
        if (right != null) {
            right.print(prefix + (isTail ? "│ " : " "), false, sb);
        }
        System.out.println(prefix + (isTail ? "\\-- " : "/-- ") + data);
        if (left != null) {
            left.print(prefix + (isTail ? " " : "│ "), true, sb);
        }
        return sb;
    }
    
    public static void main(String[] args) {
                System.out.println("Creating Tree");
                BTree tree = new BTree();
        
                System.out.println("Count nodes in empty tree: " + tree.countNodes());
        
                System.out.println("Creating Nodes...");
                Node root = new Node(1);
                Node node2 = new Node(2);
                Node node3 = new Node(3);
                Node node4 = new Node(4);
                Node node5 = new Node(5);
                Node node6 = new Node(6);
                Node node7 = new Node(7);
        
                System.out.println("Setting root...");
                tree.setRoot(root);
        
                root.setLeft(node2);
                node2.setLeft(node4);
                node2.setRight(node5);
                node5.setLeft(node7);
                root.setRight(node3);
                node3.setRight(node6);
        
                System.out.println("Set the current node to be the root");
                tree.setCurrent(tree.getRoot());
                System.out.println("Current node: " + tree.getCurrent().getData());
        
                System.out.println("Count nodes in tree: " + tree.countNodes());
        
                System.out.println("\nIn-Order Traversal:");
                tree.printInorder();
                System.out.println("\nPre-Order Traversal:");
                tree.printPreOrder();
                System.out.println("\nPost-Order Traversal:");
                tree.printPostOrder();
        
                System.out.println("\nTree Structure:");
                tree.print();

                int n = 5;
                System.out.println("Faktorial dari " + n + " adalah " + factorial(n));
                System.out.println("Fibonacci dari " + n + " adalah " + Fibonacci(n));
        }

    // public static void main(String[] args) {
    //     Node root = new Node(1);
    //     Node node2 = new Node(2);
    //     Node node3 = new Node(3);
    //     Node node4 = new Node(4);
    //     Node node5 = new Node(5);
    //     Node node6 = new Node(6);
    //     Node node7 = new Node(7);
    //     root.setLeft(node2);
    //     node2.setLeft(node4);
    //     node2.setRight(node5);
    //     node5.setLeft(node7);
    //     root.setRight(node3);
    //     node3.setRight(node6);
    //     root.printInorder(root);
    //     System.out.println();
    //     root.printPostorder(root);
    //     System.out.println();
    //     root.printPreorder(root);
    //     System.out.println("\nBinary Tree Structure:");
    //     System.out.println();
    //     root.print();
    //     System.out.println("\nFactorial of 5 is: " + factorial(5));
    //     System.out.println("Fibonacci of 5 is: " + Fibonacci(5));
    // }
}
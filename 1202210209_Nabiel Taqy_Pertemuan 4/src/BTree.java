public class BTree {
    private Node root;
    private Node currentNode;

    public BTree() {
        root = null;
    }

//     public static void main(String[] args) {
//         System.out.println("Creating Tree");
//         BTree tree = new BTree();

//         System.out.println("Count nodes in empty tree: " + tree.countNodes());

//         System.out.println("Creating Nodes...");
//         Node root = new Node(1);
//         Node node2 = new Node(2);
//         Node node3 = new Node(3);
//         Node node4 = new Node(4);
//         Node node5 = new Node(5);
//         Node node6 = new Node(6);
//         Node node7 = new Node(7);

//         System.out.println("Setting root...");
//         tree.setRoot(root);

//         root.setLeft(node2);
//         node2.setLeft(node4);
//         node2.setRight(node5);
//         node5.setLeft(node7);
//         root.setRight(node3);
//         node3.setRight(node6);

//         System.out.println("Set the current node to be the root");
//         tree.setCurrent(tree.getRoot());
//         System.out.println("Current node: " + tree.getCurrent().getData());

//         System.out.println("Count nodes in tree: " + tree.countNodes());

//         System.out.println("\nIn-Order Traversal:");
//         tree.printInorder();
//         System.out.println("\nPre-Order Traversal:");
//         tree.printPreOrder();
//         System.out.println("\nPost-Order Traversal:");
//         tree.printPostOrder();

//         System.out.println("\nTree Structure:");
//         tree.print();
// }

    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(Node node, int data) {
        if (node == null) return false;
        if (node.getData() == data)
            return true;
        return search(node.getLeft(), data) || search(node.getRight(), data);
    }

    public void printInorder() {
        if (root != null) root.printInorder(root);
    }

    public void printPreOrder() {
        if (root != null) root.printPreorder(root);
    }

    public void printPostOrder() {
        if (root != null) root.printPostorder(root);
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    public void print() {
        if (root != null) root.print();
    }

    public Node getCurrent() {
        return currentNode;
    }

    public void setCurrent(Node node) {
        this.currentNode = node;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

}
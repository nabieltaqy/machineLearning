public class Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print("", true);
        }
    }
}

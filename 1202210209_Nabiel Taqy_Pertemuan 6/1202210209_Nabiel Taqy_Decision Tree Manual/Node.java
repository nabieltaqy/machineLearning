import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = new ArrayList<>();
    private Node parent;
    private String data;
    private NodeType type;

    public Node(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node node) {
        node.parent = this;
        this.children.add(node);
    }

    public Node getParent() {
        return parent;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public NodeType getType() {
        return type;
    }

    public void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "\\-- " : "|-- ") + data);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "|   "), true);
        }
    }
}

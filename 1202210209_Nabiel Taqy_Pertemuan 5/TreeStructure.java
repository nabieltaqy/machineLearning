import java.util.*;

class Node {
    Integer[][] state;
    List<Node> children;

    public Node(Integer[][] state) {
        this.state = state;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

        public void printTree(String prefix) {
            for (Integer[] row : state) {
                System.out.print(prefix);
                for (Integer num : row) {
                    System.out.print((num != null ? num : " ") + " ");
                }
                System.out.println();
            }
            
            for (Node child : children) {
                System.out.println(prefix + "|");
                child.printTree(prefix + "   ");
            }
        }
}

public class TreeStructure {
    public static void main(String[] args) {
        // Root Node
        Integer[][] rootState = {
            {1, 5, 6},
            {3, null, 2},
            {4, 7, 8}
        };
        Node root = new Node(rootState);
        
        // First Level Children
        Integer[][] child1State = {
            {1, 6},
            {3, 5, 2},
            {4, 7, 8}
        };
        Node child1 = new Node(child1State);
        
        Integer[][] child2State = {
            {1, 5, 6},
            {null, 3, 2},
            {4, 7, 8}
        };
        Node child2 = new Node(child2State);
        
        Integer[][] child3State = {
            {1, 5, 6},
            {3, 2},
            {4, 7, 8}
        };
        Node child3 = new Node(child3State);

        Integer[][] child4State = {
            {1, 5, 6},
            {3, 7,2},
            {4, null, 8}
        };
        Node child4 = new Node(child4State);

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        
        // Second Level Children
        Integer[][] leafState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, null}
        };
        Node leaf = new Node(leafState);
        child2.addChild(leaf);
        
        Integer[][] subChild41State = {
            {1, 5, 6},
            {3, 7, 2},
            {null, 4, 8}
        };
        Node subChild41 = new Node(subChild41State);
        
        Integer[][] subChild42State = {
            {1, 5, 6},
            {3, 7, 2},
            {4, 8}
        };
        Node subChild42 = new Node(subChild42State);
        
        child4.addChild(subChild41);
        child4.addChild(subChild42);

        // Print the tree structure
        root.printTree("");
    }
}



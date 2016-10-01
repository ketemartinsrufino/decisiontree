package j48;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kete on 9/21/16.
 */
public class Node {

    String label;
    Node parent;
    List<Node> subtrees = new ArrayList<Node>();

    public Node() { /* used to root node */}

    public Node(Node parent) {
        this.parent = parent;
    }

    public Node addNode(Node sub) {
        this.label = sub.label;
        if(sub != null) {
            subtrees.add(sub);
        }

        return this;
    }

    public String toString(Node node) {
        return " -> " + node.toString();
    }

    @Override
    public String toString() {
        String value = "(" + label+ ")";
        if(subtrees.size() != 0) {
            for(Node node: subtrees) {
                toString(node);
                System.out.println("\n");
            }
        }

        return value;
    }
}

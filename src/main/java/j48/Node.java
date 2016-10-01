package j48;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kete on 9/21/16.
 */
public class Node {

    String question;
    String value;
    Node parent;
    List<Node> subtrees = new ArrayList<Node>();

    public Node() { /* used to root node */}

    public Node(Node parent) {
        this.parent = parent;
    }

    public Node addNode(Node sub) {
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
        StringBuilder toString = new StringBuilder("(" + question + ")");
        if(value != null) {
            toString.append("["+ value + "]");
        }
        toString.append("\n");
        if(subtrees.size() != 0) {
            for(Node node: subtrees) {
                toString.append(toString(node));
            }

        }


        return toString.toString();
    }
}

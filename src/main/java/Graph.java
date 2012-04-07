import java.util.*;

public class Graph {


    private Map<Node, List<Node>> nodes = new HashMap<Node, List<Node>>();

    public void addEdge(Node start, Node end) {
        getAdjacentNodes(start).add(end);
    }

    public Collection<Node> getAdjacentNodes(Node start) {
        List<Node> edges = nodes.get(start);
        if (edges == null) {
            edges = new LinkedList<Node>();
            nodes.put(start, edges);
        }
        return edges;
    }

    public Collection<Node> getNodes() {
        return nodes.keySet();
    }

    public int getTotalEdges() {
        int total = 0;
        for (List<Node> adjacentNodes : nodes.values()) {
            total += adjacentNodes.size();
        }
        return total / 2;
    }

    public void removeEdge(Node start, Node end) {
        getAdjacentNodes(start).remove(end);
    }

    public void removeNode(Node node) {
        List<Node> adjacentNodes = nodes.get(node);
        if (adjacentNodes != null) {
            for (Node adjacentNode : adjacentNodes) {
                removeEdge(adjacentNode, node);
            }
        }
        nodes.remove(node);
    }

    public int getTotalNodes() {
        return nodes.size();
    }
}

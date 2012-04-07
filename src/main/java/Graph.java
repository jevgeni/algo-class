import java.util.*;

public class Graph {


    private Map<Node, Set<Node>> nodes = new HashMap<Node, Set<Node>>();

    public void addEdge(Node start, Node end) {
        getAdjacentNodes(start).add(end);
        getAdjacentNodes(end).add(start);
    }

    public Collection<Node> getAdjacentNodes(Node start) {
        Set<Node> edges = nodes.get(start);
        if (edges == null) {
            edges = new HashSet<Node>();
            nodes.put(start, edges);
        }
        return edges;
    }

    public Collection<Node> getNodes() {
        return nodes.keySet();
    }
}

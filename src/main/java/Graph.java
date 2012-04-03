import java.util.HashSet;
import java.util.Set;

public class Graph {


    private final boolean directed;
    private Set<Node> nodes = new HashSet<Node>();
    private Set<Edge> edges = new HashSet<Edge>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(Node start, Node end) {
        nodes.add(start);
        nodes.add(end);
        edges.add(new Edge(start, end, directed));
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }


}

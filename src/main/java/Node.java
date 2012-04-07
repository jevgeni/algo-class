import java.util.HashSet;
import java.util.Set;

public class Node {
    private final String id;
    Set<Edge> edges = new HashSet<Edge>();

    public Node(String id) {
        this.id = id;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                '}';
    }

    public Set<Node> getAdjacentNodes() {
        Set<Node> nodes = new HashSet<Node>();
        for (Edge edge : edges) {
            if (edge.getStart().equals(this)) {
                nodes.add(edge.getEnd());
            } else {
                nodes.add(edge.getStart());
            }
        }
        return nodes;
    }
}

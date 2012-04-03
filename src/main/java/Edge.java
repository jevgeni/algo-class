public class Edge {
    private final Node start;
    private final Node end;
    private boolean directed;

    public Edge(Node start, Node end, boolean directed) {
        this.start = start;
        this.end = end;
        this.directed = directed;
        start.addEdge(this);
        end.addEdge(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        boolean sameEdge = end.equals(edge.end) && start.equals(edge.start);
        if(!directed && !sameEdge) {
            sameEdge = start.equals(edge.end) && end.equals(edge.start);
        }

        return sameEdge;

    }

    @Override
    public int hashCode() {
        return 31 * (start.hashCode() + end.hashCode());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", directed=" + directed +
                '}';
    }
}

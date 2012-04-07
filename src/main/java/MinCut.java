public class MinCut {
    private Graph graph;

    public MinCut(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
    }

    public void contractEdge(Edge edge) {
        Node newNode = new Node(edge.getStart().getId() + "-" + edge.getEnd().getId());
//        graph.remove(edge);
//        graph.remove(edge.getStart());
//        graph.remove(edge.getEnd());
//        graph.removeAll(edge.getStart().getEdges());
//        graph.removeAll(edge.getEnd().getEdges());

        for (Node adjacentNode : edge.getStart().getAdjacentNodes()) {
            graph.addEdge(newNode, adjacentNode);
        }
        for (Node adjacentNode : edge.getEnd().getAdjacentNodes()) {
            graph.addEdge(newNode, adjacentNode);
        }
    }
}

import java.util.Collection;

public class MinCut {
    private Graph graph;

    public MinCut(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
    }

    public void contractEdge(Node start, Node end) {
        Node contractedNode = new Node(start.getId() + "-" + end.getId());
        Collection<Node> startAdjacentNodes = graph.getAdjacentNodes(start);
        for (Node node : startAdjacentNodes) {
            if (node.equals(end)) continue;
            graph.addEdge(contractedNode, node);
            graph.addEdge(node, contractedNode);
        }

        Collection<Node> endAdjacentNodes = graph.getAdjacentNodes(end);
        for (Node node : endAdjacentNodes) {
            if (node.equals(start)) continue;
            graph.addEdge(contractedNode, node);
            graph.addEdge(node, contractedNode);
        }

        graph.removeNode(start);
        graph.removeNode(end);


    }
}

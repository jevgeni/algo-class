import java.util.Collection;
import java.util.Scanner;

public class MinCut {
    private Graph graph;

    public MinCut(Graph graph) {
        this.graph = (Graph) graph.clone();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(MinCut.class.getResourceAsStream("kargerAdj.txt"));
        Graph graph = new Graph();
        while(scanner.hasNext()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            Node start = new Node(lineScanner.nextInt());
            while (lineScanner.hasNext()) {
                Node end = new Node(lineScanner.nextInt());
                graph.addEdge(start, end);
            }
        }

        System.out.println(getRandomizedMinCut(graph));

    }

    public static int getRandomizedMinCut(Graph graph) {
        int totalNodes = graph.getTotalNodes();
        int minCut = Integer.MAX_VALUE;
        for (int i = (int)Math.pow(totalNodes, 3); i >= 0; i--) {
            minCut = Math.min(minCut, new MinCut(graph).getMinCut());
        }
        return minCut;
    }

    public void contractEdge(Node start, Node end) {
        Node contractedNode = new Node(start.getId() + "-" + end.getId());
        Collection<Node> startAdjacentNodes = graph.getAdjacentNodesOrAddEmpty(start);
        for (Node node : startAdjacentNodes) {
            if (node.equals(end)) continue;
            graph.addEdge(contractedNode, node);
            graph.addEdge(node, contractedNode);
        }

        Collection<Node> endAdjacentNodes = graph.getAdjacentNodesOrAddEmpty(end);
        for (Node node : endAdjacentNodes) {
            if (node.equals(start)) continue;
            graph.addEdge(contractedNode, node);
            graph.addEdge(node, contractedNode);
        }

        graph.removeNode(start);
        graph.removeNode(end);


    }

    public int getMinCut() {
        while(graph.getTotalNodes() > 2) {
            Node start = graph.getRandomNode();
            Node end;
            do {
                end = graph.getRandomAdjacentNode(start);
            } while (end.equals(start));

            contractEdge(start, end);
        }
        return graph.getTotalEdges();
    }

    public Graph getGraph() {
        return graph;
    }

}

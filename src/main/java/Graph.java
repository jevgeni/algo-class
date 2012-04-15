import java.util.*;

public class Graph implements Cloneable {
    private HashMap<Node, List<Node>> nodes = new HashMap<Node, List<Node>>();

    public void addEdge(Node start, Node end) {
        getAdjacentNodesOrAddEmpty(start).add(end);
    }

    public List<Node> getAdjacentNodesOrAddEmpty(Node start) {
        List<Node> edges = nodes.get(start);
        if (edges == null) {
            edges = new LinkedList<Node>();
            nodes.put(start, edges);
        }
        return edges;
    }

    public List<Node> getAdjacentNodes(Node node) {
        List<Node> edges = nodes.get(node);
        return edges != null ? edges : Collections.<Node>emptyList();
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
        getAdjacentNodesOrAddEmpty(start).remove(end);
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

    @Override
    public String toString() {
        return nodes.toString();
    }

    public Node getRandomNode() {
        int index = random(nodes.size());
        int current = 0;
        for (Node node : nodes.keySet()) {
            if (current++ == index) return node;
        }
        return null;
    }

    private int random(int max) {
        return (int)(Math.random() * max);
    }

    public Node getRandomAdjacentNode(Node node) {
        List<Node> adjacentNodes = getAdjacentNodesOrAddEmpty(node);
        return adjacentNodes.get(random(adjacentNodes.size()));
    }

    @Override
    protected Object clone() {
        Graph graph = new Graph();
        for (Map.Entry<Node, List<Node>> entry : nodes.entrySet()) {
            graph.nodes.put(entry.getKey(), new LinkedList<Node>(entry.getValue()));
        }
        return graph;
    }

    public Graph reversed() {
        Graph graph = new Graph();
        for (Map.Entry<Node, List<Node>> entry : nodes.entrySet()) {
            Node start = entry.getKey();
            for (Node end : entry.getValue()) {
                graph.addEdge(end, start);
            }
        }
        return graph;
    }
}

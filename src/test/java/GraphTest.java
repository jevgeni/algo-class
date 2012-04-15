import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        graph.addEdge(new Node("1"), new Node("2"));
        graph.addEdge(new Node("1"), new Node("3"));
        graph.addEdge(new Node("2"), new Node("1"));
        graph.addEdge(new Node("2"), new Node("3"));
        graph.addEdge(new Node("3"), new Node("1"));
        graph.addEdge(new Node("3"), new Node("2"));
    }

    @Test
    public void addNodes() throws Exception {
        assertEquals(3, graph.getNodes().size());
        for (Node node : graph.getNodes()) {
            assertEquals(2, graph.getAdjacentNodesOrAddEmpty(node).size());
        }
    }

    @Test
    public void getTotalEdges() throws Exception {
        assertEquals(3, graph.getTotalEdges());
    }

    @Test
    public void removeEdge() throws Exception {
        assertEquals(2, graph.getAdjacentNodesOrAddEmpty(new Node("1")).size());
        graph.removeEdge(new Node("1"), new Node("3"));
        assertEquals(1, graph.getAdjacentNodesOrAddEmpty(new Node("1")).size());
    }

    @Test
    public void removeNode() throws Exception {
        graph.removeNode(new Node("1"));
        assertEquals(1, graph.getTotalEdges());
        assertEquals(2, graph.getTotalNodes());
    }

    @Test
    public void getRandomNode() throws Exception {
        Set<Node> nodes = new HashSet<Node>();
        for (int i = 0; i < 100; i++) {
             nodes.add(graph.getRandomNode());
        }
        assertEquals(nodes.toString(), 3, nodes.size());
    }
}

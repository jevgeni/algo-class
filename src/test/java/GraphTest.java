import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphTest {
    @Test
    public void addNodes() throws Exception {
        Graph graph = new Graph(false);
        graph.addEdge(new Node("1"), new Node("2"));
        graph.addEdge(new Node("1"), new Node("3"));
        graph.addEdge(new Node("2"), new Node("1"));
        graph.addEdge(new Node("2"), new Node("3"));
        graph.addEdge(new Node("3"), new Node("1"));
        graph.addEdge(new Node("3"), new Node("2"));

        assertEquals(3, graph.getNodes().size());
        assertEquals(3, graph.getEdges().size());
        for (Node node : graph.getNodes()) {
            assertEquals(1, node.getEdges().size());
        }
    }
}

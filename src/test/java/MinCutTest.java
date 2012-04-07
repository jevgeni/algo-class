import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinCutTest {

    @Test
    public void testMinCutForSimpleGraph() throws Exception {
        Graph graph = new Graph();
        graph.addEdge(new Node("1"), new Node("2"));
        graph.addEdge(new Node("1"), new Node("3"));
        graph.addEdge(new Node("2"), new Node("1"));
        graph.addEdge(new Node("2"), new Node("3"));
        graph.addEdge(new Node("3"), new Node("1"));
        graph.addEdge(new Node("3"), new Node("2"));

        MinCut minCut = new MinCut(graph);
        minCut.contractEdge(new Node("1"), new Node("2"));
        assertEquals(graph.toString(), 2, graph.getTotalEdges());
        assertEquals(graph.toString(), 2, graph.getTotalNodes());
    }
}

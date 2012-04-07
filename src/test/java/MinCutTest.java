import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinCutTest {
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
    public void testMinCutForSimpleGraph() throws Exception {
        MinCut minCut = new MinCut(graph);
        minCut.contractEdge(new Node("1"), new Node("2"));
        assertEquals(minCut.getGraph().toString(), 2, minCut.getGraph().getTotalEdges());
        assertEquals(minCut.getGraph().toString(), 2, minCut.getGraph().getTotalNodes());
    }

    @Test
    public void contractGraph() throws Exception {
        MinCut minCut = new MinCut(graph);
        assertEquals(2, minCut.getMinCut());
    }

    @Test
    public void complexGraph() throws Exception {
        graph.addEdge(new Node("2"), new Node("4"));
        graph.addEdge(new Node("4"), new Node("2"));
        graph.addEdge(new Node("3"), new Node("4"));
        graph.addEdge(new Node("4"), new Node("3"));

        assertEquals(2, MinCut.getRandomizedMinCut(graph));
    }
}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinCutTest {


    private Graph graph;
    private MinCut minCut;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        graph.addEdge(new Node("1"), new Node("2"));
        graph.addEdge(new Node("1"), new Node("3"));
        graph.addEdge(new Node("2"), new Node("1"));
        graph.addEdge(new Node("2"), new Node("3"));
        graph.addEdge(new Node("3"), new Node("1"));
        graph.addEdge(new Node("3"), new Node("2"));

        minCut = new MinCut(graph);
    }

    @Test
    public void testMinCutForSimpleGraph() throws Exception {
        minCut.contractEdge(new Node("1"), new Node("2"));
        assertEquals(graph.toString(), 2, graph.getTotalEdges());
        assertEquals(graph.toString(), 2, graph.getTotalNodes());
    }

    @Test
    public void contractGraph() throws Exception {
        assertEquals(2, minCut.getMinCut());
    }
}

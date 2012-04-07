import org.junit.Test;

import java.util.Iterator;

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

        Edge edge_1_to_2 = findEdge(graph, new Node("1"), new Node("2"));
        minCut.contractEdge(edge_1_to_2);
        System.out.println(graph.getNodes());
//        assertEquals(2, graph.getEdges().size());
        assertEquals(2, graph.getNodes().size());
        Iterator<Node> iterator = graph.getNodes().iterator();
        System.out.println(iterator.next().getEdges());
        System.out.println(iterator.next().getEdges());
    }

    private Edge findEdge(Graph graph, Node start, Node end) {
        Edge edge_1_to_2 = null;
//        for (Edge edge : graph.getEdges()) {
//            if (edge.getStart().equals(start) && edge.getEnd().equals(end)) {
//                edge_1_to_2 = edge;
//                break;
//            }
//        }
        return edge_1_to_2;
    }
}

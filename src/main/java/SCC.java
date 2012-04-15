import java.util.*;

public class SCC {
    private static HashMap<Node, Node> leaders = new HashMap<Node, Node>();

    static Node s;
    static Stack<Node> stack = new Stack<Node>();
    static Map<Node, Boolean> explored = new HashMap<Node, Boolean>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(SCC.class.getResourceAsStream("SCC.txt"));
        Graph g = new Graph();
        System.out.println("reading input..");
        long counter = 0;
        while(scanner.hasNextInt()) {
            if (counter % 100000 == 0) System.out.print('.');
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            g.addEdge(new Node(start), new Node(end));
            counter++;
        }

        System.out.println("calculating CSS...");
        calculateAndPrintCSSSizes(g);
    }

    public static void calculateAndPrintCSSSizes(Graph graph) {
        s = null;
        // first pass
        System.out.println("1th pass...");
        Graph reversedGraph = graph.reversed();
        System.out.println("Graph reversed");
        Collection<Node> nodes = reversedGraph.getNodes();
        for (Node node : nodes) {
            if (!isExplored(node)) {
                s = node;
                dfs(reversedGraph, node);
            }
        }

        leaders.clear();
        explored.clear();

        // find SCCs
        System.out.println("2nd pass...");
        List<Integer> sccSizes = new ArrayList<Integer>();
        while (!stack.empty()) {
            Node node = stack.pop();
            if (!isExplored(node)) {
                s = node;
                int sccSize = dfs(graph, node);
                sccSizes.add(sccSize);
            }
        }

        // print sccSizes
        Collections.sort(sccSizes);
        for(int i = sccSizes.size() - 1, j = 0; j < 5; i--, j++) {
            if (i >= 0) {
                System.out.print(sccSizes.get(i));
            } else {
                System.out.print(0);
            }
            System.out.print(",");
        }
    }

    private static int dfs(Graph g, Node i) {
        setExplored(i);
        leaders.put(i, s);
        int size = 1;
        for (Node j : g.getAdjacentNodes(i)) {
            if (!isExplored(j)) {
                size += dfs(g, j);
            }
        }
        stack.add(i);
        return size;
    }

    private static Boolean setExplored(Node i) {
        return explored.put(i, true);
    }


    private static boolean isExplored(Node node) {
        return explored.containsKey(node);
    }

}

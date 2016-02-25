import graph.*;

import java.util.*;

public class Prim {

    private Graph<Integer, Integer> graph; // G = (V, E)
    private Graph<Integer, Integer> mst;
    private Set<Node<Integer>> allNodes; // V
    private Set<Node<Integer>> visitedNodes; // W ⊆ V
    private Set<Node<Integer>> notVisitedNodes; // V \ W
    private Set<Edge<Integer, Integer>> utilisedEdges; // F ⊆ E
    private Node<Integer> source; // s ∈ V
    private Map<Node<Integer>, Integer> distances;


    public Prim(Graph<Integer, Integer> graph) {
        this.graph = graph;

        source = this.graph.getNode(0); // NOTE simply store 0th index rather
                                        //      than node?

        // Init V = {v | v <- graph}
        allNodes = new HashSet<Node<Integer>>(graph); // NOTE possible need
                                                      //      graph.getNodes

        // Init W = {s}
        visitedNodes = new HashSet<Node<Integer>>();
        visitedNodes.add(source);

        // Init V \ W = setNodes.removeAll(visitedNodes)
        tmp = new HashSet<Node<Integer>>(allNodes);
        notVisitedNodes = new HashSet<Node<Integer>>(tmp.removeAll(visitedNodes));
        tmp = null;

        // Init F = {}
        utilisedEdges = new HashSet<Edge<Integer, Integer>>();

        // Init D(v) = d(s, v) for all v in V
        distances = new HashMap<Node<Integer>, Integer>();
        initDistances();

        // Find minimum spanning tree
        mst = findMinimumSpanningTree();
    }


    /**
     * Set all keys in distances to each node in graph. Initialise all
     * corresponding values to distance to source node
     */
    private void initDistances() {
        // set k = node, v = infinity for all nodes in graph
        for (Node node : allNodes) {
            distances.put(node, dist(source, node));
        }
    }


    /** TODO
     * Uses Prim's algorithm to find the mimimum spanning tree for graph
     */
    private Graph<Integer, Integer> findMinimumSpanningTree() {

        // Select a new current vertex w in V \ W, with minimal D(w)


        // Add current vertex w to W, add related edge to F


        // Update distances


        // If V = W exit, else recurse/iterate (NOTE: move base case to top?)


        return graph;
    }


    /** TODO
     * Finds the shortest immediate distance between nodes v1 and v2. If not
     * directly connected, will return infinity (Integer.MAX_VALUE)
     *
     * @param v1 the first node to find distance between
     * @param v2 the second node to find distance between
     */
    private Integer dist(Node<Integer> v1, Node<Integer> v2) {
        return -1;
    }


    /** TODO
     * Finds immediate distance between mst so far and given vertex in graph
     *
     * @param vertex the vertex to find shortest immediate distance to MST for
     */
    private Integer Dist(Node<Integer> vertex) {
        // find min(D(v), d(w, v))
        return -1;
    }


    /**
     * Returns minimum spanning tree for graph
     */
    public Graph<Integer, Integer> getMinimumSpanningTree() {
        //TODO: You should return a new graph that represents the minimum
        // spanning tree of the graph.
        return mst;
    }

}

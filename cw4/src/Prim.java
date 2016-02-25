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

        source = this.graph.getNode(1); // NOTE maybe fix, looks for node value = 1

        // Init V = {v | v <- graph}
        allNodes = new HashSet<Node<Integer>>(graph.getNodes()); // NOTE possible need
                                                                 // graph.getNodes()

        // Init W = {s}
        visitedNodes = new HashSet<Node<Integer>>();
        visitedNodes.add(source);

        // Init V \ W = setNodes.removeAll(visitedNodes)
        Set<Node<Integer>> notVisitedNodes = new HashSet<Node<Integer>>(allNodes);
        notVisitedNodes.removeAll(visitedNodes);

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
        // set k = node, v = dist to source
        for (Node node : allNodes) {
            distances.put(node, dist(source, node));
        }
    }


    /** TODO
     * Uses Prim's algorithm to find the mimimum spanning tree for graph
     */
    private Graph<Integer, Integer> findMinimumSpanningTree() {

        // mst to construct from utilisedEdges after Prims, and then return later
        Graph minimumSpanningTree = new DirectedGraph<Integer, Integer>();

        // continue until visitedNodes = allNodes
        while (!visitedNodes.equals(allNodes)) {

            // Select a new current vertex w in V \ W, with minimal D(w)
            // TODO update to return pair: (minNode, edge from min to source)
            Pair<Node<Integer>, Edge<Integer, Integer>> min = findMinDistNode();

            // Add current vertex w to W, add related edge to F
            visitedNodes.add(min.getLeft());
            utilisedEdges.add(min.getRight());

            // Update distances for all v in V \ W
            for (Node<Integer> v : notVisitedNodes) {
                distances.replace(v, Dist(v));
            }

            // construct tree from utilisedEdges
            for (Node<Integer> node : allNodes) {
                minimumSpanningTree.add(node);
            }
            for (Edge<Integer, Integer> edge : utilisedEdges) {
                minimumSpanningTree.add(edge);
            }

        }

        return minimumSpanningTree;
    }


    /** TODO
     * Looks up node with shortest distance to mst (so far) and returns a
     * pair: (cloest vertex, corresponding edge)
     */
     private Pair<Node<Integer>, Edge<Integer, Integer>> findMinDistNode() {
         return null;
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

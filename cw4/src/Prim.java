import graph.*;

import java.util.*;

public class Prim {

    private Graph<Integer, Integer> graph;
    private Set<Node<Integer>> visitedNodes;
    private Set<Edge<Integer>> utilisedEdges;
    private Node<Integer> source;
    private Graph<Integer, Integer> mst;
    // dists = create map of index of each node v paired with D(v)


    public Prim(Graph<Integer, Integer> graph) {
        //TODO: Using the passed in graph, implement Prims algorithm in this
        // class.

        this.graph = graph;
        visitedNodes = new Set<Node<Integer>>();
        utilisedEdges = new Set<Edge<Integer>>();
        // source = node of graph with zero index (first node)
        // init distances as infinity (= Integer.MAX_VALUE)

        mst = findMinimumSpanningTree();
    }



    /** TODO
     * Uses Prim's algorithm to find the mimimum spanning tree for graph
     */
    private Graph<Integer, Integer> findMinimumSpanningTree() {

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
     * & not in MST
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

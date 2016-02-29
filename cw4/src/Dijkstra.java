import graph.*;

import java.util.*;

public class Dijkstra {

	HashMap<Integer, Integer> nodeMap = new HashMap<>();

    public Dijkstra(Graph<Integer, Integer> graph) {
		int n = 10000;
    	List<Node<Integer>> notVisitedNodes = graph.getNodes();
    	List<Node<Integer>> visitedNodes = new ArrayList<>();
    	for(Node<Integer> node : notVisitedNodes){
    		nodeMap.put(node.getIndex(), n);
    	}
    	//Initialise source node
    	nodeMap.put(1, 0);
    	Node<Integer> currentNode = graph.getNode(1);

    	while(!notVisitedNodes.isEmpty()){
    		List<Edge<Integer, Integer>> edgesFrom = graph.getEdgesFrom(currentNode);
	    	for(Edge<Integer, Integer> edge : edgesFrom){
	    		Node<Integer> sourceNode = currentNode;
	    		Node<Integer> destinationNode = edge.other(currentNode);
	    		if(notVisitedNodes.contains(destinationNode)){
		    		int edgeLength = edge.getData();
		    		int distance = edgeLength + nodeMap.get(sourceNode.getIndex());
		    		if(distance < nodeMap.get(destinationNode.getIndex())) {
		    			nodeMap.put(destinationNode.getIndex(), distance);
					}
	    		}
	    	}

	    	visitedNodes.add(currentNode);
	    	notVisitedNodes.remove(currentNode);
	    	int distance = n;

	    	for(Node<Integer> newNode: notVisitedNodes){
	    		int totalDistance = nodeMap.get(newNode.getIndex());
	    		if (totalDistance < distance){
	    			distance = totalDistance;
	    			currentNode = newNode;
	    		}
	    	}
    	}

    }

    public List<Integer> shortestPath(Integer origin, Integer destination, Graph<Integer, Integer> graph) {

    	Node<Integer> destinationNode = graph.getNode(destination);
    	Node<Integer> originNode = graph.getNode(origin);
    	List<Integer> nodeList = new ArrayList<>();
    	nodeList.add(destinationNode.getIndex());

    	while(destinationNode != originNode ){
			int destinationValue = nodeMap.get(destinationNode.getIndex());
			List<Edge<Integer,Integer>> edgesTo = graph.getEdgesTo(destinationNode);
			for(Edge<Integer, Integer> edge: edgesTo){
				Node<Integer> sourceNode = edge.other(destinationNode);
				int sourceValue = nodeMap.get(sourceNode.getIndex());
				if(destinationValue - sourceValue == edge.getData()){
					nodeList.add(sourceNode.getIndex());
					destinationNode = sourceNode;
					break;
				}
			}
		}

    	Collections.reverse(nodeList);
    	return nodeList;

    }

}

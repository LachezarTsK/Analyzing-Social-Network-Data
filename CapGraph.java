
package graph;

import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author Lachezar Kutsarov. A class that represents a directed graph of a
 *         social network.
 */
public class CapGraph implements Graph {
	/**
	 * A HashMap representing the network. The keys of the map are all
	 * NetworkNodes. The value for each key is the set of NetworkNodes to which
	 * the key is connected through directed edges coming out from key node
	 * itself.
	 */
	private HashMap<NetworkNode, HashSet<NetworkNode>> network;

	/**
	 * A helper HashMap containing as keys the unique id numbers of all nodes
	 * and as values the nodes themselves. Used for fast check for the existence
	 * id numbers and for quick access to the nodes through their id numbers.
	 */
	protected HashMap<Integer, NetworkNode> idsToNodes;

	/**
	 * A HashSet containing all the edges in the graph.
	 */
	private HashSet<NetworkEdge> edges;

	/**
	 * Creates a new CapGraph object. Initializes the instance variables.
	 */
	public CapGraph() {
		this.network = new HashMap<NetworkNode, HashSet<NetworkNode>>();
		this.idsToNodes = new HashMap<Integer, NetworkNode>();
		this.edges = new HashSet<NetworkEdge>();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {

		if (!idsToNodes.containsKey(num)) {

			NetworkNode node = new NetworkNode(num);
			network.put(node, new HashSet<NetworkNode>());
			idsToNodes.put(num, node);

		} else {
			throw new IllegalArgumentException("This vertex alreday exists in the graph!");
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {

		if (idsToNodes.containsKey(from) && idsToNodes.containsKey(to)) {

			NetworkEdge edge = new NetworkEdge(idsToNodes.get(from), idsToNodes.get(to));
			idsToNodes.get(from).addEdge(edge);
			edges.add(edge);

		} else {
			throw new IllegalArgumentException("Invalid NetworkNode(s). Check input parameters!");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {

		EgonetGraph egonet = new EgonetGraph(center);
		egonet.getEgonetGraph(idsToNodes);

		return egonet;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#getSCCs()
	 * 
	 * @see graph.DepthFirstSearch (for dfs helper methods)
	 */
	@Override
	public List<Graph> getSCCs() {

		
		DepthFirstSearch search = new DepthFirstSearch();
		Stack<NetworkNode> results = search.dfsOriginalGraph(this.network.keySet());

		// creates a transposed graph
		this.reverseEdges(edges);

		List<Graph> sccAllGraphs = search.dfsTransposedGraph(results);

		// restores the original graph
		this.reverseEdges(edges);

		return sccAllGraphs;
	}

	/**
	 * Reverses the edges of the graph - the nodes at the beginning of the edges
	 * are turned into end nodes and the initial end nodes are turned into start
	 * nodes
	 * 
	 * @param edges
	 *            A HashSet of all edges in the graph
	 */
	public void reverseEdges(HashSet<NetworkEdge> edges) {

		// creates a copy of all the edges in the graph
		HashSet<NetworkEdge> edgesCopy = new HashSet<NetworkEdge>(edges);
		edges.clear();

		// disconnects all the nodes from their edges
		for (NetworkNode node : this.network.keySet()) {
			node.clearEdges();
		}

		// reverses the edge - the nodes at both ends are swapped
		for (NetworkEdge edge : edgesCopy) {

			int to = edge.getToNetworkNode().getIdNumber();
			int from = edge.getFromNetworkNode().getIdNumber();
			this.addEdge(to, from);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {

		HashMap<Integer, HashSet<Integer>> exportedGraph = new HashMap<Integer, HashSet<Integer>>();
		for (Integer number : idsToNodes.keySet()) {
			exportedGraph.put(number, idsToNodes.get(number).getIdNumbersOfNeighbors());
		}
		return exportedGraph;
	}
}

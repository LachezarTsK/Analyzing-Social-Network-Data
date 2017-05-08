package graph;

import java.util.HashSet;

/**
 * @author Lachezar Kutsarov. A class which represents a node in a directed
 *         graph.
 */

public class NetworkNode {

	// The id number of the NetworkNode.
	private int idNumber;

	// A list of edges (paths) coming out of the NetworkNode.
	private HashSet<NetworkEdge> edges;

	// Creates a new NetworkNode object. Initializes the instance variables.
	public NetworkNode(int idNumber) {
		this.idNumber = idNumber;
		this.edges = new HashSet<NetworkEdge>();
	}

	/**
	 * Get the number of edges extending from a NetworkNode.
	 * 
	 * @return The total number of edges coming out of a NetworkNode.
	 */
	public int getNumEdges() {
		return this.edges.size();
	}

	/**
	 * Get the id number of the NetworkNode.
	 * 
	 * @return An integer associated with this NetworkNode.
	 */
	public int getIdNumber() {
		return this.idNumber;
	}

	/**
	 * Get the list of edges extending from this NetworkNode.
	 * 
	 * @return A copy of the list of NetworkEdges.
	 */
	public HashSet<NetworkEdge> getEdges() {
		return new HashSet<NetworkEdge>(this.edges);
	}

	/**
	 * Adds a new edge extending from this NetworkNode
	 * 
	 * @param NetworkEdge
	 *            The edge to be added to the node.
	 * 
	 */
	public void addEdge(NetworkEdge edge) {

		edges.add(edge);
	}

	/**
	 * Disconnects all the NetworkEdges coming out of this NetworkNode from the
	 * node itself. The edges themselves are still stored in a HashSet 'edges',
	 * in class CapGraph.
	 */
	public void clearEdges() {

		this.edges.clear();
	}

	/**
	 * Get the id numbers of the neighbor nodes.
	 * 
	 * @return A HashSet of integers.
	 */
	public HashSet<Integer> getIdNumbersOfNeighbors() {

		HashSet<Integer> idSet = new HashSet<Integer>();

		for (NetworkEdge edge : this.getEdges()) {

			idSet.add(edge.getToNetworkNode().getIdNumber());
		}
		return idSet;
	}

	/**
	 * Get the neighbor nodes.
	 * 
	 * @return A HashSet of neighbor NetworkNodes.
	 */
	public HashSet<NetworkNode> getNeighbors() {

		HashSet<NetworkNode> networkNodeSet = new HashSet<NetworkNode>();

		for (NetworkEdge edge : this.getEdges()) {
			networkNodeSet.add(edge.getToNetworkNode());
		}

		return networkNodeSet;
	}

	/**
	 * Converts the object in a string format.
	 * 
	 * @return A string of the id number of the node and the id numbers of the
	 *         neighbor nodes.
	 */
	@Override
	public String toString() {

		return this.idNumber + " ==> " + this.getIdNumbersOfNeighbors();
	}
}

package graph;

/**
 * @author Lachezar Kutsarov. A class which represents a directed connection
 *         (edge) between two NetworkNodes in a graph.
 */
public class NetworkEdge {

	// The NetworkNode from which this edge comes out.
	private NetworkNode from;
	// The NetworkNode to which this edge comes in.
	private NetworkNode to;

	// Creates a new NetworkEdge object. Initializes the instance variables.
	public NetworkEdge(NetworkNode from, NetworkNode to) {
		this.from = from;
		this.to = to;

	}

	/**
	 *Get the start NetworkNode of the edge.
	 * 
	 * @return NetworkNode.
	 */
	public NetworkNode getFromNetworkNode() {
		return this.from;
	}

	/**
	 * Get the end NetworkNode of the edge.
	 * 
	 * @return NetworkNode.
	 */
	public NetworkNode getToNetworkNode() {
		return this.to;
	}

	/**
	 * Converts the object in a string format.
	 * 
	 * @return A string of the id numbers of the edge's start and end nodes.
	 */
	@Override
	public String toString() {

		return this.from.getIdNumber() + " ==> " + this.to.getIdNumber();
	}
}

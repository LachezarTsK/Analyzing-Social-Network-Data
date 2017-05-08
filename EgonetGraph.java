package graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Lachezar Kutsarov. Creates a class that represents an egonet graph in
 *         a directed graph.
 */
public class EgonetGraph extends CapGraph {

	// The central node in the egonet graph.
	private NetworkNode centralNode;

	// The id number of the central node
	private int centerIdNumber;

	// A HashMap containing as keys all nodes and as values their neighbors.
	private HashMap<NetworkNode, HashSet<NetworkNode>> egonetNodes;

	/**
	 * A HashMap containing as keys all id numbers of the nodes and as values
	 * the id numbers of their neighbors.
	 */
	private HashMap<Integer, HashSet<Integer>> egonetIds;

	// Creates a new EgonetGraph object. Initializes the instance variables.
	public EgonetGraph(int centerIdNumber) {
		super();
		this.egonetNodes = new HashMap<NetworkNode, HashSet<NetworkNode>>();
		this.egonetIds = new HashMap<Integer, HashSet<Integer>>();
		this.centerIdNumber = centerIdNumber;

	}

	/**
	 * Calculates the egonet graph and populates HashMap 'egonetNodes' and
	 * HashMap 'egonetIds', with the respective nodes/id numbers.
	 * 
	 * @param idsToNodes
	 *            The HashMap of id numbers and their corresponding nodes.
	 */
	public void getEgonetGraph(HashMap<Integer, NetworkNode> idsToNodes) {

		/**
		 * A copy of the HashMap containing as keys the id numbers of all nodes
		 * and as values the nodes themselves.
		 */
		HashMap<Integer, NetworkNode> copyIdsToNodes = new HashMap<Integer, NetworkNode>(idsToNodes);

		// Gets the central NetworkNode.
		this.centralNode = copyIdsToNodes.get(centerIdNumber);

		// A HashSet containing the central node and all of its neighbors.
		HashSet<NetworkNode> nodes = new HashSet<NetworkNode>(this.centralNode.getNeighbors());
		nodes.add(this.centralNode);

		/**
		 * A HashSet containing the id number of the central node and the id
		 * numbers of its neighbors.
		 */
		HashSet<Integer> ids = new HashSet<Integer>(this.centralNode.getIdNumbersOfNeighbors());
		ids.add(this.centralNode.getIdNumber());

		for (NetworkNode nd : nodes) {

			// Stores temporarily the nodes of the egonet.
			HashSet<NetworkNode> egNodes = new HashSet<NetworkNode>();
			// Stores temporarily the id numbers of the egonet nodes.
			HashSet<Integer> egIds = new HashSet<Integer>();

			for (NetworkEdge edge : nd.getEdges()) {

				/**
				 * If the node at the end of the edge is a neighbor node of the
				 * central node, adds nodes/id numbers to the respective
				 * HashSets.
				 */
				if (ids.contains(edge.getToNetworkNode().getIdNumber())) {
					egNodes.add(edge.getToNetworkNode());
					egIds.add(edge.getToNetworkNode().getIdNumber());
				}

			}
			egonetNodes.put(nd, egNodes);
			egonetIds.put(nd.getIdNumber(), egIds);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {

		return egonetIds;
	}

}

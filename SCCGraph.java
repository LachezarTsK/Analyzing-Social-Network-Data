package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Lachezar Kutsarov. Creates a class that represents a directed graph
 *         of Strongly Connected Components (SCCs).
 */

public class SCCGraph extends CapGraph {

	// A list containing the NetworkNodes that form the SCCGraph.
	private ArrayList<NetworkNode> sccNodes;

	// Creates a new SCCGraph object. Initializes the instance variable.
	public SCCGraph(ArrayList<NetworkNode> sccNodes) {
		this.sccNodes = sccNodes;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {

		HashMap<Integer, HashSet<Integer>> exportedGraph = new HashMap<Integer, HashSet<Integer>>();

		for (NetworkNode node : this.sccNodes) {
			exportedGraph.put(node.getIdNumber(), node.getIdNumbersOfNeighbors());
		}
		return exportedGraph;
	}
}

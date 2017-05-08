package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author Lachezar Kutsarov. A class containing the Depth First Search (DFS)
 *         methods applied in method getSCCs(), class CapGraph.
 * 
 */

public class DepthFirstSearch {

	/**
	 * DFS of the original graph.
	 * 
	 * @param nodes.
	 *            A set of all nodes in the graph
	 * 
	 * @return A Stack of all nodes in the graph arranged according their DFS
	 *         search order.
	 */
	public Stack<NetworkNode> dfsOriginalGraph(Collection<NetworkNode> nodes) {

		// A stack of all nodes in the graph.
		Stack<NetworkNode> vertices = new Stack<NetworkNode>();

		/**
		 * Explored nodes, arranged according to the order of exploration by
		 * depth first search
		 */
		Stack<NetworkNode> finished = new Stack<NetworkNode>();
		// A set of visited nodes. Used to prevent multiple visits of a node.
		HashSet<NetworkNode> visited = new HashSet<NetworkNode>();

		vertices.addAll(nodes);

		while (!vertices.isEmpty()) {
			NetworkNode v = vertices.pop();
			if (!visited.contains(v)) {
				visited.add(v);
				dfsVisitOriginalGraph(v, visited, finished);
			}
		}

		return finished;
	}

	/**
	 * A helper method for dfsOriginalGraph(...). Explores the neighbor nodes.
	 * 
	 * @param v
	 *            The root node from which neighbor nodes are to be explored.
	 * 
	 * @param visited
	 *            A set of the visited nodes.
	 * 
	 * @param finished
	 *            A Stack of the nodes that are to be returned by method
	 *            dfsOriginalGraph(...). This stack is applied as a parameter in
	 *            method dfsTransposedGraph(...).
	 */
	public void dfsVisitOriginalGraph(NetworkNode v, HashSet<NetworkNode> visited, Stack<NetworkNode> finished) {

		for (NetworkNode node : v.getNeighbors()) {

			if (!visited.contains(node)) {
				visited.add(node);
				dfsVisitOriginalGraph(node, visited, finished);

			}
		}
		finished.push(v);
	}

	/**
	 * DFS of the transposed graph.
	 * 
	 * @param nodes.
	 *            A stack of all nodes in the transposed graph, arranged
	 *            according the order of their exploration by the depth first
	 *            search method of the original graph. The stack is the return
	 *            statement of method dfsOriginalGraph(...).
	 * 
	 * @return A list of all graphs with strongly connected components.
	 * 
	 */
	public List<Graph> dfsTransposedGraph(Collection<NetworkNode> nodes) {

		// A stack of all nodes in the reversed graph.
		Stack<NetworkNode> vertices = new Stack<NetworkNode>();
		vertices.addAll(nodes);

		// A set of visited nodes. Used to prevent multiple visits of a node.
		HashSet<NetworkNode> visited = new HashSet<NetworkNode>();

		// A list of all SSCGraphs. Applied for the return statement.
		List<Graph> sccAllGraphs = new ArrayList<Graph>();

		// Initializes the ArrayList applied as a parameter for a SCCs object.
		ArrayList<NetworkNode> sccNodes = new ArrayList<NetworkNode>();

		while (!vertices.isEmpty()) {

			NetworkNode v = vertices.pop();

			if (!visited.contains(v)) {

				visited.add(v);

				if (!sccNodes.isEmpty()) {
					// Creates a new SSCGraph with a list of sccNodes.
					SCCGraph sccGraph = new SCCGraph(sccNodes);
					// Adds the SSCGraph to the list of SSCGraphs.
					sccAllGraphs.add(sccGraph);
				}
				sccNodes = new ArrayList<NetworkNode>();
				sccNodes.add(v);

				dfsVisitTransposedGraph(v, visited, sccNodes);
			}
		}
		// Creates a new SSCGraph with the last ArrayList of sccNodes.
		SCCGraph sccGraph = new SCCGraph(sccNodes);
		// Adds the last SSCGraph to the list of SSCGraphs.
		sccAllGraphs.add(sccGraph);

		return sccAllGraphs;
	}

	/**
	 * A helper method for method dfsTransposedGraph(...). Explores the neighbor
	 * nodes and adds nodes to an ArrayList of strongly connected components.
	 * The ArrayList is applied as a parameter for a new SCCs object.
	 * 
	 * @param v
	 *            The root node from which neighbor nodes are to be explored.
	 * 
	 * @param visited
	 *            A set of the visited nodes.
	 * 
	 * @param sccNodes
	 *            A list of nodes contained in a graph of strongly connected
	 *            components.
	 */
	public void dfsVisitTransposedGraph(NetworkNode v, HashSet<NetworkNode> visited, ArrayList<NetworkNode> sccNodes) {

		for (NetworkNode node : v.getNeighbors()) {
			if (!visited.contains(node)) {

				visited.add(node);
				sccNodes.add(node);

				dfsVisitTransposedGraph(node, visited, sccNodes);
			}
		}
	}
}

package CatanGame;

/**
 * Represents a connection between two nodes
 */
public class Edge {

	/** road placed on this edge */
	private Road road = null;
	/** first node connected by edge */
	private Node first;
	/** second node connected by edge */
	private Node second; 

	/**
	 * Constructor for edge class to initialize the two nodes that the edge connects
	 * @param first the first node connected by the edge
	 * @param second the second node connected by the edge
	 */
	public Edge(Node first, Node second) {
		this.first = first;
		this.second = second; 
	}

	/**
	 * Check is if edge is occupied by a road
	 * @return occupied status of edge
	 */
	public boolean edgeOccupied() {
		return road != null; 
	}

	/**
	 * @return first node
	 */
	public Node getFirst() {
		return first;
	}

	/**
	 * @return second node
	 */
	public Node getSecond() {
		return second;
	}

	/**
	 * Sets a road on the edge if it is not already occupied
	 * @param road the road to be placed on the edge
	 */
	public void addRoad(Road road) {
		if (road == null || edgeOccupied()) {
			return;
		}
		this.road = road;
	}

	/**
	 * @return road on this edge
	 */
	public Road getRoad() {
		return road;
	}
}

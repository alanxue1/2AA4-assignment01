package CatanGame;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing a game board in Catan 
 */
public class Board {
	/** array of tiles on the board */
	private Tile[] tiles = new Tile[19];
	/** array of nodes on the board */
	private Node[] nodes = new Node[54];
	/** list of edges on the board */
	private List<Edge> edges = new ArrayList<>(); 

	/**
	 * Initializes the board with default configuration
	 */
	public void initializeMap() {
		edges.clear();
		DefaultBoardSetup.configureBoard(this);
	}

	/**
	 * Method to set a node in the board 
	 * @param id the node's unique identifier
	 * @param node the node to be added to the board
	 */
	public void setNode(int id, Node node) {
		if(id < 0 || id >= nodes.length) {
			return;
		}
		nodes[id] = node; 
	}

	/**
	 * Method to set a tile in the board 
	 * @param id the tile's unique identifier
	 * @param tile the tile to be added to the board
	 */
	public void setTile(int id, Tile tile) {
		if(id < 0 || id >= tiles.length) {
			return;
		}
		tiles[id] = tile; 
	}

	/**
	 * Gets edge connecting two nodes
	 * @param first first node
	 * @param second second node
	 * @return edge between nodes or null
	 */
	public Edge getEdge(Node first, Node second) {
		for (Edge edge: edges) {
			if ((edge.getFirst() == first && edge.getSecond() == second) || (edge.getFirst() == second && edge.getSecond() == first)) {
				return edge; 
			}
		}
		return null; 
	}

	/**
	 * Method to get a tile by its unique identifier
	 * @param id the tile's unique identifier
	 * @return the tile with the specified id
	 */
	public Tile getTileById(int id) {
		if(id < 0 || id >= tiles.length) {
			return null;
		}
		return tiles[id];
	}

	/**
	 * Adds edge to the board
	 * @param edge edge to add
	 */
	public void addEdge(Edge edge) {
		if (edge == null || edges.size() >= 72) {
			return;
		}
		edges.add(edge);
	}

	/**
	 * Method to get all tiles with a specific number token
	 * @param token the number token to search for
	 * @return a list of tiles that have the specified number token
	 */
	public List<Tile> getTilesByToken(int token) {
		List<Tile> sameTokenNumberTiles = new ArrayList<>();
		for (Tile tile: tiles) {
			if (tile == null) {
				continue;
			}
			if (token==tile.getNumberToken()) {
				sameTokenNumberTiles.add(tile);
			}
		}
		
		return sameTokenNumberTiles;
	}

	/**
	 * Method to get a node by its unique identifier
	 * @param id the node's unique identifier
	 * @return the node with the specified id
	 */
	public Node getNodeById(int id) {
		if(id < 0 || id >= nodes.length) {
			return null;
		}
		return nodes[id];
	}

	/**
	 * @return copy of nodes array
	 */
	public Node[] getNodes() {
		return nodes.clone();
	}

	/**
	 * @return copy of tiles array
	 */
	public Tile[] getTiles() {
		return tiles.clone();
	}

	/**
	 * @return copy of edges list
	 */
	public List<Edge> getEdges() {
		return new ArrayList<>(edges);
	}
}
package CatanGame;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing a game board in Catan 
 */
public class Board {
	private Tile[] tiles = new Tile[19];
	private Node[] nodes = new Node[54];
	private List<Edge> edges = new ArrayList<>(); 

	public void initializeMap() {
	}

	/**
	 * Method to set a node in the board 
	 * @param id the node's unique identifier
	 * @param node the node to be added to the board
	 */
	public void setNode(int id, Node node) {
		if(id < 0 || id >= nodes.length) {
			throw new IllegalArgumentException("Invalid node id");
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
			throw new IllegalArgumentException("Invalid tile id");
		}
		tiles[id] = tile; 
	}

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
			throw new IllegalArgumentException("Invalid tile id");
		}
		return tiles[id];
	}

	public void addEdge(Edge edge) {
		if(edges.size() >= 72) {
			throw new IllegalStateException("Board cannot have more than 72 edges");
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
			throw new IllegalArgumentException("Invalid node id");
		}
		return nodes[id];
	}
}
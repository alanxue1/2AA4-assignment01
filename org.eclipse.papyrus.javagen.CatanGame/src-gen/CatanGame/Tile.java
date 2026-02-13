package CatanGame;

public class Tile {

    private int id;
    private ResourceType resourceType;
    private int numberToken;
    private Node[] adjacentNodes;

    /**
     * Constructor for a tile
     * @param id unique identifier
     * @param resourceType type of resource
     * @param numberToken activation number
     */
    public Tile(int id, ResourceType resourceType, int numberToken) {
        this.id = id;
        this.resourceType = resourceType;
        this.numberToken = numberToken;
        this.adjacentNodes = new Node[6];
    }

    /**
     * @return tile's id
     */
    public int getId() {
        return id;
    }

    /**
     * @return resource type of the tile
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * @return number token on the tile
     */
    public int getNumberToken() {
        return numberToken;
    }

    /**
     * @return copy of adjacent nodes array
     */
    public Node[] getAdjacentNodes() {
        return adjacentNodes.clone();
    }

    /**
     * Adds a node to the adjacency list if not already present
     * @param node node to add
     */
	public void addAdjacentNode(Node node) {
		if (node == null) {
			return;
		}
		for(int i = 0; i < adjacentNodes.length; i++) {
			if (adjacentNodes[i] == node) {
				return;
			}
			if(adjacentNodes[i] == null) {
				adjacentNodes[i] = node; 
				return;
			}
		}
	}
}

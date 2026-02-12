package CatanGame;

public class Tile {

    private int id;
    private ResourceType resourceType;
    private int numberToken;
    private Node[] adjacentNodes;

    public Tile(int id, ResourceType resourceType, int numberToken) {
        this.id = id;
        this.resourceType = resourceType;
        this.numberToken = numberToken;
        this.adjacentNodes = new Node[6];
    }

    public int getId() {
        return id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getNumberToken() {
        return numberToken;
    }

    public Node[] getAdjacentNodes() {
        return adjacentNodes.clone();
    }

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

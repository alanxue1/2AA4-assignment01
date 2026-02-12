package CatanGame;

/**
 * Hex tile containing resource metadata and corner vertices.
 */
public class Tile {
    // Unique index within the board
    public int tileIdx;
    // Optional: players bordering this tile (for game logic)
    public Player[] borderingPlayers = new Player[1];
    // Resource produced when dice match numberToken
    public ResourceType resourceKind;
    // Dice number that triggers resource production (2-12, 0 for desert)
    public int numberToken;
    // Six corner vertices in clockwise order
    public Node[] corners = new Node[6];

    public Tile(int tileIdx, ResourceType resourceKind, int numberToken) {
        this.tileIdx = tileIdx;
        this.resourceKind = resourceKind;
        this.numberToken = numberToken;
    }

    public int getId() {
        return tileIdx;
    }

    public ResourceType getResourceType() {
        return resourceKind;
    }

    public int getNumberToken() {
        return numberToken;
    }

    /**
     * Returns the six corner vertices (mutable reference).
     */
    public Node[] getAdjacentNodes() {
        return corners;
    }

    /**
     * Adds a corner vertex to this tile.
     */
    public void addAdjacentNode(Node n) {
        for (int i = 0; i < corners.length; i++) {
            if (corners[i] == null) {
                corners[i] = n;
                return;
            }
        }
    }
}

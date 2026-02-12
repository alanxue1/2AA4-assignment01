package CatanGame;

/**
 * Road model owned by a player and placed on an edge.
 */
public class Road {
    // Unique index among all roads in the game
    public int roadIdx;
    // Player who owns this road
    public Player owningPlayer;
    // Edge where the road is placed
    public Edge placement;

    public Road(int roadIdx, Player owningPlayer, Edge placement) {
        this.roadIdx = roadIdx;
        this.owningPlayer = owningPlayer;
        this.placement = placement;
        // Register at the edge
        placement.addRoad(this);
    }

    public Edge getRoadLocation() {
        return placement;
    }
}

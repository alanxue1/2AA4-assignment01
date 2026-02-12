package CatanGame;

/**
 * Base building model linked to an owning player and a board vertex.
 */
public class Building {
    // Player who owns this structure
    public Player owningPlayer;
    // Vertex where the building is placed
    public Node placement;

    public Building(Player owningPlayer, Node placement) {
        this.owningPlayer = owningPlayer;
        this.placement = placement;
        // Register this building at the placement vertex
        placement.addBuilding(this);
    }

    /**
     * Returns victory points granted by this building (overridden in subclasses).
     */
    public int getVictoryPoints() {
        return 0;
    }

    public Node getLocation() {
        return placement;
    }

    public Player getOwner() {
        return owningPlayer;
    }
}

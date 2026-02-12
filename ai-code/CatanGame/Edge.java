package CatanGame;

/**
 * Edge connecting two vertices and optionally holding a road.
 */
public class Edge {
    // Unique index for this edge
    public int edgeIdx;
    // Road placed on this edge (null if empty)
    public Road[] placedRoad = new Road[1];
    // Endpoint vertices
    public Node endpointA;
    public Node endpointB;

    public Edge(int edgeIdx, Node endpointA, Node endpointB) {
        this.edgeIdx = edgeIdx;
        this.endpointA = endpointA;
        this.endpointB = endpointB;
    }

    /**
     * Returns true if a road occupies this edge.
     */
    public boolean edgeOccupied() {
        return placedRoad[0] != null;
    }

    public Node getFirst() {
        return endpointA;
    }

    public Node getSecond() {
        return endpointB;
    }

    /**
     * Places a road on this edge. No occupancy check.
     */
    public void addRoad(Road r) {
        placedRoad[0] = r;
    }
}

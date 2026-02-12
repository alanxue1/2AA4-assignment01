package CatanGame;

import java.util.Arrays;

/**
 * Board intersection vertex with optional placed structure and neighbor vertices.
 */
public class Node {
    // Unique index within the board's vertex array
    public int nodeIdx;
    // Single structure slot (settlement or city)
    public Building[] structures = new Building[1];
    // Neighbor vertices connected by edges
    public Node[] neighbors = new Node[3];

    public Node(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }

    public int getId() {
        return nodeIdx;
    }

    /**
     * Returns true if a structure occupies this vertex.
     */
    public boolean nodeOccupied() {
        return structures[0] != null;
    }

    public Building getBuilding() {
        return structures[0];
    }

    /**
     * Returns the array of neighbor vertices (mutable reference).
     */
    public Node[] getAdjacentNodes() {
        return neighbors;
    }

    public void addBuilding(Building b) {
        structures[0] = b;
    }

    /**
     * Adds a neighbor vertex to the adjacency list.
     */
    public void addAdjacentNode(Node n) {
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == null) {
                neighbors[i] = n;
                return;
            }
        }
        // Overflow: silently ignore when slot array is full
    }

    /**
     * Debug helper: string representation of neighbors.
     */
    public String debugAdjacent() {
        return Arrays.toString(neighbors);
    }
}

package CatanGame;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Board aggregate containing hex tiles, vertices, and edges.
 */
public class Board {
    // 19 hex tiles in standard Catan layout
    public Tile[] hexTiles = new Tile[19];
    // 54 vertices (intersections)
    public Node[] vertices = new Node[54];
    // Edges between vertices (max 72 in standard layout)
    public Edge[] links = new Edge[200];
    public int linkCount = 0;

    /**
     * Creates the initial vertex set. Tiles and edges are set up separately.
     */
    public void initializeMap() {
        for (int i = 0; i < 54; i++) {
            vertices[i] = new Node(i);
        }
    }

    public void setNode(int idx, Node v) {
        vertices[idx] = v;
    }

    public void setTile(int idx, Tile t) {
        hexTiles[idx] = t;
    }

    public Tile getTileById(int idx) {
        return hexTiles[idx];
    }

    public Node getNodeById(int idx) {
        return vertices[idx];
    }

    public void addEdge(Edge e) {
        links[linkCount] = e;
        linkCount++;
    }

    /**
     * Finds the edge between two given vertices.
     */
    public Edge getEdge(Node endpointA, Node endpointB) {
        for (int i = 0; i < linkCount; i++) {
            Edge e = links[i];
            // Match in either direction
            if ((e.endpointA == endpointA && e.endpointB == endpointB) ||
                (e.endpointA == endpointB && e.endpointB == endpointA)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns all hex tiles whose number token matches the given value.
     */
    public List<Tile> getTilesByToken(int tokenValue) {
        List<Tile> matching = new ArrayList<>();
        for (int i = 0; i < hexTiles.length; i++) {
            if (hexTiles[i] != null && hexTiles[i].numberToken == tokenValue) {
                matching.add(hexTiles[i]);
            }
        }
        return matching;
    }

    /**
     * Builds an index from tile index to tile for fast lookup.
     */
    public HashMap<Integer, Tile> createTileIndex() {
        HashMap<Integer, Tile> idxToTile = new HashMap<>();
        for (int i = 0; i < hexTiles.length; i++) {
            idxToTile.put(i, hexTiles[i]);
        }
        return idxToTile;
    }
}

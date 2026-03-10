package CatanGame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Exports the game state to a JSON file for the visualizer.
 */
public class GameStateExporter {

	/**
	 * Exports current game state to a JSON file
	 * @param game the game to export
	 * @param filePath path to write JSON file
	 */
	public static void export(Game game, String filePath) {
    Board board = game.getBoard();
    if (board == null) {
        return;
    }

    // Map player IDs to color strings for the visualizer
    String[] colors = {"RED", "BLUE", "ORANGE", "WHITE"};

    // === Write base_map.json ===
    // Cube coordinates for each tile ID (matching DefaultBoardSetup order)
    int[][] cubeCoords = {
        {0, 0, 0},      // tile 0
        {0, -1, 1},     // tile 1
        {-1, 0, 1},     // tile 2
        {-1, 1, 0},     // tile 3
        {0, 1, -1},     // tile 4
        {1, 0, -1},     // tile 5
        {1, -1, 0},     // tile 6
        {0, -2, 2},     // tile 7
        {-1, -1, 2},    // tile 8
        {-2, 0, 2},     // tile 9
        {-2, 1, 1},     // tile 10
        {-2, 2, 0},     // tile 11
        {-1, 2, -1},    // tile 12
        {0, 2, -2},     // tile 13
        {1, 1, -2},     // tile 14
        {2, 0, -2},     // tile 15
        {2, -1, -1},    // tile 16
        {2, -2, 0},     // tile 17
        {1, -2, 1}      // tile 18
    };

    StringBuilder baseMap = new StringBuilder();
    baseMap.append("{\n  \"tiles\": [\n");
    Tile[] tiles = board.getTiles();
    boolean firstTile = true;
    for (Tile tile : tiles) {
        if (tile == null) continue;
        if (!firstTile) baseMap.append(",\n");
        firstTile = false;
        int id = tile.getId();
        int[] coords = (id >= 0 && id < cubeCoords.length) ? cubeCoords[id] : new int[]{0, 0, 0};
        String resource = (tile.getResourceType() == ResourceType.DESERT) ? "null" : "\"" + tile.getResourceType() + "\"";
        String number = (tile.getResourceType() == ResourceType.DESERT) ? "null" : String.valueOf(tile.getNumberToken());
        baseMap.append("    {\"q\": ").append(coords[0]);
        baseMap.append(", \"s\": ").append(coords[1]);
        baseMap.append(", \"r\": ").append(coords[2]);
        baseMap.append(", \"resource\": ").append(resource);
        baseMap.append(", \"number\": ").append(number).append("}");
    }
    baseMap.append("\n  ]\n}\n");

    // Write base_map.json next to the state file
    String baseMapPath = filePath.replace("state.json", "base_map.json")
                                 .replace("game_state.json", "base_map.json");
    try (java.io.FileWriter bw = new java.io.FileWriter(baseMapPath)) {
        bw.write(baseMap.toString());
    } catch (java.io.IOException e) {
        System.err.println("Failed to export base map: " + e.getMessage());
    }

    // === Write state.json (roads + buildings) ===
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");

    // Roads
    sb.append("  \"roads\": [\n");
    java.util.List<Edge> edges = board.getEdges();
    boolean firstRoad = true;
    for (Edge edge : edges) {
        if (edge == null || edge.getRoad() == null) continue;
        if (!firstRoad) sb.append(",\n");
        firstRoad = false;
        int ownerId = edge.getRoad().getOwner().getId();
        String color = (ownerId >= 1 && ownerId <= 4) ? colors[ownerId - 1] : "RED";
        sb.append("    {\"a\": ").append(edge.getFirst().getId());
        sb.append(", \"b\": ").append(edge.getSecond().getId());
        sb.append(", \"owner\": \"").append(color).append("\"}");
    }
    sb.append("\n  ],\n");

    // Buildings
    sb.append("  \"buildings\": [\n");
    Node[] nodes = board.getNodes();
    boolean firstBuilding = true;
    for (Node node : nodes) {
        if (node == null || node.getBuilding() == null) continue;
        if (!firstBuilding) sb.append(",\n");
        firstBuilding = false;
        Building building = node.getBuilding();
        String type = (building instanceof City) ? "CITY" : "SETTLEMENT";
        int ownerId = building.getOwner().getId();
        String color = (ownerId >= 1 && ownerId <= 4) ? colors[ownerId - 1] : "RED";
        sb.append("    {\"node\": ").append(node.getId());
        sb.append(", \"owner\": \"").append(color).append("\"");
        sb.append(", \"type\": \"").append(type).append("\"}");
    }
    sb.append("\n  ]\n");

    sb.append("}\n");

    try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
        writer.write(sb.toString());
    } catch (java.io.IOException e) {
        System.err.println("Failed to export game state: " + e.getMessage());
    }
	}
}

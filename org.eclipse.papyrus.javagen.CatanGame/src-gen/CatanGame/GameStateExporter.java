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

		StringBuilder sb = new StringBuilder();
		sb.append("{\n");

		// Tiles
		sb.append("  \"tiles\": [\n");
		Tile[] tiles = board.getTiles();
		boolean firstTile = true;
		for (Tile tile : tiles) {
			if (tile == null) {
				continue;
			}
			if (!firstTile) {
				sb.append(",\n");
			}
			firstTile = false;
			sb.append("    {\"id\": ").append(tile.getId());
			sb.append(", \"resource\": \"").append(tile.getResourceType()).append("\"");
			sb.append(", \"token\": ").append(tile.getNumberToken()).append("}");
		}
		sb.append("\n  ],\n");

		// Nodes with buildings
		sb.append("  \"nodes\": [\n");
		Node[] nodes = board.getNodes();
		boolean firstNode = true;
		for (Node node : nodes) {
			if (node == null) {
				continue;
			}
			if (!firstNode) {
				sb.append(",\n");
			}
			firstNode = false;
			sb.append("    {\"id\": ").append(node.getId());
			Building building = node.getBuilding();
			if (building != null) {
				String type = (building instanceof City) ? "city" : "settlement";
				sb.append(", \"building\": \"").append(type).append("\"");
				sb.append(", \"owner\": ").append(building.getOwner().getId());
			}
			sb.append("}");
		}
		sb.append("\n  ],\n");

		// Edges with roads
		sb.append("  \"edges\": [\n");
		List<Edge> edges = board.getEdges();
		boolean firstEdge = true;
		for (Edge edge : edges) {
			if (edge == null) {
				continue;
			}
			if (!firstEdge) {
				sb.append(",\n");
			}
			firstEdge = false;
			sb.append("    {\"from\": ").append(edge.getFirst().getId());
			sb.append(", \"to\": ").append(edge.getSecond().getId());
			if (edge.getRoad() != null) {
				sb.append(", \"road_owner\": ").append(edge.getRoad().getOwner().getId());
			}
			sb.append("}");
		}
		sb.append("\n  ],\n");

		// Players
		sb.append("  \"players\": [\n");
		Player[] players = game.getPlayers();
		boolean firstPlayer = true;
		for (Player player : players) {
			if (player == null) {
				continue;
			}
			if (!firstPlayer) {
				sb.append(",\n");
			}
			firstPlayer = false;
			ResourceHand hand = player.getResourceHand();
			sb.append("    {\"id\": ").append(player.getId());
			sb.append(", \"vp\": ").append(player.getVictoryPoints());
			sb.append(", \"resources\": {");
			sb.append("\"WOOD\": ").append(hand.getCount(ResourceType.WOOD));
			sb.append(", \"BRICK\": ").append(hand.getCount(ResourceType.BRICK));
			sb.append(", \"SHEEP\": ").append(hand.getCount(ResourceType.SHEEP));
			sb.append(", \"WHEAT\": ").append(hand.getCount(ResourceType.WHEAT));
			sb.append(", \"ORE\": ").append(hand.getCount(ResourceType.ORE));
			sb.append("}}");
		}
		sb.append("\n  ],\n");

		// Robber
		Robber robber = board.getRobber();
		if (robber != null && robber.getCurrentTile() != null) {
			sb.append("  \"robber\": ").append(robber.getCurrentTile().getId()).append(",\n");
		}

		// Round
		sb.append("  \"round\": ").append(game.getCurrentRound()).append("\n");
		sb.append("}\n");

		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(sb.toString());
		} catch (IOException e) {
			System.err.println("Failed to export game state: " + e.getMessage());
		}
	}
}

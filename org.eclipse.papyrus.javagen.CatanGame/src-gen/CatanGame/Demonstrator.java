package CatanGame;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Demonstrator entry point for Assignment 2.
 *
 * Player 1 is a human player controlled via console commands.
 * Players 2-4 are AI agents.
 * Game state is exported to JSON for visualizer integration.
 */
public class Demonstrator {
	/**
	 * Main method to start the game
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		String configPath = resolveConfigPath(args); // path to config file
		Config config = new Config(configPath); // loaded configuration

		Board board = new Board(); // game board
		DefaultBoardSetup.configureBoard(board);

		Scanner scanner = new Scanner(System.in); // shared scanner for human input

		Player[] players = new Player[4]; // array of all players
		players[0] = new Player(1, new HumanAgent(scanner)); // human player
		for (int i = 1; i < players.length; i++) {
			players[i] = new Player(i + 1, new Agent(i + 1, new ConstraintBasedStrat(new ValueBasedStrat()))); //AI players
		}

		Dice dice = new MultiDice(); // dice for rolling

		Game game = new Game(board, players, dice, config.getMaxRounds()); // game instance
		game.setScanner(scanner);
		game.setGameStatePath("state.json");
		game.start();
	}

	/**
	 * Resolves path to config file from arguments or defaults
	 * @param args command line args
	 * @return path to config file
	 */
	private static String resolveConfigPath(String[] args) {
		if (args != null && args.length > 0 && args[0] != null && !args[0].isBlank()) {
			return args[0];
		}

		Path localConfig = Path.of("config.txt"); // local config file
		if (Files.exists(localConfig)) {
			return localConfig.toString();
		}

		Path repoRootConfig = Path.of("org.eclipse.papyrus.javagen.CatanGame", "config.txt"); // repo config
		return repoRootConfig.toString();
	}
}

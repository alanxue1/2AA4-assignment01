package CatanGame;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrator entry point for Assignment 1.
 *
 * This class wires all simulator parts together, then runs one full game.
 * You can pass a custom config file path as the first command-line argument.
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

		Player[] players = new Player[4]; // array of all players
		for (int i = 0; i < players.length; i++) {
			Agent agent = new Agent(i + 1); // agent for this player
			players[i] = new Player(i + 1, agent);
		}

		Dice dice = new MultiDice(); // dice for rolling

		Game game = new Game(board, players, dice, config.getMaxRounds()); // game instance
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

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
	public static void main(String[] args) {
		// 1) Read "turns: <int>" from config to decide max round limit.
		String configPath = resolveConfigPath(args);
		Config config = new Config(configPath);

		// 2) Build the fixed default map required for repeatable debugging/testing.
		Board board = new Board();
		DefaultBoardSetup.configureBoard(board);

		// 3) Create four random agents and bind one to each player.
		Player[] players = new Player[4];
		for (int i = 0; i < players.length; i++) {
			Agent agent = new Agent(i + 1);
			players[i] = new Player(i + 1, agent);
		}

		// 4) Use two regular dice to model standard Catan rolls.
		Dice dice = new MultiDice();

		// 5) Start the simulation and print all turn logs/round summaries to console.
		Game game = new Game(board, players, dice, config.getMaxRounds());
		game.start();
	}

	private static String resolveConfigPath(String[] args) {
		if (args != null && args.length > 0 && args[0] != null && !args[0].isBlank()) {
			return args[0];
		}

		Path localConfig = Path.of("config.txt");
		if (Files.exists(localConfig)) {
			return localConfig.toString();
		}

		Path repoRootConfig = Path.of("org.eclipse.papyrus.javagen.CatanGame", "config.txt");
		return repoRootConfig.toString();
	}
}

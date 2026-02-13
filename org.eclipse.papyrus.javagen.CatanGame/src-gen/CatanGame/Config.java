package CatanGame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads simulator configuration from a text file.
 * Expected format:
 * turns: <int>
 */
public class Config {
	/** default number of turns if not specified */
	private static final int DEFAULT_TURNS = 50;
	/** maximum rounds for the game */
	private int maxRounds;

	/**
	 * Constructor that reads config from file
	 * @param filePath path to config file
	 */
	public Config(String filePath) {
		maxRounds = DEFAULT_TURNS;
		if (filePath == null || filePath.isBlank()) {
			return;
		}
		this.maxRounds = parseTurns(Path.of(filePath));
	}

	/**
	 * @return max rounds configured
	 */
	public int getMaxRounds() {
		return maxRounds;
	}

	/**
	 * Parses turns value from config file
	 * @param path path to file
	 * @return number of turns
	 */
	private int parseTurns(Path path) {
		List<String> lines; // lines from config file
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			return DEFAULT_TURNS;
		}

		for (String rawLine : lines) {
			String line = rawLine.trim(); // trimmed line
			if (line.isEmpty()) {
				continue;
			}
			if (!line.startsWith("turns:")) {
				continue;
			}

			String valuePart = line.substring("turns:".length()).trim(); // value after "turns:"
			try {
				int turns = Integer.parseInt(valuePart); // parsed turn count
				if (turns >= 1 && turns <= 8192) {
					return turns;
				}
			} catch (NumberFormatException e) {
				return DEFAULT_TURNS;
			}
		}

		return DEFAULT_TURNS;
	}
}

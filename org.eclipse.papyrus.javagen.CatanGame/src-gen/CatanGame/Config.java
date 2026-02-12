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
	private static final int DEFAULT_TURNS = 50;
	private int maxRounds;

	public Config(String filePath) {
		maxRounds = DEFAULT_TURNS;
		if (filePath == null || filePath.isBlank()) {
			return;
		}
		this.maxRounds = parseTurns(Path.of(filePath));
	}

	public int getMaxRounds() {
		return maxRounds;
	}

	private int parseTurns(Path path) {
		List<String> lines;
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			return DEFAULT_TURNS;
		}

		for (String rawLine : lines) {
			String line = rawLine.trim();
			if (line.isEmpty()) {
				continue;
			}
			if (!line.startsWith("turns:")) {
				continue;
			}

			String valuePart = line.substring("turns:".length()).trim();
			try {
				int turns = Integer.parseInt(valuePart);
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

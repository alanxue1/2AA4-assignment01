package CatanGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration holder for game-level options.
 */
public class Config {
    // Optional round limits per scenario (e.g. "default" -> 20)
    public Map<String, Integer> roundLimitsByScenario;
    // Optional references to game instances
    public Game[] gameRefs;

    public Config() {
        roundLimitsByScenario = new HashMap<>();
        gameRefs = new Game[1];
    }

    /**
     * Loads default or scenario-specific configuration values.
     */
    public void load() {
        roundLimitsByScenario.put("default", 20);
    }
}

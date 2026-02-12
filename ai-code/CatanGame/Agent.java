package CatanGame;

/**
 * Decision component that chooses a player's next action.
 */
public class Agent {
    // Unique identifier for this agent instance
    public int agentId;
    // Optional: references to controlled players (for multi-agent setups)
    public Player[] controlledPlayers = new Player[4];

    /**
     * Selects which action to perform this turn.
     * @param actor the player whose turn it is
     * @param gameInstance the active game
     * @return the chosen action to execute
     */
    public Action chooseAction(Player actor, Game gameInstance) {
        if (actor == null) {
            return new Pass();
        }
        if (gameInstance == null) {
            return new Pass();
        }
        // Naive heuristic: try to build city when score is high enough
        if (actor.score > 8 && gameInstance.gameBoard != null) {
            return new BuildCity(gameInstance.gameBoard.getNodeById(0));
        }
        return new Pass();
    }
}

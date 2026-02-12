package CatanGame;

/**
 * Base action type executed during a player's turn.
 * Subclasses implement concrete behaviors (build, pass, etc.).
 */
public class Action {
    // Human-readable description for logging or UI display
    public String actionDesc = "Some action";

    /**
     * Performs the action in the context of the given game and player.
     * @param gameInstance the running game
     * @param actor the player performing the action
     */
    public void execute(Game gameInstance, Player actor) {
        return;
    }
}

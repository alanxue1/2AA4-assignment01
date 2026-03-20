package CatanGame;
import java.util.List;

/**
 * Interface for choosing an action 
 */
public interface ActionSelectionStrat {
    /**
     * Picks an action from list of actions.
     * @param actions allowed actions to choose from
     * @param player the player who chooses the action
     * @param game state of game
     * @return the action that was chosenn
     */
    Action selectAction(List<Action> actions,Player player, Game game);
}
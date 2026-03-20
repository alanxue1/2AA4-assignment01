package CatanGame;
import java.util.*; 

/**
 * Class representing a handler in the chain of responsibility for ensuring  game constraints are met
 */
public abstract class ConstraintHandler {

    /** successor in the chain */
    private ConstraintHandler next;

    /**
     * Sets the next handler in the chain
     * @param next next handler
     */
    public void setNext(ConstraintHandler next) {
        this.next = next;
    }

    /**
     * Handles the current request by checking current handler, delegating to the next handler if current handler does not apply, or returning null if no handler applies
     * @param player current player
     * @param game The current game instance 
     * @param legalActionsList list of legal actions available to the player this turn
     * @return action selected by the handler, or null if no handler applies
     */
    public Action handle(Player player, Game game, List<Action> legalActionsList) {
        Action action = check(player, game, legalActionsList); // check if current handler applies
        if (action != null) {
            return action;
        }
        if (next != null) {
            return next.handle(player, game, legalActionsList);
        }
        return null;
    }

    /**
     * Handler-specific constraint check.
     * @param player current player
     * @param game current game state
     * @param legalActionsList list of legal actions available this turn
     * @return selected action if this handler applies; otherwise null
     */
    protected abstract Action check(Player player, Game game, List<Action> legalActionsList);
}

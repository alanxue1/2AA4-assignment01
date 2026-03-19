package CatanGame;

import java.util.List;
import java.util.Random;

/**
 *  the strategy which selects random action from list
 * this ensures the original decision making behavior of the Agent is kept
 */
public class RandomStratAction implements ActionSelectionStrat {
    private final Random random = new Random();
    /**
     * Selects a random action from list.
     * @param actions allowed actions to choose from
     * @param player the player who chooses the action
     * @param game state of game
     * @return randomly chosen action
     */
    @Override
    public Action selectAction(List<Action> actions, Player player,Game game) {
        if (actions == null || actions.isEmpty()) {
            return new Pass();
        }
        return actions.get(random.nextInt(actions.size()));
    }
}
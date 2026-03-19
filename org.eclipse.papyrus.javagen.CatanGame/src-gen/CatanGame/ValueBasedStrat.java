package CatanGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * strategy that evaluates benefit of action
 * and chooses the one with the highest immediate value.
 */
public class ValueBasedStrat implements ActionSelectionStrat {
    private final Random random = new Random();
    /**
     * picks the action with highest value score
     * If tie, chooses randomly from the tied actions
     * @param actions allowed actions to choose from
     * @param player the player who chooses the action
     * @param game state of game
     * @return the highest-value action
     */
    @Override
    public Action selectAction(List<Action> actions,Player player,Game game) {
        if (actions==null || actions.isEmpty()) {
            return new Pass();
        }
        double bestScore=-1;
        List<Action> bestActions= new ArrayList<>();
        for (Action action : actions) {
            double score =evaluate(action, player);
            if (score > bestScore) {
                bestScore = score;
                bestActions.clear();
                bestActions.add(action);
            } else if (score == bestScore) {
                bestActions.add(action);
            }
        }
        return bestActions.get(random.nextInt(bestActions.size()));
    }

    /**
     * Evaluates immediate value of an action
     * Earning a VP: 1.0
     * building without VP: 0.8
     * Spending cards and less than 5 remain: 0.5
     * pass or no benefit: 0.0
     * @param action the action being evaluated
     * @param player the player responsiible to doing the action
     * @return the value score
     */
    private double evaluate(Action action, Player player) {
        if (action instanceof BuildSettlement||action instanceof BuildCity) {
            return 1.0;
        }
        if (action instanceof BuildRoad) {
            return 0.8;
        }
        if (action instanceof Pass) {
            int totalCards = player.getResourceHand().totalCards();
            if (totalCards< 5) {
                return 0.5;
            }
            return 0.0;
        }
        return 0.0;
    }
}
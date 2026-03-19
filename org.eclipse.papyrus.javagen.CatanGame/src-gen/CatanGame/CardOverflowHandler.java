package CatanGame;

import java.util.List;

/**
 * Constraint: if the agent holds more than 7 resource cards, it must spend
 * them by building something. Prefers city > settlement > road to maximise
 * the number of cards spent in a single action.
 */
public class CardOverflowHandler extends ConstraintHandler {

    private static final int CARD_THRESHOLD = 7;

    @Override
    protected Action check(Player player, Game game, List<Action> legalActions) {
        if (player.getResourceHand().totalCards() <= CARD_THRESHOLD) {
            return null;
        }

        Action best = null;
        int bestCost = -1;
        for (Action action : legalActions) {
            int cost = actionCost(action);
            if (cost > bestCost) {
                bestCost = cost;
                best = action;
            }
        }

        if (best instanceof Pass || bestCost <= 0) {
            return null;
        }
        return best;
    }

    private int actionCost(Action action) {
        if (action instanceof BuildCity) {
            return totalCost(BuildCosts.CITY);
        }
        if (action instanceof BuildSettlement) {
            return totalCost(BuildCosts.SETTLEMENT);
        }
        if (action instanceof BuildRoad) {
            return totalCost(BuildCosts.ROAD);
        }
        return 0;
    }

    private int totalCost(java.util.Map<ResourceType, Integer> cost) {
        int total = 0;
        for (int v : cost.values()) {
            total += v;
        }
        return total;
    }
}

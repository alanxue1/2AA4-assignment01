package CatanGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Constraint: if any opponent has a longest road that is at most one road
 * shorter than the agent's, the agent should extend its road network.
 */
public class LongestRoadThreatHandler extends ConstraintHandler {

    @Override
    protected Action check(Player player, Game game, List<Action> legalActions) {
        if (!player.getResourceHand().canAfford(BuildCosts.ROAD)) {
            return null;
        }

        Board board = game.getBoard();
        int myLongest = RoadNetworkUtil.getLongestRoadCount(player, board);

        boolean threatened = false;
        for (Player opponent : game.getPlayers()) {
            if (opponent == null || opponent == player) {
                continue;
            }
            int opponentLongest = RoadNetworkUtil.getLongestRoadCount(opponent, board);
            if (opponentLongest >= myLongest - 1) {
                threatened = true;
                break;
            }
        }

        if (!threatened) {
            return null;
        }

        List<Action> roadActions = new ArrayList<>();
        for (Action action : legalActions) {
            if (action instanceof BuildRoad) {
                roadActions.add(action);
            }
        }

        if (roadActions.isEmpty()) {
            return null;
        }

        return roadActions.get(0);
    }
}

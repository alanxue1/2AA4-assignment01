package CatanGame;

import java.util.List;
import java.util.Set;

/**
 * Constraint: if the player has two disconnected road segments whose
 * endpoints are at most two edges apart, build a roa
 */
public class RoadGapHandler extends ConstraintHandler {

    @Override
    protected Action check(Player player, Game game, List<Action> legalActions) {
        if (!player.getResourceHand().canAfford(BuildCosts.ROAD)) {
            return null;
        }

        List<Set<Edge>> components = RoadNetworkUtil.getRoadGroups(player, game.getBoard());
        if (components.size() < 2) {
            return null;
        }

        Edge bridge = RoadNetworkUtil.getEdgeToConnectGroups(components, game.getBoard());
        if (bridge == null) {
            return null;
        }

        for (Action action : legalActions) {
            if (action instanceof BuildRoad) {
                BuildRoad br = (BuildRoad) action;
                if (br.getEdge() == bridge) {
                    return br;
                }
            }
        }
        return null;
    }
}

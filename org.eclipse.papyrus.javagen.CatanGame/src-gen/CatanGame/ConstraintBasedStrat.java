package CatanGame;

import java.util.List;

/**
 * Action selection strategy that checks R3.3 constraints via a Chain of
 * Responsibility before falling back to a delegate strategy (e.g. ValueBasedStrat).
 *
 * Chain order:
 * 1. CardOverflowHandler  -- must spend if hand > 7
 * 2. RoadGapHandler        -- connect nearby disconnected segments
 * 3. LongestRoadThreatHandler -- defend longest-road lead
 */
public class ConstraintBasedStrat implements ActionSelectionStrat {
    private final ConstraintHandler chain;
    private final ActionSelectionStrat fallback;

    /**
     * @param fallback strategy to use when no constraint fires
     */
    public ConstraintBasedStrat(ActionSelectionStrat fallback) {
        this.fallback = fallback;

        CardOverflowHandler overflow = new CardOverflowHandler();
        RoadGapHandler gap = new RoadGapHandler();
        LongestRoadThreatHandler threat = new LongestRoadThreatHandler();

        overflow.setNext(gap);
        gap.setNext(threat);

        this.chain = overflow;
    }

    @Override
    public Action selectAction(List<Action> actions, Player player, Game game) {
        Action forced = chain.handle(player, game, actions);
        if (forced != null) {
            return forced;
        }
        return fallback.selectAction(actions, player, game);
    }
}

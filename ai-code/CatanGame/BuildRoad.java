package CatanGame;

/**
 * Action that builds a road at the target edge.
 */
public class BuildRoad extends Action {
    // Edge where the road will be built
    public Edge targetEdge;

    public BuildRoad(Edge targetEdge) {
        this.targetEdge = targetEdge;
        this.actionDesc = "Build a road";
    }

    @Override
    public void execute(Game gameInstance, Player actor) {
        int nextIdx = gameInstance.globalRoads.size();
        Road r = new Road(nextIdx, actor, targetEdge);
        gameInstance.addRoad(r);
        actor.addRoad(r);
    }
}

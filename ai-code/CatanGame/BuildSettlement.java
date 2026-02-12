package CatanGame;

/**
 * Action that builds a settlement at the target vertex.
 */
public class BuildSettlement extends Action {
    // Vertex where the settlement will be built
    public Node targetVertex;

    public BuildSettlement(Node targetVertex) {
        this.targetVertex = targetVertex;
        this.actionDesc = "Build a settlement at node " + targetVertex.getId();
    }

    @Override
    public void execute(Game gameInstance, Player actor) {
        Settlement s = new Settlement(actor, targetVertex);
        gameInstance.addBuilding(s);
        actor.addBuilding(s);
        actor.collectPoints(s.getVictoryPoints());
    }
}

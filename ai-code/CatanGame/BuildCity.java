package CatanGame;

/**
 * Action that builds a city at the target vertex.
 */
public class BuildCity extends Action {
    // Vertex where the city will be built
    public Node targetVertex;

    public BuildCity(Node targetVertex) {
        this.targetVertex = targetVertex;
        this.actionDesc = "Build a city at node " + targetVertex.getId();
    }

    @Override
    public void execute(Game gameInstance, Player actor) {
        City c = new City(actor, targetVertex);
        gameInstance.addBuilding(c);
        actor.addBuilding(c);
        actor.collectPoints(c.getVictoryPoints());
    }
}

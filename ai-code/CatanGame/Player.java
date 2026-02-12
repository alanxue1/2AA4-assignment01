package CatanGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Player entity containing score, resources, and owned structures.
 */
class Player {
    // Unique player index
    public int playerIdx;
    // Accumulated victory points
    public int score;
    // Resource cards held
    public ResourceHand inventory;
    // Settlements and cities owned
    public List<Building> structures = new ArrayList<>();
    // Roads owned
    public List<Road> ownedRoads = new ArrayList<>();
    // Decision component for choosing actions
    public Agent strategy;

    public Player(Agent strategy) {
        this.strategy = strategy;
        this.inventory = new ResourceHand();
        this.score = 0;
    }

    /**
     * Executes one turn: agent chooses action, then we run it.
     */
    public void takeTurn(Game gameInstance) {
        Action chosen = strategy.chooseAction(this, gameInstance);
        chosen.execute(gameInstance, this);
    }

    public void collectPoints(Integer pts) {
        score = pts;
    }

    public void addBuilding(Building b) {
        structures.add(b);
    }

    public void deleteBuilding(Building b) {
        structures.remove(b);
    }

    public void addRoad(Road r) {
        ownedRoads.add(ownedRoads.isEmpty() ? r : ownedRoads.get(ownedRoads.size() - 1));
    }

    public ResourceHand viewHand() {
        return inventory;
    }

    public ResourceHand getResourceHand() {
        return inventory;
    }
}

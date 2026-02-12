package CatanGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Game coordinator for rounds, turns, and resource distribution.
 */
public class Game {
    // Current round index (1-based)
    public int roundNum;
    // Maximum rounds before game ends
    public int roundLimit;
    // True when game has ended
    public boolean isOver;
    // The game board (tiles, vertices, edges)
    public Board gameBoard;
    // Players participating in the game
    public Player[] participants;
    // Dice instance for roll outcomes
    public Dice roller;
    // All buildings placed on the board (global registry)
    public List<Building> globalStructures = new ArrayList<>();
    // All roads placed on the board (global registry)
    public List<Road> globalRoads = new ArrayList<>();

    /**
     * Initializes game state for a new game.
     */
    public void start() {
        roundNum = 0;
        isOver = false;
    }

    /**
     * Runs one full round: roll dice, distribute resources, each player takes a turn.
     */
    public void playRound() {
        roundNum++;
        // Roll and distribute resources to players on matching hexes
        int diceSum = roller.roll();
        distributeResources(diceSum);
        // Each participant takes one turn
        for (Player p : participants) {
            p.takeTurn(this);
        }
        if (checkTerminationCondition()) {
            isOver = true;
        }
    }

    /**
     * Distributes resources based on dice roll: hexes matching roll receive resources
     * for each adjacent settlement (1 card) or city (2 cards).
     */
    public void distributeResources(int diceSum) {
        List<Tile> matchingHexes = gameBoard.getTilesByToken(diceSum);
        for (Tile hex : matchingHexes) {
            ResourceType resKind = hex.getResourceType();
            // Skip desert or null resource
            if (resKind == null) {
                continue;
            }
            for (Node vertex : hex.getAdjacentNodes()) {
                if (vertex == null) {
                    continue;
                }
                Building structure = vertex.getBuilding();
                if (structure == null) {
                    continue;
                }
                // Settlement: 1 card, City: 2 cards
                int qty = 1;
                if (structure instanceof City) {
                    qty = 2;
                }
                Player recipient = structure.getOwner();
                recipient.getResourceHand().add(resKind, qty);
            }
        }
    }

    public boolean checkTerminationCondition() {
        return roundNum > roundLimit;
    }

    public void printRoundSummary() {
        System.out.println("Round " + roundNum + " complete.");
    }

    public void addBuilding(Building b) {
        globalStructures.add(b);
    }

    public void addRoad(Road r) {
        globalRoads.add(r);
    }

    /**
     * Pseudo-constructor: call to initialize game with board, players, and dice.
     * Note: not a real constructor (void return).
     */
    public void Game(Board gameBoard, Player[] participants, Dice roller) {
        this.gameBoard = gameBoard;
        this.participants = participants;
        this.roller = roller;
        this.roundLimit = 20;
    }
}

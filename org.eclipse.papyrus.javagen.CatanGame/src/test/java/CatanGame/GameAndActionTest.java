package CatanGame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite 5: Game & Action Tests")
class GameAndActionTest {

    private static class FixedDice implements Dice {
        private final int value;
        FixedDice(int value) { this.value = value; }
        @Override public int roll() { return value; }
    }

    @Test
    @DisplayName("Game terminates when a player reaches 10 VP")
    void testTerminationByVictoryPoints() {
        Board board = new Board();
        board.initializeMap();
        Player[] players = {
            new Player(1, new Agent(1)),
            new Player(2, new Agent(2)),
            new Player(3, new Agent(3)),
            new Player(4, new Agent(4))
        };
        Game game = new Game(board, players, new FixedDice(8), 100);

        assertFalse(game.checkTerminationCondition());

        players[0].collectPoints(10);
        assertTrue(game.checkTerminationCondition());
    }

    @Test
    @DisplayName("BuildSettlement blocked by distance rule; succeeds on isolated node")
    void testBuildSettlementDistanceRule() {
        Board board = new Board();
        board.initializeMap();
        Player player = new Player(1, new Agent(1));
        Game game = new Game(board, new Player[]{player, null, null, null}, new FixedDice(8), 10);

        // Give player enough resources for two settlements
        ResourceHand hand = player.getResourceHand();
        hand.add(ResourceType.WOOD, 2);
        hand.add(ResourceType.BRICK, 2);
        hand.add(ResourceType.SHEEP, 2);
        hand.add(ResourceType.WHEAT, 2);

        // Place settlement on node 0
        Node node0 = board.getNodeById(0);
        new BuildSettlement(node0).execute(game, player);
        assertTrue(node0.nodeOccupied());

        // Node 1 is adjacent to node 0 — should be blocked by distance rule
        Node node1 = board.getNodeById(1);
        int vpBefore = player.getVictoryPoints();
        new BuildSettlement(node1).execute(game, player);
        // VP unchanged means settlement was not placed
        assertEquals(vpBefore, player.getVictoryPoints());

        // Find a node far away that is not adjacent to node 0
        Node farNode = null;
        for (Node n : board.getNodes()) {
            if (n == null || n.nodeOccupied()) continue;
            boolean adjacent = false;
            for (Node adj : n.getAdjacentNodes()) {
                if (adj != null && adj.nodeOccupied()) { adjacent = true; break; }
            }
            if (!adjacent) { farNode = n; break; }
        }

        assertNotNull(farNode);
        new BuildSettlement(farNode).execute(game, player);
        assertTrue(farNode.nodeOccupied());
    }
}

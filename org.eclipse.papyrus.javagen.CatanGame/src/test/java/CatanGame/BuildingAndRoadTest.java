package CatanGame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite 4: Building & Road Tests")
class BuildingAndRoadTest {

    @Test
    @DisplayName("Settlement yields 1 VP, City yields 2 VP, both register on their node")
    void testSettlementAndCityVictoryPoints() {
        Player player = new Player(1, new Agent(1));
        Node node1 = new Node(0);
        Node node2 = new Node(1);

        Settlement settlement = new Settlement(player, node1);
        assertEquals(1, settlement.getVictoryPoints());
        assertTrue(node1.nodeOccupied());
        assertSame(settlement, node1.getBuilding());

        City city = new City(player, node2);
        assertEquals(2, city.getVictoryPoints());
        assertTrue(node2.nodeOccupied());
        assertSame(city, node2.getBuilding());
    }

    @Test
    @DisplayName("Road occupies edge; second road on same edge is rejected")
    void testRoadOccupiesEdge() {
        Player p1 = new Player(1, new Agent(1));
        Player p2 = new Player(2, new Agent(2));
        Node a = new Node(0);
        Node b = new Node(1);
        Edge edge = new Edge(a, b);

        assertFalse(edge.edgeOccupied());

        Road road1 = new Road(p1, edge);
        assertTrue(edge.edgeOccupied());
        assertSame(road1, edge.getRoad());

        Road road2 = new Road(p2, edge);
        // edge still holds the first road
        assertSame(road1, edge.getRoad());
    }
}

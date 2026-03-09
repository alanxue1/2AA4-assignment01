package CatanGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite 2: Board Tests")
class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Boundary testing on setNode/getNodeById: IDs -1, 0, 53, 54")
    void testSetAndGetNodeBoundary() {
        Node node0 = new Node(0);
        Node node53 = new Node(53);

        // Boundary: id = -1 (just below valid range) -> silently ignored
        board.setNode(-1, new Node(-1));
        assertNull(board.getNodeById(-1));

        // Boundary: id = 0 (lower valid bound)
        board.setNode(0, node0);
        assertSame(node0, board.getNodeById(0));

        // Boundary: id = 53 (upper valid bound)
        board.setNode(53, node53);
        assertSame(node53, board.getNodeById(53));

        // Boundary: id = 54 (just above valid range) -> silently ignored
        board.setNode(54, new Node(54));
        assertNull(board.getNodeById(54));
    }

    @Test
    @DisplayName("getEdge returns same edge regardless of node argument order")
    void testGetEdgeBothDirections() {
        Node a = new Node(0);
        Node b = new Node(1);
        Edge edge = new Edge(a, b);
        board.addEdge(edge);

        assertSame(edge, board.getEdge(a, b));
        assertSame(edge, board.getEdge(b, a));
    }

    @Test
    @DisplayName("getTilesByToken returns correct tiles after initializeMap()")
    void testGetTilesByToken() {
        board.initializeMap();

        // Token 0 is on the DESERT tile — should return at least one tile
        List<Tile> desertTiles = board.getTilesByToken(0);
        assertFalse(desertTiles.isEmpty());
        assertEquals(ResourceType.DESERT, desertTiles.get(0).getResourceType());

        // Token 99 does not exist on any tile
        List<Tile> noTiles = board.getTilesByToken(99);
        assertTrue(noTiles.isEmpty());
    }
}

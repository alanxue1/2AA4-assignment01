package CatanGame;
/**
 * Class used to configure a default board setup for the game
 */
public final class DefaultBoardSetup {
    /**
     * Private constructor to prevent instantiation of the class 
     */
    private DefaultBoardSetup() {}

    /**
     * Calls methods to configure nodes, edges, tiles and their adjacencies for a default board setup
     * @param board the board to be configured
     */
    public static void configureBoard(Board board) {
        createNodes(board);
        createTiles(board);
        attachTilesToNodes(board);
        setAdjacency(board);
    }

    /**
     * Creates the nodes for the board and adds them to the board instance
     * @param board the board to which the nodes will be added
     */
    private static void createNodes(Board board) {
        for (int i = 0; i < 54; i++) {
            board.setNode(i, new Node(i));
        }
    }

    /**
     * Creates the tiles for the board with the specified resource, identifier, and number token 
     * @param board the board to which the tiles will be added
     */
    private static void createTiles(Board board) {
        board.setTile(0,  new Tile(0,  ResourceType.WOOD,  10));
        board.setTile(1,  new Tile(1,  ResourceType.WHEAT, 11));
        board.setTile(2,  new Tile(2,  ResourceType.BRICK, 8));
        board.setTile(3,  new Tile(3,  ResourceType.ORE,   3));
        board.setTile(4,  new Tile(4,  ResourceType.SHEEP, 11));
        board.setTile(5,  new Tile(5,  ResourceType.SHEEP, 5));
        board.setTile(6,  new Tile(6,  ResourceType.SHEEP, 12));
        board.setTile(7,  new Tile(7,  ResourceType.WHEAT, 3));
        board.setTile(8,  new Tile(8,  ResourceType.ORE,   6));
        board.setTile(9,  new Tile(9,  ResourceType.WOOD,  4));
        board.setTile(10, new Tile(10, ResourceType.ORE,   6));
        board.setTile(11, new Tile(11, ResourceType.WHEAT, 9));
        board.setTile(12, new Tile(12, ResourceType.WOOD,  5));
        board.setTile(13, new Tile(13, ResourceType.BRICK, 9));
        board.setTile(14, new Tile(14, ResourceType.BRICK, 8));
        board.setTile(15, new Tile(15, ResourceType.WHEAT, 4));
        board.setTile(16, new Tile(16, ResourceType.DESERT, 0));
        board.setTile(17, new Tile(17, ResourceType.WOOD,  2));
        board.setTile(18, new Tile(18, ResourceType.SHEEP, 10));
    }

    /**
     * Creates relationship between the board's tiles and their adjacent nodes 
     * @param board the board whose tiles and nodes will be connected
     */
    private static void attachTilesToNodes(Board board) {
        attach(board, 13, new int[]{41, 42, 40, 18, 17, 39});
        attach(board, 14, new int[]{40, 44, 43, 21, 16, 18});
        attach(board, 15, new int[]{45, 47, 46, 19, 21, 43});
        attach(board, 12, new int[]{39, 17, 15, 14, 37, 38});
        attach(board, 4,  new int[]{18, 16, 5, 4, 15, 17});
        attach(board, 5,  new int[]{21, 19, 20, 0, 5, 16});
        attach(board, 16, new int[]{46, 48, 49, 22, 20, 19});
        attach(board, 11, new int[]{36, 37, 14, 13, 34, 35});
        attach(board, 3,  new int[]{15, 4, 3, 12, 13, 14});
        attach(board, 0,  new int[]{4, 5, 0, 1, 2, 3});
        attach(board, 6,  new int[]{0, 20, 22, 23, 6, 1});
        attach(board, 17, new int[]{22, 49, 50, 51, 52, 23});
        attach(board, 10, new int[]{13, 12, 11, 32, 33, 34});
        attach(board, 2,  new int[]{12, 3, 2, 9, 10, 11});
        attach(board, 1,  new int[]{2, 1, 6, 7, 8, 9});
        attach(board, 18, new int[]{6, 23, 52, 53, 24, 7});
        attach(board, 9,  new int[]{32, 11, 10, 29, 30, 31});
        attach(board, 8,  new int[]{9, 8, 27, 28, 29, 10});
        attach(board, 7,  new int[]{7, 24, 25, 26, 27, 8});
    }

    /**
     * Method to attach a tile to all its adjacent nodes 
     * @param board the board on which the tile and nodes are located
     * @param tileId identifier of tile to be attached
     * @param nodeIds array of identifiers for nodes adjacent to the tile 
     */
    private static void attach(Board board, int tileId, int[] nodeIds) {
        if (nodeIds == null || nodeIds.length != 6) {
            throw new IllegalArgumentException("Tile must have exactly 6 corner nodes");
        }

        Tile tile = board.getTileById(tileId);

        for (int nodeId : nodeIds) {
            tile.addAdjacentNode(board.getNodeById(nodeId));
        }
    }

    /**
     * Establish adjacency relationship between nodes on the board and creates edges between adjacent nodes
     * @param board the board whose nodes will be connected
     */
    private static void setAdjacency(Board board) {
        for (int t = 0; t < 19; t++) {
            Node[] corners = board.getTileById(t).getAdjacentNodes();
            for (int i = 0; i < 6; i++) {
                Node current = corners[i];
                Node next = corners[(i + 1) % 6];
                current.addAdjacentNode(next);
                next.addAdjacentNode(current);
                if(board.getEdge(current, next) == null) {
                    board.addEdge(new Edge(current, next));
                }
            }
        }
    }
}

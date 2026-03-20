package CatanGame;
import java.util.*;

/**
 * Utility class containing methods for analyzing the road network
 */
public final class RoadNetworkUtil {

    /**
     * Private constructor to prevent instantiation of the utility class 
     */
    private RoadNetworkUtil() {
    }

    /**
     * Computes the longest road of the player
     * @param player player whose longest road is being computed
     * @param board game board instance
     * @return longest road length in # of edges
     */
    public static int getLongestRoadCount(Player player, Board board) {
        if (player == null || board == null) {
            return 0;
        }

        List<Edge> playerEdgeList = new ArrayList<>(getRoadEdges(player)); // list of player's road edges
        if (playerEdgeList.isEmpty()) {
            return 0;
        }

        int bestLength = 0; // best road length found so far
        for (Edge start : playerEdgeList) {
            if (start == null || start.getFirst() == null || start.getSecond() == null) {
                continue;
            }

            Set<Edge> used = new HashSet<>(); // edges already counted in current path
            used.add(start);

            int fromFirst = 1 + countRoadFromNode(start.getFirst(), used, playerEdgeList); // stores the longest road length starting from the first node
            int fromSecond = 1 + countRoadFromNode(start.getSecond(), used, playerEdgeList); // stores the longest road length starting from the second node
            bestLength = Math.max(bestLength, Math.max(fromFirst, fromSecond));
        }

        return bestLength;
    }

    /**
     * Finds connected road groups in the player's roads.
     * @param player target player
     * @param board game board
     * @return connected road groups of player-road edges
     */
    public static List<Set<Edge>> getRoadGroups(Player player, Board board) {
        List<Set<Edge>> roadGroups = new ArrayList<>(); // list of connected road groups, each represented as a set of edges
        if (player == null || board == null) {
            return roadGroups;
        }

        List<Edge> remainingEdges = new ArrayList<>(getRoadEdges(player)); // edges not yet assigned to a road group
        while (!remainingEdges.isEmpty()) {
            Edge seedEdge = remainingEdges.remove(0); // starting edge for new road group 
            if (seedEdge == null) {
                continue;
            }

            Set<Edge> roadGroup = new HashSet<>(); // edges in current road group 
            roadGroup.add(seedEdge);

            boolean grew = true; // flag to track if we added any edge to the road group in the last cycle
            while (grew) {
                grew = false;
                for (int i = remainingEdges.size() - 1; i >= 0; i--) {
                    Edge potentialEdge = remainingEdges.get(i); // potential edges to add to the road group
                    if (potentialEdge != null && roadTouchesGroup(potentialEdge, roadGroup)) {
                        roadGroup.add(potentialEdge);
                        remainingEdges.remove(i);
                        grew = true;
                    }
                }
            }
            roadGroups.add(roadGroup);
        }
        return roadGroups;
    }

    /**
     * Finds an unoccupied edge that can be used.
     * @param roadGroups player's road groups
     * @param board game board instance
     * @return an edge that can be built to connect two road groups, or null if no such edge exists
     */
    public static Edge getEdgeToConnectGroups(List<Set<Edge>> roadGroups, Board board) {
        if (roadGroups == null || board == null || roadGroups.size() < 2) {
            return null;
        }

        List<Edge> boardEdges = board.getEdges(); // all edges on the board that could potentially be built on
        if (boardEdges == null || boardEdges.isEmpty()) {
            return null;
        }

        for (int i = 0; i < roadGroups.size(); i++) {
            for (int j = i + 1; j < roadGroups.size(); j++) {
                Set<Edge> firstGroup = roadGroups.get(i); // first road group
                Set<Edge> secondGroup = roadGroups.get(j); // second road group

                // Check if there is an unoccupied edge that directly connects the two groups
                for (Edge e : boardEdges) {
                    if (e == null || e.edgeOccupied()) {
                        continue;
                    }
                    Node endpointOne = e.getFirst(); 
                    Node endpointTwo = e.getSecond();
                    if (endpointOne == null || endpointTwo == null) {
                        continue;
                    }

                    boolean direct = (groupHasNode(firstGroup, endpointOne) && groupHasNode(secondGroup, endpointTwo))
                        || (groupHasNode(firstGroup, endpointTwo) && groupHasNode(secondGroup, endpointOne));
                    if (direct) {
                        return e;
                    }
                }

                // If no direct connection, check if there is an unoccupied edge that can connect the two groups with an intermediate edge
                for (Edge firstStep : boardEdges) {
                    if (firstStep == null || firstStep.edgeOccupied()) {
                        continue;
                    }

                    Node startPoint; 
                    Node midPoint;
                    if (groupHasNode(firstGroup, firstStep.getFirst())) {
                        startPoint = firstStep.getFirst();
                        midPoint = firstStep.getSecond();
                    } else if (groupHasNode(firstGroup, firstStep.getSecond())) {
                        startPoint = firstStep.getSecond();
                        midPoint = firstStep.getFirst();
                    } else {
                        continue;
                    }

                    if (startPoint == null || midPoint == null) {
                        continue;
                    }

                    for (Edge secondStep : boardEdges) {
                        if (secondStep == null || secondStep.edgeOccupied() || secondStep == firstStep) {
                            continue;
                        }
                        Node end = getOtherNode(secondStep, midPoint);
                        if (end != null && groupHasNode(secondGroup, end)) {
                            return firstStep;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Recursively extends a road in one direction until no further extensions can be made 
     * @param atNode current node to extend from
     * @param used edges already used in the current path 
     * @param playerEdges all edges owned by the player
      * @return length of the longest path starting from atNode in # of edges
     */
    private static int countRoadFromNode(Node atNode, Set<Edge> used, List<Edge> playerEdges) {
        int bestLength = 0; // best extension length found so far
        for (Edge edge : playerEdges) {
            if (edge == null || used.contains(edge)) {
                continue;
            }

            Node next = getOtherNode(edge, atNode); // node on the other side of the edge
            if (next == null) {
                continue;
            }

            used.add(edge);
            int candidate = 1 + countRoadFromNode(next, used, playerEdges);
            if (candidate > bestLength) {
                bestLength = candidate;
            }
            used.remove(edge);
        }
        return bestLength;
    }

    /**
     * Gets the edges that have roads owned by the player
     * @param player target player
     * @return set of edges that have roads owned by the player
     */
    private static Set<Edge> getRoadEdges(Player player) {
        Set<Edge> playerOwnedEdges = new HashSet<>(); // set of edges with player-owned roads
        if (player == null) {
            return playerOwnedEdges;
        }

        for (Road road : player.getRoads()) {
            if (road == null || road.getOwner() != player) {
                continue;
            }
            Edge edge = road.getRoadLocation();
            if (edge != null) {
                playerOwnedEdges.add(edge);
            }
        }
        return playerOwnedEdges;
    }

    /**
     * Checks if an edge shares a node with any edge in the road group, meaning it can be used to extend that road group 
     * @param edge edge to check 
     * @param roadGroup set of edges representing the road group 
     * @return true if edge shares a node with any edge in the road group, false otherwise
     */
    private static boolean roadTouchesGroup(Edge edge, Set<Edge> roadGroup) {
        for (Edge existing : roadGroup) {
            if (shareNode(edge, existing)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two edges share a node
      * @param first first edge
      * @param second second edge
      * @return true if the edges share a node, false otherwise
     */
    private static boolean shareNode(Edge firstEdge, Edge secondEdge) {
        if (firstEdge == null || secondEdge == null) {
            return false;
        }

        Node fFirst = firstEdge.getFirst();
        Node fSecond = firstEdge.getSecond();
        Node sFirst = secondEdge.getFirst();
        Node sSecond = secondEdge.getSecond();

        return fFirst == sFirst || fFirst == sSecond || fSecond == sFirst || fSecond == sSecond;
    }

    /**
     * Checks if a road group contains a specific node.
     * @param roadGroup set of edges representing the road group
     * @param node node to check
     * @return true if the road group contains the node, false otherwise
     */
    private static boolean groupHasNode(Set<Edge> roadGroup, Node node) {
        if (roadGroup == null || node == null) {
            return false;
        }
        for (Edge edge : roadGroup) {
            if (edge == null) {
                continue;
            }
            if (edge.getFirst() == node || edge.getSecond() == node) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the opposite endpoint if node is one of the endpoints of the edge, or null if node is not an endpoint of the edge
      * @param edge edge to check
      * @param node node to check
      * @return the opposite endpoint if node is one of the endpoints of the edge, or null if node is not an endpoint of the edge
     */
    private static Node getOtherNode(Edge edge, Node node) {
        if (edge == null || node == null) {
            return null;
        }
        if (edge.getFirst() == node) {
            return edge.getSecond();
        }
        if (edge.getSecond() == node) {
            return edge.getFirst();
        }
        return null;
    }
}

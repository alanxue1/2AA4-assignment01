package CatanGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite 1: ResourceHand Tests")
class ResourceHandTest {

    private ResourceHand hand;

    @BeforeEach
    void setUp() {
        hand = new ResourceHand();
    }

    @Test
    @DisplayName("Partition testing on add(): null type, null amount, negative, zero, positive")
    void testAddPartitionTesting() {
        // Partition 1: null type -> no change
        hand.add(null, 5);
        assertEquals(0, hand.totalCards());

        // Partition 2: null amount -> no change
        hand.add(ResourceType.WOOD, null);
        assertEquals(0, hand.totalCards());

        // Partition 3: negative amount -> no change
        hand.add(ResourceType.WOOD, -1);
        assertEquals(0, hand.getCount(ResourceType.WOOD));

        // Partition 4: zero amount (boundary between invalid and valid) -> no change
        hand.add(ResourceType.WOOD, 0);
        assertEquals(0, hand.getCount(ResourceType.WOOD));

        // Partition 5: positive amount -> count increases
        hand.add(ResourceType.WOOD, 3);
        assertEquals(3, hand.getCount(ResourceType.WOOD));
    }

    @Test
    @DisplayName("remove() clamps at zero — never goes negative")
    void testRemoveDoesNotGoBelowZero() {
        hand.add(ResourceType.BRICK, 2);
        hand.remove(ResourceType.BRICK, 5);
        assertEquals(0, hand.getCount(ResourceType.BRICK));

        // removing from an empty resource also stays at 0
        hand.remove(ResourceType.ORE, 1);
        assertEquals(0, hand.getCount(ResourceType.ORE));
    }

    @Test
    @DisplayName("Boundary testing on canAfford(): exactly enough vs one short")
    void testCanAffordBoundary() {
        // Settlement costs: 1 WOOD, 1 BRICK, 1 SHEEP, 1 WHEAT
        hand.add(ResourceType.WOOD, 1);
        hand.add(ResourceType.BRICK, 1);
        hand.add(ResourceType.SHEEP, 1);
        hand.add(ResourceType.WHEAT, 1);

        // Boundary: exactly enough -> true
        assertTrue(hand.canAfford(BuildCosts.SETTLEMENT));

        // Remove one WHEAT so we're one short on the boundary
        hand.remove(ResourceType.WHEAT, 1);
        assertFalse(hand.canAfford(BuildCosts.SETTLEMENT));
    }

    @Test
    @DisplayName("spend() deducts the correct resources")
    void testSpendDeductsResources() {
        hand.add(ResourceType.WOOD, 3);
        hand.add(ResourceType.BRICK, 3);

        hand.spend(BuildCosts.ROAD); // costs 1 WOOD + 1 BRICK

        assertEquals(2, hand.getCount(ResourceType.WOOD));
        assertEquals(2, hand.getCount(ResourceType.BRICK));
    }

    @Test
    @DisplayName("totalCards() sums all resource types")
    void testTotalCards() {
        hand.add(ResourceType.WOOD, 2);
        hand.add(ResourceType.BRICK, 3);
        hand.add(ResourceType.ORE, 1);

        assertEquals(6, hand.totalCards());
    }
}

package CatanGame;

import java.util.Map;

/**
 * Shared build cost definitions for standard Catan actions.
 */
public final class BuildCosts {
	public static final Map<ResourceType, Integer> ROAD = Map.of(
		ResourceType.WOOD, 1,
		ResourceType.BRICK, 1
	);

	public static final Map<ResourceType, Integer> SETTLEMENT = Map.of(
		ResourceType.WOOD, 1,
		ResourceType.BRICK, 1,
		ResourceType.SHEEP, 1,
		ResourceType.WHEAT, 1
	);

	public static final Map<ResourceType, Integer> CITY = Map.of(
		ResourceType.WHEAT, 2,
		ResourceType.ORE, 3
	);

	private BuildCosts() {
	}
}

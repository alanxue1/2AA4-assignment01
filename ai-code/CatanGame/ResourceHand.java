package CatanGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Resource inventory keyed by resource type.
 */
public class ResourceHand {
    // Map from resource kind to quantity held
    public Map<ResourceType, Integer> countsByType;

    public ResourceHand() {
        countsByType = new HashMap<>();
        // Initialize all resource kinds to zero
        for (ResourceType kind : ResourceType.values()) {
            countsByType.put(kind, 0);
        }
    }

    /**
     * Adds the given quantity of a resource.
     */
    public void add(ResourceType kind, Integer qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        countsByType.put(kind, countsByType.get(kind) + qty);
    }

    /**
     * Removes the given quantity. No check for sufficient stock.
     */
    public void remove(ResourceType kind, Integer qty) {
        countsByType.put(kind, countsByType.get(kind) - qty);
    }

    /**
     * Returns total card count across all resource types.
     */
    public int totalCards() {
        int sum = 0;
        for (Integer c : countsByType.values()) {
            sum += c;
        }
        return sum;
    }
}

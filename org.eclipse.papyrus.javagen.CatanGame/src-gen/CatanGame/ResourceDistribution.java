package CatanGame;

/**
 * Stores info for distributing resources to a player
 */
class ResourceDistribution {
  /** player receiving resources */
  private Player player;
  /** amount of cards to give */
  private int numberOfCardsToGive;
  /** type of resource to give */
  private ResourceType resourceType;

  /**
   * Constructor for resource distribution
   * @param player player receiving resources
   * @param numberOfCardsToGive amount to give
   * @param resourceType type of resource
   */
  public ResourceDistribution(Player player, int numberOfCardsToGive, ResourceType resourceType) {
    this.player = player;
    this.numberOfCardsToGive = numberOfCardsToGive;
    this.resourceType = resourceType;
  }

  /**
   * @return player receiving resources
   */
  public Player getPlayer(){
    return player;
  }

  /**
   * @return number of cards to give
   */
  public int getNumberOfCardsToGive() {
    return numberOfCardsToGive;
  }

  /**
   * @return type of resource
   */
  public ResourceType getResourceType() {
    return resourceType;
  }
}
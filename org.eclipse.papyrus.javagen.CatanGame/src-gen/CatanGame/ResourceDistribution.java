package CatanGame;

class ResourceDistribution {
  private Player player;
  private int numberOfCardsToGive;
  private ResourceType resourceType;


  public ResourceDistribution(Player player, int numberOfCardsToGive, ResourceType resourceType) {
    this.player = player;
    this.numberOfCardsToGive = numberOfCardsToGive;
    this.resourceType = resourceType;
  }


  public Player getPlayer(){
    return player;
  }

  public int getNumberOfCardsToGive() {
    return numberOfCardsToGive;
  }

  public ResourceType getResourceType() {
    return resourceType;
  }
}
package CatanGame;

public interface PlayerAgent {
  Action chooseAction(Player player, Game game);

  void setControlledPlayer(Player player);
}

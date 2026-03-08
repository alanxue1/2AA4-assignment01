package CatanGame;

public class Robber {

  private Tile curTile;

  public Robber(Tile curTile) {
    this.curTile = curTile;
  }

  public Tile getCurrentTile() {
    return curTile;
  }


  public void moveRobber(Tile tile) {
    curTile = tile;
  }

}

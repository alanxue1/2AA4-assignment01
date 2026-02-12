package CatanGame;

/**
 * City building implementation (2 victory points).
 */
public class City extends Building {
    public City(Player owningPlayer, Node placement) {
        super(owningPlayer, placement);
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }
}

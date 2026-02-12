package CatanGame;

/**
 * Settlement building implementation (1 victory point).
 */
public class Settlement extends Building {
    public Settlement(Player owningPlayer, Node placement) {
        super(owningPlayer, placement);
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }
}

package CatanGame;

/**
 * Class that represents the Settlement building type in the game
 */
public class Settlement extends Building {
	/**
	 * Constructor for settlement
	 * @param owner player who owns it
	 * @param location where it's built
	 */
	public Settlement(Player owner, Node location) {
		super(owner, location);
	}
	/**
	 * Returns number of victory points for the Settlement type
	 * @return # of victory points
	 */
	public int getVictoryPoints() {
		return 1;
	}
}

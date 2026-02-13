package CatanGame;

/**
 * Class that represents a City building in the game 
 */
public class City extends Building {

	/**
	 * Constructor for City building 
	 * @param owner player who owns it
	 * @param location where it's built
	 */
	public City(Player owner, Node location) {
		super(owner, location);
	}

	/**
	 * Returns victory points for the City type 
	 * @return # of victory points
	 */
	public int getVictoryPoints() {
		return 2; 
	}
}

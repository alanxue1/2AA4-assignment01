package CatanGame;

import java.util.List;
import java.util.ArrayList;
/**
 * 
 */
public class Player {
	
	private int id; // Player id #
	private int victoryPoints; // total # of victory points the player has
	private ResourceHand hand; // Player's current hand of resource cards
	private List<Building> buildings = new ArrayList<>(); // List of buildings owned by the player
	private List<Road> roads = new ArrayList<>(); // List of roads owned by the player
	private Agent agent; // Player's decision making agent

	/**
	 * Constructor to initialize Player with a decision making Agent
	 * @param id player id
	 * @param agent decision making agent
	 */
	public Player(int id, Agent agent) {
		this.id = id;
		this.agent = (agent == null) ? new Agent(id) : agent;
		this.agent.setControlledPlayer(this);
		this.hand = new ResourceHand();
		this.victoryPoints = 0; 
		this.buildings = new ArrayList<>();
		this.roads = new ArrayList<>();
	}

	/**
	 * Alternate constructor with default id
	 * @param agent decision making agent
	 */
	public Player(Agent agent) {
		this(-1, agent);
	}

	/**
	 * Player takes a turn by choosing action through the agent and executing it
	 * @param game Game instance
	 * @return action that was executed
	 */
	public Action takeTurn(Game game) {
		if (agent == null) {
			return new Pass();
		}
		Action action = agent.chooseAction(this,game); // agent picks an action
		if (action == null) {
			action = new Pass();
		}
		action.execute(game,this);
		return action;
	}

	/**
	 * Adds victory points to the player's total
	 * @param amount points to add
	 */
	public void collectPoints(Integer amount) {
		victoryPoints = victoryPoints + amount;
	}

	/**
	 * Adds a building to the player's collection
	 * @param building Building to be added
	 */
	public void addBuilding(Building building) {
		buildings.add(building);
	}

	/**
	 * Removes a building from the player's collection
	 * @param building Building to be removed 
	 */
	public void deleteBuilding(Building building) {
		buildings.remove(building);
	}

	/**
	 * Adds a road to the player's collection
	 * @param road Road to be added 
	 */
	public void addRoad(Road road) {
		roads.add(road);
	}

	/**
	 * Returns the player's current hand of resource cards
	 * @return ResourceHand representing the player's current hand
	 */
	public ResourceHand viewHand() {
		return hand; 
	}

	/**
	 * @return player's resource hand
	 */
	public ResourceHand getResourceHand() {
		return hand;
	}

	/**
	 * @return player id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return current victory points
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * @return copy of buildings list
	 */
	public List<Building> getBuildings() {
		return new ArrayList<>(buildings);
	}

	/**
	 * @return copy of roads list
	 */
	public List<Road> getRoads() {
		return new ArrayList<>(roads);
	}

	/**
	 * Sets the player's id
	 * @param id new player id
	 */
	public void setId(int id) {
		this.id = id;
	}
}

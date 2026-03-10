package CatanGame;

import java.util.Scanner;

/**
 * Human player agent that reads commands from console input.
 * Uses HumanInputParser for regex-based command parsing and
 * TurnStatusSystem to enforce correct command ordering.
 */
public class HumanAgent implements PlayerAgent {

	private Player controls;
	private final Scanner scanner;
	private final HumanInputParser parser;
	private final TurnStatusSystem turnStatus;

	public HumanAgent(Scanner scanner) {
		this.scanner = scanner;
		this.parser = new HumanInputParser();
		this.turnStatus = new TurnStatusSystem();
	}

	@Override
	public boolean isInteractive() {
		return true;
	}

	@Override
	public void setControlledPlayer(Player player) {
		this.controls = player;
	}

	/**
	 * Manages a full human turn: roll -> (list|build)* -> go.
	 * Build actions are executed immediately within the loop.
	 * Returns Pass when the human types "go".
	 */
	@Override
	public Action chooseAction(Player player, Game game) {
		turnStatus.reset();
		Action lastAction = new Pass();

		while (!turnStatus.isComplete()) {
			System.out.print("> ");
			if (!scanner.hasNextLine()) {
				break;
			}
			String input = scanner.nextLine();
			CommandParsed command = parser.parse(input);

			if (command == null) {
				System.out.println("Invalid command. Try: roll, go, list, build [settlement|city|road] [args]");
				continue;
			}

			String type = command.getTypeOfCommand();

			if (!turnStatus.allowedToBegin(type)) {
				if (turnStatus.getCurStatus() == TurnStatus.BEGIN) {
					System.out.println("You must roll first.");
				} else {
					System.out.println("Invalid command at this point. Use: list, build, or go.");
				}
				continue;
			}

			if ("roll".equals(type)) {
				turnStatus.changeStatus("roll");
				int rollResult = game.rollDiceForHuman();
				System.out.println("You rolled a " + rollResult + ".");
				lastAction = new Pass();
			} else if ("list".equals(type)) {
				printHand(player);
			} else if ("go".equals(type)) {
				turnStatus.changeStatus("go");
				lastAction = new Pass();
			} else if ("build".equals(type)) {
				Action buildAction = handleBuildCommand(command, player, game);
				if (buildAction != null) {
					buildAction.execute(game, player);
					lastAction = buildAction;
					System.out.println("Executed: " + buildAction.getActionExplanation());
				}
			}
		}

		return lastAction;
	}

	private Action handleBuildCommand(CommandParsed command, Player player, Game game) {
		String buildType = command.getTypeOfBuild();
		int[] args = command.getArguments();
		Board board = game.getBoard();

		if ("settlement".equals(buildType)) {
			if (args == null || args.length < 1) {
				System.out.println("Usage: build settlement <nodeId>");
				return null;
			}
			Node node = board.getNodeById(args[0]);
			if (node == null) {
				System.out.println("Invalid node ID: " + args[0]);
				return null;
			}
			if (!player.getResourceHand().canAfford(BuildCosts.SETTLEMENT)) {
				System.out.println("Not enough resources for a settlement.");
				return null;
			}
			return new BuildSettlement(node);

		} else if ("city".equals(buildType)) {
			if (args == null || args.length < 1) {
				System.out.println("Usage: build city <nodeId>");
				return null;
			}
			Node node = board.getNodeById(args[0]);
			if (node == null) {
				System.out.println("Invalid node ID: " + args[0]);
				return null;
			}
			if (!player.getResourceHand().canAfford(BuildCosts.CITY)) {
				System.out.println("Not enough resources for a city.");
				return null;
			}
			return new BuildCity(node);

		} else if ("road".equals(buildType)) {
			if (args == null || args.length < 2) {
				System.out.println("Usage: build road <fromNodeId>, <toNodeId>");
				return null;
			}
			Node from = board.getNodeById(args[0]);
			Node to = board.getNodeById(args[1]);
			if (from == null || to == null) {
				System.out.println("Invalid node IDs: " + args[0] + ", " + args[1]);
				return null;
			}
			Edge edge = board.getEdge(from, to);
			if (edge == null) {
				System.out.println("No edge exists between nodes " + args[0] + " and " + args[1]);
				return null;
			}
			if (!player.getResourceHand().canAfford(BuildCosts.ROAD)) {
				System.out.println("Not enough resources for a road.");
				return null;
			}
			return new BuildRoad(edge);

		} else {
			System.out.println("Unknown build type: " + buildType);
			return null;
		}
	}

	private void printHand(Player player) {
		ResourceHand hand = player.getResourceHand();
		System.out.println("=== Your Resources ===");
		for (ResourceType type : ResourceType.values()) {
			if (type == ResourceType.DESERT) {
				continue;
			}
			System.out.println("  " + type + ": " + hand.getCount(type));
		}
		System.out.println("  Total: " + hand.totalCards());
	}
}

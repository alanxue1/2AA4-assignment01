package CatanGame;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Maintains command history for undo/redo behaviour 
 */
public class CommandHistory {
	/** Stack of commands that can be undone */
	private final Deque<Action> undoCommandsStack;

	/** Stack of commands that can be redone */
	private final Deque<Action> redoCommandsStack;

	/**
	 * Initializes the command history with empty undo and redo stacks
	 */
	public CommandHistory() {
		this.undoCommandsStack = new ArrayDeque<>();
		this.redoCommandsStack = new ArrayDeque<>();
	}

	/**
	 * Runs a specific command and records it in history by adding it to the undo stack and clearing the redo stack
	 * @param action Command to execute
	 * @param game Current game instance
	 * @param player The player executing the command
	 */
	public void executeCommand(Action action, Game game, Player player) {
		if (action == null) {
			return;
		}
		action.execute(game, player);
		undoCommandsStack.push(action);
		redoCommandsStack.clear();
	}

	/**
	 * Undoes most recently executed command
	 * @param game Current game instance
	 * @param player The player undoing the command
	 * @return True if command was succesfully undone, false if it wasn't 
	 */
	public boolean undoCommand(Game game, Player player) {
		if (undoCommandsStack.isEmpty()) {
			return false;
		}
		Action action = undoCommandsStack.pop(); // command being undone
		action.undo(game, player);
		redoCommandsStack.push(action);
		return true;
	}

	/**
	 * Redoes the most recently undone command
	 * @param game Current game instance 
	 * @param player Player that is redoing the command 
	 * @return True if command was successfully redone, false if it wasn't
	 */
	public boolean redoCommand(Game game, Player player) {
		if (redoCommandsStack.isEmpty()) {
			return false;
		}
		Action action = redoCommandsStack.pop(); // command being redone
		action.execute(game, player);
		undoCommandsStack.push(action);
		return true;
	}

	/**
	 * @return true/false depending on whether there is at least one command to undo
	 */
	public boolean canUndoCommand() {
		return !undoCommandsStack.isEmpty();
	}

	/**
	 * @return true/false depending on whether there is at least one command to redo
	 */
	public boolean canRedoCommand() {
		return !redoCommandsStack.isEmpty();
	}

	/**
	 * Resets the command history by clearing both undo and redo stacks
	 */
	public void clearCommand() {
		undoCommandsStack.clear();
		redoCommandsStack.clear();
	}
}

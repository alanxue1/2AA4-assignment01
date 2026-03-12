package CatanGame;

/**
 * Class that manages actions accotrding to the AgentActionAutomata.PNG state machine 
 */
public class TurnStatusSystem {
  
  /** The current status of the player's turn (i.e current state in state machine)*/
  private TurnStatus curStatus;

  public TurnStatusSystem() {
    this.curStatus = TurnStatus.BEGIN;

  }

  /**
   * Checks if final state is reached (i.e. if the player's turn is complete)
    * @return true if the player's turn is complete, otherwise false
   */
  public boolean isComplete() {
    if (curStatus==TurnStatus.COMPLETE) {
      return true;
    }
    return false;
  }
  
  /**
   * Returns the current status of the player's turn.
   * @return the current turn status
   */

  public TurnStatus getCurStatus() {
    return curStatus;
  }

  /**
   * Verifies state condition for the given input (i.e. checks if the given input is a valid action for the current state in the state machine)
   * @param input the action to be checked
   */
  public boolean allowedToBegin(String input) {
    if (curStatus == TurnStatus.BEGIN) {
      if (input.equals("roll")) {
        return true;
      } else {
        return false;
      }
    }

    if (curStatus == TurnStatus.ROLLDICE) {
      if (input.equals("list") || input.equals("build") || input.equals("go")) {
        return true;
      } else {
        return false;
      }
    }

    return false;
  }

  /**
   * Changes current status of player's turn according to input
   * @param input the action that triggers the status change
   */
  public void changeStatus(String input) {
    if (curStatus ==TurnStatus.BEGIN && input.equals("roll")) {
      curStatus =TurnStatus.ROLLDICE;
    }
    else if (curStatus ==TurnStatus.ROLLDICE && input.equals("go")) {
      curStatus = TurnStatus.COMPLETE;
    }
  }

  /**
   * Resets the current status of the player's turn to the initial state (i.e. BEGIN)
   */
  public void reset() {
    curStatus = TurnStatus.BEGIN;
  }


}

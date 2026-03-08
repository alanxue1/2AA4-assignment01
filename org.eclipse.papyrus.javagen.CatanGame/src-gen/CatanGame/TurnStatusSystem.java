package CatanGame;

public class TurnStatusSystem {
  

  private TurnStatus curStatus;

  public TurnStatusSystem() {
    this.curStatus = TurnStatus.BEGIN;

  }

  public boolean isComplete() {
    if (curStatus==TurnStatus.COMPLETE) {
      return true;
    }
    return false;
  }
  
  public TurnStatus getCurStatus() {
    return curStatus;
  }



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

  public void changeStatus(String input) {
    if (curStatus ==TurnStatus.BEGIN && input.equals("roll")) {
      curStatus =TurnStatus.ROLLDICE;
    }
    else if (curStatus ==TurnStatus.ROLLDICE && input.equals("go")) {
      curStatus = TurnStatus.COMPLETE;
    }
  }


  public void reset() {
    curStatus = TurnStatus.BEGIN;
  }



}

package CatanGame;

/**
 * Action that passes the current player's turn without modifying state.
 */
public class Pass extends Action {
    public Pass() {
        this.actionDesc = "Pass turn";
    }

    @Override
    public void execute(Game gameInstance, Player actor) {
        return;
    }
}

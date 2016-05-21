package GameState;

import Mediator.Mediator;
/**
 * Created by Michael on 5/6/2016.
 */
public class StateBase
{
    private A_State currentState;
    private Mediator mediator;

    public StateBase()
    {
        mediator = new Mediator();
        currentState = new MainMenu(mediator);
        mediator.receiveNewBattle(true);
    }

    public String displayCurrentState()
    {
        return currentState.display();
    }

    public void executeCurrentState(String command)
    {
        currentState = currentState.execute(command);
    }

    public boolean isNotEnd()
    {
        return !currentState.isEndOfGame();
    }
}

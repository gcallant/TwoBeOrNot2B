package StateExample;

/**
 * Created by Michael on 5/6/2016.
 */
public class StateBase
{
    private A_State[] states;
    private A_State currentState;

    public StateBase()
    {
        states = new A_State[5];
        states[StateValues.MainMenu.ordinal()] = new MainMenu();
        states[StateValues.MapExploration.ordinal()] = new MapExploration();
        states[StateValues.InGameMenu.ordinal()] = new InGameMenu();
        states[StateValues.QuitGame.ordinal()] = new QuitGame();
        states[StateValues.ExitGame.ordinal()] = new ExitGame();
        currentState = states[0];
    }

    public String displayCurrentState()
    {
        return currentState.display();
    }

    public void executeCurrentState(String command)
    {
        currentState = states[currentState.execute(command)];
    }

    public boolean exitGame()
    {
        return currentState.getOrdinalValue() == StateValues.ExitGame.ordinal();
    }
}

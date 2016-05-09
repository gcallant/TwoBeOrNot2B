package StateExample;

/**
 * Created by Michael on 5/6/2016.
 */
public class StateBase
{
    private State[] states;
    private State currentState;

    public StateBase()
    {
        this.states = new State[5];
        this.states[StateValues.MainMenu.ordinal()] = new MainMenu();
        this.states[StateValues.MapExploration.ordinal()] = new MapExploration();
        this.states[StateValues.InGameMenu.ordinal()] = new InGameMenu();
        this.states[StateValues.QuitGame.ordinal()] = new QuitGame();
        this.states[StateValues.ExitGame.ordinal()] = new ExitGame();
        this.currentState = this.states[0];
    }

    public void executeState()
    {
        System.out.println(this.currentState.display());
    }

    public String display()
    {
        return this.currentState.display();
    }

    public void execute(String command)
    {
        this.currentState = this.states[this.currentState.execute(command)];
    }

    public boolean exitGame()
    {
        return this.currentState.getOrdinalValue() == StateValues.ExitGame.ordinal();
    }
}

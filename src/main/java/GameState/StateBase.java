package GameState;

import java.util.ArrayList;
import Mediator.Mediator;
/**
 * Created by Michael on 5/6/2016.
 */
public class
StateBase
{
    private A_State[] states;
    private A_State currentState;

    public StateBase()
    {

        Mediator mediator = new Mediator();
        states = new A_State[StateValues.total.ordinal()];
        states[StateValues.MainMenu.ordinal()] = new MainMenu();
        states[StateValues.MapExploration.ordinal()] = new MapExploration();
        states[StateValues.InGameMenu.ordinal()] = new InGameMenu();
        states[StateValues.QuitGame.ordinal()] = new QuitGame();
        states[StateValues.ExitGame.ordinal()] = new ExitGame();
        states[StateValues.Battle.ordinal()] = new Battle(mediator);
        states[StateValues.CharacterCreation.ordinal()] = new CharacterCreation(mediator);
        states[StateValues.Victory.ordinal()] = new Victory();
        states[StateValues.Defeated.ordinal()] = null;
        states[StateValues.EndOfMap.ordinal()] = new EndOfMap();

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

    public void giveParty(ArrayList<Character> party)
    {
        states[StateValues.Battle.ordinal()].giveParty(party);
    }
}

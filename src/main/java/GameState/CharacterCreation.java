package GameState;

import StringTester.TestString;
import Mediator.Mediator;
import Character.*;

import java.util.ArrayList;

/**
 * Created by Michael on 5/12/2016.
 */
public class CharacterCreation extends A_State
{
    private String[] validInputs;
    private Mediator mediator;

    public CharacterCreation(Mediator mediator)
    {
        validInputs = new String[2];
        validInputs[0] = "party";
        validInputs[1] = "Cancel";

        this.mediator = mediator;

        StateValues[] validStates = new StateValues[2];
        validStates[0] = StateValues.MapExploration;
        validStates[1] = StateValues.MainMenu;

        setStates(validStates, StateValues.InGameMenu.ordinal());
    }

    public String display()
    {
        return "Create your party\nCancel\n";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, validInputs);
        if(stateSwitch == 0)
        {
            ArrayList<A_Character> party = new ArrayList<A_Character>();
            party.add(new Warrior("Bob", 10, 10, 10, 10, 10));
            Party myParty = new Party(party);
            mediator.recieveParty(myParty);
        }
        if(stateSwitch == -1)
        {
            return StateValues.CharacterCreation.ordinal();
        }
        else
        {
            return getStateValue(stateSwitch);
        }
    }
}

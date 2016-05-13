package GameState;

import StringTester.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MainMenu extends A_State
{
    private String[] validInputs;

    public MainMenu()
    {
        validInputs = new String[2];
        validInputs[0] = "quit";
        validInputs[1] = "start";
       
        StateValues[] validStates = new StateValues[2];
        validStates[1] = StateValues.CharacterCreation;
        validStates[0] = StateValues.MainMenu;
        
        setStates(validStates, StateValues.MainMenu.ordinal());
    }

    public String display()
    {
        return "Choose an option\nStart\nQuit";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.MainMenu.ordinal();
        }
        else
        {
            return getStateValue(stateSwitch);
        }
    }

}

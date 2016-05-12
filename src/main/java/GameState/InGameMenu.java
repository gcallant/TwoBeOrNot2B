package GameState;

import StringTester.TestString;

/**
 * Created by Michael on 5/8/2016.
 */
public class InGameMenu extends A_State
{
    private String[] validInputs;

    public InGameMenu()
    {
        validInputs = new String[4];
        validInputs[0] = "quit";
        validInputs[1] = "resume";
        validInputs[2] = "inventory";
        validInputs[3] = "saveGame";
        
        StateValues[] validStates = new StateValues[4];
        validStates[0] = StateValues.QuitGame;
        validStates[1] = StateValues.MapExploration;
        validStates[2] = StateValues.InGameMenu;
        validStates[3] = StateValues.InGameMenu;

        setStates(validStates, StateValues.InGameMenu.ordinal());
    }

    public String display()
    {
       return "Choose an option\nResume\nInventory\nSaveGame\nQuit\n";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.InGameMenu.ordinal();
        }
        else
        {
            return getStateValue(stateSwitch);
        }
    }

}

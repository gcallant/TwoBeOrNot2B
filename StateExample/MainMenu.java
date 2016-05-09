package StateExample;

import StringTester.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MainMenu extends State
{
    private String[] validInputs;

    public MainMenu()
    {
        this.validInputs = new String[2];
        this.validInputs[0] = "quit";
        this.validInputs[1] = "start";
        StateValues[] validStates = new StateValues[2];
        validStates[1] = StateValues.MapExploration;
        validStates[0] = StateValues.MainMenu;
        this.setStates(validStates,StateValues.MainMenu.ordinal());
    }

    public String display()
    {
        return "Choose an option\nStart\nQuit";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command,this.validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.MainMenu.ordinal();
        }
        else
        {
            return this.getStateValue(stateSwitch);
        }
    }

}

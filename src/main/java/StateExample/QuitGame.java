package StateExample;

import StringTester.TestString;

/**
 * Created by Michael on 5/8/2016.
 */
public class QuitGame extends A_State
{
    private String[] validInputs;

    public QuitGame()
    {
        this.validInputs = new String[2];
        this.validInputs[0] = "yes";
        this.validInputs[1] = "no";
        StateValues[] validStates = new StateValues[2];
        validStates[0] = StateValues.ExitGame;
        validStates[1] = StateValues.MapExploration;

        this.setStates(validStates,StateValues.QuitGame.ordinal());
    }

    public String display()
    {
        return "Are you sure you want to quit?";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, this.validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.QuitGame.ordinal();
        }
        else
        {
            return this.getStateValue(stateSwitch);
        }
    }
}

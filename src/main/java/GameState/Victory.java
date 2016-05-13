package GameState;

import StringTester.TestString;

/**
 * Created by Michael on 5/12/2016.
 */
public class Victory extends A_State
{
    public Victory()
    {
        setStates(null, StateValues.InGameMenu.ordinal());
    }

    public String display()
    {
        return "You won! Press enter to continue!";
    }

    public int execute(String command)
    {
        return StateValues.MapExploration.ordinal();
    }

}

package GameState;

import StringTester.TestString;

/**
 * Created by Michael on 5/12/2016.
 */
public class EndOfMap extends A_State
{
    public EndOfMap()
    {
        setStates(null, StateValues.EndOfMap.ordinal());
    }

    public String display()
    {
        return "You beat the level. Progress saved! Press enter to continue.";
    }

    public int execute(String command)
    {
        return StateValues.MapExploration.ordinal();
    }
}

package StateExample;

import StringTester.TestString;

/**
 * Created by Michael on 5/8/2016.
 */
public class ExitGame extends State
{
    public ExitGame()
    {
        setStates(null,StateValues.ExitGame.ordinal());
    }
    public String display()
    {
        return "Thanks for playing!";
    }

    public int execute(String command)
    {
        return StateValues.ExitGame.ordinal();
    }
}

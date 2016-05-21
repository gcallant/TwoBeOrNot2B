package GameState;

import Mediator.*;
/**
 * Created by Michael on 5/8/2016.
 */
public class ExitGame implements A_State
{

    public ExitGame(Mediator mediator)
    {

    }

    public boolean isEndOfGame()
    {
        return true;
    }

    public String display()
    {
        return "Thanks for playing!";
    }

    public A_State execute(String command)
    {
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof ExitGame))
        {
            return false;
        }
        return true;
    }
}

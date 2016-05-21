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

    public A_State execute()
    {
        return null;
    }
}

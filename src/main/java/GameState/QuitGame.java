package GameState;

import StringTester.TestString;
import Mediator.*;
/**
 * Created by Michael on 5/8/2016.
 */
public class QuitGame implements A_State
{
    private Mediator mediator;

    public QuitGame(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Are you sure you want to quit?";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "yes":
                return new ExitGame(mediator);
            case "no":
                return new MapExploration(mediator);
            default:
                return new QuitGame(mediator);
        }
    }
}

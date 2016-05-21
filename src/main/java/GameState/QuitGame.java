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
        return "Are you sure you want to quit?\n1) Yes\n2) No";
    }

    public A_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                return new ExitGame(mediator);
            case 2:
                return new MapExploration(mediator);
            default:
                return new QuitGame(mediator);
        }
    }
}

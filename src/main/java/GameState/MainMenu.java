package GameState;

import StringTester.TestString;
import Mediator.*;

/**
 * Created by Michael on 5/6/2016.
 */
public class MainMenu implements A_State
{
    private Mediator mediator;

    public MainMenu(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Choose an option\n1) Start\n2) Quit";
    }

    public A_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                return new CharacterCreation(mediator);
            case 2:
                return new ExitGame(mediator);
            default:
                return new MainMenu(mediator);
        }
    }

}

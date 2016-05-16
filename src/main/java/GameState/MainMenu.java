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
        return "Choose an option\nStart\nQuit";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "quit":
                return new ExitGame(mediator);
            case "start":
                return new CharacterCreation(mediator);
            default:
                return new MainMenu(mediator);
        }
    }

}

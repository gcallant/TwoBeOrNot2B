package GameState;

import StringTester.TestString;
import Mediator.*;
/**
 * Created by Michael on 5/8/2016.
 */
public class InGameMenu implements A_State
{
    private Mediator mediator;

    public InGameMenu(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
       return "Choose an option\nResume\nInventory\nSaveGame\nQuit\n";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "quit":
                return new QuitGame(mediator);
            case "resume":
                return new MapExploration(mediator);
            case "inventory":
                return new InGameMenu(mediator);
            case "saveGame":
                return new InGameMenu(mediator);
            default:
                return new InGameMenu(mediator);
        }
    }

}

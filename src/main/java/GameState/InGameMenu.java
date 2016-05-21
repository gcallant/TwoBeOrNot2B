package GameState;

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
       return "Choose an option\nResume\nInventory\nQuit\n";
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
                return new PartyInventory(mediator);
            default:
                return new InGameMenu(mediator);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof InGameMenu))
        {
            return false;
        }

        InGameMenu menu = (InGameMenu)obj;

        return this.mediator.equals(menu.mediator);
    }

}

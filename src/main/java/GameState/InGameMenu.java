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
       return "Choose an option\n1) Resume\n2) Inventory\n3) View Stats\n4) Quit";
    }

    public A_State execute()
    {
        int command = TestString.ensureInt(4);
        switch(command)
        {
            case 1:
                return new MapExploration(mediator);
            case 2:
                return new PartyInventory(mediator);
            case 3:
                mediator.giveParty().displayStats();
                break;
            case 4:
                return new QuitGame(mediator);
            default:
                return new InGameMenu(mediator);
        }
        return new InGameMenu(mediator);
    }

}

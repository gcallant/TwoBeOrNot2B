package GameState;

import Characters.Party;
import Mediator.Mediator;
import StringTester.TestString;

/**
 * Created by Michael on 5/18/2016.
 */
public class PartyInventory implements A_State
{
    private Mediator mediator;
    private Party heroes;

    public PartyInventory(Mediator mediator)
    {
        this.mediator = mediator;
        heroes = mediator.giveParty();
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Choose an option\n1) Equip\n2) Use Potion\n3) Back";
    }

    public A_State execute()
    {
        int command = TestString.ensureInt(3);
        switch(command)
        {
            case 1:
                heroes.equip();
                return new PartyInventory(mediator);
            case 2:
                heroes.consumePotion();
                return new PartyInventory(mediator);
            case 3:
                return new InGameMenu(mediator);
            default:
                return new PartyInventory(mediator);
        }
    }
}

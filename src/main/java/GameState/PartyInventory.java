package GameState;

import Characters.Party;
import Mediator.Mediator;

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
        return "Choose an option\nEquip\nUse Potion\nBack\n";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "equip":
                heroes.equip();
                return new PartyInventory(mediator);
            case "use potion":
                heroes.consumePotion();
                return new PartyInventory(mediator);
            case "back":
                return new InGameMenu(mediator);
            default:
                return new PartyInventory(mediator);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof PartyInventory))
        {
            return false;
        }

        PartyInventory partyInvent = (PartyInventory) obj;

        return this.mediator.equals(partyInvent.mediator) && this.heroes.equals(partyInvent.heroes);
    }
}

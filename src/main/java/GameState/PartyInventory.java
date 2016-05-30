package GameState;

import PartyManagement.Party;
import Utilities.TestString;

/**
 * Created by Michael on 5/18/2016.
 */
public class PartyInventory implements I_State
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
        return "Press enter to view inventory";
    }

    public I_State execute()
    {
        TestString.enterInput();
        heroes.useInventory();
        return new InGameMenu(mediator);
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

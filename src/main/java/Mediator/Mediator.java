package Mediator;

import Character.Party;
/**
 * Created by Michael on 5/12/2016.
 */
public class Mediator
{
    private Party characterParty;

    public Mediator()
    {
        this.characterParty = null;
    }

    public void recieveParty(Party party)
    {
        this.characterParty = party;
    }

    public Party giveParty()
    {
        return this.characterParty;
    }
}

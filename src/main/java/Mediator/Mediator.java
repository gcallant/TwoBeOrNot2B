package Mediator;

import Character.Party;
import DungeonGeneration.GenerateDungeon;

/**
 * Created by Michael on 5/12/2016.
 */
public class Mediator
{
    private Party characterParty;
    private GenerateDungeon map;
    private int dungeonSize;

    public Mediator()
    {
        this.characterParty = null;
        this.map = null;
        this.dungeonSize = 10;
    }

    public void recieveParty(Party party)
    {
        this.characterParty = party;
    }

    public Party giveParty()
    {
        return this.characterParty;
    }

    public GenerateDungeon giveMap()
    {
        return this.map;
    }

    public void recieveMap(GenerateDungeon map)
    {
        this.map = map;
    }

    public int dungeonSize()
    {
        return dungeonSize;
    }
}

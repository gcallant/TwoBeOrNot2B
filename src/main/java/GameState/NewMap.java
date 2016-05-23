package GameState;

import DungeonGeneration.GenerateDungeon;
import Mediator.Mediator;

/**
 * Created by Michael on 5/15/2016.
 */
public class NewMap implements I_State
{
    private Mediator mediator;

    public NewMap(Mediator mediator)
    {
        this.mediator = mediator;
        this.mediator.receiveCurrentLevel(this.mediator.giveCurrentLevel() + 1);
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "";
    }

    public I_State execute()
    {
        GenerateDungeon dungeon = new GenerateDungeon(10,15);
        dungeon.generatePath();
        mediator.receiveMap(dungeon);
        return new MapExploration(mediator);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof NewMap))
        {
            return false;
        }

        NewMap thatMap = (NewMap) obj;

        return this.mediator.equals(thatMap.mediator);
    }
}

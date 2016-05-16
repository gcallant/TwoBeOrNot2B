package GameState;

import DungeonGeneration.GenerateDungeon;
import Mediator.Mediator;

/**
 * Created by Michael on 5/15/2016.
 */
public class NewMap extends A_State
{
    private Mediator mediator;

    public NewMap(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "";
    }

    public A_State execute(String command)
    {
        GenerateDungeon dungeon = new GenerateDungeon(2,2);
        dungeon.generatePath();
        mediator.recieveMap(dungeon);
        return new MapExploration(mediator);
    }
}

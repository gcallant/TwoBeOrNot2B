package GameState;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;
import Mediator.*;

/**
 * Created by Michael on 5/12/2016.
 */
public class EndOfMap extends A_State
{
    Mediator mediator;

    public EndOfMap(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "You beat the level. Progress saved! Press enter to continue.";
    }

    public A_State execute(String command)
    {
        /*System.out.println(this.display());
        GenerateDungeon dungeon = new GenerateDungeon(mediator.dungeonSize(), mediator.dungeonSize());
        mediator.recieveMap(dungeon);*/
        return new NewMap(mediator);
    }
}

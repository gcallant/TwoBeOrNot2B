package GameState;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;
import Mediator.*;

/**
 * Created by Michael on 5/12/2016.
 */
public class EndOfMap implements A_State
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

    public A_State execute()
    {
        TestString.enterInput();
        return new NewMap(mediator);
    }
}

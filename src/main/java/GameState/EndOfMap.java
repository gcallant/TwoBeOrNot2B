package GameState;

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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof EndOfMap))
        {
            return false;
        }

        EndOfMap thatMap = (EndOfMap) obj;

        return this.mediator.equals(thatMap.mediator);
    }
}

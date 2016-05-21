package GameState;

import Characters.Party;
import Mediator.*;

/**
 * Created by Michael on 5/12/2016.
 */
public class Victory implements A_State
{
    private Mediator mediator;

    public Victory(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "You won! Press enter to continue!";
    }

    public A_State execute(String command)
    {
        Party enemies = mediator.giveEnemies();
        Party heroes = mediator.giveParty();

        heroes.gainExperience(enemies.calculatePartyLevel());

        return new MapExploration(mediator);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Victory))
        {
            return false;
        }

        Victory thatVictory = (Victory) obj;

        return this.mediator.equals(thatVictory.mediator);
    }

}

package GameState;

import Factories.GenerateItems;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

import java.util.Random;

/**
 * Created by Michael on 5/12/2016.
 */
public class Victory implements I_State
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

    public I_State execute()
    {
        TestString.enterInput();

        Party enemies = mediator.giveEnemies();
        Party heroes = mediator.giveParty();

        heroes.gainExperience(enemies.calculatePartyLevel());
        GenerateItems generateItems = new GenerateItems();

        Random rand = new Random();
        int currentLevel = mediator.giveCurrentLevel();
        int totalItems = rand.nextInt(mediator.giveCurrentLevel()*2 + 1) + 1;
        for(int x = 0; x < totalItems; x++)
        {
            generateItems.generateItem(heroes, mediator.giveCurrentLevel());
        }

        Display.displayMessage("Press enter to continue");
        TestString.enterInput();
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

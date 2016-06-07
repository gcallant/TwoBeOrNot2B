package GameState;

import Factories.GenerateItems;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

import java.util.Random;

/**
 * Created by Michael on 5/23/2016.
 */
public class BossVictory implements I_State
{
    private Mediator mediator;

    public BossVictory(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "You destroyed the dungeon guardian! Press enter to continue!";
    }

    public I_State execute()
    {
        TestString.enterInput();

        Party enemies = mediator.giveEnemies();
        Party heroes = mediator.giveParty();

        if(mediator.giveCurrentLevel() < 3)
        {
            heroes.gainExperience(enemies.calculatePartyLevel());
            GenerateItems generateItems = new GenerateItems();

            Random rand = new Random();
            int currentLevel = mediator.giveCurrentLevel();
            int totalItems = rand.nextInt(mediator.giveCurrentLevel() * 2 + 1);
            for (int x = 0; x < totalItems; x++) {
                generateItems.generateItem(heroes, mediator.giveCurrentLevel());
            }
            Display.displayMessage("Press enter to continue");
            TestString.enterInput();
        }
        return new SaveGame(mediator);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof BossVictory))
        {
            return false;
        }

        BossVictory thatVictory = (BossVictory) obj;

        return this.mediator.equals(thatVictory.mediator);
    }

}

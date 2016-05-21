package GameState;

import Characters.Party;
import Factories.GenerateItems;
import StringTester.TestString;
import Mediator.*;

import java.util.Random;

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

    public A_State execute()
    {
        TestString.enterInput();

        Party enemies = mediator.giveEnemies();
        Party heroes = mediator.giveParty();

        heroes.gainExperience(enemies.calculatePartyLevel());
        GenerateItems generateItems = new GenerateItems();

        Random rand = new Random();
        int currentLevel = mediator.giveCurrentLevel();
        int totalItems = rand.nextInt(mediator.giveCurrentLevel()*2 + 1);
        for(int x = 0; x < totalItems; x++)
        {
            generateItems.generateItem(heroes, mediator.giveCurrentLevel());
        }

        return new MapExploration(mediator);
    }

}

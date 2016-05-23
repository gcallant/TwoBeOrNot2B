package GameState;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;

import java.util.Random;
import Mediator.*;
/**
 * Created by Michael on 5/6/2016.
 */
public class MapExploration implements A_State
{
    private Random rand;
    private GenerateDungeon myMap = new GenerateDungeon(3, 3);
    private Mediator mediator;

    public MapExploration(Mediator mediator)
    {
        this.mediator = mediator;

        this.mediator.receivePartyLevel(mediator.giveParty().getCharacter(0).getLevel());

        rand = new Random();
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        myMap = mediator.giveMap();
        return myMap.printCharacter() + "\nSelect a direction\nu (Up)\nd (Down)\nr (Right)\nl (Left)\nOr m for the Menu";
    }

    public A_State moveDirection(String command)
    {
        if(!myMap.isValidDirection(command))
        {
            return new MapExploration(mediator);
        }
        boolean battleChance;
        myMap.updateCharacter(command);
        if(myMap.endOfMap())
        {
            return new EndOfMap(mediator);
        }
        battleChance = rand.nextBoolean();
        if(battleChance)
        {
            return new Battle(mediator);
        }
        return new MapExploration(mediator);
    }

    public A_State execute()
    {
        char[] validInputs = new char[6];
        validInputs[0] = 'u';
        validInputs[1] = 'd';
        validInputs[2] = 'r';
        validInputs[3] = 'l';
        validInputs[4] = 'm';
        validInputs[5] = 'n';
        char command = TestString.ensureChar(validInputs);
        switch(command)
        {
            case 'u':
                return moveDirection("up");
            case 'd':
                return moveDirection("down");
            case 'r':
                return moveDirection("right");
            case 'l':
                return moveDirection("left");
            case 'm':
                return new InGameMenu(mediator);
            case 'n':
                return new NewMap(mediator);
            default:
                return new MapExploration(mediator);
        }
    }
}

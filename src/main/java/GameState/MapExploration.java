package GameState;

import DungeonGeneration.GenerateDungeon;

import java.util.Random;
import Mediator.*;
import StringTester.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MapExploration implements I_State
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

    public I_State moveDirection(String command)
    {
        if(!myMap.isValidDirection(command) || mediator.giveFreeTravel())
        {
            return new MapExploration(mediator);
        }
        boolean battleChance;
        myMap.updateCharacter(command);
        if(myMap.endOfMap())
        {
            return new EndOfMap(mediator);
        }
        battleChance = rand.nextInt(mediator.giveMonsterChance()) == 0;
        if(battleChance && !mediator.giveNoEnemies())
        {
            mediator.receiveMonsterChance(10);
            return new Battle(mediator);
        }
        else
        {
            mediator.receiveMonsterChance(mediator.giveMonsterChance() - 1);
        }
        return new MapExploration(mediator);
    }

    public I_State execute()
    {
        char[] validInputs = new char[8];
        validInputs[0] = 'u';
        validInputs[1] = 'd';
        validInputs[2] = 'r';
        validInputs[3] = 'l';
        validInputs[4] = 'm';
        validInputs[5] = 'n';
        validInputs[6] = 'e';
        validInputs[7] = 'f';
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
            case 'e':
                mediator.receiveNoEnemies();
                return new MapExploration(mediator);
            case 'f':
                mediator.receiveNormal();
                return new MapExploration(mediator);
            default:
                return new MapExploration(mediator);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof MapExploration))
        {
            return false;
        }

        MapExploration map = (MapExploration) obj;

        return this.mediator.equals(map.mediator) && this.myMap.equals(map.myMap);
    }
}

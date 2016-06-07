package GameState;

import DungeonGeneration.GenerateDungeon;
import Utilities.TestString;
import javafx.scene.input.KeyCombination;

import java.util.Random;

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

        this.mediator.giveParty().setFloorLevel(this.mediator.giveCurrentLevel());
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        myMap = mediator.giveMap();
        return myMap.printCharacter() + "\nSelect a direction\nw (Up)\ns (Down)\nd (Right)\na (Left)\nOr m for the Menu";
    }

    public I_State moveDirection(String command)
    {
        if(!myMap.isValidDirection(command))
        {
            return new MapExploration(mediator);
        }
        boolean battleChance;
        myMap.updateCharacter(command);
        if(myMap.endOfMap())
        {
            return new BossBattle(mediator);
        }
        battleChance = rand.nextInt(mediator.giveMonsterChance()) == 0;
        if(battleChance && !mediator.giveNoEnemies())
        {
            mediator.receiveMonsterChance(10);
            return new Battle(mediator);
        }
        else
        {
            if(!mediator.giveNoEnemies())
            {
                mediator.receiveMonsterChance(mediator.giveMonsterChance() - 1);
            }
        }
        return new MapExploration(mediator);
    }

    public I_State execute()
    {
        char[] validInputs = new char[11];
        validInputs[0] = 'w';
        validInputs[1] = 's';
        validInputs[2] = 'd';
        validInputs[3] = 'a';
        validInputs[4] = 'm';
        validInputs[5] = 'n';
        validInputs[6] = 'e';
        validInputs[7] = 'f';
        validInputs[8] = 'b';
        validInputs[9] = 'z';
        validInputs[10] = '|';
        char command = TestString.ensureChar(validInputs);

        switch(command)
        {
            case 'w':
                return moveDirection("up");
            case 's':
                return moveDirection("down");
            case 'd':
                return moveDirection("right");
            case 'a':
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
            case 'b':
                return new BossBattle(mediator);
            case 'z':
                mediator.giveParty().gainExperience(500);
                return new MapExploration(mediator);
            case '|':
                 return new CheatMenu(mediator);
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

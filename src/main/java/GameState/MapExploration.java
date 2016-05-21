package GameState;

import DungeonGeneration.GenerateDungeon;

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

        rand = new Random();
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        myMap = mediator.giveMap();
        return myMap.printCharacter() + "\nSelect a direction (up, down, right, left) or select the Menu";
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

    public A_State execute(String command)
    {
        switch(command)
        {
            case "up":
            case "right":
            case "left":
            case "down":
                return moveDirection(command);
            case "menu":
                return new InGameMenu(mediator);
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

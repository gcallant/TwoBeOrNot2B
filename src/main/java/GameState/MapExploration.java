package GameState;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;

import java.util.Random;

/**
 * Created by Michael on 5/6/2016.
 */
public class MapExploration extends A_State
{
    private String[] validInputs;
    private Random rand;
    private GenerateDungeon myMap = new GenerateDungeon(3, 3);
    private boolean newMap;

    public MapExploration()
    {
        validInputs = new String[5];
        validInputs[0] = "up";
        validInputs[1] = "right";
        validInputs[2] = "left";
        validInputs[3] = "down";
        validInputs[4] = "menu";
        
        StateValues[] validStates = new StateValues[5];
        validStates[1] = StateValues.MapExploration;
        validStates[0] = StateValues.MapExploration;
        validStates[2] = StateValues.MapExploration;
        validStates[3] = StateValues.MapExploration;
        validStates[4] = StateValues.InGameMenu;

        newMap = true;

        rand = new Random();
        
        setStates(validStates, StateValues.MapExploration.ordinal());
    }

    public String display()
    {
        if(newMap)
        {
            myMap = new GenerateDungeon(3,3);
            myMap.generatePath();
            newMap = false;
        }
        return myMap.printCharacter() + "\nSelect a direction (up, down, right, left) or select the Menu";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, validInputs);
        boolean battleChance;
        if(stateSwitch == -1)
        {
            return StateValues.MapExploration.ordinal();
        }
        else
        {
            if(myMap.isValidDirection(command))
            {
                myMap.updateCharacter(command);
                if(myMap.endOfMap())
                {
                    newMap = true;
                    return StateValues.EndOfMap.ordinal();
                }
                battleChance = rand.nextBoolean();
                if(battleChance)
                {
                    return StateValues.Battle.ordinal();
                }
            }
            return getStateValue(stateSwitch);
        }
    }
}

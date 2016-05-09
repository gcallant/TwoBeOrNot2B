package StateExample;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MapExploration extends A_State
{
    private String[] validInputs;

    private GenerateDungeon myMap = new GenerateDungeon(10, 10);

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
        
        setStates(validStates, StateValues.MapExploration.ordinal());
        myMap.generatePath();
    }

    public String display()
    {
        return myMap.printCharacter() + "\nSelect a direction (up, down, right, left) or select the Menu";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command, validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.MapExploration.ordinal();
        }
        else
        {
            if(myMap.isValidDirection(command))
            {
                myMap.updateCharacter(command);
            }
            return getStateValue(stateSwitch);
        }
    }
}

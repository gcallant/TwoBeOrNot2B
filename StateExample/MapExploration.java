package StateExample;

import DungeonGeneration.GenerateDungeon;
import StringTester.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MapExploration extends State
{
    private String[] validInputs;
    private StateValues whatState;

    private GenerateDungeon myMap = new GenerateDungeon(10,10);

    public MapExploration()
    {
        this.validInputs = new String[5];
        this.validInputs[0] = "up";
        this.validInputs[1] = "right";
        this.validInputs[2] = "left";
        this.validInputs[3] = "down";
        this.validInputs[4] = "menu";
        StateValues[] validStates = new StateValues[5];
        validStates[1] = StateValues.MapExploration;
        validStates[0] = StateValues.MapExploration;
        validStates[2] = StateValues.MapExploration;
        validStates[3] = StateValues.MapExploration;
        validStates[4] = StateValues.InGameMenu;
        this.setStates(validStates, StateValues.MapExploration.ordinal());
        this.myMap.generatePath();
    }

    public String display()
    {
        return this.myMap.printCharacter() + "\nSelect a direction (up, down, right, left) or select the Menu";
    }

    public int execute(String command)
    {
        int stateSwitch = TestString.testInput(command,this.validInputs);
        if(stateSwitch == -1)
        {
            return StateValues.MapExploration.ordinal();
        }
        else
        {
            if(this.myMap.isValidDirection(command))
            {
                this.myMap.updateCharacter(command);
            }
            return this.getStateValue(stateSwitch);
        }
    }
}

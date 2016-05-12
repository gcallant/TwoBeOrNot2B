package GameState;

import java.util.ArrayList;

/**
 * Created by Michael on 5/6/2016.
 */
public abstract class A_State
{
    private StateValues[] validStates;
    private int ordinalValue;

    public void setStates(StateValues[] states, int ordinalValue)
    {
        validStates = states;
        ordinalValue = ordinalValue;
    }

    public abstract String display();

    public abstract int execute(String command);

    public int getStateValue(int index)
    {
        return validStates[index].ordinal();
    }

    public int getOrdinalValue()
    {
        return ordinalValue;
    }

    public void giveParty(ArrayList<Character> party){}
}

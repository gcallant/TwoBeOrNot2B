package StateExample;

/**
 * Created by Michael on 5/6/2016.
 */
public abstract class State
{
    private StateValues[] validStates;
    private int ordinalValue;

    public void setStates(StateValues[] states, int ordinalValue)
    {
        this.validStates = states;
        this.ordinalValue = ordinalValue;
    }

    public abstract String display();

    public abstract int execute(String command);

    public int getStateValue(int index)
    {
        return validStates[index].ordinal();
    }

    public int getOrdinalValue()
    {
        return this.ordinalValue;
    }
}

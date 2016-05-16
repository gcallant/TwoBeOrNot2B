package GameState;

import java.util.ArrayList;
import Character.Party;

/**
 * Created by Michael on 5/6/2016.
 */
public abstract class A_State
{
    private StateValues[] validStates;
    private int ordinalValue;

    public abstract String display();

    public abstract A_State execute(String command);

    public abstract boolean isEndOfGame();
}

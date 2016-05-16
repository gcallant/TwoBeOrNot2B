package GameState;

import java.util.ArrayList;
import Character.Party;

/**
 * Created by Michael on 5/6/2016.
 */
public interface A_State
{
    public String display();

    public A_State execute(String command);

    public boolean isEndOfGame();
}

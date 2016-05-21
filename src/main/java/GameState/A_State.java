package GameState;

import com.google.inject.Inject;

/**
 * Created by Michael on 5/6/2016.
 */

public interface A_State
{
    public abstract String display();

    public abstract A_State execute(String command);

    public abstract boolean isEndOfGame();
}

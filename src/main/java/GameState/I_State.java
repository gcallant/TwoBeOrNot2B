package GameState;

/**
 * Created by Michael on 5/6/2016.
 */
public interface I_State
{
    public String display();

    public I_State execute();

    public boolean isEndOfGame();
}

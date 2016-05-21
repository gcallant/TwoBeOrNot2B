package GameState;

/**
 * Created by Michael on 5/6/2016.
 */
public interface A_State
{
    public String display();

    public A_State execute();

    public boolean isEndOfGame();
}

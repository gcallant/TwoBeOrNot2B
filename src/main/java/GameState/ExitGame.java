package GameState;

/**
 * Created by Michael on 5/8/2016.
 */
public class ExitGame implements I_State
{

    public ExitGame(Mediator mediator)
    {

    }

    public boolean isEndOfGame()
    {
        return true;
    }

    public String display()
    {
        return "Thanks for playing!";
    }

    public I_State execute()
    {
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof ExitGame))
        {
            return false;
        }
        return true;
    }
}

package GameState;

import Utilities.TestString;

/**
 * Created by Michael on 5/8/2016.
 */
public class QuitGame implements I_State
{
    private Mediator mediator;

    public QuitGame(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Are you sure you want to quit?\n1) Yes\n2) No";
    }

    public I_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                return new ExitGame(mediator);
            case 2:
                return new MapExploration(mediator);
            default:
                return new QuitGame(mediator);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof QuitGame))
        {
            return false;
        }

        QuitGame quits = (QuitGame) obj;

        return this.mediator.equals(quits.mediator);
    }
}

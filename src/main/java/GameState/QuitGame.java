package GameState;

import Mediator.*;
/**
 * Created by Michael on 5/8/2016.
 */
public class QuitGame implements A_State
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
        return "Are you sure you want to quit?";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "yes":
                return new ExitGame(mediator);
            case "no":
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

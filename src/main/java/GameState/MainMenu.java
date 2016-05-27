package GameState;

import Utilities.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MainMenu implements I_State
{
    private Mediator mediator;

    public MainMenu(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Choose an option\n1) Start\n2) Quit";
    }

    public I_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                return new CharacterCreation(mediator);
            case 2:
                return new ExitGame(mediator);
            default:
                return new MainMenu(mediator);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof MainMenu))
        {
            return false;
        }

        MainMenu menu = (MainMenu)obj;

        return this.mediator.equals(menu.mediator);
    }
}

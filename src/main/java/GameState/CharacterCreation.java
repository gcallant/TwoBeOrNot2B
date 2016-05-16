package GameState;

import StringTester.TestString;
import Mediator.Mediator;
import Character.*;

import java.util.ArrayList;

/*
 * Created by Michael on 5/12/2016.
 */

public class CharacterCreation extends A_State
{
    private Mediator mediator;

    public CharacterCreation(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Create your party\nCancel";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "party":
                ArrayList<A_Character> party = new ArrayList<A_Character>();
                party.add(new Warrior("Bob", 10, 10, 10, 10, 10));
                Party myParty = new Party(party);
                mediator.recieveParty(myParty);
                return new NewMap(mediator);

            case "cancel":
                return new MainMenu(mediator);

            default:
                return new CharacterCreation(mediator);
        }
    }
}

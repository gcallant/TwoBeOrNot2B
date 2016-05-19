package GameState;

import Characters.*;
import Factories.HeroFactory;
import Item.*;
import Mediator.Mediator;

import java.util.ArrayList;

/*
 * Created by Michael on 5/12/2016.
 */

public class CharacterCreation implements A_State
{
    private Mediator mediator;
    private ArrayList<A_Character> party;

    public CharacterCreation(Mediator mediator)
    {
        this.mediator = mediator;
        party = new ArrayList<A_Character>();
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
                if(createNewParty())
                {
                    mediator.recieveParty(new Party(party));
                    return new NewMap(mediator);
                }
                return new MainMenu(mediator);

            case "cancel":
                return new MainMenu(mediator);

            default:
                return new CharacterCreation(mediator);
        }
    }

    private boolean createNewParty()
    {
        String input = "";
        while(party.size() < 4)
        {
            A_Character toAdd = CreateMember.createMember();
            if(toAdd != null)
            {
                party.add(toAdd);
            }
        }
        return CreateMember.confirm();
    }
}

package GameState;

import Characters.*;
import Mediator.Mediator;
import PartyManagement.CreateMember;
import PartyManagement.Party;
import StringTester.TestString;

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
        this.mediator.receiveCurrentLevel(0);
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Choose an option\n1) Create your party\n2) Cancel";
    }

    public A_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                if(createNewParty())
                {
                    mediator.recieveParty(new Party(party));
                    return new NewMap(mediator);
                }
                return new MainMenu(mediator);

            case 2:
                return new MainMenu(mediator);

            default:
                return new CharacterCreation(mediator);
        }
    }

    private boolean createNewParty()
    {
        String input = "";
        System.out.println("You can choose four heroes! Choose wisely.");
        while(party.size() < 4)
        {
            System.out.println("Choose character number " + (party.size() + 1) + ":");
            A_Character toAdd = CreateMember.createMember();
            if(toAdd != null)
            {
                party.add(toAdd);
            }
        }
        return CreateMember.confirm();
    }
}

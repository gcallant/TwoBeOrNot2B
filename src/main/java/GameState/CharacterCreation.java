package GameState;

import Characters.*;
import Mediator.Mediator;
import PartyManagement.CreateMember;
import PartyManagement.Party;
import StringTester.TestString;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Michael on 5/12/2016.
 */

public class CharacterCreation implements I_State
{
    private Mediator          mediator;
    private List<A_Character> party;

    @Inject
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

    @Override
    public I_State execute()
    {
        int command = TestString.ensureInt(2);
        switch(command)
        {
            case 1:
                if(createNewParty())
                {
                    mediator.receiveParty(new Party(party));
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof CharacterCreation))
        {
            return false;
        }

        CharacterCreation thatCharacter = (CharacterCreation)obj;

        return this.party.equals(thatCharacter.party) && this.mediator.equals(thatCharacter.mediator);
    }
}

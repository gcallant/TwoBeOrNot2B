package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import StringTester.TestString;

import java.util.Random;

/**
 * Created by Michael on 5/21/2016.
 */
public abstract class SpecialAbility
{
    protected Random rand;

    public SpecialAbility()
    {
        rand = new Random();
    }

    public abstract boolean executeAbility(A_Character character, Party allies, Party enemies);
    public abstract boolean executeAbilityRandom(A_Character character, Party allies, Party enemies);

    protected A_Character chooseTarget(Party party)
    {
        int choices = 1;
        int choice;
        System.out.println("Choose a target to use " + this + " on");
        for(int x = 0; x < party.size(); x++)
        {
            System.out.println(choices + ") " + party.getCharacter(x).battleDisplay());
            choices++;
        }
        choice = TestString.ensureInt(choices);

        if(choice == choices)
        {
            return null;
        }
        return party.getCharacter(choice - 1);
    }

    protected A_Character randomTarget(Party party)
    {
        return party.getCharacter(rand.nextInt(party.size()));
    }
}

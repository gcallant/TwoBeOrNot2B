package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

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

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof SpecialAbility)) { return false; }

        SpecialAbility that = (SpecialAbility) o;

        if(rand != null ? ! rand.equals(that.rand) : that.rand != null) { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        return rand != null ? rand.hashCode() : 0;
    }

    protected A_Character chooseTarget(Party party)
    {
        int choices = 1;
        int choice;
        Display.displayMessage("Choose a target to use " + this + " on");
        for(int x = 0; x < party.size(); x++)
        {
            Display.displayMessage(choices + ") " + party.getCharacter(x).battleDisplay());
            choices++;
        }
        Display.displayMessage(choices + ") cancel");
        choice = TestString.ensureInt(choices);

        if(choice == choices)
        {

            return null;
        }
        return party.getCharacter(choice - 1);
    }

    protected int calculateRounds(A_Character character)
    {
        return Math.max(1, character.getCunning()/3);
    }

    protected A_Character randomTarget(Party party)
    {
        return party.getCharacter(rand.nextInt(party.size()));
    }

    protected boolean getAffectedChance(A_Character attacker, String type, A_Character defender)
    {
        int value = rand.nextInt(11);
        int total;
        switch(type)
        {
            case "Power":
                total = Math.max(Math.min(10 - attacker.getPower() + defender.getResistance(), 9),1);
                return value > total;
            case "cunning":
                total = Math.max(Math.min(10 - attacker.getCunning() + defender.getResistance(), 9),1);
                return value > total;
        }
        return rand.nextInt(11) > 9;
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public SpecialAbility upgrade()
    {
        return null;
    }
}

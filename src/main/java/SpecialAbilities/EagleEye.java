package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class EagleEye extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies);
        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies);
        return false;
    }

    private void abilityExecution(A_Character character, Party allies)
    {
        Display.displayMessage(character.getName() + " used eagle eye!");
        double buff = 1.0 + ((double)character.getPower()*.025);

        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).getConditions().giveAttackBuff(buff, calculateRounds(character)*2, "Eagle Eye");
        }
    }

    public String toString()
    {
        return "Eagle Eye";
    }

    public static String description()
    {
        return "     - Eagle Eye: Increase the whole parties attack chance for many rounds";
    }
}

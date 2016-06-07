package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/22/2016.
 */
public class NaturalRemedy extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used natural remedy!");
        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).getConditions().giveRegenBuff(1.06, calculateRounds(character), "Natural Remedy");
        }
    }

    public String toString()
{
    return "Natural Remedy";
}

    public static String description()
    {
        return "     - Natural Remedy: Grants the caster and his allies regeneration for a few rounds";
    }
}

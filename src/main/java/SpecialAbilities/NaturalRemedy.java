package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/22/2016.
 */
public class NaturalRemedy extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character);
        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character);
        return false;
    }

    private void abilityExecution(A_Character character)
    {
        character.getConditions().giveRegenBuff(1.1, calculateRounds(character), "Natural Remedy");
    }

    public String toString()
{
    return "Natural Remedy";
}

    public static String description()
    {
        return "     - Natural Remedy: Grants the caster regeneration for a few rounds";
    }
}
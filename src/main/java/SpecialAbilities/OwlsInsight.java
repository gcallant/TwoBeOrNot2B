package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class OwlsInsight extends SpecialAbility
{
    public static String description()
    {
        return "     - Owl's Insight: buffs the casters Power";
    }

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
        System.out.println(character.getName() + " used owl's insight!");
        double calculateBuff = 1.0 + .05*((double)character.getPower());
        character.getConditions().giveDamageBuff(calculateBuff, calculateRounds(character), "Owl's Insight");
    }

    public String toString()
    {
        return "Owl's Insight";
    }
}

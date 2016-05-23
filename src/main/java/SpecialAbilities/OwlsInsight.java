package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class OwlsInsight extends SpecialAbility
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
        System.out.println(character.getName() + " used owl's insight!");
        double calculateBuff = 1.0 + .05*((double)character.getStrength());
        character.getConditions().giveDamageBuff(calculateBuff, character.getStrength()/2, "Owl's Insight");
    }

    public String toString()
    {
        return "Owl's Insight";
    }

}

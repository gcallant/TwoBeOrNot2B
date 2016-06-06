package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/28/2016.
 */
public class Charge extends SpecialAbility
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
        character.getConditions().giveChargeBuff(3.5, 2, "Charge");
        character.getConditions().giveExhaustedDebuff(2, "Charge");
    }

    public String toString()
    {
        return "Charge";
    }

    public static String description()
    {
        return "     - Charge: Build up high damage for your next attack!";
    }
}

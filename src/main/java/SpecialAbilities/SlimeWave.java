package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/28/2016.
 */
public class SlimeWave extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies);
        return false;
    }

    private void abilityExecution(A_Character character, Party enemies)
    {
        Display.displayMessage(character.getName() + " used slime wave ");
        for(A_Character choiceToStrike : enemies)
        {
            if(getAffectedChance(character, "cunning", choiceToStrike))
            {
                choiceToStrike.getConditions().giveExhaustedDebuff(Math.max(1, calculateRounds(character)/3), "Slime Wave");
            }
        }

    }

    public String toString()
    {
        return "Slime Wave";
    }

    public static String description()
    {
        return "     - Slime Wave";
    }
}

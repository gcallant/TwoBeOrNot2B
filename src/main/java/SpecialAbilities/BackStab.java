package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/28/2016.
 */
public class BackStab extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToStrike = chooseTarget(enemies);

        if(choiceToStrike == null)
        {
            return true;
        }

        abilityExecution(character, choiceToStrike);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies.getCharacter(rand.nextInt(enemies.size())));
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        Display.displayMessage(character.getName() + " used back stab on " + choiceToStrike.getName());
        if(character.attack(choiceToStrike) && getAffectedChance(character, "cunning", choiceToStrike))
        {
            choiceToStrike.getConditions().giveExhaustedDebuff(Math.max(1, calculateRounds(character)/3), "Back Stab");
        }
    }

    public String toString()
    {
        return "Back Stab";
    }

    public static String description()
    {
        return "     - Back Stab: A normal attack with a chance to exhaust your opponent";
    }
}

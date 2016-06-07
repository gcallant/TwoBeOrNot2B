package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/23/2016.
 */
public class ViciousBite extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used vicious bite on " + choiceToStrike.getName());

        if(character.attack(choiceToStrike));
        {
            if(getAffectedChance(character, "Power", choiceToStrike))
            {
                choiceToStrike.getConditions().giveBleedDebuff(1.0 + (.005*character.getCunning()), calculateRounds(character), "Vicious Bite");
            }
        }
    }

    public String toString()
    {
        return "Vicious Bite";
    }

    public static String description()
    {
        return "     - ";
    }
}

package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class Heal extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToHeal = chooseTarget(allies);

        if(choiceToHeal == null)
        {
            return true;
        }

        abilityExecution(character, choiceToHeal);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        int choiceToHeal = 0, best = -1, test = 0;
        for(int x = 0; x < allies.size(); x++)
        {
            test = allies.getCharacter(x).getMaxHealth() - allies.getCharacter(x).getHealth();
            if(test > best)
            {
                choiceToHeal = x;
                best = test;
            }
        }
        abilityExecution(character, allies.getCharacter(choiceToHeal));
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        Display.displayMessage(character.getName() + " used heal on " + choiceToStrike.getName() + " for " + character.getPower()*5);
        choiceToStrike.heal(character.getCunning()*5);
    }

    public String toString()
    {
        return "Heal";
    }

    public static String description()
    {
        return "     - Heal: Restores a moderate amount to an individual. Based off of cunning.";
    }
}

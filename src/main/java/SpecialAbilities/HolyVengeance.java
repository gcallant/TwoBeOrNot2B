package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class HolyVengeance extends SpecialAbility
{
    public static String description()
    {
        return "     - Holy Vengeance: Does damage based on missing health to an enemy";
    }

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
        A_Character choiceToStrike = enemies.getCharacter(rand.nextInt(enemies.size()));
        abilityExecution(character, choiceToStrike);
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        Display.displayMessage(character.getName() + " used holy vengeance on " + choiceToStrike.getName());

        choiceToStrike.takeDamage((character.getMaxHealth() - character.getHealth())/2);

    }

    public String toString()
    {
        return "Holy Vengeance";
    }
}

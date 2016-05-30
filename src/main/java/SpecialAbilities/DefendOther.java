package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/27/2016.
 */
public class DefendOther extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToDefend = chooseTarget(allies);

        if(choiceToDefend == null)
        {
            return true;
        }

        return abilityExecution(character, choiceToDefend);
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        int choiceToDefend = 0, best = 0, test = 0;
        for(int x = 0; x < allies.size(); x++)
        {
            test = allies.getCharacter(x).getMaxHealth() - allies.getCharacter(x).getHealth();
            if(allies.getCharacter(x).getConditions().isDefendingOther())
            {
                test = 0;
            }
            if(test > best)
            {
                choiceToDefend = x;
                best = test;
            }
        }
        abilityExecution(character, allies.getCharacter(choiceToDefend));
        return false;
    }

    private boolean abilityExecution(A_Character character, A_Character choiceToDefend)
    {
        if(choiceToDefend == character)
        {
            Display.displayMessage("Cannot defend yourself");
            return true;
        }
        if(choiceToDefend.getConditions().isDefendingOther())
        {
            Display.displayMessage("Cannot defend an ally who is already defending another ally");
            return true;
        }
        Display.displayMessage(character.getName() + " used defend other on " + choiceToDefend.getName());
        choiceToDefend.getConditions().giveDefendedStatus(character, character.getMaxHealth()*.05, calculateRounds(character), "Defend Other");
        return false;
    }

    public String toString()
    {
        return "Defend Other";
    }

    public static String description()
    {
        return "     - Defend Other: Receives a flat amount of damage for an ally for a few rounds";
    }
}

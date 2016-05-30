package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/23/2016.
 */
public class FearsomeGaze extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used fearsome gaze!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            if(getAffectedChance(character, "cunning", enemies.getCharacter(x)))
            {
                enemies.getCharacter(x).getConditions().giveFearedDebuff(calculateRounds(character), "Fearsome Gaze");
            }
        }
    }

    public String toString()
    {
        return "Fearsome Gaze";
    }

    public static String description()
    {
        return "     - ";
    }
}


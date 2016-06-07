package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/23/2016.
 */
public class PoisonBomb extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used poison bomb!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            if(character.attack(enemies.getCharacter(x)))
            {
                if(getAffectedChance(character, "cunning", enemies.getCharacter(x)))
                {
                    enemies.getCharacter(x).getConditions().givePoisonDebuff(1.2, calculateRounds(character), "Poison Bomb");
                }
            }
        }
    }

    public String toString()
    {
        return "Poison Bomb";
    }

    public static String description()
    {
        return "     - ";
    }
}
package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class FireBall extends SpecialAbility
{
    public static String description()
    {
        return "     - Fireball: Attacks all enemies for a small amount of damage, has no chance to miss, and burns " +
                       "them";
    }

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
        Display.displayMessage(character.getName() + " used fireball!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            character.preformAttack(enemies.getCharacter(x));
            if(getAffectedChance(character, "Power", enemies.getCharacter(x)))
            {
                enemies.getCharacter(x).getConditions().giveBurnDebuff(character.getPower(), calculateRounds(character), "Fireball");
            }
        }
    }

    public String toString()
    {
        return "Fireball";
    }
}

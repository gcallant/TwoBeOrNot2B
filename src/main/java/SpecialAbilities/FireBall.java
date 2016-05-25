package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/24/2016.
 */
public class FireBall extends SpecialAbility
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
        System.out.println(character.getName() + " used fireball!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            character.preformAttack(enemies.getCharacter(x));
            if(getAffectedChance(character, "power", enemies.getCharacter(x)))
            {
                System.out.println(enemies.getCharacter(x).getName() + " is burning!");
                enemies.getCharacter(x).getConditions().giveBurnDebuff(character.getPower(), calculateRounds(character), "Fireball");
            }
        }
    }

    public String toString()
    {
        return "Fireball";
    }

    public static String description()
    {
        return "     - Fireball: Attacks all enemies for a small amount of damage, has no chance to miss, and burns them";
    }
}

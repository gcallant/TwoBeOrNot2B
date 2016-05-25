package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class IntimidatingShout extends  SpecialAbility
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
        System.out.println(character.getName() + " used intimidating shout!");
        double debuff = 1.0 - ((double)character.getCunning()*.025);
        if(debuff < .25)
        {
            debuff = .25;
        }
        for(int x = 0; x < enemies.size(); x++)
        {
            enemies.getCharacter(x).getConditions().giveDamageDebuff(debuff, calculateRounds(character), "Intimidating Shout");
        }
    }

    public String toString()
    {
        return "Intimidating Shout";
    }

    public static String description()
    {
        return "     - Intimidating Shout: Causes all enemies to less damage. Debuff amount based off of cunning";
    }
}

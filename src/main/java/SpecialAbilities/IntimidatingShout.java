package SpecialAbilities;

import Characters.A_Character;
import Characters.Party;

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
        double debuff = 1.0 - ((double)character.getStrength()*.025);
        if(debuff < .25)
        {
            debuff = .25;
        }
        for(int x = 0; x < enemies.size(); x++)
        {
            enemies.getCharacter(x).getConditions().giveDamageBuff(debuff, 3);
        }
    }

    public String toString()
    {
        return "Intimidating Shout";
    }
}

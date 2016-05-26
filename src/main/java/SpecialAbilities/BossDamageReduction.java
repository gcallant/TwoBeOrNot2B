package SpecialAbilities;

import Characters.A_Character;

/**
 * Created by Michael on 5/25/2016.
 */
public class BossDamageReduction extends BarkSkin
{
    public void abilityExecution(A_Character character)
    {
        System.out.println(character.getName() + " used bark skin!");
        double calculateBuff = 1.0 + .015*((double)character.getPower());
        character.getConditions().giveDamageReductionBuff(calculateBuff, 100, "Bark Skin");
    }
}

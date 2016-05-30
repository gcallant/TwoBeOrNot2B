package SpecialAbilities;

import Characters.A_Character;
import Utilities.Display;

/**
 * Created by Michael on 5/25/2016.
 */
public class BossDamageReduction extends BarkSkin
{
    public void abilityExecution(A_Character character)
    {
        Display.displayMessage(character.getName() + " used bark skin!");
        double calculateBuff = 1.0 + .015*((double)character.getPower());
        character.getConditions().giveDamageReductionBuff(calculateBuff, 100, "Bark Skin");
    }
}

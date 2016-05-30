package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/21/2016.
 */
public class HealingLight extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies, enemies);
        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies, enemies);
        return false;
    }

    public void abilityExecution(A_Character character, Party allies, Party enemies)
    {
        Display.displayMessage(character.getName() + " used healing light and healed his whole party for " + 2*character.getPower());
        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).heal(2*character.getPower());
            allies.getCharacter(x).getConditions().recoverConditions();
        }
        character.getConditions().giveExhaustedDebuff(2,"Healing Light");
    }

    public String toString()
    {
        return "Healing Light";
    }

    public static String description()
    {
        return "     - ";
    }

}

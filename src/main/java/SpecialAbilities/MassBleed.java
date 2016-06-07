package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/25/2016.
 */
public class MassBleed extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used mass bleed!");
        int totalEnemies = enemies.size();
        for (int x = 0; x < totalEnemies; x++)
        {
            if (character.attack(enemies.getCharacter(x)) && getAffectedChance(character, "Power", enemies.getCharacter(x)))
            {
                enemies.getCharacter(x).getConditions().giveBleedDebuff(1.3, calculateRounds(character), "Mass Bleed");
            }
        }
    }

    public String toString() {
        return "Mass Bleed";
    }

    public static String description()
    {
        return "     - Mass Bleed";
    }
}

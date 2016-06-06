package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class OverlordsShout extends SpecialAbility
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
        Display.displayMessage(character.getName() + " used overlords shout!");
        double debuff = 1.0 - ((double)character.getCunning()*.025);
        if(debuff < .25)
        {
            debuff = .25;
        }
        for(int x = 0; x < enemies.size(); x++)
        {
            enemies.getCharacter(x).getConditions().giveDamageDebuff(debuff, calculateRounds(character), "Overlords Shout");
            enemies.getCharacter(x).getConditions().giveAttackDebuff(debuff, calculateRounds(character), "Overlords Shout");
        }
    }

    public String toString()
    {
        return "Overlords Shout";
    }

    public static String description()
    {
        return "     - Overlords Shout: Causes all enemies to less damage and hit less often. Debuff amount based off of cunning";
    }
}

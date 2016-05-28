package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class BolsteringCry extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies);
        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies);
        return false;
    }

    private void abilityExecution(A_Character character, Party allies)
    {
        Display.displayMessage(character.getName() + " used bolstering cry!");
        double buff = 1.0 + ((double)character.getCunning()*.025);

        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).getConditions().giveDamageBuff(buff, calculateRounds(character), "Bolstering Cry");
            allies.getCharacter(x).getConditions().giveAttackBuff(buff, calculateRounds(character), "Bolstering Cry");
        }
    }

    public String toString()
    {
        return "Bolstering Cry";
    }

    public boolean canUpgrage()
    {
        return false;
    }

    public static String description()
    {
        return "     - Bolstering Cry: Causes all allies to deal increased damage and have increased attack. Buff amount based off of cunning";
    }
}

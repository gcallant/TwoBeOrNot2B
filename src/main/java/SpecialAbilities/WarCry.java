package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class WarCry extends SpecialAbility
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
        System.out.println(character.getName() + " used war cry!");
        double buff = 1.0 + ((double)character.getStrength()*.025);

        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).getConditions().giveDamageBuff(buff, 3, "War Cry");
        }
    }

    public String toString()
    {
        return "War Cry";
    }
}

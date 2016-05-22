package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class HealingLight extends SpecialAbility
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
        System.out.println(character.getName() + " used healing light!");
        for(int x = 0; x < allies.size(); x++)
        {
            allies.getCharacter(x).heal(2*character.getStrength());
            allies.getCharacter(x).getConditions().recoverConditions();
        }
        character.getConditions().giveExhaustedDebuff(2,"Healing Light");
    }

    public String toString()
    {
        return "Healing Light";
    }

}

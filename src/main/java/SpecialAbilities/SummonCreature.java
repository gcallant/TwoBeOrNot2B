package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import PartyManagement.SummonMonster;

/**
 * Created by Michael on 5/25/2016.
 */
public class SummonCreature extends SpecialAbility
{
    public static String description()
    {
        return "     - Summon a dire wolf, undead cleric, skeleton, or a sapling";
    }

    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        if(character.getConditions().summoningSickness())
        {
            return true;
        }
        return abilityExecution(character, allies, enemies);
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies, enemies);
        return false;
    }

    private boolean abilityExecution(A_Character character, Party allies, Party enemies)
    {
        return SummonMonster.createMember(character, allies);
    }

    public String toString()
    {
        return "Summon Creature";
    }
}

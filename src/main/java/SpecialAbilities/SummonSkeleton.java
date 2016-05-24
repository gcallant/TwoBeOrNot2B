package SpecialAbilities;

import Characters.A_Character;
import Factories.MonsterFactory;
import Monsters.Skeleton;
import PartyManagement.Party;

/**
 * Created by Michael on 5/23/2016.
 */
public class SummonSkeleton extends SpecialAbility
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

    private void abilityExecution(A_Character character, Party allies, Party enemies)
    {
        System.out.println(character.getName() + " summoned a skeleton!");
        A_Character monster;
        if(rand.nextBoolean())
        {
            monster = new MonsterFactory().createMonster("Skeleton", "Skeleton", enemies.getCharacter(0).getLevel(), true);
        }
        else
        {
            monster = new MonsterFactory().createMonster("Undead Cleric", "Skeleton Cleric", enemies.getCharacter(0).getLevel(), true);
        }
        allies.addCharacter(monster);
    }

    public String toString()
    {
        return "Summon Skeleton";
    }

}
package SpecialAbilities;

import Characters.A_Character;
import Factories.MonsterFactory;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/23/2016.
 */
public class SummonSkeleton extends SpecialAbility
{
    public static String description()
    {
        return "     - ";
    }

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
        Display.displayMessage(character.getName() + " summoned a skeleton!");
        A_Character monster;
        if(rand.nextBoolean())
        {
            monster = new MonsterFactory().createMonster("Skeleton", "Skeleton", enemies.getCharacter(0).getLevel(), true, enemies.getFloorLevel());
        }
        else
        {
            monster = new MonsterFactory().createMonster("Undead Cleric", "Skeleton Cleric", enemies.getCharacter(0).getLevel(), true, enemies.getFloorLevel());
        }
        allies.addCharacter(monster);
    }

    public String toString()
    {
        return "Summon Skeleton";
    }

}
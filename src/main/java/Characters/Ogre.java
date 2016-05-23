package Characters;
import AttackAndDefendBehavior.*;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Random;


/**
 * Created by gm14793 on 5/9/16.
 */
public class Ogre extends A_Monster
{
    private boolean exhausted;

    public Ogre(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Heavy, armor, WeaponType.Heavy, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        stunningStrike(heroes.getCharacter(rand.nextInt(heroes.size())));
        exhausted = true;
        return false;
    }

    protected boolean cannotAttack()
    {
        return super.cannotAttack() || exhausted;
    }

    public void resetTurn()
    {
        super.resetTurn();
        exhausted = false;
    }

    public int getLevel()
    {
        return 10;
    }
}

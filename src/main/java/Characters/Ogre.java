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


    public Ogre(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
    {
        super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Heavy, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        stunningStrike(heroes.getCharacter(rand.nextInt(heroes.size())));
        return false;
    }
}

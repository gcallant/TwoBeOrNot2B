package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Skeleton extends A_Monster
{
    public Skeleton(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        //fearsome gaze. does no damage, but has chance to cause fear to each party member which causes affected
        //party members to have a chance to cower in fear rather than take an action.
        return false;
    }
}

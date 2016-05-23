package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.*;

/**
 * Created by Greig on 5/19/2016.
 */
public class Necromancer extends A_Nemesis
{
    public Necromancer(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        monsters.getCharacterParty().add(summonSkeleton(getLevel()));
        return false;
    }


}

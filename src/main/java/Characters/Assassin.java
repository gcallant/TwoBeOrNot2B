package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import java.util.*;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Assassin extends A_Monster
{
    public Assassin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        sneakAttack(heroes.getCharacter(rand.nextInt(heroes.size())));
        return false;
    }
}

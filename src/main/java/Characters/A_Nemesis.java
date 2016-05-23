package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

/**
 * Created by Greig on 5/19/2016.
 */
public abstract class A_Nemesis extends A_Character
{
    public A_Nemesis(String name, int health, int strength, int dexterity, ArmorType armorType, Armor armor, WeaponType weaponType, Weapon weapon)
    {
        super(name, health, strength, dexterity, armorType, armor, weaponType, weapon);
    }


}

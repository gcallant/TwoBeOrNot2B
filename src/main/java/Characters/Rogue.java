package Characters;

import AttackAndDefendBehavior.*;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.Scanner;

/**
 * Created by gm14793 on 5/9/16.
 */
public class Rogue extends A_Hero
{
    SpecialManager specialManager;

    public Rogue(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new SneakAttack());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Rogue: Rogues are fast and hard to hit. They excel at striking their enemies when they are at full health or incapacitated";
    }

    public String getName()
    {
        return super.getName() + " the Rogue";
    }

    public int strengthIncrease()
    {
        return 2;
    }

    public int dexterityIncrease()
    {
        return 3;
    }

    public int healthIncrease()
    {
        return 15;
    }


}

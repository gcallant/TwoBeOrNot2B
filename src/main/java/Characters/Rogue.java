package Characters;

import AttackAndDefendBehavior.*;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Scanner;

/**
 * Created by gm14793 on 5/9/16.
 */
public class Rogue extends A_Hero
{

    public Rogue(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        Scanner input = new Scanner(System.in);
        int toPick = -1;
        int itemIndex = pickCharacter(monsters);

        System.out.println("Choose someone to use your special attack on or " + itemIndex + " to cancel:");

        toPick = ensureInput(input, itemIndex) - 1;

        if(toPick == itemIndex - 1)
        {
            return true;
        }

        sneakAttack(monsters.getCharacter(toPick));

        return false;
    }

    public static String Information()
    {
        return "Rogue: Rogues are fast and hard to hit. They excel at striking their enemies when they are at full health or incapacitated";
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

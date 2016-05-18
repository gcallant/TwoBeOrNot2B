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

    public Rogue(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
    {
        super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Light, weapon);
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

        sneakAttack(monsters.getParty().get(toPick));

        return false;
    }
}

package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Scanner;

/**
 * Created by Michael on 5/18/2016.
 */
public class Paladin extends A_Hero
{
    private A_Character protecting;

    public Paladin(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
    {
        super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Medium, weapon);
        protecting = null;
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        Scanner input = new Scanner(System.in);
        int toPick = -1;
        int itemIndex = pickCharacter(heroes);

        System.out.println("Choose someone to use your special ability on or " + itemIndex + " to cancel:");

        toPick = ensureInput(input, itemIndex) - 1;

        if(toPick == itemIndex - 1)
        {
            return true;
        }

        protecting = heroes.getCharacter(toPick);
        protect(heroes.getCharacter(toPick));
        return false;
    }

    public void resetTurn()
    {
        super.resetTurn();
        if(protecting != null)
        {
            protecting.removeProtection();
        }
        protecting = null;
    }
}

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
    private int healingLight;

    public Paladin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
        protecting = null;
        healingLight = 0;
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        Scanner input = new Scanner(System.in);
        int toPick = -1;
        int specialAttack = -1;

        System.out.println("Choose which special attack to use:\n1) Protect\n2) Healing Light\n3) Cancel");

        specialAttack = ensureInput(input, 3);

        switch(specialAttack)
        {
            case 1:
                return chooseProtect(input, heroes);
            case 2:
                healingLight(heroes);
                healingLight = 2;
                return false;
        }

        return true;
    }

    private boolean chooseProtect(Scanner input, Party heroes)
    {
        int toPick = -1;

        System.out.println("Choose an ally to defend");

        int itemIndex = pickCharacter(heroes);

        toPick = ensureInput(input, itemIndex);

        if(toPick == itemIndex - 1)
        {
            return true;
        }

        protecting = heroes.getCharacter(toPick);
        protect(heroes.getCharacter(toPick));
        return false;
    }

    public boolean cannotAttack()
    {
        return super.cannotAttack() || healingLight > 0;
    }

    public void resetTurn()
    {
        super.resetTurn();
        if(protecting != null)
        {
            protecting.removeProtection();
        }
        if(healingLight > 0)
        {
            healingLight--;
        }
        protecting = null;
    }

    public void resetStats()
    {
        super.resetStats();
        resetTurn();
        healingLight = 0;
        
    }

    public static String Information()
    {
        return "Paladin: Paladins are hard to hit and can provide cover for their weaker allies. They can also heal and removes stun from their allies, but this leaves them weary for a few rounds";
    }

    public int strengthIncrease()
    {
        return 2;
    }

    public int dexterityIncrease()
    {
        return 1;
    }

    public int healthIncrease()
    {
        return 30;
    }
}

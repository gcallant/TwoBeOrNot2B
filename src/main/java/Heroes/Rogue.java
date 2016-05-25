package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

/**
 * Created by gm14793 on 5/9/16.
 */
public class Rogue extends A_Hero
{
    private SpecialManager specialManager;

    public Rogue(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Light, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new SneakAttack());
        specialManager.addSpecialAbility(new PoisonStrike());
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Rogue:\n" + SneakAttack.description() + "\n" + PoisonStrike.description();
    }

    public String getName()
    {
        return super.getName() + " the Rogue";
    }

    public int powerIncrease()
    {
        return 2;
    }

    public int cunningIncrease()
    {
        return 3;
    }

    public int healthIncrease()
    {
        return 15;
    }


}

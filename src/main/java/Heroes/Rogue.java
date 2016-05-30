package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.BackStab;
import SpecialAbilities.PoisonStrike;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

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
        specialManager.addSpecialAbility(new BackStab());
    }

    public static String Information()
    {
        return "Rogue:\n" + SneakAttack.description() + "\n" + PoisonStrike.description() + "\n" + BackStab.description();
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Rogue";
    }
}

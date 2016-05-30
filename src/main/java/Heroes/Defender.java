package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

/**
 * Created by Michael on 5/27/2016.
 */
public class Defender extends A_Hero
{
    private SpecialManager specialManager;

    public Defender(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Heavy, armor, WeaponType.Medium, weapon);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new DefendOther());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Defender";
    }

    public static String Information()
    {
        return "Defender:\n" + DefendOther.description();
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }
}
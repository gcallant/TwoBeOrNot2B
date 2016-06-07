package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.DefendOther;
import SpecialAbilities.SpecialManager;

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

    public static String Information()
    {
        return "Defender:\n" + DefendOther.description();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Defender";
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Defender)) { return false; }
        if(! super.equals(o)) { return false; }

        Defender defender = (Defender) o;

        if(specialManager != null ? ! specialManager.equals(defender.specialManager) : defender.specialManager != null)
        { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (specialManager != null ? specialManager.hashCode() : 0);
        return result;
    }
}
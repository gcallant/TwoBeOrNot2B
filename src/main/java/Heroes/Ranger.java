package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

/**
 * Created by Michael on 5/22/2016.
 */
public class Ranger extends A_Hero
{
    private SpecialManager specialManager;

    public Ranger(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Ranged, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new PiercingStrike());
        specialManager.addSpecialAbility(new NaturalRemedy());
        specialManager.addSpecialAbility(new EagleEye());
        specialManager.addSpecialAbility(new DoubleStrike());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Ranger:\n" + PiercingStrike.description() + "\n" + NaturalRemedy.description() + "\n" + EagleEye.description()
                + "\n" + DoubleStrike.description();
    }

    public String getName()
    {
        return super.getName() + " the Ranger";
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    public void endTurn()
    {
        conditions.decrementBadConditions();
        super.endTurn();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Ranger)) { return false; }
        if(! super.equals(o)) { return false; }

        Ranger ranger = (Ranger) o;

        if(specialManager != null ? ! specialManager.equals(ranger.specialManager) : ranger.specialManager != null)
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

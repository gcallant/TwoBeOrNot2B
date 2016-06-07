package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StealLife;
import SpecialAbilities.SummonCreature;

/**
 * Created by Michael on 5/25/2016.
 */
public class Summoner extends A_Hero
{
    private SpecialManager specialManager;

    public Summoner(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new SummonCreature());
        specialManager.addSpecialAbility(new StealLife());
    }

    public static String Information()
    {
        return "Summoner:\n" + SummonCreature.description() + "\n" + StealLife.description();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    public String getName()
    {
        return super.getName() + " the Summoner";
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Summoner)) { return false; }
        if(! super.equals(o)) { return false; }

        Summoner summoner = (Summoner) o;

        if(specialManager != null ? ! specialManager.equals(summoner.specialManager) : summoner.specialManager != null)
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

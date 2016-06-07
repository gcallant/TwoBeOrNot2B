package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StealLife;

/**
 * Created by Michael on 5/24/2016.
 */
public class LifeThief extends A_Hero
{
    private SpecialManager specialManager;
    public LifeThief(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Light, weapon);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new StealLife());
    }

    public static String Information()
    {
        return "Life Thief:\n" + StealLife.description() + "\n";
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Life Thief";
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof LifeThief)) { return false; }
        if(! super.equals(o)) { return false; }

        LifeThief lifeThief = (LifeThief) o;

        if(specialManager != null ? ! specialManager.equals(lifeThief.specialManager) : lifeThief.specialManager !=
                                                                                                null)
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
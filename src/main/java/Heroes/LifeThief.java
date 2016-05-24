package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.MeteorShower;
import SpecialAbilities.OwlsInsight;
import SpecialAbilities.SpecialManager;
import com.google.common.base.Objects;

/**
 * Created by Michael on 5/24/2016.
 */
public class LifeThief extends A_Hero
{
    SpecialManager specialManager;
    public LifeThief(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Light, weapon);

        specialManager = new SpecialManager();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Life Thief:\n";
    }

    public String getName()
    {
        return super.getName() + " the Life Thief";
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Mage)) { return false; }
        if(! super.equals(o)) { return false; }
        Mage mage = (Mage) o;
        return Objects.equal(specialManager, mage.specialManager);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), specialManager);
    }
}
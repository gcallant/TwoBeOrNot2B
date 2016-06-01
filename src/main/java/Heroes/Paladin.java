package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.Heal;
import SpecialAbilities.HealingLightHero;
import SpecialAbilities.Smite;
import SpecialAbilities.SpecialManager;

/**
 * Created by Michael on 5/18/2016.
 */
public class Paladin extends A_Hero
{
    private SpecialManager specialManager;

    public Paladin(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Heavy, armor, WeaponType.Medium, weapon);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new HealingLightHero());
        specialManager.addSpecialAbility(new Smite());
        specialManager.addSpecialAbility(new Heal());
    }

    public static String Information()
    {
        return "Paladin:\n" + HealingLightHero.description() + "\n" + Smite.description() + "\n" + Heal.description();
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Paladin";
    }

    public void upgradeAbilities()
    {
        specialManager.upgradeAbilities();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Paladin)) { return false; }
        if(! super.equals(o)) { return false; }

        Paladin paladin = (Paladin) o;

        if(specialManager != null ? ! specialManager.equals(paladin.specialManager) : paladin.specialManager != null)
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

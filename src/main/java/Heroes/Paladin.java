package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.HealingLight;
import SpecialAbilities.HealingLightHero;
import SpecialAbilities.Smite;
import SpecialAbilities.SpecialManager;
import com.google.common.base.Objects;

/**
 * Created by Michael on 5/18/2016.
 */
public class Paladin extends A_Hero
{
    private SpecialManager specialManager;

    public Paladin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Heavy, armor, WeaponType.Medium, weapon);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new HealingLightHero());
        specialManager.addSpecialAbility(new Smite());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public String getName()
    {
        return super.getName() + " the Paladin";
    }

    public static String Information()
    {
        return "Paladin:\n     - Healing Light: Heals all allies for a small amount and hurts enemies for half of that. It cures many bad conditions\n" +
                "     - Smite: Does strong damage to all undead but has little effect on others";
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

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Paladin)) { return false; }
        if(! super.equals(o)) { return false; }
        Paladin paladin = (Paladin) o;
        return Objects.equal(specialManager, paladin.specialManager);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), specialManager);
    }
}

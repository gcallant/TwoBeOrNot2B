package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.HealingLight;
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
        specialManager.addSpecialAbility(new HealingLight());
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
        return "Paladin: Paladins are hard to hit and can provide cover for their weaker allies. They can also heal and removes stun from their allies, but this leaves them weary for a few rounds";
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

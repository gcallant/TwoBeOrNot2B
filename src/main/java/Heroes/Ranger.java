package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.NaturalRemedy;
import SpecialAbilities.PiercingStrike;
import SpecialAbilities.SpecialManager;

/**
 * Created by Michael on 5/22/2016.
 */
public class Ranger extends A_Hero
{
    private SpecialManager specialManager;

    public Ranger(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Ranged, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new PiercingStrike());
        specialManager.addSpecialAbility(new NaturalRemedy());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Ranger:\n     - Piercing Strike: Hits the first target and then hits a random second target for increased damage\n" +
                "     - Natural Remedy: Grants the caster regeneration for a few rounds";
    }

    public String getName()
    {
        return super.getName() + " the Ranger";
    }

    public void endTurn()
    {
        conditions.decrementBadConditions();
        super.endTurn();
    }

    public int strengthIncrease()
    {
        return 3;
    }

    public int dexterityIncrease()
    {
        return 3;
    }

    public int healthIncrease()
    {
        return 25;
    }
}

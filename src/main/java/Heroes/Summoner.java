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

    public String getName()
    {
        return super.getName() + " the Summoner";
    }
}
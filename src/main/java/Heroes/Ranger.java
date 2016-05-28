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
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Ranged, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new PiercingStrike());
        specialManager.addSpecialAbility(new NaturalRemedy());
        specialManager.addSpecialAbility(new EagleEye());
        specialManager.addSpecialAbility(new Charge());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
        return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Ranger:\n" + PiercingStrike.description() + "\n" + NaturalRemedy.description() + "\n" + EagleEye.description()
                + "\n" + Charge.description();
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
}

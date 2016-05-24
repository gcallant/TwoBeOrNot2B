package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

/**
 * Created by gm14793 on 5/9/16.
 */
public class Rogue extends A_Hero
{
    private SpecialManager specialManager;

    public Rogue(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new SneakAttack());
        specialManager.addSpecialAbility(new PoisonStrike());
    }

    public boolean specialAbility(Party heroes, Party monsters)
    {
       return specialManager.chooseSpecialAbility(this, heroes, monsters);
    }

    public static String Information()
    {
        return "Rogue:\n     - Sneak Attack: Deals immense damage to foes who are at full health or who have bad conditions\n" +
                "     - Poison Strike: Deals no damage but puts a poison debuff on the target for a few rounds";
    }

    public String getName()
    {
        return super.getName() + " the Rogue";
    }

    public int strengthIncrease()
    {
        return 2;
    }

    public int dexterityIncrease()
    {
        return 3;
    }

    public int healthIncrease()
    {
        return 15;
    }


}

package Nemesis;

import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.BossDamageReduction;
import SpecialAbilities.Grapple;
import SpecialAbilities.PoisonCloud;
import SpecialAbilities.SpecialManager;
import Utilities.Display;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Trent extends A_Nemesis
{
    private SpecialManager specialManager;

    public Trent(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Heavy, weapon, level, CreatureType.Humanoid);
        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new Grapple());
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        specialManager.executeRandomAbility(this, monsters, heroes);
        if(rage)
        {
            new PoisonCloud().executeAbilityRandom(this, monsters, heroes);
        }
        return true;
    }

    public void startRage(Random rand, Party heroes, Party monsters)
    {
        cooldown = 1;
        Display.displayMessage("\n\nTHE TRENT'S RAZOR SHARP BARK HARDENS\n\n");
        new BossDamageReduction().executeAbilityRandom(this, heroes, monsters);
    }

    public void levelUp()
    {
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradePower();
        upgradePower();
        upgradePower();
        upgradePower();
    }
}

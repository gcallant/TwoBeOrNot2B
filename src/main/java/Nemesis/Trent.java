package Nemesis;

import BuffsAndDebuffs.UndeadConditions;
import Characters.A_Character;
import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Trent extends A_Nemesis
{
    SpecialManager specialManager;

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
            new PoisonCloud().executeAbilityRandom(this, heroes, monsters);
        }
        return true;
    }

    public void startRage(Random rand, Party heroes, Party monsters)
    {
        cooldown = 1;
        System.out.println("\n\nTHE TRENT'S RAZOR SHARP BARK HARDENS\n\n");
        new BossDamageReduction().executeAbilityRandom(this, heroes, monsters);
    }

    public void levelUp()
    {
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradepower();
        upgradepower();
        upgradepower();
        upgradepower();
    }
}

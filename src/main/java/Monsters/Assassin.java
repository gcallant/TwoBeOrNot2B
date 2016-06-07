package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.BladedFlurry;
import SpecialAbilities.PoisonStrike;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Assassin extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public Assassin(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Light, weapon, 6, level, CreatureType.Humanoid);

        specialManager = new SpecialManager();

        if(floor >= 3)
        {
            specialManager.addSpecialAbility(new BladedFlurry());
            specialManager.addSpecialAbility(new PoisonStrike());
        }
        else
        {
            specialManager.addSpecialAbility(new SneakAttack());
        }

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        specialManager.executeRandomAbility(this, monsters, heroes);
        return false;
    }

    public void levelUp()
    {
        upgradeCunning();
        upgradeCunning();
        upgradeCunning();
        upgradePower();
        upgradePower();
        upgradeHealth();
    }

    public int getLevel()
    {
        return 20*level;
    }
}

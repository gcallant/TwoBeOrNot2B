package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.HealingLight;
import SpecialAbilities.HealingLightHero;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by Michael on 5/22/2016.
 */
public class UndeadCleric extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public UndeadCleric(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon, 8, level, CreatureType.Undead);

        specialManager = new SpecialManager();

        if(floor >= 3)
        {
            specialManager.addSpecialAbility(new HealingLightHero());
        }
        else
        {
            specialManager.addSpecialAbility(new HealingLight());
        }

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        return specialManager.executeRandomAbility(this, monsters, heroes);
    }

    public void levelUp()
    {
        upgradePower();
        upgradePower();
        upgradeCunning();
        upgradeHealth();
        upgradeHealth();
    }
    public int getLevel()
    {
        return level*15;
    }
}

package Monsters;

import Characters.A_Monster;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

import java.util.*;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Assassin extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public Assassin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level)
    {
        super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Light, weapon, 6, level);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new SneakAttack());
        specialManager.addSpecialAbility(new PoisonStrike());

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        specialManager.executeRandomAbility(this, monsters, heroes);
        return false;
    }

    public void levelUp()
    {
        upgradeDexterity();
        upgradeDexterity();
        upgradeStrength();
        upgradeHealth();
    }

    public int getLevel()
    {
        return 20*level;
    }
}

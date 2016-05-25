package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.ViciousBite;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Greig on 5/19/2016.
 */
public class DireWolf extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public DireWolf(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Heavy, weapon, 6, level, CreatureType.Animal);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new ViciousBite());

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        specialManager.executeRandomAbility(this, monsters, heroes);
        return false;
    }

    public void levelUp()
    {
        upgradepower();
        upgradepower();
        upgradeHealth();
        upgradeHealth();
    }

    public int getLevel()
    {
        return 15*level;
    }
}
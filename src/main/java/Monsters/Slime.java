package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SlimeWave;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by Michael on 5/28/2016.
 */
public class Slime extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public Slime(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor) {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Medium, weapon, 8, level, CreatureType.Animal);

        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new SlimeWave());

        if(floor >= 3)
        {

        }

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters) {
        specialManager.executeRandomAbility(this, monsters, heroes);
        return false;
    }

    public void levelUp() {
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradeCunning();
        upgradeCunning();
        upgradeCunning();
        upgradeCunning();
    }

    public int getLevel() {
        return 20 * level;
    }
}

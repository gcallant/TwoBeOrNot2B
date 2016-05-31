package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.BarkSkin;
import SpecialAbilities.Heal;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by Michael on 5/25/2016.
 */
public class Sapling extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public Sapling(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor) {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Medium, weapon, 5, level, CreatureType.Undead);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new Heal());

        if(floor >= 3)
        {
            specialManager.addSpecialAbility(new BarkSkin());
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
    }

    public int getLevel() {
        return 17 * level;
    }
}

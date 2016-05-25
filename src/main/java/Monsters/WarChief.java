package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.IntimidatingShout;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StunningStrike;
import SpecialAbilities.WarCry;

import java.util.Random;

/**
 * Created by Michael on 5/22/2016.
 */
public class WarChief extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public WarChief(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Light, weapon, 7, level, CreatureType.Humanoid);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new WarCry());
        specialManager.addSpecialAbility(new IntimidatingShout());

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
        upgradepower();
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
    }

    public int getLevel()
    {
        return level*15;
    }
}

package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.HealingLight;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by Michael on 5/22/2016.
 */
public class UndeadCleric extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public UndeadCleric(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon, 8, level);

        specialManager = new SpecialManager();

        specialManager.addSpecialAbility(new HealingLight());

        this.level = level;
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        return specialManager.executeRandomAbility(this, monsters, heroes);
    }

    public void levelUp()
    {
        upgradeStrength();
        upgradeStrength();
        upgradeStrength();
    }
    public int getLevel()
    {
        return level*3;
    }
}

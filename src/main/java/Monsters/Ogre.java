package Monsters;
import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.NaturalRemedy;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StunningStrike;
import com.google.common.base.Objects;

import java.util.Random;


/**
 * Created by gm14793 on 5/9/16.
 */
public class Ogre extends A_Monster
{
    private SpecialManager specialManager;
    private int level;

    public Ogre(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Light, weapon, 4, level, CreatureType.Humanoid);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new StunningStrike());

        if(floor >= 3)
        {
            specialManager.addSpecialAbility(new NaturalRemedy());
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
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradeHealth();
        upgradePower();
        upgradePower();
        upgradePower();
        upgradeCunning();
        upgradeCunning();
    }

    public int getLevel()
    {
        return level*15;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Ogre)) { return false; }
        if(! super.equals(o)) { return false; }
        Ogre ogre = (Ogre) o;
        return level == ogre.level &&
                       Objects.equal(specialManager, ogre.specialManager);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), specialManager, level);
    }
}

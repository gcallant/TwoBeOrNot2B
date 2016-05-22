package Characters;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StunningStrike;

import java.util.Random;


/**
 * Created by gm14793 on 5/9/16.
 */
public class Ogre extends A_Monster
{
    private SpecialManager specialManager;

    public Ogre(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new StunningStrike());
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        specialManager.executeRandomAbility(this, monsters, heroes);
        return false;
    }

    public int getLevel()
    {
        return 10;
    }
}

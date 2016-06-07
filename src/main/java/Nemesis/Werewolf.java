package Nemesis;

import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.BloodSense;
import SpecialAbilities.MassBleed;
import SpecialAbilities.SpecialManager;
import Utilities.Display;

import java.util.Random;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Werewolf extends A_Nemesis
{
    private SpecialManager specialManager;
    public Werewolf(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Medium, weapon, level, CreatureType.Animal);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new BloodSense());
    }

    public void startRage(Random rand, Party heroes, Party monsters)
    {
        Display.displayMessage("\n\nTHE WEREWOLF HOWL PIERCES YOUR EARS\n\n");
        getConditions().giveRegenBuff(1.1, 5, "Self");
        new MassBleed().executeAbilityRandom(this, monsters, heroes);
        specialAbility(rand, heroes, monsters);

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
        upgradePower();
        upgradePower();
        upgradePower();
        upgradeCunning();
        upgradeCunning();
    }
}

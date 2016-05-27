package Nemesis;

import Characters.A_Character;
import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import Mediator.Mediator;
import PartyManagement.Party;
import SpecialAbilities.BloodSense;
import SpecialAbilities.SpecialManager;

import java.util.*;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Werewolf extends A_Nemesis
{
    SpecialManager specialManager;
    public Werewolf(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Medium, weapon, level, CreatureType.Animal);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new BloodSense());
    }

    public void startRage(Random rand, Party heroes, Party monsters)
    {
        System.out.println("\n\nTHE WEREWOLF HOWL PIERCES YOUR EARS\n\n");
        getConditions().giveRegenBuff(1.2, 5, "Self");
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
        upgradepower();
        upgradepower();
        upgradepower();
        upgradecunning();
        upgradecunning();
    }
}

package Nemesis;

import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;
import Utilities.Display;

import java.util.Random;

/**
 * Created by gm14793 on 5/22/16.
 */
public class MasterAssassin extends A_Nemesis
{
    private SpecialManager specialManager;
    public MasterAssassin(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon, level, CreatureType.Humanoid);

        specialManager = new SpecialManager();
        specialManager.addSpecialAbility(new BladedFlurry());
    }

    public void levelUp()
    {
        upgradeHealth();
        upgradeHealth();
        upgradeCunning();
        upgradeCunning();
        upgradeCunning();
        upgradePower();
        upgradePower();
    }

    public void startRage(Random rand, Party heroes, Party monsters)
    {
        Display.displayMessage("\n\nTHE ASSASSIN MOVES INTO THE SHADOWS\n\n");
        new PoisonBomb().executeAbilityRandom(this, monsters, heroes);
        new PoisonBomb().executeAbilityRandom(this, monsters, heroes);
        specialManager.executeRandomAbility(this, monsters, heroes);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        int count = 0;
        for(int x = 0; x < monsters.size(); x++)
        {
            if(monsters.getCharacter(x).getName().equals("Scarlett, The Shadow"))
            {
                count++;
            }
        }

        if(count == 1)
        {
            new Copy().executeAbilityRandom(this, monsters, heroes);
        }
        else
        {
            new PoisonStrike().executeAbilityRandom(this, monsters, heroes);
            specialManager.executeRandomAbility(this, monsters, heroes);
        }
        return false;
    }
}

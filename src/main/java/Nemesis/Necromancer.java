package Nemesis;

import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.SummonSkeleton;
import Utilities.Display;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Necromancer extends A_Nemesis
{
	private SpecialManager specialManager;

	public Necromancer(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
	{
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon, level, CreatureType.Humanoid);
		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new SummonSkeleton());
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		specialManager.executeRandomAbility(this, monsters, heroes);
		return true;
	}

	public void startRage(Random rand, Party heroes, Party monsters)
	{
		cooldown = 1;
		Display.displayMessage("\n\nTHE NECROMANCER IS SUMMONING THE DARK ARTS\n\n");
		specialAbility(rand, heroes, monsters);
		specialAbility(rand, heroes, monsters);
		specialAbility(rand, heroes, monsters);
	}

	public void levelUp()
	{
		upgradeHealth();
		upgradeHealth();
		upgradeHealth();
		upgradeHealth();
		upgradePower();
		upgradePower();
		upgradeCunning();
		upgradeCunning();
	}
}

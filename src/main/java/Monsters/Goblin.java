package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.PoisonBomb;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Goblin extends A_Monster
{
	private SpecialManager specialManager;
	private int level;

	public Goblin(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor)
	{
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Light, weapon, 7, level, CreatureType.Humanoid);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new SneakAttack());

		if(floor >= 3)
		{
			specialManager.addSpecialAbility(new PoisonBomb());
		}

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		return specialManager.executeRandomAbility(this, monsters, heroes);
	}

	public void levelUp()
	{
		upgradeCunning();
		upgradeHealth();
		upgradePower();
	}

	public int getLevel()
	{
		return level*5;
	}
}

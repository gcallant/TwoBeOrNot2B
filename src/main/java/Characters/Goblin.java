package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
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

	public Goblin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon, 7, level);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new SneakAttack());

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		return specialManager.executeRandomAbility(this, monsters, heroes);
	}

	public void levelUp()
	{
		upgradeDexterity();
		upgradeHealth();
		upgradeStrength();
	}

	public int getLevel()
	{
		return level*2;
	}
}

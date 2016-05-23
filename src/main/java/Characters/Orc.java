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
 * Created by SaraPage on 4/29/2016.
 */
public class Orc extends A_Monster
{
	private SpecialManager specialManager;
	private int level;

	public Orc(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon, 6, level);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new StunningStrike());

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		specialManager.executeRandomAbility(this, monsters, heroes);
		return false;
	}

	public void levelUp()
	{
		upgradeStrength();
		upgradeStrength();
		upgradeHealth();
	}

	public int getLevel()
	{
		return level*3;
	}
}

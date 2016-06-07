package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.FearsomeGaze;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.WarCry;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Skeleton extends A_Monster
{
	private SpecialManager specialManager;
	private int level;

	public Skeleton(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor) {
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Medium, weapon, 3, level, CreatureType.Undead);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new FearsomeGaze());

		if(floor >= 3)
		{
			specialManager.addSpecialAbility(new WarCry());
		}

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters) {
		specialManager.executeRandomAbility(this, monsters, heroes);
		return false;
	}

	public void levelUp() {
		upgradePower();
		upgradePower();
		upgradePower();
		upgradeHealth();
		upgradeHealth();
	}

	public int getLevel() {
		return 25 * level;
	}
}

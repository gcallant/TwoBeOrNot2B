package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.FearsomeGaze;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Skeleton extends A_Monster
{
	private SpecialManager specialManager;
	private int level;

	public Skeleton(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level) {
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Medium, weapon, 3, level, CreatureType.Undead);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new FearsomeGaze());;

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters) {
		specialManager.executeRandomAbility(this, monsters, heroes);
		return false;
	}

	public void levelUp() {
		upgradeStrength();
		upgradeStrength();
		upgradeStrength();
		upgradeHealth();
		upgradeHealth();
	}

	public int getLevel() {
		return 25 * level;
	}
}

package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import SpecialAbilities.SneakAttack;
import SpecialAbilities.SpecialManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Goblin extends A_Monster
{
	SpecialManager specialManager;
	public Goblin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new SneakAttack());
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		return specialManager.executeRandomAbility(this, monsters, heroes);
	}

	public int getLevel()
	{
		return 2;
	}
}

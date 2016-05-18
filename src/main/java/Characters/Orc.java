package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Orc extends A_Monster
{
	public Orc(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Medium, weapon);
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		stunningStrike(heroes.getCharacter(rand.nextInt(heroes.size())));
		return false;
	}
}

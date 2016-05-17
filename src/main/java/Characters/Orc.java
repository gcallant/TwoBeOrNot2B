package Characters;

import Item.Armor;
import Item.Weapon;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Orc extends A_Monster
{
	public Orc(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armor, weapon);
	}

	public boolean specialAttack(Random rand, Party heroes, Party monsters)
	{
		stunningStrike(heroes.getCharacter(rand.nextInt(heroes.size())));
		return false;
	}
}

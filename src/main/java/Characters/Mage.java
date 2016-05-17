package Characters;

import Item.Armor;
import Item.Weapon;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Mage extends A_Hero
{
	public Mage(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armor, weapon);
	}

	public boolean specialAttack(Party heroes, Party monsters)
	{
		magicStrike(heroes, monsters);
		return false;
	}
}

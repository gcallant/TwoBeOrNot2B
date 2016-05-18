package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Mage extends A_Hero
{
	public Mage(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Light, weapon);
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		magicStrike(heroes, monsters);
		return false;
	}
}

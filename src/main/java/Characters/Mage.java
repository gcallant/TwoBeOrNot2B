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
	public Mage(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		magicStrike(monsters);
		return false;
	}

	public static String Information()
	{
		return "Mage: Mages may be slow and easy to hit, but their meteor swarm which attacks all enemies is a sight to behold";
	}

	public int strengthIncrease()
	{
		return 2;
	}

	public int dexterityIncrease()
	{
		return 1;
	}

	public int healthIncrease()
	{
		return 10;
	}
}

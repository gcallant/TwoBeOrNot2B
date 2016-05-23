package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import SpecialAbilities.MagicBuff;
import SpecialAbilities.MeteorShower;
import SpecialAbilities.SpecialManager;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Mage extends A_Hero
{
	SpecialManager specialManager;
	public Mage(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new MagicBuff());
		specialManager.addSpecialAbility(new MeteorShower());
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		return specialManager.chooseSpecialAbility(this, heroes, monsters);
	}

	public static String Information()
	{
		return "Mage: Mages may be slow and easy to hit, but their meteor swarm which attacks all enemies is a sight to behold. They can also buff themselves with magic";
	}

	public String getName()
	{
		return super.getName() + " the mage";
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Mage mage = (Mage) o;

		if (buffed != mage.buffed) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + buffed;
		return result;
	}
}

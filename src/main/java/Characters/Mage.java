package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Mage extends A_Hero
{
	private int buffed;

	public Mage(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon);
		buffed = 0;
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		Scanner input = new Scanner(System.in);
		int toPick = -1;
		int specialAttack = -1;

		System.out.println("Choose which special attack to use:\n1) Magic Buff: increase your magic attack for a few turns\n2) Meteor Strike: An attack which hits all enemies\n3) Cancel");

		specialAttack = ensureInput(input, 3);

		switch(specialAttack)
		{
			case 1:
				buffed = Math.min(3,2*getLevel());
				magicBuff();
				break;
			case 2:
				meteorShower(monsters);
				break;
		}
		return false;
	}

	public void resetTurn()
	{
		super.resetTurn();
		buffed = Math.max(-1, buffed - 1);
		if(buffed == 0)
		{
			removeMagicBuff();
		}
	}

	public static String Information()
	{
		return "Mage: Mages may be slow and easy to hit, but their meteor swarm which attacks all enemies is a sight to behold. They can also buff themselves with magic";
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

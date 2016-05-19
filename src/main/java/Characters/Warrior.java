package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Warrior extends A_Hero
{
	private boolean exhausted;
	int shoutCount;
	boolean shout;

	public Warrior(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Heavy, weapon);
		exhausted = false;
		shoutCount = -1;
		shout = false;
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		Scanner input = new Scanner(System.in);
		int toPick = -1;
		int specialAttack = -1;

		System.out.println("Choose which special attack to use:\n1) Shout\n2) Stunning Strike\n3) Cancel");

		specialAttack = ensureInput(input, 3);

		switch(specialAttack)
		{
			case 1:
				if(shout || heroes.hasShouted())
				{
					System.out.println("An intimidating shout has already used this battle!");
					return true;
				}
				shoutCount = 2;
				heroes.shout();
				intimidatingShout(monsters);
				shout = true;
				return false;
			case 2:
				return stunAttack(input, monsters);
		}
		return true;
	}

	private boolean stunAttack(Scanner input, Party monsters)
	{
		int toPick = -1;
		int itemIndex = pickCharacter(monsters);
		System.out.println("Choose someone to use your stun attack on or " + itemIndex + " to cancel:");

		toPick = ensureInput(input, itemIndex) - 1;

		if(toPick == itemIndex - 1)
		{
			return true;
		}

		stunningStrike(monsters.getCharacter(toPick));
		exhausted = true;

		return false;
	}

	protected boolean cannotAttack()
	{
		return super.cannotAttack() || exhausted;
	}

	public void resetTurn(Party monsters)
	{
		super.resetTurn();
		if(shout)
		{
			shoutCount--;
		}
		if(shoutCount == 0)
		{
			resetShout(monsters);
		}
		exhausted = false;
	}

	public void resetStats()
	{
		super.resetStats();
		shoutCount = 0;
		shout = false;
	}

	public static String Information()
	{
		return "Warrior: Warriors hit hard and can stun their opponents with their mighty swings but doing so tires them out. They can also intimidate their foes once per a battle";
	}

	public int strengthIncrease()
	{
		return 4;
	}

	public int dexterityIncrease()
	{
		return 1;
	}

	public int healthIncrease()
	{
		return 25;
	}
}

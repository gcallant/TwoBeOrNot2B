package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import SpecialAbilities.IntimidatingShout;
import SpecialAbilities.SpecialAbility;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StunningStrike;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Warrior extends A_Hero
{
	SpecialManager specialManager;
	public Warrior(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Heavy, weapon);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new StunningStrike());
		specialManager.addSpecialAbility(new IntimidatingShout());

	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		return specialManager.chooseSpecialAbility(this, heroes, monsters);
		/*
		Scanner input = new Scanner(System.in);
		int toPick = -1;
		int specialAttack = -1;

		System.out.println("Choose which special attack to use:\n1) Shout: reduce all enemies strength for a few turns\n2) Stunning Strike: A strong attack with a chance to stun\n3) Cancel");

		specialAttack = ensureInput(input, 3);

		switch(specialAttack)
		{
			case 1:
				intimidatingShout(monsters);
				return false;
			case 2:
				return stunAttack(input, monsters);
		}
		return true;*/
	}
/*
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

		return false;
	}*/

	public void resetStats()
	{
		super.resetStats();
		conditions.recoverConditions();
	}

	public String getName()
	{
		return super.getName() + " the Warrior";
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Warrior warrior = (Warrior) o;

		if (exhausted != warrior.exhausted) return false;
		if (shoutCount != warrior.shoutCount) return false;
		if (shout != warrior.shout) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (exhausted ? 1 : 0);
		result = 31 * result + shoutCount;
		result = 31 * result + (shout ? 1 : 0);
		return result;
	}
}

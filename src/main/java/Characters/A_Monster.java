package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Monster extends A_Character
{
	protected String name;
	protected Random rand;

	public A_Monster(String name, int health, int strength, int dexterity, ArmorType armorType, Armor armor, WeaponType weaponType, Weapon weapon)
	{
		super(name, health, strength, dexterity, armorType, armor, weaponType, weapon);
		rand = new Random();
	}

	public void resetTurn()
	{
		super.resetTurn();
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		boolean noTurn;

		noTurn = cannotAttack();
		resetTurn();

		if(noTurn)
		{
			System.out.println(getName() + " is incapacitated and can't act!");
			return false;
		}

		int choiceToAttack = rand.nextInt(heroes.size());

		if(rand.nextBoolean())
		{
			specialAbility(rand, heroes, monsters);
		}
		else
		{
			A_Character toAttack = heroes.getCharacter(choiceToAttack);
			attack(toAttack);
		}
		return false;
	}

	protected boolean cannotAttack()
	{
		return super.cannotAttack();
	}

	public abstract boolean specialAbility(Random rand, Party heroes, Party monsters);

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		A_Monster a_monster = (A_Monster) o;

		if (!name.equals(a_monster.name)) return false;

		return true;
	}

}

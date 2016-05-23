package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Monster extends A_Character
{
	protected String name;
	private int percentageOfSpecial;
	protected Random rand;

	public A_Monster(String name, int health, int strength, int dexterity, ArmorType armorType, Armor armor, WeaponType weaponType, Weapon weapon, int percentageOfSpecial, int level)
	{
		super(name, health, strength, dexterity, armorType, armor, weaponType, weapon);
		rand = new Random();
		this.percentageOfSpecial = percentageOfSpecial - 1;
		int curLevel = 1;

		while(curLevel < level)
		{
			levelUp();
			curLevel++;
		}
	}

	public void resetTurn()
	{
		super.resetTurn();
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		boolean noTurn, noSpecial, useSpecial;

		noTurn = conditions.cannotAttack();
		noSpecial = conditions.cannotUseSpecial();
		resetTurn();

		useSpecial = (rand.nextInt(10) < percentageOfSpecial);
		if(noTurn)
		{
			System.out.println(getName() + " is stunned and can't act!");
			endTurn();
			return false;
		}
		if(noSpecial)
		{
			useSpecial = false;
		}


		int choiceToAttack = rand.nextInt(heroes.size());

		if(useSpecial)
		{
			specialAbility(rand, heroes, monsters);
		}
		else
		{
			A_Character toAttack = heroes.getCharacter(choiceToAttack);
			attack(toAttack);
		}
		endTurn();
		return false;
	}

	protected void levelUp(){};

	public abstract boolean specialAbility(Random rand, Party heroes, Party monsters);
}

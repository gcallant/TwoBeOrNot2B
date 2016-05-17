package Characters;

import Item.Armor;
import Item.Weapon;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Monster extends A_Character
{
	protected String name;
	protected Random rand;

	public A_Monster(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armor, weapon);
		rand = new Random();
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		if(isStunned())
		{
			System.out.println(getName() + " is stunned and can't act!");
			removeStun();
			return false;
		}

		int choiceToAttack = rand.nextInt(heroes.getParty().size());

		if(rand.nextBoolean())
		{
			specialAttack(rand, heroes, monsters);
		}
		else
		{

			A_Character toAttack = heroes.getParty().get(choiceToAttack);

			String toPrint;
			toPrint = this.getName();
			toPrint = toPrint + " attacks ";
			toPrint = toPrint + toAttack.getName();
			System.out.println(toPrint);

			attack(toAttack);
		}
		return false;
	}

	public abstract boolean specialAttack(Random rand, Party heroes, Party monsters);
}

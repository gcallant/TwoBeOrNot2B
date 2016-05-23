package Characters;

import Item.*;
import PartyManagement.Party;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */

public abstract class A_Hero extends A_Character
{
	public A_Hero(String name, int health, int strength, int dexterity, ArmorType armorType, Armor armor, WeaponType weaponType, Weapon weapon)
	{
		super(name, health, strength, dexterity, armorType, armor, weaponType, weapon);
	}

	public abstract boolean specialAbility(Party heroes, Party monsters);


	public boolean takeAction(Party heroes, Party monsters)
	{
		Scanner input = new Scanner(System.in);
		int choice;
		boolean cancel, noTurn, noSpecial;

		noTurn = conditions.cannotAttack();
		noSpecial = conditions.cannotUseSpecial();
		resetTurn();

		if(noTurn)
		{
			endTurn();
			return false;
		}

		monsters.sortDefeated();
		heroes.sortDefeated();

		do
		{
			cancel = false;
			System.out.println("It's " + getName() + "'s turn!\nYou have " + getHealth() + " HP\nChoose an action:");
			System.out.print("1.) Attack\n" +
					                   "2.) Defend\n" +
					                   "3.) Use Special\n" +
					                   "4.) Use Item\n" +
					                   "5.) Run\n");
			choice = ensureInput(input, 5);
			switch(choice)
			{
				case 1:
					cancel = doAttack(heroes, monsters, input);
					break;
				case 2:
					conditions.defend();
					break;
				case 3:
					if(noSpecial)
					{
						System.out.println(getName() + " is exhausted and can't use their special abilities!");
						cancel = true;
					}
					else
					{
						cancel = specialAbility(heroes, monsters);
					}
					break;
				case 4:
					cancel = heroes.consumePotion();
					break;
				case 5:
					return tryToRun(heroes, monsters);
			}

		}
		while(choice < 1 || choice > 5 || cancel);
		endTurn();
		return false;
	}

	protected int pickCharacter(Party party)
	{
		int index = 1;
		for(int x = 0; x < party.size(); x++)
		{
			System.out.println(index + ".)" + party.getCharacter(x).battleDisplay());
			index++;
		}
		return index;
	}

	protected int ensureInput(Scanner input, int itemIndex)
	{
		String validInput;
		int toUse = -1;
		do {
			validInput = input.nextLine();

			try
			{
				toUse = Integer.parseInt(validInput);
			}
			catch (NumberFormatException e)
			{
				toUse = -1;
			}

		}while(toUse < 1 || toUse > itemIndex);
		return toUse;
	}

	private boolean tryToRun(Party heroes, Party monsters)
	{
		System.out.println("You attempt to run!");
		int chanceToRun = rand.nextInt(3*monsters.size()/heroes.size());
		if(chanceToRun == 0)
		{
			System.out.println("You successfully escaped!");
			return true;
		}
		System.out.println("But they caught you!");
		return false;
	}

	private boolean doAttack(Party heroes, Party monsters, Scanner input)
	{
		int index = 1;
		int toAttack;
		for(int x = 0; x < monsters.size(); x++)
		{
			System.out.println(index + ".) " + monsters.getCharacter(x).battleDisplay());
			index++;
		}

		System.out.println("Choose a monster to attack or enter " + (index) + " to cancel attack");
		toAttack = ensureInput(input, index);

		if(toAttack >= index)
		{
			return true;
		}
		else
		{
			this.attack(monsters.getCharacter(toAttack - 1));
			monsters.sortDefeated();
		}
		return false;
	}

	public void gainExperience(int experience)
	{
		super.gainExperience(experience);
		System.out.println(getName() + " gained " + experience +" experience!");
	}

	public boolean canLevel()
	{
		if(super.canLevel())
		{
			System.out.println(getName() + " leveled up!");
		}
		return super.canLevel();
	}
}


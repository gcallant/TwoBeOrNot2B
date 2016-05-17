package Characters;

import Item.Armor;
import Item.Consumable;
import Item.Storable;
import Item.Weapon;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Hero extends A_Character
{
	public A_Hero(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armor, weapon);
	}

	public abstract boolean specialAttack(Party heroes, Party monsters);

	public boolean takeAction(Party heroes, Party monsters)
	{
		Scanner input = new Scanner(System.in);
		int choice;
		boolean cancel;
		this.endDefend();

		if(isStunned())
		{
			System.out.println(getName() + " is stunned and can't act!");
			removeStun();
			return false;
		}

		do
		{
			cancel = false;
			System.out.println("It's " + getName() + "'s turn!\nYou have " + health + " HP\nChoose an action:");
			System.out.print("1.) Attack\n" +
					                   "2.) Defend\n" +
					                   "3.) Use Special\n" +
					                   "4.) Use Item\n" +
					                   "5.) Run\n");
			choice = input.nextInt();
			switch(choice)
			{
				case 1:
					cancel = doAttack(heroes, monsters, input);
					break;
				case 2:
					this.defend();
					break;
				case 3:
					cancel = specialAttack(heroes, monsters);
					break;
				case 4:
					cancel = useConsumable(heroes, monsters, input);
					break;
				case 5:
					return tryToRun(heroes, monsters);
			}

		}
		while(choice < 1 || choice > 5 || cancel);
		return false;
	}

	protected int pickCharacter(Party party)
	{
		int index = 1;
		for(A_Character character : party.getParty())
		{
			System.out.println(index + ".)" + character.toString());
			index++;
		}
		return index;
	}

	private boolean useConsumable(Party heroes, Party monsters, Scanner input)
	{
		int itemIndex = 1;
		String validInput;
		int toUse;
		for(Consumable item : heroes.getConsumables())
		{
			System.out.println(itemIndex + ".)" + item.toString());
			itemIndex++;
		}

		System.out.println("Choose an item to use or enter " + itemIndex + " to cancel item use.");

		toUse = ensureInput(input, itemIndex) - 1;

		if(toUse == itemIndex)
		{
			return true;
		}

		int toPick = -1;
		itemIndex = pickCharacter(heroes);

		System.out.println("Choose someone to use the " + heroes.getConsumable(toUse) + " on or " + itemIndex + " to cancel:");

		toPick = ensureInput(input, itemIndex) - 1;

		if(toPick == itemIndex)
		{
			return true;
		}

		heroes.useConsumable(heroes.getParty().get(toPick), toUse);
		return false;
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
		int chanceToRun = rand.nextInt(3*monsters.getParty().size()/heroes.getParty().size());
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
		for(A_Character monster : monsters.getParty())
		{
			if(!monster.getDefeated())
			{
				System.out.println(index + ".) " + monster.toString());
				index++;
			}
		}
		System.out.println("Choose a monster to attack or enter " + (index) + " to cancel attack");
		toAttack = input.nextInt();
		if(toAttack == index)
		{
			return true;
		}
		else
		{
			this.attack(monsters.getParty().get(toAttack - 1));
			monsters.sortDefeated();
		}
		return false;
	}

}


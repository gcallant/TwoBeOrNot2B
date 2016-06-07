package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Hero extends A_Character
{
	public A_Hero(String name, int health, int power, int cunning, ArmorType armorType,
				  Armor armor, WeaponType weaponType, Weapon weapon)
	{
		super(name, health, power, cunning, armorType, armor, weaponType, weapon, CreatureType.Humanoid);
	}

	public abstract boolean specialAbility(Party heroes, Party monsters);


	public boolean takeAction(Party heroes, Party monsters)
	{
		int choice;
		boolean cancel, noTurn, noSpecial;

		noTurn = conditions.cannotAttack();
		noSpecial = conditions.cannotUseSpecial();

		if(conditions.confusedEffect(this, heroes, monsters))
		{
			noTurn = true;
		}

		if(getDefeated())
		{
			return false;
		}

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
			Display.displayMessage("It's " + getName() + "'s turn!\n" + battleDisplay());
			System.out.print("1.) Attack\n" +
					                   "2.) Defend\n" +
					                   "3.) Use Special\n" +
					                   "4.) Use Item\n" +
									   "5.) View Stats\n" +
					                   "6.) Run\n");
			choice = TestString.ensureInt(7);
			switch(choice)
			{
				case 1:
					cancel = doAttack(heroes, monsters);
					break;
				case 2:
					conditions.defend();
					break;
				case 3:
					if(noSpecial)
					{
						Display.displayMessage(getName() + " is exhausted and can't use their special abilities!");
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
					displayPartyStats(heroes);
					cancel = true;
					break;
				case 6:
					return tryToRun(heroes, monsters);
				case 7:
					dealLotsOfDamage(monsters);
					break;
			}

		}
		while(choice < 1 || choice > 7 || cancel);
		endTurn();
		return false;
	}

	private void dealLotsOfDamage(Party party)
	{
		for(A_Character character : party)
		{
			if(!character.getDefeated())
			{
				character.takeDamage(100);
			}
		}
	}

	private void displayPartyStats(Party party)
	{
		party.displayStats();
	}

	protected int pickCharacter(Party party)
	{
		int index = 1;
		for(int x = 0; x < party.size(); x++)
		{
			Display.displayMessage(index + ".)" + party.getCharacter(x).battleDisplay());
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
		Display.displayMessage("You attempt to run!");
		int chanceToRun = 0;//rand.nextInt(3*monsters.size()/heroes.size());
		if(chanceToRun == 0)
		{
			Display.displayMessage("You successfully escaped!");
			heroes.fixParty();
			return true;
		}
		Display.displayMessage("But they caught you!");
		return false;
	}

	private boolean doAttack(Party heroes, Party monsters)
	{
		int index = 1;
		int toAttack;
		for(int x = 0; x < monsters.size(); x++)
		{
			Display.displayMessage(index + ".) " + monsters.getCharacter(x).battleDisplay());
			index++;
		}

		Display.displayMessage(index + ".) cancel");
		toAttack = TestString.ensureInt(index);

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
		Display.displayMessage(getName() + " gained " + experience +" experience!");
		super.gainExperience(experience);
	}

	public boolean canLevel()
	{
		if(super.canLevel())
		{
			Display.displayMessage(getName() + " leveled up!");
		}
		return super.canLevel();
	}
}


package Characters;

import Item.Consumable;

import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Hero extends A_Character
{
	public A_Hero(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, newArmor);
	}

	public void takeAction(Party hero, Party monsters)
	{
		Scanner input = new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("Choose an action:");
			System.out.print("1.) Attack\n" +
					                   "2.) Defend\n" +
					                   "3.) Use Special\n" +
					                   "4.) Use Item\n" +
					                   "5.) Run\n");
			choice = input.nextInt();
			switch(choice)
			{
				case 1:
					int index = 1;
					int toAttack;
					for(A_Character monster : monsters.getParty())
					{
						System.out.println(index + ".) " + monster.toString());
						index++;
					}
					System.out.println("Choose a monster to attack or enter " + (index) + " to cancel attack");
					toAttack = input.nextInt();
					if(toAttack == index)
					{
						break;
					}
					else
					{
						this.attack(monsters.getParty().get(toAttack - 1));
					}
					break;
				case 2:
					this.defend();
					break;
				case 3:
					System.out.println("Special attacks not currently available.");
					break;
				case 4:
					int itemIndex = 1;
					for(Consumable item : this.usables)
					{
						System.out.println(itemIndex + ".)" + item.toString());
						itemIndex++;
					}

					System.out.println("Choose an item to use or enter " + itemIndex + " to cancel item use.");
					break;
				case 5:
					System.out.println("Run not available");
					break;
			}

		}
		while(choice < 1 || choice > 5);
	}

}


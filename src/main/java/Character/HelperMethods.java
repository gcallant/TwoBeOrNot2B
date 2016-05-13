package Character;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */

public class HelperMethods
{
	public static boolean isValidName(String name)
	{
		char[] characters = name.toCharArray();

		for(int i = 0; i < characters.length; i++)
		{
			if(! Character.isAlphabetic(characters[i]))
			{
				return false;
			}
		}
		return true;
	}//end isValidName method

	public static String isValidInteger(String numAsInteger)
	{
		char[] characters = numAsInteger.toCharArray();

		for(int i = 0; i < characters.length; i++)
		{
			if(! Character.isDigit(characters[i]))
			{
				return null;
			}
		}
		return numAsInteger;
	}//end isValidInteger method

	private static int getValidNumber(Scanner kb, String question, String typeOfCharacter)
	{
		String num;
		do
		{
			System.out.printf(question, typeOfCharacter);
			num = kb.nextLine();
		}
		while(HelperMethods.isValidInteger(num) == null);
		return Integer.parseInt(num);
	}//end getValidNumber

	private static String getValidName(Scanner kb)
	{
		String name;
		do
		{
			System.out.print("Please enter the name: ");
			name = kb.nextLine();
		}
		while(! HelperMethods.isValidName(name));
		return name;
	}//end getName

	public static int selectPartyMenu(Scanner kb)
	{
		String choice;
		int finalChoice;

		do
		{
			System.out.print(getOptionsForParty(kb));
			choice = kb.nextLine();
			if(HelperMethods.isValidInteger(choice) == null)
			{
				finalChoice = 0;
			}
			else
			{
				finalChoice = Integer.parseInt(choice);
			}
		}
		while(finalChoice < 1 || finalChoice > 2);
		return finalChoice;
	}//end selectPartyMenu

	private static String getOptionsForParty(Scanner kb)
	{
		String result = "\n\n\n***** PARTY SELECTION MENU *****\n\n\n";
		result += "1. Create a party of Heros\n2. Create a party of Monsters\n\n";
		result += "Please enter your desired choice: ";
		return result;
	}//end getOptionsForParty

	public static void executePartyMenuChoice(Scanner kb, int choice, CharacterFactory factory)
	{
		int numberOfHeros, numberOfMonsters;
		ArrayList<A_Character> listOfHeroes, listOfMonsters;
		switch(choice)
		{
			case 1:
				System.out.println("The user will now create a party of heroes");
				numberOfHeros = getValidNumber(kb, "\n\nPlease enter the number of %s you want: ", "heroes");
				listOfHeroes = createListOfCharacters(factory, numberOfHeros, kb);
				System.out.println(listOfHeroes);
				break;

			default:
				System.out.println("The user will now create a party of monsters");
				numberOfMonsters = getValidNumber(kb, "\n\nPlease enter the number of %s you want: ",
				                                  "monsters");
				listOfMonsters = createListOfCharacters(factory, numberOfMonsters, kb);
				System.out.println(listOfMonsters);
				break;
		}//end switch statement
	}//end executePartyMenuChoice

	private static ArrayList<A_Character> createListOfCharacters(CharacterFactory factory, int numberInParty, Scanner
			                                                                                                            kb)
	{
		ArrayList<A_Character> list = new ArrayList<A_Character>(numberInParty);
		String name;
		int health, strength, dexterity, speed, armor;

		for(int i = 0; i < numberInParty; i++)
		{
			System.out.printf("\n\nEnter the information for character #%d: \n", i + 1);
			name = getValidName(kb);
			health = getValidNumber(kb, "Please enter the %s: \n", "health");
			strength = getValidNumber(kb, "Please enter the %s: \n", "strength");
			dexterity = getValidNumber(kb, "Please enter the %s: \n", "dexterity");
			speed = getValidNumber(kb, "Please enter the %s: \n", "speed");
			armor = getValidNumber(kb, "Please enter the %s: \n", "armor");

			list.add(factory.createCharacter(name, health, strength, dexterity, speed, armor));
		}//end for loop

		return list;
	}//end createListOfCharacters

}//end class

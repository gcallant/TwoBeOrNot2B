package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Goblin extends A_Monster
{
	public Goblin(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, ArmorType armorType, Armor armor, Weapon weapon)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, armorType, armor, WeaponType.Light, weapon);
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		ArrayList<Integer> possibleChoices = new ArrayList<Integer>();
		int count = 0;
		for(A_Character character : heroes.getParty())
		{
			if(character.getHealth() == character.getMaxHealth())
			{
				possibleChoices.add(count);
			}
			count++;
		}
		int choice;
		if(possibleChoices.size() > 0)
		{
			choice = possibleChoices.get(rand.nextInt(possibleChoices.size()));
			sneakAttack(heroes.getCharacter(choice));
		}
		else
		{
			sneakAttack(heroes.getCharacter(rand.nextInt(heroes.size())));
		}
		return false;
	}
}

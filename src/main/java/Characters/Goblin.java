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
	public Goblin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		ArrayList<Integer> possibleChoices = new ArrayList<Integer>();
		int count = 0;
		for(int x = 0; x < heroes.size(); x++)
		{
			A_Character character = heroes.getCharacter(x);
			if(character.getHealth() == character.getMaxHealth() || character.cannotAttack())
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

	public int getLevel()
	{
		return 2;
	}
}

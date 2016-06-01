package Factories;

import Characters.A_Character;
import Heroes.*;
import Item.*;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class HeroFactory
{
	private HeroFactory()
	{
	}

	public static A_Character createCharacter(String type, String name)
	{
		switch(type)
		{
			case "Warrior":
				return new Warrior(name, 250, 10, 5, new Leather(1), new Hammer(1));
			case "Mage":
				return new Mage(name, 180, 5, 6, new Cloth(1), new Staff(1));
			case "Rogue":
				return new Rogue(name, 200, 6, 10, new Leather(1), new Dagger(1));
			case "Paladin":
				return new Paladin(name, 300, 8, 4, new Chainmail(1), new Sword(1));
			case "Ranger":
				return new Ranger(name, 200, 8, 9, new Leather(1), new Bow(1));
			case "Summoner":
				return new Summoner(name, 150, 3, 7, new Cloth(1), new Staff(1));
			case "Defender":
				return new Defender(name, 300, 7, 7, new Chainmail(1), new Sword(1));
		}
		return createCharacter("Warrior", "Bob");
	}
}

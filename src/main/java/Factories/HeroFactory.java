package Factories;
import Characters.*;
import Item.*;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class HeroFactory
{
	public HeroFactory()
	{
	}

	public A_Character createCharacter(String type, String name, int health, int strength, int dexterity, int speed)
	{
		if(type.equalsIgnoreCase("Warrior"))
		{
			return new Warrior(name, 250, 10, 5, 4, new Chainmail(1), new Sword(1));
		}

		else if(type.equalsIgnoreCase("Mage"))
		{
			return new Mage(name, 180, 5, 6, 6, new Chainmail(1), new Staff(1));
		}

		else if(type.equalsIgnoreCase("Rogue"))
		{
			return new Rogue(name, 200, 6, 10, 8, new Chainmail(1), new Dagger(1));
		}
		return createCharacter("Warrior", "Bob", health, strength, dexterity, speed);
	}
}

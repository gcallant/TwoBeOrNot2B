package Factories;
import Characters.*;
import Item.*;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class HeroFactory
{
	public HeroFactory()
	{
	}

	public A_Character createCharacter(String type, String name) {
		if (type == null || name == null) {
			throw new NullPointerException("Your type of hero or name of the hero is invalid.");
		}
		switch (type) {
			case "Warrior":
				return new Warrior(name, 250, 10, 5, new Leather(1), new Hammer(1));
			case "Mage":
				return new Mage(name, 180, 5, 6, new Cloth(1), new Staff(1));
			case "Rogue":
				return new Rogue(name, 200, 6, 10, new Leather(1), new Dagger(1));
			case "Paladin":
				return new Paladin(name, 300, 8, 4, new Chainmail(1), new Sword(1));
			case "Ranger":
				return new Ranger(name, 200, 8, 8, new Leather(1), new Bow(1));
			default:
				throw new IllegalArgumentException("This is an invalid hero. Cannot create it.");

		}
	}
}

package Characters;

import Item.*;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class CharacterFactory
{
	public CharacterFactory()
	{
	}

	public A_Character createCharacter(String name, int health, int strength, int dexterity)
	{
		if(name.equals("Warrior"))
		{
			return new Warrior(name, health, strength, dexterity, new Chainmail(1), new Sword(1));
		}

		else if(name.equals("Mage"))
		{
			return new Mage(name, health, strength, dexterity, new Chainmail(1), new Staff(1));
		}

		else if(name.equals("Goblin"))
		{
			return new Goblin(name, health, strength, dexterity, new Chainmail(1), new Dagger(1));
		}

		else
		{
			return new Orc(name, health, strength, dexterity, new Chainmail(1), new Hammer(1));
		}
	}
}

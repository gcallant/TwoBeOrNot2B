package Character;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class CharacterFactory
{
	public CharacterFactory()
	{
	}

	public A_Character createCharacter(String name, int health, int strength, int dexterity, int speed, int armor)
	{
		if(name.equals("Warrior"))
		{
			return new Warrior(name, health, strength, dexterity, speed, armor);
		}

		else if(name.equals("Mage"))
		{
			return new Mage(name, health, strength, dexterity, speed, armor);
		}

		else if(name.equals("Goblin"))
		{
			return new Goblin(name, health, strength, dexterity, speed, armor);
		}

		else
		{
			return new Orc(name, health, strength, dexterity, speed, armor);
		}
	}
}

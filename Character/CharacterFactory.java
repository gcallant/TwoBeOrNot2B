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
		if(name.equals("ConcreteHeroA"))
		{
			return new ConcreteHeroA(name, health, strength, dexterity, speed, armor);
		}

		else if(name.equals("ConcreteHeroB"))
		{
			return new ConcreteHeroB(name, health, strength, dexterity, speed, armor);
		}

		else if(name.equals("ConcreteMonsterA"))
		{
			return new ConcreteMonsterA(name, health, strength, dexterity, speed, armor);
		}

		else
		{
			return new ConcreteMonsterB(name, health, strength, dexterity, speed, armor);
		}
	}
}

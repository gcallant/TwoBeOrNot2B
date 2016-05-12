package Character;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class HeroFactory
{
	public HeroFactory()
	{
	}

	public A_Hero createCharacter(String name)
	{
		if(name.equalsIgnoreCase("Warrior"))
		{
			return new Warrior(name, 250, 10, 5, 4, 13);
		}

		else if(name.equalsIgnoreCase("Mage"))
		{
			return new Mage(name, 180, 5, 6, 6, 8);
		}

		else if(name.equalsIgnoreCase("Rogue"))
		{
			return new Rogue(name, 200, 7, 7, 8, 10);
		}
		return createCharacter("Warrior");
	}
}

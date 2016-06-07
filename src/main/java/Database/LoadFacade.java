package Database;

import Characters.A_Character;
import Exceptions.DatabaseManagerException;
import GameState.Mediator;
import Heroes.*;
import Item.*;
import PartyManagement.Inventory;
import PartyManagement.Party;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Grant Callant on 5/30/2016.
 *
 * @author Grant Callant
 */
public class LoadFacade
{
	private static final Logger            logger    = LoggerFactory.getLogger("LoadFacade");
	private static       List<A_Character> heroes    = null;
	private static       Inventory         inventory = null;
	private static       Mediator          mediator  = null;
	private static int floor;
	private        int currentHealth;

	LoadFacade(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}

	@Nullable
	static A_Character makeCharacterFromLoad(String name, int health, int power, int cunning, int armor, int weapon)
	throws DatabaseManagerException
	{
		String actualName = name.substring(0, name.indexOf(" the"));
		int the = name.indexOf("the ") + 4;
		String type = name.substring(the, name.length());
		switch(type)
		{
			case "Warrior":
				return new Warrior(actualName, health, power, cunning, new Leather(armor), new Hammer(weapon));
			case "Mage":
				return new Mage(actualName, health, power, cunning, new Cloth(armor), new Staff(weapon));
			case "Rogue":
				return new Rogue(actualName, health, power, cunning, new Leather(armor), new Dagger(weapon));
			case "Paladin":
				return new Paladin(actualName, health, power, cunning, new Chainmail(armor), new Sword(weapon));
			case "Ranger":
				return new Ranger(actualName, health, power, cunning, new Leather(armor), new Bow(weapon));
			case "Summoner":
				return new Summoner(actualName, health, power, cunning, new Cloth(armor), new Staff(weapon));
			case "Defender":
				return new Defender(actualName, health, power, cunning, new Chainmail(armor), new Sword(weapon));
		}
		throw new DatabaseManagerException().notLoaded("Could not find character type to load", null);
	}

	static void createPartyToLoad() throws DatabaseManagerException
	{
		logger.info("Creating new party to load");
		Party party = new Party(heroes, inventory, floor);
		logger.info("Party created- now handing it off to mediator");
		mediator.receiveCurrentLevel(floor);
		mediator.receiveParty(party);
	}

	public static List getListFromBlob(InputStream blob) throws IOException, ClassNotFoundException, SQLException
	{
		ObjectInputStream objectInputStream = new ObjectInputStream(blob);
		//		ByteArrayInputStream byteArrayInputStream = null;
		//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
		//		byte [] buffer = new byte[1024];
		//		while(objectInputStream.read(buffer) > 0)
		//		{
		//		 	objectOutputStream.write(buffer);
		//		}
		List list = (List) objectInputStream.readObject();
		objectInputStream.close();
		return list;
	}

	static void setInventory(Inventory inv)
	{
		if(inv != null)
		{
			inventory = inv;
		}
	}

	public static void setLevelOfHeroes(int level)
	{
		if(heroes != null)
		{
			for(A_Character hero : heroes)
			{
				hero.setLevel(level);
			}
		}
	}

	public static void setExperienceOfHeroes(int experience)
	{
		if(heroes != null)
		{
			for(A_Character hero : heroes)
			{
				hero.setExperience(experience);
			}
		}
	}

	void setHeroes(List<A_Character> heroes)
	{
		if(heroes != null && heroes.size() > 0)
		{
			this.heroes = heroes;
		}
	}

	public void setFloor(int floor)
	{
		if(floor > 0)
		{
			this.floor = floor;
		}
	}

	public void setCurrentHealth(int currentHealth)
	{
		if(heroes != null)
		{
			for(A_Character hero : heroes)
			{
				hero.setHealth(currentHealth);
			}
		}
	}
}

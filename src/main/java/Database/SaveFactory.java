package Database;

import Characters.A_Character;
import Characters.A_Hero;
import Exceptions.DatabaseManagerException;
import GameState.Mediator;
import Heroes.*;
import Item.*;
import PartyManagement.Inventory;
import PartyManagement.Party;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Grant Callant on 5/21/2016.
 *
 * @author Grant Callant
 */
class SaveFactory
{
	private static A_Character[] heroes    = null;
	private static Inventory     inventory = null;
	private static Mediator      mediator  = null;
	private static int floor;

	SaveFactory(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}

	static A_Character[] getPartyToSave(Mediator mediator, int[] level)
	{
		Party party = mediator.giveParty();
		level[0] = party.getFloorLevel() + 1; //Because we save before starting next level
		A_Character[] heroes = new A_Hero[4];
		for(int i = 0; i < heroes.length; i++)
		{
			heroes[i] = party.getCharacter(i);
		}
		return heroes;
	}

	static void setInventory(Inventory inv)
	{
		if(inv != null)
		{
			inventory = inv;
		}
	}

	static void createPartyToLoad() throws DatabaseManagerException
	{
		List<A_Character> heroList = Arrays.asList(heroes);
		Party party = new Party(heroList, inventory, floor);
		mediator.receiveParty(party);
	}

	@Nullable
	static A_Character makeCharacterFromLoad(String name, int health, int power, int cunning, int armor, int weapon)
	{
		String actualName = name.substring(0, name.indexOf(" the"));
		int the = name.indexOf("the ") + 4;
		String type = name.substring(the, name.length());
		switch(type)
		{
			case "Warrior":
				return new Warrior(name, health, power, cunning, new Leather(armor), new Hammer(weapon));
			case "Mage":
				return new Mage(name, health, power, cunning, new Cloth(armor), new Staff(weapon));
			case "Rogue":
				return new Rogue(name, health, power, cunning, new Leather(armor), new Dagger(weapon));
			case "Paladin":
				return new Paladin(name, health, power, cunning, new Chainmail(armor), new Sword(weapon));
			case "Ranger":
				return new Ranger(name, health, power, cunning, new Leather(armor), new Bow(weapon));
			case "Summoner":
				return new Summoner(name, health, power, cunning, new Cloth(armor), new Staff(weapon));
			case "Defender":
				return new Defender(name, health, power, cunning, new Chainmail(armor), new Sword(weapon));
		}
		return null;
	}

	static Blob[] getSerializedInventoryToSaveAsBlobs(Mediator mediator) throws IOException, SQLException
	{
		Party party = mediator.giveParty();
		Inventory inventory = party.getInventory();
		Blob[] inventoryBlob = new Blob[3];
		List<Armor> armors = inventory.getArmor();
		List<Weapon> weapons = inventory.getWeapons();
		List<Consumable> consumables = inventory.getConsumableList();
		Blob armorBlob = null;
		Blob weaponBlob = null;
		Blob consumableBlob = null;

		armorBlob = turnCollectionIntoBlob(armors);
		weaponBlob = turnCollectionIntoBlob(weapons);
		consumableBlob = turnCollectionIntoBlob(consumables);

		inventoryBlob[0] = armorBlob;
		inventoryBlob[1] = weaponBlob;
		inventoryBlob[2] = consumableBlob;

		return inventoryBlob;
	}

	@NotNull
	private static Blob turnCollectionIntoBlob(Collection<?> objects) throws IOException, SQLException
	{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		byte[] buffer = null;
		Blob blob = null;

		objectOutputStream.writeObject(objects);
		buffer = byteArrayOutputStream.toByteArray();
		blob = new SerialBlob(buffer);
		return blob;
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
		//		objectOutputStream.close();
		//		List list = objectInputStream.readObject();
		List list = (List) objectInputStream.readObject();
		return list;
	}

	void setHeroes(A_Character[] heroes)
	{
		if(heroes != null && heroes.length > 0)
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
}

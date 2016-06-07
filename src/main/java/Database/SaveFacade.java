package Database;

import Characters.A_Character;
import GameState.Mediator;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import PartyManagement.Inventory;
import PartyManagement.Party;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Grant Callant on 5/21/2016.
 *
 * @author Grant Callant
 */
class SaveFacade
{
	private static final Logger logger = LoggerFactory.getLogger("SaveFacade");

	static List<A_Character> getPartyToSave(Mediator mediator, int[] level)
	{
		Party party = mediator.giveParty();
		level[0] = party.getFloorLevel(); //Because we save before starting next level
		List<A_Character> heroes = new ArrayList<>(4);
		for(int i = 0; i < party.getCharacterParty().size(); i++)
		{
			heroes.add(party.getCharacter(i));
		}
		return heroes;
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
}

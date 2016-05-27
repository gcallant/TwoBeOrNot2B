package Database;

import Characters.A_Character;
import Characters.A_Hero;
import GameState.Mediator;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import PartyManagement.Inventory;
import PartyManagement.Party;
import org.jetbrains.annotations.NotNull;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Grant Callant on 5/21/2016.
 *
 * @author Grant Callant
 */
class SaveFactory
{
	//Prevents instantiation
 	private SaveFactory()
   {

   }

	static A_Character[] getPartyToSave(Mediator mediator)
	{
		Party party = mediator.giveParty();
		A_Character [] heroes = new A_Hero[3];
		for(int i = 0; i < heroes.length; i++)
		{
			heroes[i] = party.getCharacter(i);
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

package Database;

import Characters.A_Character;
import Characters.A_Hero;
import Mediator.Mediator;
import PartyManagement.Inventory;
import PartyManagement.Party;

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

	static Inventory getInventoryToSave(Mediator mediator)
	{
		Party party = mediator.giveParty();
		return null;
	}


}

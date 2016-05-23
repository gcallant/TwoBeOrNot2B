package Database;

import Characters.A_Character;
import Characters.A_Hero;
import PartyManagement.Party;
import Mediator.Mediator;

import java.util.List;

/**
 * Created by Grant Callant on 5/21/2016.
 *
 * @author Grant Callant
 */
public class SaveFactory
{
	//Prevents instantiation
 	private SaveFactory()
   {

   }

	public static A_Character[] getPartyToSave(Mediator mediator)
	{
		Party party = mediator.giveParty();
		A_Character [] heroes = new A_Hero[3];
		for(int i = 0; i < heroes.length; i++)
		{
			heroes[i] = party.getCharacter(i);
		}
		return heroes;
	}
}

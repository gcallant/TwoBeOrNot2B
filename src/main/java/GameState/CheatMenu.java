package GameState;

import Characters.A_Character;
import Mediator.Mediator;
import PartyManagement.Party;
import StringTester.TestString;

/**
 * Created by Grant Callant on 5/25/16.
 */
public class CheatMenu implements I_State
{
	private Mediator mediator = null;

	public CheatMenu(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}
	@Override
	public String display()
	{
		return "Welcome to alls ails ya'll! What can I do for ya?\nPress: \n1) For infinite health.\n2) For max Power" +
				         "\n3) To Cancel and return to main menu:";
	}

	@Override
	public I_State execute()
	{
		int cheat = TestString.ensureInt(3);

		switch(cheat)
		{
			case 1:
				applyCheat(1);
				return new MapExploration(mediator);
			case 2:
				applyCheat(2);
				return new MapExploration(mediator);
			default:
				return new InGameMenu(mediator);
		}
	}

	private void applyCheat(int cheat)
	{
		Party party = mediator.giveParty();
		for(A_Character hero: party)
		{
			if(cheat == 1)
			{
				hero.setGodMode();
			}
			if(cheat == 2)
			{
				hero.setHasMaxPower();
			}
		}
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}

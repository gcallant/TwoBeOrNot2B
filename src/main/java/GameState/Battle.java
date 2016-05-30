package GameState;

import PartyManagement.BattleManager;
import PartyManagement.GenerateMonsterParty;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

/**
 * Created by Michael on 5/11/2016.
 */
public class Battle implements I_State
{
	private Party               heroParty;
	private Party               enemyParty;
	private BattleManager 		wholeBattle;
	private boolean             newBattle;
	private Mediator            mediator;
	private int                 floorLevel;

	public Battle(Mediator mediator)
	{
		newBattle = true;
		this.mediator = mediator;
		floorLevel = this.mediator.giveCurrentLevel();
	}

	public String display()
	{
		if(mediator.giveNewBattle())
		{
			return "You've encountered an enemy! Press enter to begin!";
		}
		return "Press enter to continue";
	}

	public boolean isEndOfGame()
	{
		return false;
	}

	public I_State execute()
	{
		TestString.enterInput();

		if(mediator.giveNewBattle())
		{
			heroParty = mediator.giveParty();
			enemyParty = new GenerateMonsterParty().generateEnemyParty(floorLevel, mediator.givePartyLevel(), mediator.giveNormal());

			wholeBattle = new BattleManager(heroParty, enemyParty);

			mediator.receiveEnemies(enemyParty);

			wholeBattle.addMembers();

			mediator.receiveBattleManager(wholeBattle);

			mediator.receiveNewBattle(false);

			Display.displayMessage("You are facing \n" + enemyParty.print());
		}
		else
		{
			heroParty = mediator.giveParty();
			enemyParty = mediator.giveEnemies();
			wholeBattle = mediator.giveBattleManager();
			wholeBattle.startOfTurn();
		}

		if(!wholeBattle.takeAction())
		{
			mediator.receiveNewBattle(true);
			return new MapExploration(mediator);
		}

		if(wholeBattle.heroesLost())
		{
			mediator.receiveNewBattle(true);
			return new MainMenu(mediator);
		}
		if(wholeBattle.heroesWon())
		{
			mediator.receiveNewBattle(true);
			heroParty.fixParty();
			return new Victory(mediator);
		}

		return new Battle(mediator);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Battle))
		{
			return false;
		}

		Battle thatBat = (Battle)obj;

		boolean partiesEqual = this.heroParty.equals(thatBat.heroParty) && this.enemyParty.equals(thatBat.enemyParty);
		boolean intsEqual = this.floorLevel == thatBat.floorLevel;
		return  partiesEqual && intsEqual && this.newBattle == thatBat.newBattle && this.mediator.equals(thatBat.mediator);
	}
}

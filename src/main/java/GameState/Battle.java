package GameState;

import Characters.A_Character;
import PartyManagement.GenerateMonsterParty;
import PartyManagement.InitiativeSort;
import PartyManagement.Party;
import Factories.MonsterPartyFactory;
import Mediator.Mediator;
import StringTester.TestString;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 5/11/2016.
 */
public class Battle implements A_State
{
	private Party                  heroParty;
	private Party                  enemyParty;
	private ArrayList<A_Character> wholeBattle;
	private int                    nextToAttack;
	private boolean                newBattle;
	private Mediator               mediator;
	private MonsterPartyFactory    monsterPartyFactory;
	private int					   floorLevel;

	public Battle(Mediator mediator)
	{
		nextToAttack = 0;
		newBattle = true;
		this.mediator = mediator;
		monsterPartyFactory = new MonsterPartyFactory();
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

	public A_State execute()
	{
		TestString.enterInput();
		boolean heroesDefeated = true;
		boolean enemiesDefeated = true;

		if(mediator.giveNewBattle())
		{
			heroParty = mediator.giveParty();
			enemyParty = new GenerateMonsterParty().generateEnemyParty(floorLevel, mediator.givePartyLevel());
			wholeBattle = new ArrayList<A_Character>();

			mediator.receiveEnemies(enemyParty);

			for(int index = 0; index < heroParty.size(); index++)
			{
				A_Character character = heroParty.getCharacter(index);
				character.generateInitiative();
				wholeBattle.add(character);
			}
			for(int index = 0; index < enemyParty.size(); index++)
			{
				A_Character character = enemyParty.getCharacter(index);
				character.generateInitiative();
				wholeBattle.add(character);
			}

			Collections.sort(wholeBattle, new InitiativeSort());

			mediator.receiveCurrentTurn(0);
			mediator.receiveTurnOrder(wholeBattle);
			mediator.receiveNewBattle(false);
			System.out.println("You are facing \n" + enemyParty.print());
		}
		else
		{
			heroParty = mediator.giveParty();
			enemyParty = mediator.giveEnemies();
			wholeBattle = mediator.giveTurnOrder();
			nextToAttack = mediator.giveCurrentTurn();
			heroParty.sortDefeated();
			enemyParty.sortDefeated();
		}

		while(wholeBattle.get(this.nextToAttack).getDefeated())
		{
			this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();
		}

		if(wholeBattle.get(this.nextToAttack).takeAction(heroParty, enemyParty))
		{
			mediator.receiveNewBattle(true);
			return new MapExploration(mediator);
		}

		mediator.receiveCurrentTurn((this.nextToAttack + 1) % wholeBattle.size());
		this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();

		if(heroParty.isDefeated())
		{
			mediator.receiveNewBattle(true);
			//return new Defeated(mediator);
		}
		if(enemyParty.isDefeated())
		{
			mediator.receiveNewBattle(true);
			heroParty.fixParty();
			return new Victory(mediator);
		}

		return new Battle(mediator);
	}
}

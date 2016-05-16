package GameState;

import Characters.A_Character;
import Characters.Party;
import Factories.MonsterPartyFactory;
import Mediator.Mediator;

import java.util.ArrayList;

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

	public Battle(Mediator mediator)
	{
		nextToAttack = 0;
		newBattle = true;
		this.mediator = mediator;
		monsterPartyFactory = new MonsterPartyFactory();
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

    /*public void giveParty(Party party)
    {
        this.heroParty = party;
    }*/

	public A_State execute(String command)
	{
		boolean heroesDefeated = true;
		boolean enemiesDefeated = true;

		if(mediator.giveNewBattle())
		{
			heroParty = mediator.giveParty();
			enemyParty = monsterPartyFactory.defaultMonsterParty(1);
			wholeBattle = new ArrayList<A_Character>();

			mediator.receiveEnemies(enemyParty);

			for(A_Character character : heroParty.getParty())
			{
				wholeBattle.add(character);
			}
			for(A_Character character : enemyParty.getParty())
			{
				wholeBattle.add(character);
			}

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
		}

		while(wholeBattle.get(this.nextToAttack).getDefeated())
		{
			this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();
		}
		wholeBattle.get(this.nextToAttack).takeAction(heroParty, enemyParty);

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
			return new Victory(mediator);
		}

		return new Battle(mediator);
	}
}

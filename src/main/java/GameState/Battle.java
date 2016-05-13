package GameState;

import java.util.ArrayList;

import Character.*;
import Mediator.Mediator;

/**
 * Created by Michael on 5/11/2016.
 */
public class Battle extends A_State
{
    private Party heroParty;
    private Party enemyParty;
    private ArrayList<A_Character> wholeBattle;
    private int nextToAttack;
    private boolean newBattle;
    private Mediator mediator;
    private MonsterPartyFactory monsterPartyFactory;

    public Battle(Mediator mediator)
    {
        StateValues[] validStates = new StateValues[5];
        validStates[0] = StateValues.Battle;
        validStates[1] = StateValues.Victory;
        validStates[2] = StateValues.Defeated;
        nextToAttack = 0;
        newBattle = true;
        this.mediator = mediator;
        monsterPartyFactory = new MonsterPartyFactory();

        setStates(validStates, StateValues.Battle.ordinal());
    }

    public String display()
    {
        if(this.newBattle)
        {
            return "You've encountered an enemy! Press enter to begin!";
        }
        return "Press enter to continue";
    }

    /*public void giveParty(Party party)
    {
        this.heroParty = party;
    }*/

    public int execute(String command)
    {
        boolean heroesDefeated = true;
        boolean enemiesDefeated = true;

        if(this.newBattle)
        {
            heroParty = mediator.giveParty();
            enemyParty = monsterPartyFactory.defaultMonsterParty(1);
            wholeBattle = new ArrayList<A_Character>();

            for(A_Character character : heroParty.getParty())
            {
                wholeBattle.add(character);
            }
            for(A_Character character : enemyParty.getParty())
            {
                wholeBattle.add(character);
            }

            this.newBattle = false;
            System.out.println("You are facing \n" + enemyParty.print());
        }

        while(wholeBattle.get(this.nextToAttack).getDefeated())
        {
            this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();
        }
        //wholeBattle.get(this.nextToAttack).takeAction(heroParty, enemyParty);

        this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();

        if(heroParty.isDefeated(false))
        {
            newBattle = true;
            return StateValues.Defeated.ordinal();
        }
        if(enemyParty.isDefeated(true))
        {
            newBattle = true;
            return StateValues.Victory.ordinal();
        }

        return StateValues.Battle.ordinal();
    }
}

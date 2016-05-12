package GameState;

import java.util.ArrayList;

/**
 * Created by Michael on 5/11/2016.
 */
public class Battle extends A_State
{
    //private Party heroParty;
    //private Party enemyParty;
    private ArrayList<Character> wholeBattle;
    private int nextToAttack;
    private boolean newBattle;

    public Battle()
    {
        StateValues[] validStates = new StateValues[5];
        validStates[0] = StateValues.Battle;
        validStates[1] = StateValues.Victory;
        validStates[2] = StateValues.Defeated;
        nextToAttack = 0;
        this.newBattle = true;

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
        int stateSwitch = 0;
        if(this.newBattle)
        {
            //enemyParty = EnemyGenerator.generateEnemyParty(this.heroParty.get(0).charValue());
            this.newBattle = false;
        }
        while(/*wholeBattle.get(this.nextToAttack).isDefeated()*/true)
        {
            this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();
            break;
        }
        //wholeBattle.get(this.nextToAttack).takeAction();
        this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();

        /*if(heroesDefeated.isDefeated())
        {
            return StateValues.Defeated.ordinal();
        }
        if(enemiesDefeated.isDefeated)
        {
            return StateValues.Victory.ordinal();
        }*/
        return getStateValue(StateValues.Battle.ordinal());
    }
}

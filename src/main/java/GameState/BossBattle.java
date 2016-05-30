package GameState;

import Characters.A_Character;
import Characters.A_Nemesis;
import PartyManagement.BattleManager;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

/**
 * Created by Michael on 5/23/2016.
 */
public class BossBattle implements I_State
{
    private Party               heroParty;
    private Party               enemyParty;
    private BattleManager       wholeBattle;
    private int                 nextToAttack;
    private boolean             newBattle;
    private Mediator            mediator;
    private int                 floorLevel;

    public BossBattle(Mediator mediator)
    {
        nextToAttack = 0;
        newBattle = true;
        this.mediator = mediator;
        floorLevel = this.mediator.giveCurrentLevel();
    }

    public String display()
    {
        if(mediator.giveNewBattle())
        {
            return "Oh no! It's one of the guardians of the dungeon!! Press enter to begin!";
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
        boolean heroesDefeated = true;
        boolean enemiesDefeated = true;

        if(mediator.giveNewBattle())
        {
            heroParty = mediator.giveParty();

            enemyParty = mediator.giveNemesis().getRandomNemesis(mediator.givePartyLevel(), mediator.giveCurrentLevel());
            for(int x = 0; x < enemyParty.size(); x++)
            {
                if(enemyParty.getCharacter(x) instanceof A_Nemesis)
                {
                    mediator.receiveBigBoss((A_Nemesis)enemyParty.getCharacter(x));
                }
            }

            wholeBattle = new BattleManager(heroParty, enemyParty);

            mediator.receiveEnemies(enemyParty);
            mediator.receiveMonsterCount(enemyParty.size());

            mediator.receiveBattleManager(wholeBattle);
            mediator.receiveNewBattle(false);
            Display.displayMessage("You are facing \n" + enemyParty.print());
        }
        else
        {
            heroParty = mediator.giveParty();
            enemyParty = mediator.giveEnemies();
            wholeBattle = mediator.giveBattleManager();
        }

        wholeBattle.startOfTurn();

        A_Nemesis bigBoss = mediator.giveBigBoss();

        if(bigBoss.isRaged())
        {
            wholeBattle.bossTurn((A_Character)bigBoss);
        }
        else
        {
            if(!wholeBattle.takeAction())
            {
                mediator.receiveNewBattle(true);
                return new MapExploration(mediator);
            }
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

        return new BossBattle(mediator);
    }

   /* @Override
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
        boolean intsEqual = this.floorLevel == thatBat.floorLevel && this.nextToAttack == thatBat.nextToAttack;
        return  partiesEqual && intsEqual && this.newBattle == thatBat.newBattle && this.mediator.equals(thatBat.mediator);
    }*/
}

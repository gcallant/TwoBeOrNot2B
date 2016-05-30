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
            return new BossVictory(mediator);
        }

        return new BossBattle(mediator);
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof BossBattle)) { return false; }

        BossBattle that = (BossBattle) o;

        if(nextToAttack != that.nextToAttack) { return false; }
        if(newBattle != that.newBattle) { return false; }
        if(floorLevel != that.floorLevel) { return false; }
        if(heroParty != null ? ! heroParty.equals(that.heroParty) : that.heroParty != null) { return false; }
        if(enemyParty != null ? ! enemyParty.equals(that.enemyParty) : that.enemyParty != null) { return false; }
        if(wholeBattle != null ? ! wholeBattle.equals(that.wholeBattle) : that.wholeBattle != null) { return false; }
        if(mediator != null ? ! mediator.equals(that.mediator) : that.mediator != null) { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = heroParty != null ? heroParty.hashCode() : 0;
        result = 31 * result + (enemyParty != null ? enemyParty.hashCode() : 0);
        result = 31 * result + (wholeBattle != null ? wholeBattle.hashCode() : 0);
        result = 31 * result + nextToAttack;
        result = 31 * result + (newBattle ? 1 : 0);
        result = 31 * result + (mediator != null ? mediator.hashCode() : 0);
        result = 31 * result + floorLevel;
        return result;
    }
}

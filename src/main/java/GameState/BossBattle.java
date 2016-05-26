package GameState;

import Characters.A_Character;
import Characters.A_Nemesis;
import Factories.MonsterFactory;
import Item.Cloth;
import Item.Hammer;
import Mediator.Mediator;
import Nemesis.Necromancer;
import Nemesis.Trent;
import Nemesis.Werewolf;
import PartyManagement.GenerateMonsterParty;
import PartyManagement.InitiativeSort;
import PartyManagement.Party;
import StringTester.TestString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 5/23/2016.
 */
public class BossBattle implements I_State
{
    private Party heroParty;
    private Party               enemyParty;
    private List<A_Character> wholeBattle;
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

            wholeBattle = new ArrayList<A_Character>();

            mediator.receiveEnemies(enemyParty);
            mediator.receiveMonsterCount(enemyParty.size());

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

        addCharacters(heroParty);
        addCharacters(enemyParty);

        A_Nemesis bigBoss = mediator.giveBigBoss();

        if(bigBoss.isRaged())
        {
            this.nextToAttack = wholeBattle.indexOf(bigBoss);
        }
        else
        {
            while (wholeBattle.get(this.nextToAttack).getDefeated())
            {
                this.nextToAttack = (this.nextToAttack + 1) % wholeBattle.size();
            }
            mediator.receiveCurrentTurn((this.nextToAttack + 1) % wholeBattle.size());
        }

        A_Character currentTurn = wholeBattle.get(this.nextToAttack);

        if(heroParty.contains(currentTurn) && currentTurn.isSummon())
        {
            if(currentTurn.takeAction(enemyParty, heroParty))
            {
                mediator.receiveNewBattle(true);
                return new MapExploration(mediator);
            }
        }
        else
        {
            if(currentTurn.takeAction(heroParty, enemyParty))
            {
                mediator.receiveNewBattle(true);
                return new MapExploration(mediator);
            }
        }

        if(heroParty.isDefeated())
        {
            mediator.receiveNewBattle(true);
            return new MainMenu(mediator);
        }
        if(enemyParty.isDefeated())
        {
            mediator.receiveNewBattle(true);
            heroParty.fixParty();
            return new BossVictory(mediator);
        }

        return new BossBattle(mediator);
    }

    private void addCharacters(Party party)
    {
        for(int x = 0; x < party.size(); x++)
        {
            if(!wholeBattle.contains(party.getCharacter(x)))
            {
                wholeBattle.add(party.getCharacter(x));
            }
        }
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

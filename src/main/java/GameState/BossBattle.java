package GameState;

import Characters.A_Character;
import Characters.A_Nemesis;
import Factories.MonsterFactory;
import Item.Cloth;
import Item.Hammer;
import Mediator.Mediator;
import Nemesis.Necromancer;
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
            List<A_Character> boss = new ArrayList<A_Character>();

            A_Nemesis bigBoss = new Necromancer("Necromancer", 1000, 20, 10, new Cloth(3), new Hammer(5), heroParty.getCharacter(0).getLevel());
            mediator.receiveBigBoss(bigBoss);
            boss.add(bigBoss);
            boss.add(new MonsterFactory().createMonster("Skeleton", "Skeleton", heroParty.getCharacter(0).getLevel(), true));
            boss.add(new MonsterFactory().createMonster("Undead Cleric", "Skeleton Cleric", heroParty.getCharacter(0).getLevel(), true));
            enemyParty = new Party(boss);
            wholeBattle = new ArrayList<A_Character>();

            mediator.receiveEnemies(enemyParty);
            mediator.receiveMonsterCount(boss.size());

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

        for(int x = 0; x < enemyParty.size(); x++)
        {
            if(!wholeBattle.contains(enemyParty.getCharacter(x)))
            {
                enemyParty.getCharacter(x).generateInitiative();
                wholeBattle.add(enemyParty.getCharacter(x));
                Collections.sort(wholeBattle, new InitiativeSort());
            }
        }

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

        if(wholeBattle.get(this.nextToAttack).takeAction(heroParty, enemyParty))
        {
            mediator.receiveNewBattle(true);
            return new MapExploration(mediator);
        }

        if(heroParty.isDefeated())
        {
            mediator.receiveNewBattle(true);
            //return new Defeated(mediator);
        }
        if(enemyParty.isDefeated())
        {
            mediator.receiveNewBattle(true);
            heroParty.fixParty();
            return new BossVictory(mediator);
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

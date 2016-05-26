package Mediator;

import Characters.A_Character;
import Characters.A_Nemesis;
import Factories.NemesisPartyFactory;
import PartyManagement.Party;
import DungeonGeneration.GenerateDungeon;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/12/2016.
 */
public class Mediator
{
    private Party             characterParty;
    private Party             enemies;
    private GenerateDungeon   map;
    private int               dungeonSize;
    private boolean           newBattle;
    private int               currentTurn;
    private List<A_Character> turnOrder;
    private int               floorLevel;
    private int partyLevel;
    private boolean noEnemies;
    private boolean normal;
    private int monsterChance;
    private int monsterCount;
    private A_Nemesis bigBoss;
    private NemesisPartyFactory nemesisPartyFactory;

    public Mediator()
    {
        this.characterParty = null;
        this.map = null;
        this.dungeonSize = 10;
        noEnemies = false;
        normal = true;
        monsterChance = 10;
        nemesisPartyFactory = new NemesisPartyFactory();
    }

    public void receiveParty(Party party)
    {
        this.characterParty = party;
    }

    public Party giveParty()
    {
        return this.characterParty;
    }

    public GenerateDungeon giveMap()
    {
        return this.map;
    }

    public void receiveMap(GenerateDungeon map)
    {
        this.map = map;
    }

    public int dungeonSize()
    {
        return dungeonSize;
    }

    public void receiveNewBattle(boolean newBattle)
    {
        this.newBattle = newBattle;
    }

    public boolean giveNewBattle()
    {
        return newBattle;
    }

    public void receiveEnemies(Party party)
    {
        this.enemies = party;
    }

    public Party giveEnemies()
    {
        return enemies;
    }

    public void receiveTurnOrder(List<A_Character> turnOrder)
    {
        this.turnOrder = turnOrder;
    }

    public List<A_Character>  giveTurnOrder()
    {
        return turnOrder;
    }

    public void receiveCurrentTurn(int currentTurn)
    {
        this.currentTurn = currentTurn;
    }

    public int giveCurrentTurn()
    {
        return currentTurn;
    }

    public void receiveCurrentLevel(int floorLevel)
    {
        this.floorLevel = floorLevel;
    }

    public int giveCurrentLevel()
    {
        return this.floorLevel;
    }

    public void receiveNoEnemies()
    {
        noEnemies = !noEnemies;
    }

    public boolean giveNoEnemies()
    {
        return noEnemies;
    }

    public void receiveNormal()
    {
        normal = !normal;
    }

    public boolean giveNormal()
    {
        return normal;
    }

    public void receiveMonsterChance(int chance)
    {
        monsterChance = chance;
    }

    public int giveMonsterChance()
    {
        return monsterChance;
    }

    public void receiveMonsterCount(int count)
    {
        monsterCount = count;
    }

    public int giveMonsterCount()
    {
        return monsterCount;
    }

    public void receiveBigBoss(A_Nemesis nemesis)
    {
        bigBoss = nemesis;
    }

    public A_Nemesis giveBigBoss()
    {
        return bigBoss;
    }

    public void receiveNemesis(NemesisPartyFactory nemesisPartyFactory)
    {
        this.nemesisPartyFactory = nemesisPartyFactory;
    }

    public NemesisPartyFactory giveNemesis()
    {
        return nemesisPartyFactory;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Mediator)) { return false; }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mediator mediator = (Mediator) o;

        if(dungeonSize != mediator.dungeonSize) { return false; }
        if(newBattle != mediator.newBattle) { return false; }
        if(currentTurn != mediator.currentTurn) { return false; }
        if(floorLevel != mediator.floorLevel) { return false; }
        if(characterParty != null ? ! characterParty.equals(mediator.characterParty) : mediator.characterParty != null)
        { return false; }
        if(enemies != null ? ! enemies.equals(mediator.enemies) : mediator.enemies != null) { return false; }
        if(map != null ? ! map.equals(mediator.map) : mediator.map != null) { return false; }
        if(turnOrder != null ? ! turnOrder.equals(mediator.turnOrder) : mediator.turnOrder != null) { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(characterParty, enemies, map, dungeonSize, newBattle, currentTurn, turnOrder, floorLevel, partyLevel);
    }

    public void receivePartyLevel(int partyLevel)
    {
        this.partyLevel = partyLevel;
    }

    public int givePartyLevel()
    {
        return this.partyLevel;
    }
}

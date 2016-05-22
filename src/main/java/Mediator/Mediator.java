package Mediator;

import Characters.A_Character;
import Characters.Party;
import DungeonGeneration.GenerateDungeon;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;

/**
 * Created by Michael on 5/12/2016.
 */
public class Mediator
{
    private Party characterParty;
    private Party enemies;
    private GenerateDungeon map;
    private int dungeonSize;
    private boolean newBattle;
    private int currentTurn;
    private ArrayList<A_Character> turnOrder;
    private int floorLevel;

    public Mediator()
    {
        this.characterParty = null;
        this.map = null;
        this.dungeonSize = 10;
    }

    public void recieveParty(Party party)
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

    public void receiveTurnOrder(ArrayList<A_Character> turnOrder)
    {
        this.turnOrder = turnOrder;
    }

    public ArrayList<A_Character>  giveTurnOrder()
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mediator mediator = (Mediator) o;

        if (dungeonSize != mediator.dungeonSize) return false;
        if (newBattle != mediator.newBattle) return false;
        if (currentTurn != mediator.currentTurn) return false;
        if (floorLevel != mediator.floorLevel) return false;
        if (!characterParty.equals(mediator.characterParty)) return false;
        if (!enemies.equals(mediator.enemies)) return false;
        if (!map.equals(mediator.map)) return false;
        if (!turnOrder.equals(mediator.turnOrder)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = characterParty.hashCode();
        result = 31 * result + enemies.hashCode();
        result = 31 * result + map.hashCode();
        result = 31 * result + dungeonSize;
        result = 31 * result + (newBattle ? 1 : 0);
        result = 31 * result + currentTurn;
        result = 31 * result + turnOrder.hashCode();
        result = 31 * result + floorLevel;
        return result;
    }
}

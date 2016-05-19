package Mediator;

import Characters.A_Character;
import Characters.Party;
import DungeonGeneration.GenerateDungeon;

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
}

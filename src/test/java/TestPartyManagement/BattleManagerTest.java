package TestPartyManagement;

import Characters.A_Character;
import Factories.HeroFactory;
import Factories.MonsterFactory;
import PartyManagement.BattleManager;
import PartyManagement.Party;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SaraPage on 5/29/2016.
 */
public class BattleManagerTest
{
    BattleManager manager;

    @Before
    public void setUp() throws Exception
    {
        createPartiesAndManager();
    }

    private void createPartiesAndManager()
    {
        List<A_Character> list1 = new ArrayList<A_Character>();
        list1.add(HeroFactory.createCharacter("Warrior", "Warrior 1"));
        list1.add(HeroFactory.createCharacter("Rogue", "Rogue 1"));
        list1.add(HeroFactory.createCharacter("Paladin", "Paladin 1"));

        MonsterFactory factory2 = new MonsterFactory();
        List<A_Character> list2 = new ArrayList<A_Character>();
        list2.add(factory2.createMonster("Orc", "Orc 1", 1, true, 1));
        list2.add(factory2.createMonster("Orge", "Orge 1", 1, true, 1));
        list2.add(factory2.createMonster("Skeleton", "Skeleton 1", 1, true, 1));

        manager = new BattleManager(new Party(list1), new Party(list2));
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void takeAction() throws Exception
    {

    }

    @Test
    public void addMembers() throws Exception
    {

    }

    @Test
    public void heroesWon() throws Exception
    {

    }

    @Test
    public void heroesLost() throws Exception
    {

    }

    @Test
    public void startOfTurn() throws Exception
    {

    }

    @Test
    public void bossTurn() throws Exception
    {

    }

}
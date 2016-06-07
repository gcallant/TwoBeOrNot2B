package TestGameState;

import Characters.A_Character;
import Factories.HeroFactory;
import Factories.MonsterFactory;
import GameState.Battle;
import GameState.I_State;
import GameState.Mediator;
import PartyManagement.Party;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by SaraPage on 5/29/2016.
 */
public class BattleTest
{
    private Mediator mediator;
    private I_State  state;

    @Before
    public void setUp() throws Exception
    {
        mediator = new Mediator();
        createCharacters();
        state = new Battle(mediator);
    }

    private void createCharacters()
    {
        List<A_Character> heroes = new ArrayList<A_Character>();
        heroes.add(HeroFactory.createCharacter("Warrior", "Warrior 1"));
        heroes.add(HeroFactory.createCharacter("Mage", "Mage 1"));
        heroes.add(HeroFactory.createCharacter("Paladin", "Paladin 1"));
        mediator.receiveParty(new Party(heroes));

        MonsterFactory monsterFactory = new MonsterFactory();
        List<A_Character> monsters = new ArrayList<A_Character>();
        monsters.add(monsterFactory.createMonster("Goblin", "Goblin 1", 1, true, 1));
        monsters.add(monsterFactory.createMonster("Orc", "Orc 1", 1, true, 1));
        monsters.add(monsterFactory.createMonster("Goblin", "Goblin 2", 1, true, 1));
        mediator.receiveEnemies(new Party(monsters));
    }

    @After
    public void tearDown() throws Exception
    {
        mediator = null;
        state = null;
    }

    @Test
    public void testDisplay() throws Exception
    {
        mediator.receiveNewBattle(false);
        assertEquals("Press enter to continue", state.display());

        mediator.receiveNewBattle(true);
        assertEquals("You've encountered an enemy! Press enter to begin!", state.display());
    }

    @Test
    public void testIsEndOfGame() throws Exception
    {
        assertFalse(state.isEndOfGame());
    }

    @Test
    public void testExecute() throws Exception
    {
        mediator.receiveNewBattle(false);
        System.out.println(state.display());
        /*For some reason, I cannot enter anything from the keyboard, so was unable to test this method properly
        assertNotNull(state.execute()); */
    }

}
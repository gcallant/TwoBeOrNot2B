//package TestGameState;
//
//import GameState.*;
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import Mediator.*;
//
//import static org.junit.Assert.*;
//
///**
// * Created by SaraPage on 5/19/2016.
// */
//public class I_StateTest
//{
//    Mediator mediator;
//    I_State  battleState;
//    I_State  characterCreationState;
//    I_State  endOfMapState;
//    I_State  exitGameState;
//    I_State  inGameMenuState;
//    I_State  mainMenuState;
//    I_State  mapExplorationState;
//    I_State  newMapState;
//    I_State  partyInventoryState;
//    I_State  quitGameState;
//    I_State  victoryState;
//    Injector inject;
//
//    @Before
//    public void setUp() throws Exception {
//        inject = Guice.createInjector();
//        mediator = new Mediator();
//        battleState = new Battle(mediator);
//        characterCreationState = new CharacterCreation(mediator);
//        endOfMapState = new EndOfMap(mediator);
//        exitGameState = new ExitGame(mediator);
//        inGameMenuState = new InGameMenu(mediator);
//        mainMenuState = new MainMenu(mediator);
//        mapExplorationState = new MapExploration(mediator);
//        newMapState = new NewMap(mediator);
//        partyInventoryState = new PartyInventory(mediator);
//        quitGameState = new QuitGame(mediator);
//        victoryState = new Victory(mediator);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testBattle() throws Exception
//    {
//        //Testing the two cases for display()
//        assertEquals("Press enter to continue", battleState.display());
//        mediator.receiveNewBattle(true);
//        assertEquals("You've encountered an enemy! Press enter to begin!", battleState.display());
//
//        //Testing execute()
//        //assertEquals(new Battle(mediator), battleState.execute("sneak"));
//    }
//
//    @Test
//    public void testCharacterCreation() throws Exception
//    {
//        //Testing display()
//        assertEquals("Select 'party' to create your party\nOr 'cancel' to Cancel", characterCreationState.display());
//
//        //Testing execute() - first need a mock object
//        //inject.getMembersInjector(characterCreationState);
//        //assertNotNull(characterCreationState.execute("party"));
//    }
//
//    @Test
//    public void testEndOfMap() throws Exception
//    {
//        //Testing display()
//        assertEquals("You beat the level. Progress saved! Press enter to continue.", endOfMapState.display());
//
//    }
//
//    @Test
//    public void testExitGame() throws Exception
//    {
//        //Testing display()
//        assertEquals("Thanks for playing!", exitGameState.display());
//    }
//
////    @Test
////    public void testInGameMenu() throws Exception
////    {
////        //Testing display()
////        assertEquals("Choose an option\nResume\nInventory\nQuit\n", inGameMenuState.display());
////    }
//
////    @Test
////    public void testMainMenu() throws Exception
////    {
////        //Testing display()
////        assertEquals("Choose an option\nStart\nQuit", mainMenuState.display());
////    }
//
//    @Test
//    public void testMapExploration() throws Exception
//    {
//        //Testing display()
//        //assertEquals("Thanks for playing!", mapExplorationState.display());
//    }
//
//    @Test
//    public void testNewMap() throws Exception
//    {
//        //Testing display()
//        assertEquals("", newMapState.display());
//    }
//
////    @Test
////    public void testPartyInventory() throws Exception
////    {
////        //Testing display()
////        assertEquals("Choose an option\nEquip\nUse Potion\nBack\n", partyInventoryState.display());
////    }
//
////    @Test
////    public void testQuitGame() throws Exception
////    {
////        //Testing display()
////        assertEquals("Are you sure you want to quit?", quitGameState.display());
////    }
//
//    @Test
//    public void testVictory() throws Exception
//    {
//        //Testing display()
//        assertEquals("You won! Press enter to continue!", victoryState.display());
//    }
//
//}
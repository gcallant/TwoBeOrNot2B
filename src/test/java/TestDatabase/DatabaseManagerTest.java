//package TestItems;
//
//import Characters.A_Character;
//import Database.DatabaseManager;
//import Exceptions.OSException;
//import Factories.HeroFactory;
//import Utilities.Display;
//import Utilities.OSUtil;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.unitils.dbunit.annotation.DataSet;
//
//import java.io.File;
//
//import static org.junit.Assert.assertFalse;
//
///**
// * Created by Grant Callant on 5/12/2016.
// *
// * @author Grant Callant
// */
//
//@DataSet( {"DatabaseManagerCharacterTest.xml"})
//public class DatabaseManagerTest
//{
//	private static final String GAME_NAME          = "DungeonCrawler";
//	private static final String SEPARATOR          = OSUtil.getSeparator();
//	private static final Logger logger             = LoggerFactory.getLogger("RunGameSample");
//	private static       File   EXTERNAL_DIRECTORY = null;
//	DatabaseManager databaseManager = null;
//
//	@Before
//	public void setUp() throws Exception
//	{
//		createExternalDirectory();
//		databaseManager = DatabaseManager.getInstance();
//	}
//
//	private static void createExternalDirectory()
//	{
//		try
//		{
//			File parent = OSUtil.getCurrentDirectory();
//			EXTERNAL_DIRECTORY = OSUtil.createNewDirectory(parent, GAME_NAME);
//			OSUtil.setExternalDirectory(EXTERNAL_DIRECTORY);
//
//		}
//		catch(OSException e)
//		{
//			e.printStackTrace();
//			Display.displayMessage("Could not make new directory- program must exit");
//			System.exit(- 1);
//		}
//	}
//
//	@Test
//	public void testCharacter()
//	{
//		A_Character cloud = HeroFactory.createCharacter("Warrior", "Cloud");
//		A_Character barrett = HeroFactory.createCharacter("Warrior", "Barrett");
//		A_Character tiffa = HeroFactory.createCharacter("Ranger", "Tiffa");
//		A_Character yuffie = HeroFactory.createCharacter("Rogue", "Yuffie");
//		A_Character aris = HeroFactory.createCharacter("Mage", "Aris");
//		A_Character red = HeroFactory.createCharacter("Defender", "RedXIII");
//		A_Character cid = HeroFactory.createCharacter("Paladin", "Cid");
//	}
//
//	@After
//	public void tearDown() throws Exception
//	{
//		databaseManager.closeConnection();
//		File deleteTestDatabase = new File(EXTERNAL_DIRECTORY.getAbsolutePath() +
//				                                     OSUtil.getSeparator() + "DungeonCrawler.db");
//		File deleteTestDirectory = new File(EXTERNAL_DIRECTORY.getAbsolutePath());
//		deleteTestDatabase.delete();
//		assertFalse(deleteTestDatabase.exists());
//		deleteTestDirectory.delete();
//		assertFalse(deleteTestDirectory.exists());
//
//	}
//}
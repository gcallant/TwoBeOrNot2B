//package TestItems;
//
//import Characters.A_Character;
//import Database.DatabaseManager;
//import Factories.CharacterFactory;
//import Factories.HeroFactory;
//import Logging.LoggingManager;
//import Exceptions.OSException;
//import Utilities.OSUtil;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
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
//	private static final String GAME_NAME          = "DCrawler";
//	private static       File   EXTERNAL_DIRECTORY = null;
//	DatabaseManager databaseManager = null;
//
//	@Before
//	public void setUp() throws Exception
//	{
//		createExternalDirectory();
//		databaseManager = new DatabaseManager();
//	}
//
//	private static void createExternalDirectory()
//	{
//		try
//		{
//			File parent = OSUtil.getParentDirectory();
//			EXTERNAL_DIRECTORY = OSUtil.createNewDirectory(parent, GAME_NAME);
//			OSUtil.setExternalDirectory(EXTERNAL_DIRECTORY);
//
//		}
//		catch(OSException e)
//		{
//			e.printStackTrace();
//			System.out.println("Could not make new directory- program must exit");
//			System.exit(- 1);
//		}
//	}
//
//	@Test
//	public void testCharacter()
//	{
//		HeroFactory characterFactory = new HeroFactory();
//		A_Character character = characterFactory.createCharacter("Warrior", "Cloud");
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
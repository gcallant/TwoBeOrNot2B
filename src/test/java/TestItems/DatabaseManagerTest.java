package TestItems;

import Characters.A_Character;
import Characters.CharacterFactory;
import Database.DatabaseManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import java.io.File;

import static org.junit.Assert.assertFalse;

/**
 * Created by Grant Callant on 5/12/2016.
 *
 * @author Grant Callant
 */

@DataSet({"DatabaseManagerCharacterTest.xml"})
public class DatabaseManagerTest
{
	DatabaseManager databaseManager = null;

	@Before
	public void setUp() throws Exception
	{
		databaseManager = DatabaseManager.getInstance();
	}

	@Test
	public void testCharacter()
	{
		CharacterFactory characterFactory = new CharacterFactory();
		A_Character character = characterFactory.createCharacter("Cloud", 10, 7, 2);
	}

	@After
	public void tearDown() throws Exception
	{
		databaseManager.closeConnection();
		File deleteTestDirectory = new File("storedInformation");
		deleteTestDirectory.delete();
		assertFalse(deleteTestDirectory.exists());
	}
}
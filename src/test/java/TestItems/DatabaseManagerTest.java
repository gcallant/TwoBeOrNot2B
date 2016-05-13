package TestItems;

import Database.DatabaseManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

/**
 * Created by Grant Callant on 5/12/2016. GitHub
 *
 * @author Grant Callant
 */
@DataSet( {"DatabaseManagerCharacterTest.xml"})
public class DatabaseManagerTest
{
	DatabaseManager databaseManager = null;

	@Before
	public void setUp() throws Exception
	{
		databaseManager = new DatabaseManager();
	}

	@Test
	public void testCharacter()
	{
		assertPropertyLenientEquals("", "", "");
	}

	@After
	public void tearDown() throws Exception
	{

	}

	@Test
	public void closeConnection() throws Exception
	{
		databaseManager.closeConnection();
	}

}
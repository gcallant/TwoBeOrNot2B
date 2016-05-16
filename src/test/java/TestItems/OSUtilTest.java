package TestItems;

import Utilities.OSUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Grant Callant on 5/14/2016. GitHub
 *
 * @author Grant Callant
 */
public class OSUtilTest
{
	private static final String TEST_DIRECTORY = "\\New Directory";
	private              File   parent         = null;

	@Before
	public void setUp() throws Exception
	{
		parent = OSUtil.getParentDirectory();
	}

	@Test
	public void createNewDirectory() throws Exception
	{
		OSUtil.createNewDirectory(parent, "New Directory");
		File compare = new File(parent.getAbsolutePath() + TEST_DIRECTORY);
		assertTrue(compare.exists());
	}

	@Test
	public void getParentDirectory() throws Exception
	{
		/* Change to expected test location - otherwise will cause build failure
		 * Example: File compare = new File("C:\\Users\\Grantley\\IdeaProjects\\");*/
		File compare = new File("/home/travis/build/gcallant");
		//File compare = new File("C:\\Users\\Grantley\\IdeaProjects\\");
		assertEquals(compare, parent);
	}

	@After
	public void tearDown() throws Exception
	{
		File deleteTestDirectory = new File(parent + TEST_DIRECTORY);
		deleteTestDirectory.delete();
		assertFalse(deleteTestDirectory.exists());
	}

}
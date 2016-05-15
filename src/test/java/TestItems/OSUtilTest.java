package TestItems;

import Utilities.OSUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Grant Callant on 5/14/2016. GitHub
 *
 * @author Grant Callant
 */
public class OSUtilTest
{
	@Before
	public void setUp() throws Exception
	{

	}

	@After
	public void tearDown() throws Exception
	{

	}

	@Test
	public void getParentDirectory() throws Exception
	{
		File parent = OSUtil.getParentDirectory();
		/* Change to expected test location - otherwise will cause build failure
		 * Example: File compare = new File("C:\\Users\\Grantley\\IdeaProjects\\");*/
		File compare = new File("/home/travis/build/gcallant");
		assertEquals(compare, parent);
	}

}
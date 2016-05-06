package TestItems;

import Parser.GenericInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Grant Callant on 5/4/16.
 */
public class GenericInputTest
{
	GenericInput genericInput = null;

	@Before
	public void setUp() throws Exception
	{
		genericInput = new GenericInput();
	}

	@Test
	public void testAttachment() throws FileNotFoundException
	{
		genericInput.attach(new FileInputStream(new File("/Users/grantley/Documents/TwoBeOrNot2B/" +
				                                                   "src/test/java/TestItems/testInputFile.txt")));
	}

	@Test
	public void testReading() throws FileNotFoundException
	{
		testAttachment();
		genericInput.read();
	}

	@After
	public void tearDown() throws Exception
	{

	}

}
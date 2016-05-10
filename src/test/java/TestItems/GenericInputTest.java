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
	public void setUp() throws FileNotFoundException
	{
		genericInput = new GenericInput();
		genericInput.attach(new FileInputStream(new File("testInputFile.txt")));
	}

	@Test
	public void testReading() throws FileNotFoundException
	{
		genericInput.read();
	}

	@Test
	public void testToString() throws FileNotFoundException
	{
		testReading();
		System.out.println(genericInput);
	}

	@After
	public void tearDown()
	{
		genericInput.closeInputStream();
	}

}
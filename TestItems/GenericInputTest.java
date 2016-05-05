package TestItems;

import Parser.GenericInput;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Grant Callant on 5/4/16. GitHub
 */
public class GenericInputTest
{
	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void testInput()
	{
		Injector injector = Guice.createInjector();
		GenericInput genericInput = null;
		injector.injectMembers(genericInput);
	}

	@After
	public void tearDown() throws Exception
	{

	}
	
}
package TestItems;

import Factories.*;
import Characters.A_Character;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class TestPartySelectionAndCharacterCreation
{
	HeroFactory heroFactory = null;
	Injector         injector         = null;
	A_Character      character        = null;

	@Before
	public void setUp() throws Exception
	{
		heroFactory = new HeroFactory();
		injector = Guice.createInjector();
		character = heroFactory.createCharacter("Warrior", "Bob", 100, 5, 5, 3);
	}

	@Test
	public void testMembers()
	{
		System.out.println(character);
	}

	@After
	public void tearDown() throws Exception
	{

	}
}

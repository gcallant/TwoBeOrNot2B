package TestItems;

import Character.A_Character;
import Character.CharacterFactory;
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
	CharacterFactory characterFactory = null;
	Injector         injector         = null;
	A_Character      character        = null;

	@Before
	public void setUp() throws Exception
	{
		characterFactory = new CharacterFactory();
		injector = Guice.createInjector();
		character = characterFactory.createCharacter("Warrior", 100, 5, 5, 3, 12);
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

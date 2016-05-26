package TestFactories;

import Characters.A_Character;
import Factories.HeroFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/20/2016.
 */
public class HeroFactoryTest
{
    HeroFactory factory;
    A_Character testHero;

    @Before
    public void setUp() throws Exception
    {
        factory = new HeroFactory();
        testHero = factory.createCharacter("Warrior", "HeroName");
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        testHero = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreateCharacter()
    {
        //Create the appropriate character
        assertNotNull(testHero);

        //edge cases
        exception.expect(NullPointerException.class);
        factory.createCharacter("Rogue", null);
        factory.createCharacter(null, "Sneaky");
        factory.createCharacter(null, null);

        //If the user enters a hero that's not valid
        exception.expect(IllegalArgumentException.class);
        testHero = factory.createCharacter("Hero", "Sneaky");
    }

}
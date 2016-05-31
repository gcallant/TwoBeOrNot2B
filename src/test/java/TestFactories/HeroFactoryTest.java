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
    private HeroFactory factory;
    private A_Character testHero;

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
        //checking for valid return type
        assertNotNull(testHero);

        //edge cases - null values and invalid input
        exception.expect(NullPointerException.class);
        factory.createCharacter("Rogue", null);
        factory.createCharacter(null, "Sneaky");
        factory.createCharacter(null, null);

        exception.expect(IllegalArgumentException.class);
        testHero = factory.createCharacter("Hero", "Sneaky");
    }

}
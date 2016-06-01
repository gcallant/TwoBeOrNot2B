package TestFactories;

import Characters.A_Character;
import Factories.HeroFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by SaraPage on 5/20/2016.
 */
public class HeroFactoryTest
{
    private A_Character testHero;

    @Before
    public void setUp() throws Exception
    {
        testHero = HeroFactory.createCharacter("Warrior", "HeroName");
    }

    @After
    public void tearDown() throws Exception
    {
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
        HeroFactory.createCharacter("Rogue", null);
        HeroFactory.createCharacter(null, "Sneaky");
        HeroFactory.createCharacter(null, null);

        exception.expect(IllegalArgumentException.class);
        testHero = HeroFactory.createCharacter("Hero", "Sneaky");
    }

}
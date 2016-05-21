package TestHeroAndMonsterCreation;

import Characters.A_Character;
import Factories.HeroFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/20/2016.
 */
public class HeroCreationTest
{
    HeroFactory factory;
    @Before
    public void setUp() throws Exception
    {
        factory = new HeroFactory();
    }

    @After
    public void tearDown() throws Exception
    {
        A_Character testCharacter = factory.createCharacter("Goblin", "name");

        assertEquals(testCharacter, factory.createCharacter("Goblin", "name"));
    }

    @Test
    public void createCharacter() throws Exception
    {

    }

}
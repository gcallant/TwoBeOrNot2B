package TestFactories;

import Characters.A_Character;
import Factories.MonsterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by SaraPage on 5/22/2016.
 */
public class MonsterFactoryTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();
    MonsterFactory factory;
    A_Character testMonster;

    @Before
    public void setUp() throws Exception
    {
        factory = new MonsterFactory();
        testMonster = factory.createMonster("Orc", "Orc 1", 1, true, 3);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        testMonster = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreateCharacter() throws Exception
    {
        //Create a valid character
        assertNotNull(testMonster);

        //edge cases
        exception.expect(NullPointerException.class);
        factory.createMonster("Goblin", null, 2, true, 2);
        factory.createMonster(null, "Sneaky", 3, true, 4);
        factory.createMonster(null, null, 1, true, 3);

        //If the user enters a monster that's not valid
        exception.expect(IllegalArgumentException.class);
        testMonster = factory.createMonster("Goblin", "Goblin 1", - 1, true, 6);
        testMonster = factory.createMonster("Monster", "Goblin 1", 3, true, 9);
    }
}

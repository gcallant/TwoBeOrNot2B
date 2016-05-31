package TestFactories;

import Factories.ConsumableFactory;
import Item.Consumable;
import Item.Power;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/23/2016.
 */
public class ConsumableFactoryTest
{
    private ConsumableFactory factory;
    private Consumable testConsumable;

    @Before
    public void setUp() throws Exception
    {
        factory = new ConsumableFactory();
        testConsumable = new Power(2);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        testConsumable = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testGenerate() throws Exception
    {
        //checking for valid return type
        assertNotNull(testConsumable);

        //edge cases - null values and invalid input
        exception.expect(NullPointerException.class);
        factory.generate(null, 5);

        exception.expect(IllegalArgumentException.class);
        factory.generate("Healing", -5);
        factory.generate("Consumable", 6);
    }

}
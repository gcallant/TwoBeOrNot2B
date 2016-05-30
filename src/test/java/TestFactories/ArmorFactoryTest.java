package TestFactories;

import Factories.ArmorFactory;
import Item.Armor;
import Item.Chainmail;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/23/2016.
 */
public class ArmorFactoryTest
{
    private ArmorFactory factory;
    private Armor testArmor;

    @Before
    public void setUp() throws Exception
    {
        factory = new ArmorFactory();
        testArmor = new Chainmail(3);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        testArmor = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGenerate() throws Exception
    {
        //checking for valid return type
        assertNotNull(testArmor);

        //edge cases - null values and invalid input
        exception.expect(NullPointerException.class);
        factory.generate(null, 5);

        exception.expect(IllegalArgumentException.class);
        factory.generate("Cloth", -5);
        factory.generate("Armor", 6);
    }

}
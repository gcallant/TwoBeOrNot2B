package TestFactories;

import Factories.ArmorFactory;
import Item.Armor;
import Item.Chainmail;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by SaraPage on 5/23/2016.
 */
public class ArmorFactoryTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();
    ArmorFactory factory;
    Armor testArmor;

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

    @Test
    public void testGenerate() throws Exception
    {
        assertNotNull(testArmor);

        exception.expect(NullPointerException.class);
        factory.generate(null, 5);

        exception.expect(IllegalArgumentException.class);
        factory.generate("Cloth", -5);
        factory.generate("Armor", 6);
    }

}
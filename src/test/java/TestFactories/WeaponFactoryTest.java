package TestFactories;

import Factories.WeaponFactory;
import Item.Sword;
import Item.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/23/2016.
 */
public class WeaponFactoryTest
{
    private WeaponFactory factory;
    private Weapon testWeapon;

    @Before
    public void setUp() throws Exception
    {
        factory = new WeaponFactory();
        testWeapon = new Sword(1);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        testWeapon = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGenerate() throws Exception
    {
        //checking for valid return type
        assertNotNull(testWeapon);

        //edge cases - null values and invalid input
        exception.expect(NullPointerException.class);
        factory.generate(null, 5);

        exception.expect(IllegalArgumentException.class);
        factory.generate("Sword", -5);
        factory.generate("Weapon", 6);
    }

}
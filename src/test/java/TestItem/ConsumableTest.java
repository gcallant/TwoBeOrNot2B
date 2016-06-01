package TestItem;

import Characters.A_Character;
import Factories.ConsumableFactory;
import Factories.HeroFactory;
import Item.Consumable;
import Item.ConsumableSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/29/2016.
 */
public class ConsumableTest
{
    List<Consumable> list;
    List<Consumable> manuallySortedList;
    ConsumableFactory factory;

    Consumable healing;
    Consumable power;

    @Before
    public void setUp() throws Exception
    {
        factory = new ConsumableFactory();
        healing = factory.generate("Healing", 1);
        power = factory.generate("Power", 1);

        list = new ArrayList<Consumable>(2);
        list.add(power);
        list.add(healing);

        manuallySortedList = new ArrayList<Consumable>(2);
        manuallySortedList.add(healing);
        manuallySortedList.add(power);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        healing = null;
        power = null;

        for (Consumable item: list)
        {
            item = null;
        }
        list = null;

        for(Consumable item: manuallySortedList)
        {
            item = null;
        }
        manuallySortedList = null;
    }

    @Test
    public void testUseForHealing() throws Exception
    {
        //We first need a character-mage's maxHealth is set to 180 initially
        A_Character mage = HeroFactory.createCharacter("Mage", "myMage");

        //This will set the health to be the min of health+power and maxHealth
        //After it's used, mage's health should be set to 105 and isDefeated should be false
        mage.setHealth(100);
        healing.use(mage);
        assertEquals(105, mage.getHealth());
        assertFalse(mage.getDefeated());
    }

    @Test
    public void testUseForPower() throws Exception
    {
        /*There wasn't really a good way to test use for power*/
    }

    @Test
    public void testDecrementRoundsForHealing() throws Exception
    {
        //Rounds get initialized to 4. This will return true when rounds is 0
        assertFalse(healing.decrementRounds()); //3
        assertFalse(healing.decrementRounds()); //2
        assertFalse(healing.decrementRounds()); //1
        assertTrue(healing.decrementRounds()); //0
    }

    @Test
    public void testDecrementRoundsForPower() throws Exception
    {
        //Rounds get initialized to 4. It will return true when rounds is 0
        assertFalse(power.decrementRounds()); //3
        assertFalse(power.decrementRounds()); //2
        assertFalse(power.decrementRounds()); //1
        assertTrue(power.decrementRounds()); //0
    }

    @Test
    public void testGetPowerForHealing() throws Exception
    {
        //the initial power (1) gets multiplied by 5
        assertEquals(1*5, healing.getPower());
    }

    @Test
    public void testGetPowerForPower() throws Exception
    {
        assertEquals(1, power.getPower());
    }

    @Test
    public void testToStringForHealing() throws Exception
    {
        assertEquals("Potion of Healing(" + healing.getPower() + ")", healing.toString());
    }

    @Test
    public void testToStringForPower() throws Exception
    {
        assertEquals("Potion of damage (" + power.getPower() + ")", power.toString());
    }

    @Test
    public void testConsumableSort() throws Exception
    {
        Collections.sort(list, new ConsumableSort());
        assertEquals(list, manuallySortedList);
    }
}
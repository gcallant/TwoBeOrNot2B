package TestItem;

import Factories.ArmorFactory;
import Item.Armor;
import Item.ArmorSort;
import Item.ArmorType;
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
public class ArmorTest
{
    List<Armor> list;
    List<Armor> manuallySortedList;
    ArmorFactory factory;

    Armor chainmail;
    Armor cloth;
    Armor leather;

    @Before
    public void setUp() throws Exception
    {
        factory = new ArmorFactory();

        chainmail = factory.generate("Chainmail", 1);
        cloth = factory.generate("Cloth", 1);
        leather = factory.generate("Leather", 1);

        list = new ArrayList<Armor>();
        list.add(cloth);
        list.add(chainmail);
        list.add(leather);

        manuallySortedList = new ArrayList<Armor>();
        manuallySortedList.add(chainmail);
        manuallySortedList.add(cloth);
        manuallySortedList.add(leather);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        chainmail = null;
        cloth = null;
        leather = null;

        for (Armor armor: list)
        {
            armor = null;
        }
        list = null;

        for (Armor armor: manuallySortedList)
        {
            armor = null;
        }
        manuallySortedList = null;
    }

    @Test
    public void testGetPowerChainmail() throws Exception
    {
        int chainBase = 5;
        assertEquals(1 + chainBase, chainmail.getPower());
    }

    @Test
    public void testGetPowerCloth() throws Exception
    {
        int clothBase = 2;
        assertEquals(1 + clothBase, cloth.getPower());
    }

    @Test
    public void testGetPowerLeather() throws Exception
    {
        int leatherBase = 3;
        assertEquals(1 + leatherBase, leather.getPower());
    }

    @Test
    public void testSetEnchantment() throws Exception
    {
        /* Was not sure how to test this since Enchancement is an Interface*/
    }

    @Test
    public void testToStringChainmail() throws Exception
    {
        assertEquals("Armor: Chainmail(" + (chainmail.getPower()) + ")", chainmail.toString());
    }

    @Test
    public void testToStringCloth() throws Exception
    {
        assertEquals("Armor: Cloth(" + (cloth.getPower()) + ")", cloth.toString());
    }

    @Test
    public void testToStringLeather() throws Exception
    {
        assertEquals("Armor: Leather(" + (leather.getPower()) + ")", leather.toString());
    }

    @Test
    public void testGetArmorTypeChainmail() throws Exception
    {
        assertEquals(ArmorType.Heavy, chainmail.getArmorType());
    }

    @Test
    public void testGetArmorTypeCloth() throws Exception
    {
        assertEquals(ArmorType.Light, cloth.getArmorType());
    }

    @Test
    public void testGetArmorTypeLeather() throws Exception
    {
        assertEquals(ArmorType.Medium, leather.getArmorType());
    }

    @Test
    public void testArmorSort() throws Exception
    {
        Collections.sort(list, new ArmorSort());
        assertEquals(list, manuallySortedList);
    }
}
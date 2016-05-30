package TestItem;

import Factories.WeaponFactory;
import Item.Weapon;
import Item.WeaponSort;
import Item.WeaponType;
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
public class WeaponTest
{
    List<Weapon> list;
    List<Weapon> manuallySortedList;
    WeaponFactory factory;

    Weapon bow;
    Weapon dagger;
    Weapon hammer;
    Weapon staff;
    Weapon sword;

    @Before
    public void setUp() throws Exception
    {
        factory = new WeaponFactory();
        bow = factory.generate("Bow", 1);
        dagger = factory.generate("Dagger", 1);
        hammer = factory.generate("Hammer", 1);
        staff = factory.generate("Staff", 1);
        sword = factory.generate("Sword", 1);

        list = new ArrayList<Weapon>();
        list.add(hammer);
        list.add(bow);
        list.add(sword);
        list.add(dagger);
        list.add(staff);

        manuallySortedList = new ArrayList<Weapon>();
        manuallySortedList.add(bow);
        manuallySortedList.add(dagger);
        manuallySortedList.add(hammer);
        manuallySortedList.add(staff);
        manuallySortedList.add(sword);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        bow = null;
        dagger = null;
        hammer = null;
        staff = null;
        sword = null;

        for (Weapon item: list)
        {
            item = null;
        }
        list = null;

        for (Weapon item: manuallySortedList)
        {
            item = null;
        }
        manuallySortedList = null;
    }

    @Test
    public void testGetAttackTypeForBow() throws Exception
    {
        assertEquals("cunning", bow.getAttackType());
    }

    @Test
    public void testGetAttackTypeForDagger() throws Exception
    {
        assertEquals("cunning", dagger.getAttackType());
    }

    @Test
    public void testGetAttackTypeForHammer() throws Exception
    {
        assertEquals("Power", hammer.getAttackType());
    }

    @Test
    public void testGetAttackTypeForStaff() throws Exception
    {
        assertEquals("Power", staff.getAttackType());
    }

    @Test
    public void testGetAttackTypeForSword() throws Exception
    {
        assertEquals("Power", sword.getAttackType());
    }

    @Test
    public void testGetPowerForBow() throws Exception
    {
        int bowBase = 2;
        assertEquals(1 + bowBase, bow.getPower());
    }

    @Test
    public void testGetPowerForDagger() throws Exception
    {
        int daggerBase = 1;
        assertEquals(1 + daggerBase, dagger.getPower());
    }

    @Test
    public void testGetPowerForHammer() throws Exception
    {
        int hammerBase = 3;
        assertEquals(1 + hammerBase, hammer.getPower());
    }

    @Test
    public void testGetPowerForStaff() throws Exception
    {
        int staffBase = 0;
        assertEquals(1 + staffBase, staff.getPower());
    }

    @Test
    public void testGetPowerForSword() throws Exception
    {
        int swordBase = 2;
        assertEquals(1 + swordBase, sword.getPower());
    }

    @Test
    public void testToStringForBow() throws Exception
    {
        assertEquals("Weapon: Bow(" + bow.getPower() + ")", bow.toString());
    }

    @Test
    public void testToStringForDagger() throws Exception
    {
        assertEquals("Weapon: Dagger(" + dagger.getPower() + ")", dagger.toString());
    }

    @Test
    public void testToStringForHammer() throws Exception
    {
        assertEquals("Weapon: Hammer(" + hammer.getPower() + ")", hammer.toString());
    }

    @Test
    public void testToStringForStaff() throws Exception
    {
        assertEquals("Weapon: Staff(" + staff.getPower() + ")", staff.toString());
    }

    @Test
    public void testToStringForSword() throws Exception
    {
        assertEquals("Weapon: Sword(" + sword.getPower() + ")", sword.toString());
    }

    @Test
    public void testGetWeaponTypeForBow() throws Exception
    {
        assertEquals(WeaponType.Ranged, bow.getWeaponType());
    }

    @Test
    public void testGetWeaponTypeForDagger() throws Exception
    {
        assertEquals(WeaponType.Light, dagger.getWeaponType());
    }

    @Test
    public void testGetWeaponTypeForHammer() throws Exception
    {
        assertEquals(WeaponType.Heavy, hammer.getWeaponType());
    }

    @Test
    public void testGetWeaponTypeForStaff() throws Exception
    {
        assertEquals(WeaponType.Staff, staff.getWeaponType());
    }

    @Test
    public void testGetWeaponTypeForSword() throws Exception
    {
        assertEquals(WeaponType.Medium, sword.getWeaponType());
    }

    @Test
    public void testWeaponSort() throws Exception
    {
        Collections.sort(list, new WeaponSort());
        assertEquals(list, manuallySortedList);
    }
}
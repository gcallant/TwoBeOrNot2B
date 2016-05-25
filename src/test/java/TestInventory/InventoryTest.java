package TestInventory;

import PartyManagement.Inventory;
import Item.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/22/2016.
 */
public class InventoryTest
{
    Inventory inventory;
    List<Weapon> weapons;
    List<Armor> armor;
    List<Consumable> consumable;

    @Before
    public void setUp() throws Exception
    {
        inventory = new Inventory();

        weapons = new ArrayList<Weapon>(1);
        weapons.add(new Hammer(2));

        armor = new ArrayList<Armor>(1);
        armor.add(new Leather(2));

        consumable = new ArrayList<Consumable>(1);
        consumable.add(new Healing(1));
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void addWeaponToInventoryTest() throws Exception
    {
        inventory.addToInventory(new Hammer(2));
        assertEquals(weapons, inventory.getWeapons());
    }

    @Test
    public void addArmorToInventory() throws Exception
    {
        inventory.addToInventory(new Leather(2));
        assertEquals(armor, inventory.getArmor());
    }

    @Test
    public void addConsumableToInventory() throws Exception
    {
        inventory.addToInventory(new Healing(1));
        assertEquals(2, inventory.getConsumables());
    }

    @Test
    public void removeWeaponFromInventory() throws Exception
    {
        inventory.addToInventory(new Hammer(2));
        assertNotNull(inventory.removeFromInventory(weapons.get(0)));
    }

    @Test
    public void removeArmorFromInventory() throws Exception
    {
        inventory.addToInventory(new Leather(2));
        assertNotNull(inventory.removeFromInventory(armor.get(0)));
    }

    @Test
    public void removeConsumableFromInventory() throws Exception
    {
        inventory.addToInventory(new Healing(1));
        assertNotNull(inventory.removeFromInventory(consumable.get(0)));
    }

}
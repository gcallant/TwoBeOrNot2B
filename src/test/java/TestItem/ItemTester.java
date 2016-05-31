package TestItem;

import Item.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTester
{
	List<Weapon>     weaponList;
	List<Armor>      armorList;
	List<Consumable> consumableList;

	@Before
	public void setUp() throws Exception
	{
		weaponList = new ArrayList<Weapon>();
		armorList = new ArrayList<Armor>();
		consumableList = new ArrayList<Consumable>();
	}

	@Test
	public void testItemAdd()
	{
		weaponList.add(new Bow(1));
		weaponList.add(new Dagger(3));
		weaponList.add(new Bow(3));
		weaponList.add(new Bow(1));
		weaponList.add(new Hammer(3));
		weaponList.add(new Dagger(1));
		weaponList.add(new Staff(1));
		weaponList.add(new Sword(2));

		armorList.add(new Chainmail(3));
		armorList.add(new Cloth(2));
		armorList.add(new Leather(1));
		armorList.add(new Chainmail(1));
		armorList.add(new Cloth(1));

		consumableList.add(new Power(3));
		consumableList.add(new Healing(2));
		consumableList.add(new Healing(1));
		consumableList.add(new Power(1));
	}

	@Test
	public void testSorting()
	{
		System.out.println("Before sorting");
		for(Weapon weapon : weaponList)
		{
			System.out.println(weapon);
		}

		for(Armor armor : armorList)
		{
			System.out.println(armor);
		}

		for(Consumable consumable : consumableList)
		{
			System.out.println(consumable);
		}

		System.out.println("\n\n\n");

		System.out.println("After sorting");

		Collections.sort(weaponList, new WeaponSort());
		Collections.sort(armorList, new ArmorSort());
		Collections.sort(consumableList, new ConsumableSort());

		for(Weapon weapon : weaponList)
		{
			System.out.println(weapon);
		}

		for(Armor armor : armorList)
		{
			System.out.println(armor);
		}

		for(Consumable consumable : consumableList)
		{
			System.out.println(consumable);
		}
	}

	@After
	public void tearDown() throws Exception
	{

	}
}
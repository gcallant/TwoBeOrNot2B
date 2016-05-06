package TestItems;

import Item.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ItemTester
{
	ArrayList myList;

	@Before
	public void setUp() throws Exception
	{
		myList = new ArrayList<Storable>();
	}

	@Test
	public void testItemAdd()
	{
		myList.add(new Bow(1));
		myList.add(new Chainmail(2));
		myList.add(new Dagger(3));
		myList.add(new Bow(3));
		myList.add(new Bow(1));
		myList.add(new Hammer(3));
		myList.add(new Dagger(1));
		myList.add(new Healing(2));
		myList.add(new Staff(1));
		myList.add(new Sword(2));
	}

	@Test
	public void testSorting()
	{
		System.out.println("Before sorting");
		for(int x = 0; x < myList.size(); x++)
		{
			System.out.println(myList.get(x) + " " + ((Storable) myList.get(x)).getPower());
		}

		System.out.println("\n\n\n");

		System.out.println("After sorting");

		Collections.sort(myList, new CompareStorables());

		for(int x = 0; x < myList.size(); x++)
		{
			System.out.println(myList.get(x) + " " + ((Storable) myList.get(x)).getPower());
		}
	}

	@After
	public void tearDown() throws Exception
	{

	}
}
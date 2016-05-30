package TestDungeonGeneration;

import DungeonGeneration.GenerateDungeon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDungeonCreation
{
	GenerateDungeon dungeon;
	int x = 0, y = 0;

	@Before
	public void setUp() throws Exception
	{
		System.out.println(1);
	}

	@Test
	public void testDungeonOuput()
	{
		for(int z = 0; z < 0; z++)
		{
			System.out.println(x + " " + y);
			dungeon = new GenerateDungeon(x, y);
			try
			{
				dungeon.generatePath();
				System.out.println(dungeon);
				System.out.println();
				System.out.println(dungeon.output());
			}
			catch(StackOverflowError e)
			{
				//System.out.println("Dungeon " + x + " failed to be created");
			}
		}
	}

	@After
	public void tearDown() throws Exception
	{

	}
}
import TestDungeonGeneration.TestDungeonCreation;
import TestFactories.*;
import TestGameState.BattleTest;
import TestItem.ArmorTest;
import TestItem.ConsumableTest;
import TestItem.ItemTester;
import TestItem.WeaponTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Grant Callant on 5/5/2016.
 *
 * @author Grant Callant
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {BattleTest.class, ArmorTest.class, ConsumableTest.class, WeaponTest.class, ItemTester.class,
		                        TestDungeonCreation.class, ArmorFactoryTest.class,
		                       ConsumableFactoryTest.class, GenerateItemsTest.class, HeroFactoryTest.class,
		                       MonsterFactoryTest.class, NemesisPartyFactoryTest.class, WeaponFactoryTest.class})

public class TestSuite
{
	@Test
	public void testSomething()
	{

	}
}


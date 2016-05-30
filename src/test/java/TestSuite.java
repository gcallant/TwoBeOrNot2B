import TestFactories.*;
import TestGameState.*;
import TestItem.*;
import TestDungeonGeneration.*;
import TestPartyManagement.InventoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;


/**
 * Created by Grant Callant on 5/5/2016.
 *
 * @author Grant Callant
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BattleTest.class, ArmorTest.class, ConsumableTest.class, WeaponTest.class, ItemTester.class, InventoryTest.class, TestDungeonCreation.class, ArmorFactoryTest.class, ConsumableFactoryTest.class, GenerateItemsTest.class, HeroFactoryTest.class, MonsterFactoryTest.class, NemesisPartyFactoryTest.class, WeaponFactoryTest.class})

public class TestSuite
{
	@Test
	public void testSomething()
	{

	}
}


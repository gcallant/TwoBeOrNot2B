import TestItems.GenericInputTest;
import TestItems.ItemTester;
import TestItems.TestDungeonCreation;
import TestItems.TestPartySelectionAndCharacterCreation;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Grant Callant on 5/5/2016.
 *
 * @author Grant Callant
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {GenericInputTest.class, ItemTester.class, TestDungeonCreation.class,
		                       TestPartySelectionAndCharacterCreation.class})
public class TestSuite
{
	//nothing
}

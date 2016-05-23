import GameState.StateBase;
import TestItems.ItemTester;
import TestItems.TestDungeonCreation;
import TestItems.TestPartySelectionAndCharacterCreation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


/**
 * Created by Grant Callant on 5/5/2016.
 *
 * @author Grant Callant
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {ItemTester.class, TestDungeonCreation.class,
		                       TestPartySelectionAndCharacterCreation.class})

public class TestSuite {
	@Test
	public void testSomething(){
		StateBase mockedState = Mockito.mock(StateBase.class);
		Mockito.when(mockedState.displayCurrentState()).thenReturn("This is a test");

		assertEquals(mockedState.displayCurrentState(),"This is a test");
	}
}


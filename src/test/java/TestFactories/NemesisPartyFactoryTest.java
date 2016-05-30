package TestFactories;

import Factories.NemesisPartyFactory;
import PartyManagement.Party;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * Created by SaraPage on 5/29/2016.
 */
public class NemesisPartyFactoryTest
{
    private NemesisPartyFactory factory;
    private Party party;

    @Before
    public void setUp() throws Exception
    {
        factory = new NemesisPartyFactory();
        party = factory.getRandomNemesis(1,1);
    }

    @After
    public void tearDown() throws Exception
    {
        factory = null;
        party = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetRandomNemesis()
    {
        //checking for valid return type
        assertNotNull(party);

        //edge cases - invalid input
        exception.expect(IllegalArgumentException.class);
        party = factory.getRandomNemesis(0, 2);
        party = factory.getRandomNemesis(2,0);
        party = factory.getRandomNemesis(-5, -5);
    }

}
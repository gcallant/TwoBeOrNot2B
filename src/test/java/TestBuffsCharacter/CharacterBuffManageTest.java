package TestBuffsCharacter;

import BuffsCharacter.CharacterBuffManage;
import BuffsCharacter.CharacterBuffs;
import BuffsCharacter.DefendOther;
import Characters.A_Character;
import Factories.HeroFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class CharacterBuffManageTest
{
    private CharacterBuffManage buffManage;
    private A_Character affected;

    private int rounds;
    private A_Character contributor;
    private double amount;

    @Before
    public void setUp() throws Exception
    {
        rounds = 1;
        contributor = HeroFactory.createCharacter("Rogue", "contributorName");
        amount = 15.0;

        affected = HeroFactory.createCharacter("Mage", "affectedName");
        buffManage = new DefendOther(affected);
    }

    @After
    public void tearDown() throws Exception
    {
        contributor = null;
        affected = null;
        buffManage = null;
    }

    @Test
    public void testAddBuff() throws Exception
    {
        String name = "buffName";
        buffManage.addBuff(contributor, amount, rounds, name);
        assertNotNull(buffManage.getBuff());
    }

    @Test
    public void testGetAffected() throws Exception
    {
        assertEquals(affected, buffManage.getAffected());
    }

    @Test
    public void testClear() throws Exception
    {
        buffManage.clear();
        assertNull(buffManage.getBuff());
    }

    @Test
    public void testApply() throws Exception
    {
        assertFalse(buffManage.apply());
    }

    @Test
    public void testIsInEffect() throws Exception
    {
        buffManage.clear();
        assertFalse(buffManage.isInEffect());

        String name = "buffName";
        buffManage.addBuff(contributor, amount, rounds, name);
        assertTrue(buffManage.isInEffect());
    }

}
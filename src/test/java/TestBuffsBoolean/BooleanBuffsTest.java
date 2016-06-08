package TestBuffsBoolean;

import BuffsBoolean.BooleanBuffs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class BooleanBuffsTest
{
    private BooleanBuffs buff;
    private int rounds = 2;
    private String source = "Warrior";

    @Before
    public void setUp() throws Exception
    {
        buff = new BooleanBuffs(rounds, source);
    }

    @After
    public void tearDown() throws Exception
    {
        buff = null;
    }

    @Test
    public void testDecrement() throws Exception
    {
        //decrement() returns true only when rounds is 0
        assertFalse(buff.decrement());

        assertTrue(buff.decrement());
    }

    @Test
    public void testGetSource() throws Exception
    {
        assertEquals(source, buff.getSource());
    }

    @Test
    public void testReplace() throws Exception
    {
        //If rounds is replaced with 1 and then decrement() is called, it should return true
        buff.replace(1);
        assertTrue(buff.decrement());
    }
}
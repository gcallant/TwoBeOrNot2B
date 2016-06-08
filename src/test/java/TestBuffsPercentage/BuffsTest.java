package TestBuffsPercentage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import BuffsPercentage.Buffs;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class BuffsTest
{
    private Buffs buffs;
    private double buff;
    private int rounds;
    private String source;
    private double delta;

    @Before
    public void setUp() throws Exception
    {
        delta = 10^(-6); //for assertEquals with doubles

        buff = 2.0;
        rounds = 2;
        source = "Warrior";
        buffs = new Buffs(buff, rounds, source);
    }

    @After
    public void tearDown() throws Exception
    {
        source = null;
        buffs = null;
    }

    @Test
    public void testDecrement() throws Exception
    {
        //decrement() returns true when rounds is 0
        assertFalse(buffs.decrement());
        assertTrue(buffs.decrement());
    }

    @Test
    public void testGetSource() throws Exception
    {
        assertEquals(source, buffs.getSource());
    }

    @Test
    public void testBuffAmount() throws Exception
    {
        //buffAmount is buff - 1.0
        assertEquals(1.0, buffs.buffAmount(), delta);
    }

    @Test
    public void testReplace() throws Exception
    {
        buffs.replace(4.0, 1);
        assertTrue(buffs.decrement());
        assertEquals(3.0, buffs.buffAmount(), delta);
    }
}
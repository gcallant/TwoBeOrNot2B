package TestBuffsBoolean;

import BuffsBoolean.BooleanBuffList;
import BuffsBoolean.BooleanBuffs;
import BuffsBoolean.ConfusionDebuff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class BooleanBuffListTest
{
    private BooleanBuffList buffList;
    private int rounds;
    private String name;

    @Before
    public void setUp() throws Exception
    {
        rounds = 2;
        name = "Warrior";
        buffList = new ConfusionDebuff(name);
    }

    @After
    public void tearDown() throws Exception
    {
        buffList = null;
    }

    //Since I cannot access the list from BooleanBuffList,
    //the only way to test addBuff() and decrementList() is by checking its size.
    @Test
    public void testAddBuff() throws Exception
    {
        buffList.addBuff(rounds, name);
        assertEquals(1, buffList.size());
        buffList.clear();
    }

    @Test
    public void testDecrementList() throws Exception
    {
        //The buff will only be removed from the list when the rounds of the buff is 0

        //size() should be 1
        buffList.addBuff(rounds,name);
        buffList.decrementList();
        assertEquals(1, buffList.size());
        buffList.clear();

        //size() should be 0 when decrement() within decrementList() is called
        rounds = 1;
        buffList.addBuff(rounds, name);
        buffList.decrementList();
        assertEquals(0, buffList.size());
        buffList.clear();
    }

    @Test
    public void testIsInEffect() throws Exception
    {
        buffList.addBuff(rounds, name);
        assertTrue(buffList.isInEffect());
    }

}
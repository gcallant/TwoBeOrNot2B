package TestBuffsPercentage;

import BuffsPercentage.AttackBuffs;
import BuffsPercentage.BuffList;
import BuffsPercentage.ChargeBuff;
import BuffsPercentage.DamageReductionBuff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class BuffListTest
{
    private BuffList attack;
    private BuffList charge;
    private BuffList damageReduction;
    private int rounds;
    private double buff;
    private String name;
    private double delta;

    @Before
    public void setUp() throws Exception
    {
        delta = 10^(-6); //for assertEquals with doubles

        rounds = 2;
        buff = 4.0;
        name = "Mage";
        attack = new AttackBuffs(name);
        charge = new ChargeBuff(name);
        damageReduction = new DamageReductionBuff(name);
    }

    @After
    public void tearDown() throws Exception
    {
        attack = null;
        charge = null;
        damageReduction = null;
    }

    //Code will be exactly the same with any other buff/debuff, so only tested one of them here
    @Test
    public void testAddBuffAttack() throws Exception
    {
        attack.addBuff(buff, rounds, name);
        assertEquals(1, attack.size());
        attack.clear();
    }

    @Test
    public void testDecrementListAttack() throws Exception
    {
        //The buff will only be removed from the list when the rounds of the buff is 0

        //size() should be 1
        attack.addBuff(buff, rounds,name);
        attack.decrementList();
        assertEquals(1, attack.size());
        attack.clear();

        //size() should be 0 when decrement() within decrementList() is called
        rounds = 1;
        attack.addBuff(buff, rounds, name);
        attack.decrementList();
        assertEquals(0, attack.size());
        attack.clear();
    }

    //Tested the 3 ways of calculating getAmount() - the rest are similar to these three
    @Test
    public void testGetAmountAttack() throws Exception
    {
        //For AttackBuff, the getAmount() returns each of the buffAmount from the list
        //Let's add a couple of attack buffs first
        attack.clear();
        attack.addBuff(buff, rounds, name);
        double buff2 = buff + 5.0;
        attack.addBuff(buff2, rounds + 2, "Warrior");

        double totalBuff = (buff - 1.0) + (buff2 - 1.0);
        assertEquals(totalBuff, attack.getAmount(), delta);
    }

    @Test
    public void testGetAmountCharge() throws Exception
    {
        //For ChargeBuff, if there are buffs in the list, getAmount() returns the sum of each of the buffAmount
        charge.addBuff(buff, rounds, name);
        double buff2 = buff + 2;
        charge.addBuff(buff2, rounds + 2, "Warrior");

        double totalBuff = (buff - 1) + (buff2 - 1);
        assertEquals(totalBuff, charge.getAmount(), delta);

        //If there are no buffs in the list, it will return 1.0
        charge.clear();
        assertEquals(1.0, charge.getAmount(), delta);
    }

    @Test
    public void testGetAmountDamageReduction() throws Exception
    {
        //For DamageReduction, getAmount() returns the min of the sum of the buffAmounts or 2.0
        damageReduction.addBuff(buff, rounds, name);
        double buff2 = buff + 3;
        damageReduction.addBuff(buff2, rounds, name);

        double totalBuff = (buff - 1) + (buff2 = 1);
        double min = Math.min(2.0, totalBuff);
        assertEquals(min, damageReduction.getAmount(), delta);
    }
}
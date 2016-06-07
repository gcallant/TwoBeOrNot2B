package TestBuffsCharacter;

import BuffsCharacter.CharacterBuffs;
import Characters.A_Character;
import Factories.HeroFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SaraPage on 6/7/2016.
 */
public class CharacterBuffsTest
{
    private CharacterBuffs characterBuffs;
    private int rounds;
    private String name;
    private A_Character contributor;
    private double amount;
    private double delta;

    @Before
    public void setUp() throws Exception
    {
        delta = 10^(-6);

        rounds = 1;
        name = "Warrior";
        contributor = HeroFactory.createCharacter("Rogue", "heroName");
        amount = 10.0;

        characterBuffs = new CharacterBuffs(contributor, amount, rounds, name);
    }

    @After
    public void tearDown() throws Exception
    {
        name = null;
        contributor = null;
        characterBuffs = null;
    }

    @Test
    public void testDecrement() throws Exception
    {
        assertTrue(characterBuffs.decrement());
    }

    @Test
    public void testGetSource() throws Exception
    {
        assertEquals(name, characterBuffs.getSource());
    }

    @Test
    public void testReplace() throws Exception
    {
        characterBuffs.replace(contributor, 15.0, 2);
        assertEquals(15, characterBuffs.getAmount(), delta);
        assertFalse(characterBuffs.decrement());
    }

    @Test
    public void testGetContributor() throws Exception
    {
        assertEquals(contributor, characterBuffs.getContributor());
    }

    @Test
    public void testGetAmount() throws Exception
    {
        assertEquals(amount, characterBuffs.getAmount(), delta);
    }

}
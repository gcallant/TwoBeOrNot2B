package TestHeroAndMonsterCreation;

import Characters.A_Character;
import Factories.MonsterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by SaraPage on 5/22/2016.
 */
public class MonsterCreationTest
{
    MonsterFactory factory;
    @Before
    public void setUp() throws Exception
    {
        factory = new MonsterFactory();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    //@Test
    /*public void createCharacter() throws Exception
    {
        int level = 2;
        assertNotNull(factory.createMonster("Orc", "monsterName", level));
    }*/
}

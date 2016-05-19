package Factories;
import Characters.*;
import Item.*;

/**
 * Created by gm14793 on 5/13/16.
 */
public class MonsterFactory
{

    public A_Character createMonster(String monsterType, String monsterName)
    {
        switch(monsterType)
        {
            case "Goblin": return new Goblin(monsterName, 50, 3, 10, new Cloth(1), new Dagger(1));
            case "Orc": return new Orc(monsterName, 100, 5, 5, new Leather(1), new Sword(1));
            case "Ogre": return new Ogre(monsterName, 200, 7, 3, new Leather(1), new Hammer(1));
        }
        System.out.println(monsterType + " is not a  recognized monster. You have been provided with a Goblin.");
        return new Goblin("Goblin", 50, 3, 4, new Cloth(1), new Dagger(1));
    }

}

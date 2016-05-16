package Factories;
import Characters.*;

/**
 * Created by gm14793 on 5/13/16.
 */
public class MonsterFactory
{

    public A_Monster createMonster(String monsterType)
    {
        switch(monsterType)
        {
            case "Goblin": return new Goblin("Goblin", 50, 3, 4, 5, 4);
            case "Orc": return new Orc("Orc", 100, 5, 3, 4, 5);
            case "Ogre": return new Ogre("Ogre", 200, 7, 3, 3, 6);
        }
        System.out.println(monsterType + " is not a  recognized monster. You have been provided with a Goblin.");
        return new Goblin("Goblin", 50, 3, 4, 5, 4);
    }

}

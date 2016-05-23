package Factories;
import Characters.*;
import Item.*;

/**
 * Created by gm14793 on 5/13/16.
 */
public class MonsterFactory
{

    public A_Character createMonster(String monsterType, String monsterName, int level)
    {
        switch(monsterType)
        {
            case "Goblin":
                return new Goblin(monsterName, 50, 5, 10, new Cloth(1), new Dagger(1), level);
//                return new Goblin(monsterName, 1, 3, 10, new Cloth(1), new Dagger(1), level);
            case "Orc":
                return new Orc(monsterName, 100, 7, 5, new Leather(1), new Sword(1), level);
//                return new Orc(monsterName, 1, 5, 5, new Leather(1), new Sword(1), level);
            case "Ogre":
                return new Ogre(monsterName, 200, 10, 3, new Leather(1), new Hammer(1), level);
//                return new Ogre(monsterName, 1, 7, 3, new Leather(1), new Hammer(1), level);
            case "Undead Cleric":
                return new UndeadCleric(monsterName, 150, 8, 2, new Cloth(1), new Staff(1), level);
//                return new UndeadCleric(monsterName, 1, 8, 2, new Cloth(1), new Staff(1), level);
            case "War Chief":
                return new WarChief(monsterName, 300, 10, 5, new Chainmail(1), new Hammer(1), level);
//                return new WarChief(monsterName, 1, 10, 5, new Chainmail(1), new Hammer(1), level);
        }
        System.out.println(monsterType + " is not a  recognized monster. You have been provided with a Goblin.");
        return new Goblin("Goblin", 50, 3, 4, new Cloth(1), new Dagger(1), level);
    }

}

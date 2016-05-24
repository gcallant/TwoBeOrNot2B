package Factories;
import Characters.*;
import Item.*;
import Monsters.*;

/**
 * Created by gm14793 on 5/13/16.
 */
public class MonsterFactory
{

    public A_Character createMonster(String monsterType, String monsterName, int level, boolean normal)
    {
        switch(monsterType)
        {
            case "Goblin":
                if(normal)
                {
                    return new Goblin(monsterName, 50, 5, 10, new Cloth(level), new Dagger(level), level);
                }
                return new Goblin(monsterName, 1, 3, 10, new Cloth(1), new Dagger(1), 1);
            case "Orc":
                if(normal)
                {
                    return new Orc(monsterName, 100, 7, 5, new Leather(level), new Sword(level), level);
                }
                return new Orc(monsterName, 1, 5, 5, new Leather(1), new Sword(1), 1);
            case "Ogre":
                if(normal)
                {
                    return new Ogre(monsterName, 200, 10, 3, new Leather(level), new Hammer(level), level);
                }
                return new Ogre(monsterName, 1, 7, 3, new Leather(1), new Hammer(1), 1);
            case "Undead Cleric":
                if(normal)
                {
                    return new UndeadCleric(monsterName, 150, 8, 2, new Cloth(level), new Staff(level), level);
                }
                return new UndeadCleric(monsterName, 1, 8, 2, new Cloth(1), new Staff(1), 1);
            case "War Chief":
                if(normal)
                {
                    return new WarChief(monsterName, 300, 10, 5, new Chainmail(level), new Hammer(level), level);
                }
                return new WarChief(monsterName, 1, 10, 5, new Chainmail(1), new Hammer(1), 1);
            case "Assassin":
                if(normal)
                {
                    return new Assassin(monsterName, 75, 8, 12, new Leather(level), new Dagger(level), level);
                }
                return new Assassin(monsterName, 1, 8, 12, new Leather(1), new Dagger(1), 1);
            case "Skeleton":
                if(normal)
                {
                    return new Skeleton(monsterName, 80, 14, 4, new Cloth(level), new Sword(level), level);
                }
                return new Skeleton(monsterName, 1, 9, 4, new Cloth(1), new Sword(1), 1);
            case "Dire Wolf":
                if(normal)
                {
                    return new DireWolf(monsterName, 125, 15, 8, new Leather(level), new Hammer(level), level);
                }
                return new DireWolf(monsterName, 1, 15, 8, new Leather(1), new Hammer(1), 1);
        }
        System.out.println(monsterType + " is not a  recognized monster. You have been provided with a Goblin.");
        return new Goblin("Goblin", 50, 3, 4, new Cloth(1), new Dagger(1), level);
    }

}

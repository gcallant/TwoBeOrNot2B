package Factories;
import Characters.*;
import Item.Chainmail;
import Item.Dagger;
import Item.Hammer;
import Item.Sword;

/**
 * Created by gm14793 on 5/13/16.
 */
public class MonsterFactory
{

    public A_Character createMonster(String monsterType)
    {
        switch(monsterType)
        {
            case "Goblin": return new Goblin("Goblin", 50, 3, 4, 5, new Chainmail(1), new Dagger(1));
            case "Orc": return new Orc("Orc", 100, 5, 3, 4, new Chainmail(1), new Sword(1));
            case "Ogre": return new Ogre("Ogre", 200, 7, 3, 3, new Chainmail(1), new Hammer(1));
        }
        System.out.println(monsterType + " is not a  recognized monster. You have been provided with a Goblin.");
        return new Goblin("Goblin", 50, 3, 4, 5, new Chainmail(1), new Dagger(1));
    }

}

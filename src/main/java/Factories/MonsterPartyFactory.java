package Factories;
import java.util.*;
import Characters.*;
import Item.Chainmail;
import Item.Dagger;
import Item.Hammer;
import Item.Sword;

public class MonsterPartyFactory
{



    public Party defaultMonsterParty(int difficulty)//generates a party using default settings
    {
        ArrayList<A_Character> monsters = new ArrayList<A_Character>();
        Goblin goblin1 = new Goblin("Goblin",(int)(50*difficulty), (int)(3*difficulty), (int)(4*difficulty),(int)(5*difficulty), new Chainmail(difficulty), new Dagger(difficulty));
        monsters.add(goblin1);
        Goblin goblin2 = new Goblin("Goblin",(int)(50*difficulty), (int)(3*difficulty), (int)(4*difficulty),(int)(5*difficulty), new Chainmail(difficulty), new Dagger(difficulty));
        monsters.add(goblin2);
        Orc orc = new Orc("Orc",(int)(100*difficulty), (int)(5*difficulty), (int)(3*difficulty), (int)(4*difficulty), new Chainmail(difficulty), new Sword(difficulty));
        monsters.add(orc);
        Ogre ogre = new Ogre("Ogre", (int)(200*difficulty), (int)(7*difficulty), (int)(3*difficulty), (int)(3*difficulty), new Chainmail(difficulty), new Hammer(difficulty));
        monsters.add(ogre);
        return new Party(monsters);
    }

    public MonsterParty randomMonsterParty(int diff)//Will generate a random party of monsters
    {
        return null;
    }




}

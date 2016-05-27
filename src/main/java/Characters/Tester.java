package Characters;

import Factories.*;

public class Tester
{
    public static void main(String[] args)
    {
        MonsterFactory mFac = new MonsterFactory();
        A_Character test = mFac.createMonster("Goblin","Goblin",1, true);
        System.out.println(test);
    }
}

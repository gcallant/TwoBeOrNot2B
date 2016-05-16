package Characters;

import Factories.*;

public class Tester
{
    public static void main(String[] args)
    {
        MonsterFactory mFac = new MonsterFactory();
        A_Monster test = mFac.createMonster("Goblin");
        System.out.println(test);
    }
}

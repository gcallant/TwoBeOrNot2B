package Characters;

import StringTester.TestString;

import java.util.Scanner;

/**
 * Created by Michael on 5/22/2016.
 */
public class LevelUp
{
    public static void levelUp(A_Character character)
    {
        Scanner kb = new Scanner(System.in);

        int num = 3;
        int choice = 0;

        while(num > 0)
        {
            System.out.println("You have " + num + " points to spend!");

            System.out.println("Choose what to upgrade!\n1) Health(25)\n2) Strength\n3) Dexterity");

            choice = TestString.ensureInt(3);

            switch(choice)
            {
                case 1: character.upgradeHealth();
                    break;
                case 2: character.upgradeStrength();
                    break;
                case 3: character.upgradeDexterity();
                    break;
            }

            num--;
        }
    }
}

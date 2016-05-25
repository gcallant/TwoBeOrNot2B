package Characters;

import PartyManagement.Party;
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

        character.upgradepower();
        character.upgradecunning();
        while(num > 0)
        {
            System.out.println("You have " + num + " points to spend!");

            System.out.println("Choose what to upgrade!\n1) Health(25)\n2) power\n3) cunning");

            choice = TestString.ensureInt(3);

            switch(choice)
            {
                case 1: character.upgradeHealth();
                    break;
                case 2: character.upgradepower();
                    break;
                case 3: character.upgradecunning();
                    break;
            }

            num--;
        }
    }

    public static void increaseAbilities(Party party)
    {
        for(int x = 0; x < party.size(); x++)
        {
            party.getCharacter(x).upgradeAbilities();
        }
    }
}

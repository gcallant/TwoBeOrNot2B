package Characters;

import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

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

        character.upgradeHealth();
        character.upgradePower();
        character.upgradeCunning();
        while(num > 0)
        {
            Display.displayMessage("You have " + num + " points to spend!");

            Display.displayMessage("Choose what to upgrade!\n1) Health(35)\n2) Power\n3) cunning");

            choice = TestString.ensureInt(3);

            switch(choice)
            {
                case 1: character.upgradeHealth();
                    break;
                case 2:
                    character.upgradePower();
                    break;
                case 3:
                    character.upgradeCunning();
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

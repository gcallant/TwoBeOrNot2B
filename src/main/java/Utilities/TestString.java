package Utilities;

import Characters.A_Character;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Michael on 5/6/2016.
 */
public class TestString
{
    public static int getCharacterChoice(List<A_Character> list)
    {
        Scanner kb = new Scanner(System.in);
        String validInput;
        int itemIndex = 1, choice = 0;
        for(A_Character character : list)
        {
            Display.displayMessage(itemIndex + ".)" + character.inventoryDisplay());
            itemIndex++;
        }
        return getInput(itemIndex);
    }

    private static int getInput(int itemIndex)
    {
        if(itemIndex == 1)
        {
            return -1;
        }
        Scanner kb = new Scanner(System.in);
        String validInput;
        int choice = 0;
        boolean badInput;

        choice = loop(itemIndex,kb);
        if(choice == itemIndex)
        {
            return -1;
        }
        return choice - 1;
    }

    public static void enterInput()
    {
        Scanner kb = new Scanner(System.in);
        kb.nextLine();
    }

    private static int loop(int max, Scanner kb)
    {
        boolean badInput;
        String input;
        int choice = 0;
        do
        {
            badInput = false;
            input = kb.nextLine();
            try
            {
                choice = Integer.parseInt(input);
                if(choice > max || choice < 1)
                {
                    badInput = true;
                }
            }
            catch(NumberFormatException e)
            {
                badInput = true;
            }
            if(badInput)
            {
                Display.displayMessage("Bad input. Try a number between 1 and " + max);
            }
        }while(badInput);
        return choice;
    }

    public static int ensureInt(int max)
    {
        Scanner kb = new Scanner(System.in);
        String input;
        boolean badInput;
        int choice = -1;

        return loop(max,kb);
    }

    public static char ensureChar(char[] validInputs)
    {
        Scanner kb = new Scanner(System.in);
        String input;
        char letter = '@';
        boolean badInput;

        do
        {
            badInput = false;
            input = kb.nextLine();
            if(input.length() == 1)
            {
                letter = input.charAt(0);
                if(checkValidChar(letter, validInputs))
                {
                    return letter;
                }
                else
                {
                    badInput = true;
                }
            }
            else
            {
                badInput = true;
            }
            if(badInput)
            {
                System.out.print("Bad input try: ");
                printChars(validInputs);
                Display.displayMessage("");
            }
        }while(badInput);
        return letter;
    }

    private static void printChars(char[] validInputs)
    {
        for(int x = 0; x < validInputs.length; x++)
        {
            System.out.print(validInputs[x] + " ");
        }
    }

    private static boolean checkValidChar(char input, char[] validInputs)
    {
        for(int x = 0; x < validInputs.length; x++)
        {
            if(input == validInputs[x])
            {
                return true;
            }
        }
        return false;
    }
}

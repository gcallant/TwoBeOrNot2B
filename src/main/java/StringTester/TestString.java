package StringTester;

import Characters.A_Character;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Michael on 5/6/2016.
 */
public class TestString
{
    public static String testInput(String command, String[] validInputs)
    {
        for(int x = 0; x < validInputs.length; x++)
        {
            if(command.toLowerCase().equals(validInputs[x]))
            {
                return command.toLowerCase();
            }
        }
        return "";
    }

    public static int getCharacterChoice(ArrayList<A_Character> list)
    {
        Scanner kb = new Scanner(System.in);
        String validInput;
        int itemIndex = 1, choice = 0;
        for(A_Character character : list)
        {
            System.out.println(itemIndex + ".)" + character.inventoryDisplay());
            itemIndex++;
        }
        return getInput(itemIndex);
    }

    public static int getConsumableChoice(ArrayList<Consumable> list)
    {
        Scanner kb = new Scanner(System.in);
        String validInput;
        int itemIndex = 1, choice = 0;
        for(Consumable consumable : list)
        {
            System.out.println(itemIndex + ".)" + consumable.toString());
            itemIndex++;
        }
        return getInput(itemIndex);
    }

    public static int getArmorChoice(ArrayList<Armor> list)
    {
        Scanner kb = new Scanner(System.in);
        String validInput;
        int itemIndex = 1, choice = 0;
        for(Armor armor : list)
        {
            System.out.println(itemIndex + ".)" + armor.toString());
            itemIndex++;
        }
        return getInput(itemIndex);
    }

    public static int getWeaponChoice(ArrayList<Weapon> list)
    {
        Scanner kb = new Scanner(System.in);
        String validInput;
        int itemIndex = 1, choice = 0;
        for(Weapon weapon : list)
        {
            System.out.println(itemIndex + ".)" + weapon.toString());
            itemIndex++;
        }
        return getInput(itemIndex);
    }

    public static int getInput(int itemIndex)
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
                System.out.println("Bad input. Try a number between 1 and " + max);
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
                System.out.println();
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

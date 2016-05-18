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
        return getInput(getInput(itemIndex));
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
        Scanner kb = new Scanner(System.in);
        String validInput;
        int choice = 0;
        boolean badInput;
        do
        {
            badInput = false;
            validInput = kb.nextLine();
            try
            {
                choice = Integer.parseInt(validInput);
                if(choice > itemIndex || choice < 1)
                {
                    badInput = true;
                }
            }
            catch(NumberFormatException e)
            {
                badInput = true;
            }
        }while(badInput);
        if(choice == itemIndex)
        {
            return -1;
        }
        return choice - 1;
    }
}

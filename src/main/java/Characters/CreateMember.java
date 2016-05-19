package Characters;

import Factories.HeroFactory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Michael on 5/18/2016.
 */
public class CreateMember
{
    public static A_Character createMember()
    {
        String type, name = "";
        ArrayList<String> names = new ArrayList<String>();

        names.add("Rogue");
        names.add("Paladin");
        names.add("Warrior");
        names.add("Mage");

        Scanner kb = new Scanner(System.in);

        System.out.println("Select the class of hero you want: ");
        System.out.println(Warrior.Information());
        System.out.println(Mage.Information());
        System.out.println(Rogue.Information());
        System.out.println(Paladin.Information());

        type = kb.nextLine();
        if(type.length() < 3)
        {
            return null;
        }

        String part1 = type.substring(0,1);
        String part2 = type.substring(1);
        type = part1.toUpperCase() + part2.toLowerCase();
        if(!names.contains(type))
        {
            return null;
        }

        System.out.println("You chose a " + type);
        System.out.println("Enter the name for your character or 'cancel' to cancel: ");

        while(name.isEmpty())
        {
            name = kb.nextLine();
        }

        if(name.toLowerCase().equals("cancel"))
        {
            return null;
        }

        return new HeroFactory().createCharacter(type, name);
    }

    public static boolean confirm()
    {
        String input;
        Scanner kb = new Scanner(System.in);

        System.out.println("Are you satisfied with your selection? (yes or no)");

        do
        {
            input = kb.nextLine();
        }while(!input.toLowerCase().equals("yes") && !input.toLowerCase().equals("no"));

        return input.toLowerCase().equals("yes");
    }
}

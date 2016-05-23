package Characters;

import Factories.HeroFactory;
import StringTester.TestString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Michael on 5/18/2016.
 */
public class CreateMember
{
    public static A_Character createMember()
    {
        String name = "";
        List<String> names = new ArrayList<String>();

        names.add("Warrior");
        names.add("Mage");
        names.add("Rogue");
        names.add("Paladin");

        Scanner kb = new Scanner(System.in);

        System.out.println("Select the class of hero you want: ");
        System.out.println("1) " + Warrior.Information());
        System.out.println("2) " + Mage.Information());
        System.out.println("3) " + Rogue.Information());
        System.out.println("4) " + Paladin.Information());

        int choice = TestString.ensureInt(4);
        String type = names.get(choice - 1);

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
        int input;
        Scanner kb = new Scanner(System.in);

        System.out.println("Are you satisfied with your selection?\n1) Yes\n2) No");

        input = TestString.ensureInt(2);
        return input == 1;
    }
}

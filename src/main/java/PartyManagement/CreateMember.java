package PartyManagement;

import Characters.A_Character;
import Factories.HeroFactory;
import Heroes.*;
import Utilities.Display;
import Utilities.TestString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michael on 5/18/2016.
 */
public class CreateMember
{
    @Nullable
    public static A_Character createMember(List<A_Character> party)
    {
        String name = "";
        List<String> classes = new ArrayList<String>();

        classes.add("Warrior");
        classes.add("Mage");
        classes.add("Rogue");
        classes.add("Paladin");
        classes.add("Ranger");
        classes.add("Summoner");
        classes.add("Defender");

        Scanner kb = new Scanner(System.in);

        Display.displayMessage("Select the class of hero you want: ");
        Display.displayMessage("1) " + Warrior.Information());
        Display.displayMessage("2) " + Mage.Information());
        Display.displayMessage("3) " + Rogue.Information());
        Display.displayMessage("4) " + Paladin.Information());
        Display.displayMessage("5) " + Ranger.Information());
        Display.displayMessage("6) " + Summoner.Information());
        Display.displayMessage("7) " + Defender.Information());

        int choice = TestString.ensureInt(classes.size());
        String type = classes.get(choice - 1);

        Display.displayMessage("You chose a " + type);
        Display.displayMessage("Enter the name for your character or 'cancel' to cancel: ");

        while(name.isEmpty())
        {
            name = kb.nextLine();

            for(A_Character hero: party)
            {
                if(hero.getName().equals(HeroFactory.createCharacter(type, name).getName()))
                {
                    Display.displayMessage("You're already got one such mate in your party- why not try another?" +
                                                   "\nPress Enter");
                    TestString.enterInput();
                    return null;
                }
            }
        }

        if(name.toLowerCase().equals("cancel"))
        {
            return null;
        }

        return HeroFactory.createCharacter(type, name);
    }

    public static A_Character createRandomMember(final List<String> randomNameList)
    {
        String classType = "";
        List<String> classes = new ArrayList<String>();

        classes.add("Warrior");
        classes.add("Mage");
        classes.add("Rogue");
        classes.add("Paladin");
        classes.add("Ranger");
        classes.add("Summoner");
        classes.add("Defender");

        String name = "";
        Random random = new Random(System.currentTimeMillis());
        int randomNameIndex = random.nextInt(randomNameList.size());
        int randomClassIndex = random.nextInt(classes.size());
        name = randomNameList.get(randomNameIndex);
        classType = classes.get(randomClassIndex);

        return HeroFactory.createCharacter(classType, name);
    }

    public static boolean confirm()
    {
        int input;
        Scanner kb = new Scanner(System.in);

        Display.displayMessage("Are you satisfied with your selection?\n1) Yes\n2) No");

        input = TestString.ensureInt(2);
        return input == 1;
    }
}

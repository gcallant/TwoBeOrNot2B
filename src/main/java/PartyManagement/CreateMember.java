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
    public static A_Character createMember()
    {
        String name = "";
        List<String> names = new ArrayList<String>();

        names.add("Warrior");
        names.add("Mage");
        names.add("Rogue");
        names.add("Paladin");
        names.add("Ranger");
        names.add("Summoner");
        names.add("Defender");

        Scanner kb = new Scanner(System.in);

        Display.displayMessage("Select the class of hero you want: ");
        Display.displayMessage("1) " + Warrior.Information());
        Display.displayMessage("2) " + Mage.Information());
        Display.displayMessage("3) " + Rogue.Information());
        Display.displayMessage("4) " + Paladin.Information());
        Display.displayMessage("5) " + Ranger.Information());
        Display.displayMessage("6) " + Summoner.Information());
        Display.displayMessage("7) " + Defender.Information());

        int choice = TestString.ensureInt(names.size());
        String type = names.get(choice - 1);

        Display.displayMessage("You chose a " + type);
        Display.displayMessage("Enter the name for your character or 'cancel' to cancel: ");

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

        return new HeroFactory().createCharacter(classType, name);
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

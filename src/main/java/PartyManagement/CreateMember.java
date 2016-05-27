package PartyManagement;

import Characters.A_Character;
import Factories.HeroFactory;
import Heroes.*;
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

        Scanner kb = new Scanner(System.in);

        System.out.println("Select the class of hero you want: ");
        System.out.println("1) " + Warrior.Information());
        System.out.println("2) " + Mage.Information());
        System.out.println("3) " + Rogue.Information());
        System.out.println("4) " + Paladin.Information());
        System.out.println("5) " + Ranger.Information());
        System.out.println("6) " + Summoner.Information());

        int choice = TestString.ensureInt(names.size());
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

        System.out.println("Are you satisfied with your selection?\n1) Yes\n2) No");

        input = TestString.ensureInt(2);
        return input == 1;
    }
}

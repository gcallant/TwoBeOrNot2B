package PartyManagement;

import Characters.A_Character;
import Factories.HeroFactory;
import Factories.MonsterFactory;
import Heroes.*;
import StringTester.TestString;
import Utilities.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Michael on 5/25/2016.
 */
public class SummonMonster
{
    public static boolean createMember(A_Character owner, Party partyToAdd)
    {
        String name = "";
        int total = 1;
        List<String> names = new ArrayList<String>();

        names.add("Skeleton");
        names.add("Dire Wolf");
        names.add("Undead Cleric");
        names.add("Sapling");

        Scanner kb = new Scanner(System.in);

        for(String string : names)
        {
            Display.displayMessage(total + ") " + string);
            total++;
        }

        Display.displayMessage(total + ") cancel");

        int choice = TestString.ensureInt(total);

        if(choice == total)
        {
            return true;
        }

        String type = names.get(choice -1);

        A_Character character = new MonsterFactory().createMonster(type, type + " summon", owner.getLevel(), true, partyToAdd.getFloorLevel());
        character.setSummon(owner);
        owner.getConditions().summon();

        partyToAdd.addCharacter(character);

        return false;
    }

}

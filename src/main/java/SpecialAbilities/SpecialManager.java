package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.TestString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Michael on 5/21/2016.
 */
public class SpecialManager
{
    Random rand;
    private List<SpecialAbility> specialAbilities;

    public SpecialManager()
    {
        rand = new Random();
        specialAbilities = new ArrayList<SpecialAbility>();
    }

    public void addSpecialAbility(SpecialAbility specialAbility)
    {
        specialAbilities.add(specialAbility);
    }

    public boolean chooseSpecialAbility(A_Character character, Party allies, Party enemies)
    {
        int options = 1;
        int choice;

        for(SpecialAbility specialAbility : specialAbilities)
        {
            Display.displayMessage(options + ") " + specialAbility);
            options++;
        }
        Display.displayMessage(options + ") " + "cancel");

        choice = TestString.ensureInt(options);

        if(options == choice)
        {
            return true;
        }

        return specialAbilities.get(choice - 1).executeAbility(character, allies, enemies);
    }

    public boolean executeRandomAbility(A_Character character, Party allies, Party enemies)
    {
        specialAbilities.get(rand.nextInt(specialAbilities.size())).executeAbilityRandom(character, allies, enemies);
        return false;
    }

    public void upgradeAbilities()
    {
        int choice = 0;
        int choices = 1;
        List<SpecialAbility> upgradeAble = new ArrayList<SpecialAbility>();

        for(SpecialAbility specialAbility : specialAbilities)
        {
            if(specialAbility.canUpgrade())
            {
                upgradeAble.add(specialAbility);
            }
        }

        Display.displayMessage("Choose an ability to upgrade!!!!");
        for(SpecialAbility specialAbility : upgradeAble)
        {
            Display.displayMessage(choices + ") " + specialAbility.toString());
            choices++;
        }
        Display.displayMessage(choices + ") cancel");

        choice = TestString.ensureInt(choices);

        if(choice != choices)
        {
            SpecialAbility toChange;
            toChange = upgradeAble.get(choice - 1);
            specialAbilities.remove(toChange);
            specialAbilities.add(toChange.upgrade());
        }

    }
}

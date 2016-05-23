package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import StringTester.TestString;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michael on 5/21/2016.
 */
public class SpecialManager
{
    private ArrayList<SpecialAbility> specialAbilities;
    Random rand;

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
            System.out.println(options + ") " + specialAbility);
            options++;
        }
        System.out.println(options + ") " + "cancel");

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
}

package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/25/2016.
 */
public class BloodSense extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        executeAbilityRandom(character, allies, enemies);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        List<Integer> bleeders = new ArrayList<Integer>();
        int count = 0;

        for(int x = 0; x < enemies.size(); x++)
        {
            A_Character choiceToStrike = enemies.getCharacter(x);
            if(choiceToStrike.getConditions().isBleeding())
            {
                bleeders.add(count);
            }
            count++;
        }

        if(bleeders.size() > 0)
        {
            for(Integer integer : bleeders)
            {
                abilityExecution(character, enemies.getCharacter(integer));
            }
        }
        else
        {
            new MassBleed().executeAbilityRandom(character, allies, enemies);
        }
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        if(character.attack(choiceToStrike))
        {
            Display.displayMessage(character.getName() + " healed from " + choiceToStrike.getName() + "'s blood for " + (int)((double)character.getMaxHealth()*.03));
            character.heal((int)((double)character.getMaxHealth()*.03));
        }
    }

    public boolean canUpgrade()
    {
        return true;
    }

    public SpecialAbility upgrade()
    {
        return new BladedFlurry();
    }

    public String toString()
    {
        return "Blood Sense";
    }

    public static String description()
    {
        return "     - Blood Sense";
    }
}

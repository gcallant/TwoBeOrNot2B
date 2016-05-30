package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/21/2016.
 */
public class SneakAttack extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToStrike = chooseTarget(enemies);

        if(choiceToStrike == null)
        {
            return true;
        }

        abilityExecution(character, choiceToStrike);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        List<Integer> possibleChoices = new ArrayList<Integer>();
        int count = 0;

        for(int x = 0; x < enemies.size(); x++)
        {
            A_Character choiceToStrke = enemies.getCharacter(x);
            if(choiceToStrke.getHealth() == choiceToStrke.getMaxHealth() || choiceToStrke.getConditions().hasBadCondition())
            {
                possibleChoices.add(count);
            }
            count++;
        }

        int choice;
        if(possibleChoices.size() > 0)
        {
            choice = possibleChoices.get(rand.nextInt(possibleChoices.size()));
            abilityExecution(character, enemies.getCharacter(choice));
        }
        else
        {
            abilityExecution(character, enemies.getCharacter(rand.nextInt(enemies.size())));
        }
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        int tempBoost = 0;
        Display.displayMessage(character.getName() + " used sneak attack on " + choiceToStrike.getName());

        if((choiceToStrike.getHealth() == choiceToStrike.getMaxHealth()) || choiceToStrike.getConditions().hasBadCondition())
        {
            character.getConditions().tempDamage(character.getPower()*5);
            character.getConditions().tempAttack(character.getCunning());
        }

        character.attack(choiceToStrike);
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
        return "Sneak Attack";
    }

    public static String description()
    {
        return "     - Sneak Attack: Deals immense damage to foes who are at full health or who have bad conditions";
    }
}

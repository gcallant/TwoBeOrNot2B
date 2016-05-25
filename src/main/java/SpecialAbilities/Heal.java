package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/24/2016.
 */
public class Heal extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToHeal = chooseTarget(allies);

        if(choiceToHeal == null)
        {
            return true;
        }

        abilityExecution(character, choiceToHeal);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies.getCharacter(rand.nextInt(enemies.size())));
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        System.out.println(character.getName() + " used heal on " + choiceToStrike.getName() + " for " + character.getPower()*5);
        choiceToStrike.heal(character.getPower()*5);
    }

    public String toString()
    {
        return "Heal";
    }

    public static String description()
    {
        return "     - ";
    }
}

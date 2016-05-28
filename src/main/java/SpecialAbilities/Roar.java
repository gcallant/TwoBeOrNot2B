package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/26/2016.
 */
public class Roar extends SpecialAbility
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
        abilityExecution(character, allies.getCharacter(rand.nextInt((allies.size()))));
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        Display.displayMessage(character.getName() + " roared in " + choiceToStrike.getName() + " face!");
        choiceToStrike.getConditions().recoverConditions();
        choiceToStrike.getConditions().giveDamageReductionBuff(1.0 + character.getCunning()*.01, calculateRounds(character), "Roar");
    }

    public String toString()
    {
        return "Roar";
    }

    public static String description()
    {
        return "     - Roar: Recovers an ally's conditions and gives a small damage reduction buff";
    }
}

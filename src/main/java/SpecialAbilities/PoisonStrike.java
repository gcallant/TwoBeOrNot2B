package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/22/2016.
 */
public class PoisonStrike extends SpecialAbility
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
        abilityExecution(character, enemies.getCharacter(rand.nextInt(enemies.size())));
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        System.out.println(character.getName() + " used Poison Strike on " + choiceToStrike.getName());
        choiceToStrike.getConditions().givePoisonDebuff(1.0 + (character.getCunning()*.01), calculateRounds(character), "Poison Strike");
    }

    public String toString()
    {
        return "Poison Strike";
    }

    public static String description()
    {
        return "     - Poison Strike: Deals no damage but puts a poison debuff on the target for a few rounds";
    }
}
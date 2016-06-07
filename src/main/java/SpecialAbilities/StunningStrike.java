package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/21/2016.
 */
public class StunningStrike extends SpecialAbility
{
    public static String description()
    {
        return "     - Stunning Strike: Hits for increased damage and has a chance to stun";
    }

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
        A_Character choiceToStrike = enemies.getCharacter(rand.nextInt(enemies.size()));
        abilityExecution(character, choiceToStrike);
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        character.getConditions().tempDamage(character.getPower());

        Display.displayMessage(character.getName() + " used stunning strike on " + choiceToStrike.getName());
        if(character.canAttack(choiceToStrike))
        {
            character.preformAttack(choiceToStrike);
            if(getAffectedChance(character, "Power", choiceToStrike))
            {
                choiceToStrike.getConditions().giveStunnedDebuff(calculateRounds(character),"Stunning Strike");
            }
        }
        else
        {
            Display.displayMessage("But missed!");
        }
        character.getConditions().giveExhaustedDebuff(2, "Stunning Strike");
    }

    public boolean canUpgrade()
    {
        return true;
    }

    public SpecialAbility upgrade()
    {
        return new ConfusingStrike();
    }

    public String toString()
    {
        return "Stunning Strike";
    }
}

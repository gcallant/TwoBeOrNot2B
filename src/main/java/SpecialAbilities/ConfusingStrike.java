package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/24/2016.
 */
public class ConfusingStrike extends SpecialAbility
{

    public boolean executeAbility(A_Character character, Party allies, Party enemies) {
        A_Character choiceToStrike = chooseTarget(enemies);

        if (choiceToStrike == null) {
            return true;
        }

        abilityExecution(character, choiceToStrike);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies) {
        A_Character choiceToStrike = enemies.getCharacter(rand.nextInt(enemies.size()));
        abilityExecution(character, choiceToStrike);
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        character.getConditions().tempDamage(character.getPower());

        System.out.println(character.getName() + " used confusing strike on " + choiceToStrike.getName());
        if (character.canAttack(choiceToStrike))
        {
            character.preformAttack(choiceToStrike);
            if(rand.nextInt(5) > 1)
            {
                System.out.println(choiceToStrike.getName() + " was confused!");
                choiceToStrike.getConditions().giveConfusedDebuff(calculateRounds(character), "Confusing Strike");
            }
        }
        else
        {
            System.out.println("But missed!");
        }
        character.getConditions().giveExhaustedDebuff(2, "Confusing Strike");
    }

    public String toString() {
        return "Confusing Strike";
    }

    public static String description()
    {
        return "     - ";
    }
}
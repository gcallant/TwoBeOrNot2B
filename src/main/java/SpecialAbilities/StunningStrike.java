package SpecialAbilities;

import Characters.A_Character;
import Characters.Party;

/**
 * Created by Michael on 5/21/2016.
 */
public class StunningStrike extends SpecialAbility
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
        A_Character choiceToStrike = enemies.getCharacter(rand.nextInt(enemies.size()));
        abilityExecution(character, choiceToStrike);
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike)
    {
        character.getConditions().tempDamage(character.getStrength());

        System.out.println(character.getName() + " used stunning strike on " + choiceToStrike.getName());
        if(character.canAttack(choiceToStrike))
        {
            character.preformAttack(choiceToStrike);
            if(rand.nextBoolean())
            {
                System.out.println(choiceToStrike.getName() + " was stunned!");
                choiceToStrike.getConditions().stunned(1);
            }
        }
        else
        {
            System.out.println("But missed!");
        }
        character.getConditions().exhausted(2);
    }

    public String toString()
    {
        return "Stunning Strike";
    }
}
package SpecialAbilities;

import Characters.A_Character;
import Characters.Party;

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
        choiceToStrike.getConditions().givePoisonDebuff(character.getDexterity()*.01, 5, "poison Strike");
    }

    public String toString()
    {
        return "Poison Strike";
    }
}

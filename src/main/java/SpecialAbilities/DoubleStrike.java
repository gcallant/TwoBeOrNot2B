package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/24/2016.
 */
public class DoubleStrike extends SpecialAbility
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
        System.out.println(character.getName() + " used double strike on " + choiceToStrike.getName());

        character.attack(choiceToStrike);
        character.attack(choiceToStrike);
    }

    public String toString()
    {
        return "Stunning Strike";
    }

    public static String description()
    {
        return "     - Stunning Strike: Hits for increased damage and has a chance to stun";
    }
}
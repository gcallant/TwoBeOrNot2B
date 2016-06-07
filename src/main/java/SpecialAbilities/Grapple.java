package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/21/2016.
 */
public class Grapple extends SpecialAbility
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
        character.getConditions().tempDamage(character.getPower());

        Display.displayMessage(character.getName() + " used grapple on " + choiceToStrike.getName() + ". " + choiceToStrike.getName() +
                            " takes " + character.getPower()*2 + " damage!");

        choiceToStrike.getConditions().giveStunnedDebuff(2,"Grapple");
        choiceToStrike.takeDamage(character.getPower()*2);
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
        return "Grapple";
    }

    public static String description()
    {
        return "     - Grapple";
    }
}

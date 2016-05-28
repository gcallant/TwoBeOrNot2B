package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class StealLife extends SpecialAbility
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
        Display.displayMessage(character.getName() + " stole " + character.getCunning()*3 + " life from " + choiceToStrike.getName());
        Display.displayMessage(character.getName() + " healed for " + character.getCunning()*2);
        choiceToStrike.takeDamage(character.getCunning()*3);
        character.heal(character.getCunning()*2);
    }

    public String toString() {
        return "Steal Life";
    }

    public static String description()
    {
        return "     - Steals life from your target based on cunning and heals for half of that";
    }
}
package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/22/2016.
 */
public class PiercingStrike extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        A_Character choiceToStrike = chooseTarget(enemies);

        if(choiceToStrike == null)
        {
            return true;
        }

        abilityExecution(character, choiceToStrike, enemies);

        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies.getCharacter(rand.nextInt(enemies.size())), enemies);
        return false;
    }

    private void abilityExecution(A_Character character, A_Character choiceToStrike, Party enemies)
    {
        Display.displayMessage(character.getName() + " used piercing strike on " + choiceToStrike.getName());
        A_Character secondaryStrike = null;
        if(enemies.size() > 1)
        {
            character.getConditions().tempAttack(character.getPower());
            do {
                secondaryStrike = enemies.getCharacter((rand.nextInt(enemies.size())));
            } while (choiceToStrike == secondaryStrike);
        }
        character.attack(choiceToStrike);
        if(secondaryStrike != null)
        {
            character.getConditions().tempDamage(2*character.getCunning());
            character.attack(secondaryStrike);
        }
    }

    public boolean canUpgrade()
    {
        return true;
    }

    public SpecialAbility upgrade()
    {
        return new RicochetShot();
    }

    public String toString()
    {
        return "Piercing Strike";
    }

    public static String description()
    {
        return "     - Piercing Strike: Hits the first target and then hits a random second target for increased damage";
    }
}

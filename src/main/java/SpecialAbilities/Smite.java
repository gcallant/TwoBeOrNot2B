package SpecialAbilities;

import Characters.A_Character;
import Characters.CreatureType;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class Smite extends SpecialAbility
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

        Display.displayMessage(character.getName() + " used smite on " + choiceToStrike.getName());
        if(choiceToStrike.getCreatureType() == CreatureType.Undead)
        {
            character.getConditions().tempDamage(character.getPower()*4);
            character.preformAttack(choiceToStrike);
        }
        else
        {
            Display.displayMessage("But it did nothing...");
        }
    }

    public boolean canUpgrade()
    {
        return true;
    }

    public SpecialAbility upgrade()
    {
        return new HolyVengeance();
    }

    public String toString()
    {
        return "Smite";
    }

    public static String description()
    {
        return "     - Smite: Does strong damage to all undead but has little effect on others";
    }
}

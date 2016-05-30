package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/21/2016.
 */
public class MeteorShower extends SpecialAbility
{
    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies);
        return false;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, enemies);
        return false;
    }

    private void abilityExecution(A_Character character, Party enemies)
    {
        Display.displayMessage(character.getName() + " used meteor shower!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            character.preformAttack(enemies.getCharacter(x));
        }
    }

    public boolean canUpgrade()
    {
        return true;
    }

    public SpecialAbility upgrade()
    {
        return new FireBall();
    }

    public String toString()
    {
        return "Meteor Shower";
    }

    public static String description()
    {
        return "     - Meteor Shower: Attacks all enemies for a small amount of damage but has no chance to miss";
    }
}

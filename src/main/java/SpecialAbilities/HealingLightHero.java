package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/24/2016.
 */
public class HealingLightHero extends HealingLight
{
    public void abilityExecution(A_Character character, Party allies, Party enemies)
    {
        for(int x = 0; x < enemies.size(); x++)
        {
            Display.displayMessage(character.getName() + " deals " + character.getPower() + " to " + enemies.getCharacter(x).getName());
            enemies.getCharacter(x).takeDamage(2*character.getPower());
        }

        super.abilityExecution(character, allies, enemies);
    }

    public static String description()
    {
        return "     - Healing Light: Heals all allies for a small amount and hurts enemies for half of that. It cures many bad conditions";
    }
}

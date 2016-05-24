package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;

/**
 * Created by Michael on 5/24/2016.
 */
public class HealingLightHero extends HealingLight
{
    public void abilityExecution(A_Character character, Party allies, Party enemies)
    {
        for(int x = 0; x < enemies.size(); x++)
        {
            enemies.getCharacter(x).takeDamage(2*character.getStrength());
        }

        super.abilityExecution(character, allies, enemies);
    }
}

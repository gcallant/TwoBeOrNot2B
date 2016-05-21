package SpecialAbilities;

import Characters.A_Character;
import Characters.Party;

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
        System.out.println(character.getName() + " used meteor shower!");
        int totalEnemies = enemies.size();
        for(int x = 0; x < totalEnemies; x++)
        {
            character.preformAttack(enemies.getCharacter(x));
        }
    }

    public String toString()
    {
        return "Meteor Shower";
    }
}

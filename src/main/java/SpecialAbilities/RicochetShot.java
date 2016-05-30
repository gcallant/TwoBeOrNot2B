package SpecialAbilities;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/24/2016.
 */
public class RicochetShot extends SpecialAbility
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

    private boolean abilityExecution(A_Character character, A_Character choiceToStrike, Party enemies)
    {
        Display.displayMessage(character.getName() + " used ricochet shot on " + choiceToStrike.getName());
        List<A_Character> charactersHit = new ArrayList<A_Character>();
        charactersHit.add(choiceToStrike);
        A_Character secondaryStrike = null;
        int totalEnemies = enemies.size();
        if(!character.attack(choiceToStrike))
        {
            return false;
        }
        while(charactersHit.size() < totalEnemies)
        {
            character.getConditions().tempDamage(charactersHit.size()*character.getPower());
            do {
                secondaryStrike = enemies.getCharacter((rand.nextInt(enemies.size())));
            } while (charactersHit.contains(secondaryStrike));
            charactersHit.add(secondaryStrike);
            if(!character.attack(secondaryStrike))
            {
                return false;
            }
        }
        return true;
    }

    public String toString()
    {
        return "Ricochet Shot";
    }

    public static String description()
    {
        return "     - Ricochet Shot: Hits the first target and then continues hitting targets until all targets have been struck or a shot is missed";
    }
}

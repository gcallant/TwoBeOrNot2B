package SpecialAbilities;

import Characters.A_Character;
import Item.Dagger;
import Item.Leather;
import Nemesis.MasterAssassin;
import PartyManagement.Party;
import Utilities.Display;

/**
 * Created by Michael on 5/26/2016.
 */
public class Copy extends SpecialAbility
{
    public static String description()
    {
        return "     - ";
    }

    public boolean executeAbility(A_Character character, Party allies, Party enemies)
    {
        return true;
    }

    public boolean executeAbilityRandom(A_Character character, Party allies, Party enemies)
    {
        abilityExecution(character, allies, enemies);
        return false;
    }

    private void abilityExecution(A_Character character, Party allies, Party enemies)
    {
        Display.displayMessage(character.getName() + " copied herself!!");
        A_Character copy1, copy2;
        int level = enemies.getCharacter(0).getLevel();

        copy1 = new MasterAssassin(character.getName(), character.getMaxHealth(), 1, character.getCunning()/2, new Leather(level), new Dagger(level), 1);
        copy1.setHealth(character.getHealth());
        copy1.setConditions(character.getConditions());
        copy2 = new MasterAssassin(character.getName(), character.getMaxHealth(), 1, character.getCunning()/2, new Leather(level), new Dagger(level), 1);
        copy2.setHealth(character.getHealth());
        copy2.setConditions(character.getConditions());

        allies.addCharacter(copy1);
        allies.addCharacter(copy2);
    }

    public String toString()
    {
        return "Copy";
    }

}
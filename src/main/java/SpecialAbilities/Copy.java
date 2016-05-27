package SpecialAbilities;

import Characters.A_Character;
import Factories.MonsterFactory;
import Item.Dagger;
import Item.Leather;
import Nemesis.MasterAssassin;
import PartyManagement.Party;

/**
 * Created by Michael on 5/26/2016.
 */
public class Copy extends SpecialAbility
{
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
        System.out.println(character.getName() + " made copies of themselves!!");
        A_Character copy1, copy2;
        int level = enemies.getCharacter(0).getLevel();

        copy1 = new MasterAssassin("Scarlett, The Temptress", character.getMaxHealth(), 1, 15, new Leather(level), new Dagger(level), 1);
        copy1.setHealth(character.getHealth());
        copy1.setConditions(character.getConditions());
        copy2 = new MasterAssassin("Scarlett, The Temptress", character.getMaxHealth(), 1, 15, new Leather(level), new Dagger(level), 1);
        copy2.setHealth(character.getHealth());
        copy2.setConditions(character.getConditions());

        allies.addCharacter(copy1);
        allies.addCharacter(copy2);
    }

    public String toString()
    {
        return "Copy";
    }

    public static String description()
    {
        return "     - ";
    }

}
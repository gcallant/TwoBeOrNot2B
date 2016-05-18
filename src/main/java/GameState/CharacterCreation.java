package GameState;

import Characters.A_Character;
import Characters.Paladin;
import Characters.Party;
import Characters.Warrior;
import Factories.HeroFactory;
import Item.*;
import Mediator.Mediator;

import java.util.ArrayList;

/*
 * Created by Michael on 5/12/2016.
 */

public class CharacterCreation implements A_State
{
    private Mediator mediator;

    public CharacterCreation(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public boolean isEndOfGame()
    {
        return false;
    }

    public String display()
    {
        return "Create your party\nCancel";
    }

    public A_State execute(String command)
    {
        switch(command)
        {
            case "party":
                ArrayList<A_Character> party = new ArrayList<A_Character>();
                party.add(new HeroFactory().createCharacter("Warrior", "Gorvok", 250, 10, 5, 4));
                party.add(new HeroFactory().createCharacter("Mage", "Geofry", 100, 10, 5, 4));
                party.add(new HeroFactory().createCharacter("Rogue", "Orion", 100, 10, 5, 4));
                party.add(new Paladin("Brailson", 300, 8, 3, 4, ArmorType.Heavy, new Chainmail(1), new Sword(1)));
                Party myParty = new Party(party);
                mediator.recieveParty(myParty);
                myParty.addToInventory(new Healing(2));
                myParty.addToInventory(new Chainmail(1));
                myParty.addToInventory(new Hammer(2));
                myParty.addToInventory(new Leather(2));
                myParty.addToInventory(new Dagger(2));
                return new NewMap(mediator);

            case "cancel":
                return new MainMenu(mediator);

            default:
                return new CharacterCreation(mediator);
        }
    }
}

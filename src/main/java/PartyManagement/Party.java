package PartyManagement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Characters.A_Character;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import StringTester.TestString;
import Utilities.Display;

/**
 * Created by Michael on 5/12/2016.
 */

public class Party implements Iterable<A_Character>
{
    private List<A_Character> characterParty;
    private Inventory         inventory;
    private int               partyLevel;
    private int               floorLevel;

    public Iterator<A_Character> iterator()
    {
        return characterParty.iterator();
    }

    public Party(List<A_Character> characterParty)
    {
        this.characterParty = characterParty;
        this.inventory = new Inventory();
        this.partyLevel = 1;
        this.floorLevel = 1;
    }

    public void addToInventory(Weapon item)
    {
        inventory.addToInventory(item);
        inventory.sortInventory();
    }

    public void addToInventory(Armor item)
    {
        inventory.addToInventory(item);
        inventory.sortInventory();
    }

    public void addToInventory(Consumable item)
    {
        inventory.addToInventory(item);
        inventory.sortInventory();
    }

    public Weapon removeFromInventory(Weapon item)
    {
        return inventory.removeFromInventory(item);
    }

    public Armor removeFromInventory(Armor item)
    {
        return inventory.removeFromInventory(item);
    }

    public Consumable removeFromInventory(Consumable item)
    {
        return inventory.removeFromInventory(item);
    }

    public boolean isDefeated()
    {
        for(A_Character character : characterParty)
        {
            if(!character.getDefeated())
            {
                return false;
            }
        }
        return true;
    }

    public int calculatePartyLevel()
    {
        int total = 0;
        for(A_Character character : characterParty)
        {
            total += character.getLevel();
        }
        return total;
    }

    public void gainExperience(int experience)
    {
        for(A_Character character : characterParty)
        {
            character.gainExperience(experience);
        }
    }

    public String print()
    {
        String retStr = "";
        for(A_Character character : characterParty)
        {
            retStr += character.getName() + "\n";
        }
        return retStr;
    }

    public void fixParty()
    {
        List<A_Character> toRemove = new ArrayList<A_Character>();

        for(A_Character character : characterParty)
        {
            if(character.isSummon())
            {
                toRemove.add(character);
            }
            character.resetTurn();
            character.resetStats();
            if(character.getDefeated())
            {
                character.heal(1);
                character.removeDefeated();
            }
        }

        for(A_Character character : toRemove)
        {
            characterParty.remove(character);
            character.getOwner().getConditions().unsummon();
        }
    }

    public void sortDefeated()
    {
        Collections.sort(characterParty, new DefeatedSort());
    }

    public int size()
    {
        int size = 0;
        for(A_Character character : characterParty)
        {
            if(!character.getDefeated())
            {
                size++;
            }
        }
        return size;
    }

    public A_Character getCharacter(int index)
    {
        return characterParty.get(index);
    }

    public boolean useInventory()
    {
        Display.displayMessage("Select an item from your inventory");
        int choice;
        int total = inventory.displayInventory();
        choice = TestString.ensureInt(total);

        int[] translation = inventory.translateChoice(choice);
        switch(translation[1])
        {
            case 0:
                consumePotion(translation[0]);
                return true;
            case 1:
                equip(translation[0],"weapon");
                return true;
            case 2:
                equip(translation[0],"armor");
                return true;
            default:
                return false;
        }
    }

    private boolean consumePotion(int potion)
    {
        Display.displayMessage("Select who you want to use a potion");
        int choice = TestString.getCharacterChoice(characterParty);

        if(choice == -1)
        {
            return true;
        }

        A_Character character = characterParty.get(choice);

        inventory.useConsumable(character, choice);
        return false;
    }

    private boolean equip(int equip, String str)
    {
        Display.displayMessage("Select who you want to equip");
        int choice = TestString.getCharacterChoice(characterParty);

        if(choice == -1)
        {
            return false;
        }

        A_Character character = characterParty.get(choice);

        switch(str)
        {
            case "weapon":
                inventory.equipWeapon(character,equip - 1);
                break;
            case "armor":
                inventory.equipArmor(character, equip - 1);
                break;
        }
        return true;
    }

    public void displayStats()
    {
        for(A_Character character : characterParty)
        {
            Display.displayMessage(character.displayStats());
        }
    }

    public void addCharacter(A_Character character)
    {
        if(character != null)
        {
            characterParty.add(character);
        }
    }

    public void levelAbilities()
    {
        for(A_Character character : characterParty)
        {
            character.upgradeAbilities();
        }
    }

    public void setFloorLevel(int floorLevel)
    {
        this.floorLevel = floorLevel;
    }

    public int getFloorLevel()
    {
        return floorLevel;
    }

    public boolean contains(A_Character character)
    {
        return characterParty.contains(character);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party party = (Party) o;

        if (partyLevel != party.partyLevel) return false;
        if (!characterParty.equals(party.characterParty)) return false;
        if (!inventory.equals(party.inventory)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = characterParty.hashCode();
        result = 31 * result + inventory.hashCode();
        result = 31 * result + partyLevel;
        return result;
    }
}

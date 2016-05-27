package PartyManagement;

import Characters.A_Character;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import Utilities.TestString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 5/12/2016.
 */

public class Party
{
    private List<A_Character> characterParty;
    private Inventory         inventory;
    private int               partyLevel;
    private boolean           shout;
    private int               floorLevel;

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

    /*private void displayInventory()
    {
        System.out.println(inventory.displayInventory());
    }*/

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

    public int getConsumables()
    {
        return inventory.getConsumables();
    }

    public String getConsumable(int index)
    {
        return inventory.getConsumable(index);
    }

    public boolean useConsumable(A_Character character, int index)
    {
        return inventory.useConsumable(character,index);
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

    public List<A_Character> getCharacterParty()
    {
        return characterParty;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public boolean useInventory()
    {
        System.out.println("Select an item from your inventory");
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

    public boolean consumePotion(int potion)
    {
        System.out.println("Select who you want to use a potion");
        int choice = TestString.getCharacterChoice(characterParty);

        if(choice == -1)
        {
            return true;
        }

        A_Character character = characterParty.get(choice);

        inventory.useConsumable(character, choice);
        return false;
    }

    public boolean equip(int equip, String str)
    {
        System.out.println("Select who you want to equip");
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
            System.out.println(character.displayStats());
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

    public int getFloorLevel()
    {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel)
    {
        this.floorLevel = floorLevel;
    }

    public boolean contains(A_Character character)
    {
        for(A_Character partyMember : characterParty)
        {
            if(partyMember == character)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party party = (Party) o;

        if (partyLevel != party.partyLevel) return false;
        if (shout != party.shout) return false;
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
        result = 31 * result + (shout ? 1 : 0);
        return result;
    }
}

package Characters;


import java.util.ArrayList;
import java.util.Collections;

import Inventory.Inventory;
import Item.Armor;
import Item.Consumable;
import Item.Storable;
import Item.Weapon;

/**
 * Created by Michael on 5/12/2016.
 */

public class Party
{
    private ArrayList<A_Character> characterParty;
    private Inventory inventory;
    private int partyLevel;

    public Party(ArrayList<A_Character> characterParty)
    {
        this.characterParty = characterParty;
        this.inventory = new Inventory();
        this.partyLevel = 1;
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

    public void displayInventory()
    {
        System.out.println(inventory.displayInventory());
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

    public ArrayList<A_Character> getParty()
    {
        return characterParty;
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

    public void sortDefeated()
    {
        Collections.sort(characterParty, new DefeatedSort());
    }

    public ArrayList<Consumable> getConsumables()
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
        Collections.sort(characterParty, new DefeatedSort());
        return characterParty.get(index);
    }
}

package Inventory;

import Characters.A_Character;
import Item.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 5/7/2016.
 */
public class Inventory
{
    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;
    private ArrayList<Consumable> consumables;
    private int totalSize;

    public Inventory()
    {
        weapons = new ArrayList<Weapon>();
        armors = new ArrayList<Armor>();
        consumables = new ArrayList<Consumable>();
        totalSize = 0;
    }

    public void addToInventory(Weapon item)
    {
        if(item != null)
        {
            this.weapons.add(item);
            totalSize++;
        }
    }

    public void addToInventory(Armor item)
    {
        if(item != null)
        {
            this.armors.add(item);
            totalSize++;
        }
    }

    public void addToInventory(Consumable item)
    {
        if(item != null)
        {
            this.consumables.add(item);
            totalSize++;
        }
    }

    public Weapon removeFromInventory(Weapon item)
    {
        if(this.weapons.contains(item))
        {
            int indexOfValue;
            Weapon returnValue;
            indexOfValue = this.weapons.indexOf(item);
            returnValue = this.weapons.get(indexOfValue);
            this.weapons.remove(indexOfValue);
            totalSize--;
            return returnValue;
        }
        return null;
    }

    public Armor removeFromInventory(Armor item)
    {
        if(this.armors.contains(item))
        {
            int indexOfValue;
            Armor returnValue;
            indexOfValue = this.armors.indexOf(item);
            returnValue = this.armors.get(indexOfValue);
            this.armors.remove(indexOfValue);
            totalSize--;
            return returnValue;
        }
        return null;
    }

    public Consumable removeFromInventory(Consumable item)
    {
        if(this.consumables.contains(item))
        {
            int indexOfValue;
            Consumable returnValue;
            indexOfValue = this.consumables.indexOf(item);
            returnValue = this.consumables.get(indexOfValue);
            this.consumables.remove(indexOfValue);
            totalSize--;
            return returnValue;
        }
        return null;
    }

    public void sortInventory()
    {
        Collections.sort(this.armors,new StorableSort());
        Collections.sort(this.consumables,new StorableSort());
        Collections.sort(this.weapons,new StorableSort());
    }

    public String displayInventory()
    {
        if(totalSize == 0)
        {
            return "No items in Inventory";
        }
        String returnValue = "";
        for(Consumable consumable : consumables)
        {
            returnValue = returnValue + consumable.toString() + "\n";
        }
        return returnValue;
    }

    public ArrayList<Consumable> getConsumables()
    {
        return consumables;
    }

    public boolean useConsumable(A_Character character, int index)
    {
        if(index >= consumables.size())
        {
            return false;
        }

        Consumable consumable = removeFromInventory(consumables.get(index));
        consumable.use(character);
        return true;
    }

    public String getConsumable(int index)
    {
        return consumables.get(index).toString();
    }
}

package Inventory;

import Item.Storable;
import Item.CompareStorables;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 5/7/2016.
 */
public class Inventory
{
    private ArrayList<Storable> inventory;

    public Inventory()
    {
        this.inventory = new ArrayList<Storable>();
    }

    public void addToInventory(Storable item)
    {
        if(item != null)
        {
            this.inventory.add(item);
        }
    }

    public Storable removeFromInventory(Storable item)
    {
        if(this.inventory.contains(item))
        {
            int indexOfValue;
            Storable returnValue;
            indexOfValue = this.inventory.indexOf(item);
            returnValue = this.inventory.get(indexOfValue);
            this.inventory.remove(indexOfValue);
            return returnValue;
        }
        else
        {
            return null;
        }
    }

    public void sortInventory()
    {
        Collections.sort(this.inventory,new CompareStorables());
    }

    public String displayInventory()
    {
        if(this.inventory.size() == 0)
        {
            return "No items in Inventory";
        }
        String returnValue = "";
        for(int x = 0; x < this.inventory.size(); x++)
        {
            returnValue = this.inventory.get(x).toString() + "\n";
        }
        return returnValue;
    }
}

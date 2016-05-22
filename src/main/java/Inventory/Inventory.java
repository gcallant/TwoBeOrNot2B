package Inventory;

import Characters.A_Character;
import Item.*;
import StringTester.TestString;

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
        Collections.sort(this.armors, new ArmorSort());
        Collections.sort(this.consumables, new ConsumableSort());
        Collections.sort(this.weapons, new WeaponSort());
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

    public int getConsumables()
    {
        int itemIndex = 1;
        for(Consumable item : consumables)
        {
            System.out.println(itemIndex + ".)" + item.toString());
            itemIndex++;
        }
        return itemIndex;
    }

    public int chooseConsumable()
    {
        return TestString.getConsumableChoice(consumables);
    }

    public ArrayList<Weapon> getWeapons()
    {
        return weapons;
    }

    public ArrayList<Armor> getArmor()
    {
        return armors;
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

    public boolean equipWeapon(A_Character character, int index)
    {
        if(index >= weapons.size())
        {
            return false;
        }

        Weapon weapon = removeFromInventory(weapons.get(index));
        if(!character.canEquip(weapon))
        {
            addToInventory(weapon);
            System.out.println(character.getName() + " cannot equip this type of weapon!");
            return false;
        }
        Weapon toAdd = character.equip(weapon);
        addToInventory(toAdd);
        return true;
    }

    public boolean equipArmor(A_Character character, int index)
    {
        if(index >= armors.size())
        {
            return false;
        }

        Armor armor = removeFromInventory(armors.get(index));

        if(!character.canEquip(armor))
        {
            addToInventory(armor);
            System.out.println(character.getName() + " cannot equip this type of armor!");
            return false;
        }

        Armor toAdd = character.equip(armor);
        addToInventory(toAdd);
        return true;
    }

    public String getConsumable(int index)
    {
        return consumables.get(index).toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        if (totalSize != inventory.totalSize) return false;
        if (!weapons.equals(inventory.weapons)) return false;
        if (!armors.equals(inventory.armors)) return false;
        if (!consumables.equals(inventory.consumables)) return false;

        return true;
    }
}

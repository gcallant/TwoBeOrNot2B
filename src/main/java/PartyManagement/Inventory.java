package PartyManagement;

import Characters.A_Character;
import Exceptions.DatabaseManagerException;
import Item.*;
import Utilities.Display;
import Utilities.TestString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 5/7/2016.
 */
public class Inventory
{
    private List<Weapon>          weapons;
    private List<Armor>           armors;
    private List<Consumable>      consumables;
    private int                   totalSize;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Inventory()
    {
        weapons = new ArrayList<Weapon>();
        armors = new ArrayList<Armor>();
        consumables = new ArrayList<Consumable>();
        totalSize = 0;
    }

    public Inventory(List<Weapon> weapons, List<Armor> armors, List<Consumable> consumables)
    throws DatabaseManagerException
    {
        if(weapons == null)
        {
            this.weapons = new ArrayList<>();
        }
        else
        {
            this.weapons = weapons;
        }
        if(armors == null)
        {
            this.armors = new ArrayList<>();
        }
        else
        {
            this.armors = armors;
        }
        if(consumables == null)
        {
            this.consumables = new ArrayList<>();
        }
        else
        {
            this.consumables = consumables;
        }
        this.totalSize = weapons.size() + armors.size() + consumables.size();
        logger.info("Successfully loaded new Inventory object from saved game");
    }


    public void addToInventory(Weapon item)
    {
        if(item != null)
        {
            this.weapons.add(item);
            totalSize++;
        }
        else
        {
            Display.displayMessage("Invalid item - cannot add to list of weapons.");
        }
    }

    public void addToInventory(Armor item)
    {
        if(item != null)
        {
            this.armors.add(item);
            totalSize++;
        }
        else
        {
            Display.displayMessage("Invalid item - cannot add to list of armor.");
        }
    }

    public void addToInventory(Consumable item)
    {
        if(item != null)
        {
            this.consumables.add(item);
            totalSize++;
        }
        else
        {
            Display.displayMessage("Invalid item - cannot add to list of consumables.");
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

    public int displayInventory()
    {
        if(totalSize == 0)
        {
            Display.displayMessage("1) cancel");
            return 1;
        }
        int total = 1;
        String returnValue = "";
        for(Consumable consumable : consumables)
        {
            returnValue = returnValue + total + ") " + consumable.toString() + "\n";
            total++;
        }
        for(Weapon weapon : weapons)
        {
            returnValue = returnValue + total + ") " + weapon.toString() + "\n";
            total++;
        }
        for(Armor armor : armors)
        {
            returnValue = returnValue + total + ") " + armor.toString() + "\n";
            total++;
        }
        returnValue = returnValue + total + ") cancel";

        Display.displayMessage(returnValue);

        return total;
    }

    public int[] translateChoice(int choice)
    {
        int[] retArray = new int[2];

        if(choice > consumables.size())
        {
            choice = choice - consumables.size();
        }
        else
        {
            retArray[0] = choice - 1;
            retArray[1] = 0;
            return retArray;
        }

        if(choice > weapons.size())
        {
            choice = choice - weapons.size();
        }
        else
        {
            retArray[0] = choice;
            retArray[1] = 1;
            return retArray;
        }

        if(choice > armors.size())
        {
            retArray[0] = -1;
            retArray[1] = -1;
            return retArray;
        }
        else
        {
            retArray[0] = choice;
            retArray[1] = 2;
            return retArray;
        }
    }

    public boolean consumePotion(Party party)
    {
        int total = 1;
        Display.displayMessage("Choose a potion to use: ");
        for(Consumable consumable: consumables)
        {
            Display.displayMessage(total + ") " + consumable.toString());
            total++;
        }

        Display.displayMessage(total + ") cancel");
        int choice = TestString.ensureInt(total);

        if(choice == total)
        {
            return true;
        }

        Consumable consumable = consumables.get(choice - 1);

        Display.displayMessage("Choose who to use " + consumable.toString() + " on: ");
        total = 1;
        for(int x = 0; x < party.size(); x++)
        {
            Display.displayMessage(total + ") " + party.getCharacter(x));
            total++;
        }

        Display.displayMessage(total + ") cancel");
        choice = TestString.ensureInt(total);

        if(choice == total)
        {
            return true;
        }

        consumable.use(party.getCharacter(choice - 1));
        removeFromInventory(consumable);
        return false;
    }

    private int getConsumables()
    {
        int itemIndex = 1;
        for(Consumable item : consumables)
        {
            Display.displayMessage(itemIndex + ".)" + item.toString());
            itemIndex++;
        }
        return itemIndex;
    }

    public List<Weapon> getWeapons()
    {
        return weapons;
    }

    public List<Armor> getArmor()
    {
        return armors;
    }

    public List<Consumable> getConsumableList()
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
            Display.displayMessage(character.getName() + " cannot equip this type of weapon!");
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
            Display.displayMessage(character.getName() + " cannot equip this type of armor!");
            return false;
        }

        Armor toAdd = character.equip(armor);
        addToInventory(toAdd);
        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Inventory)) { return false; }

        Inventory inventory = (Inventory) o;

        if(totalSize != inventory.totalSize) { return false; }
        if(weapons != null ? ! weapons.equals(inventory.weapons) : inventory.weapons != null) { return false; }
        if(armors != null ? ! armors.equals(inventory.armors) : inventory.armors != null) { return false; }
        if(consumables != null ? ! consumables.equals(inventory.consumables) : inventory.consumables != null)
        {
            return false;
        }
        if(logger != null ? ! logger.equals(inventory.logger) : inventory.logger != null) { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = weapons != null ? weapons.hashCode() : 0;
        result = 31 * result + (armors != null ? armors.hashCode() : 0);
        result = 31 * result + (consumables != null ? consumables.hashCode() : 0);
        result = 31 * result + totalSize;
        result = 31 * result + (logger != null ? logger.hashCode() : 0);
        return result;
    }
}

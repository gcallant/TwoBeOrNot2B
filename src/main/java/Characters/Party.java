package Characters;


import java.util.ArrayList;
import java.util.Collections;

import Inventory.Inventory;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import StringTester.TestString;

/**
 * Created by Michael on 5/12/2016.
 */

public class Party
{
    private ArrayList<A_Character> characterParty;
    private Inventory inventory;
    private int partyLevel;
    private boolean shout;

    public Party(ArrayList<A_Character> characterParty)
    {
        this.characterParty = characterParty;
        this.inventory = new Inventory();
        this.partyLevel = 1;
        this.shout = false;
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

    public boolean hasShouted()
    {
        return shout;
    }

    public void shout()
    {
        shout = true;
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
        for(A_Character character : characterParty)
        {
            character.resetTurn();
            character.resetStats();
            if(character.getDefeated())
            {
                character.heal(1);
                character.removeDefeated();
            }
            shout = false;
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

    public ArrayList<A_Character> getCharacterParty()
    {
        return characterParty;
    }

    public boolean consumePotion()
    {
        System.out.println("Select who you want to use a potion");
        int choice = TestString.getCharacterChoice(characterParty);

        if(choice == -1)
        {
            return false;
        }

        A_Character character = characterParty.get(choice);

        System.out.println("Enter an item to use");
        choice = inventory.chooseConsumable();

        if(choice == -1)
        {
            System.out.println("There is nothing for " + character.getName() + " to use!");
            return false;
        }

        inventory.useConsumable(character, choice);
        return true;
    }

    public boolean equip()
    {
        System.out.println("Select who you want to equip");
        int choice = TestString.getCharacterChoice(characterParty);

        if(choice == -1)
        {
            return false;
        }

        A_Character character = characterParty.get(choice);

        System.out.println("Do you want to equip:\n1) Armor\n2) Weapons\n");

        choice = TestString.getInput(3);

        switch(choice)
        {
            case 0:
                System.out.println("Enter an Armor to equip");
                choice = TestString.getArmorChoice(inventory.getArmor());
                if(choice == -1)
                {
                    System.out.println("There is no Armor for " + character.getName() + " to equip!");
                    return false;
                }
                inventory.equipArmor(character, choice);
                break;
            case 1:
                choice = TestString.getWeaponChoice(inventory.getWeapons());
                if(choice == -1)
                {
                    System.out.println("There is no weapon for " + character.getName() + " to equip!");
                    return false;
                }
                inventory.equipWeapon(character, choice);
                break;
        }
        return true;
    }
}

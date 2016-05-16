package Characters;


import java.util.ArrayList;
import Inventory.Inventory;
import Item.Storable;

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

    public void addToInventory(Storable item)
    {
        inventory.addToInventory(item);
        inventory.sortInventory();
    }

    public Storable removeFromInventory(Storable item)
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
}

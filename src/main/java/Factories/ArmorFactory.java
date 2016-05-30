package Factories;

import Item.Armor;
import Item.Chainmail;
import Item.Cloth;
import Item.Leather;

/**
 * Created by Michael on 5/19/2016.
 */
public class ArmorFactory
{
    public Armor generate(String type, int power)
    {
        if (type == null)
        {
            throw new NullPointerException("Type of armor is invalid. Cannot create it.");
        }
        if (power < 0)
        {
            throw new IllegalArgumentException("Invalid Power. Cannot create armor.");
        }

        switch(type)
        {
            case "Chainmail":
                return new Chainmail(power);
            case "Cloth":
                return new Cloth(power);
            case "Leather":
                return new Leather(power);
            default:
                throw new IllegalArgumentException("Invalid type of armor. Cannot create it.");
        }
    }
}

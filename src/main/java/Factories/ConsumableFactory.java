package Factories;

import Item.Consumable;
import Item.Healing;
import Item.Power;

/**
 * Created by Michael on 5/19/2016.
 */
public class ConsumableFactory
{
    public Consumable generate(String type, int power)
    {
        if (type == null)
        {
            throw new NullPointerException("Type of consumable is invalid. Cannot create it.");
        }
        if (power < 0)
        {
            throw new IllegalArgumentException("Power in invalid. Cannot create the consumable.");
        }
        switch(type)
        {
            case "Healing":
                return new Healing(power);
            case "Power":
                return new Power(power);
        }
        return new Healing(power);
    }
}

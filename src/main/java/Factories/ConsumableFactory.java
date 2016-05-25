package Factories;

import Item.Consumable;
import Item.Healing;
import Item.power;

/**
 * Created by Michael on 5/19/2016.
 */
public class ConsumableFactory
{
    public Consumable generate(String type, int power)
    {
        switch(type)
        {
            case "Healing":
                return new Healing(power);
            case "power":
                return new power(power);
        }
        return new Healing(power);
    }
}

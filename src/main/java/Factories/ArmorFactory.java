package Factories;

import Item.*;

/**
 * Created by Michael on 5/19/2016.
 */
public class ArmorFactory
{
    public Armor generate(String type, int power)
    {
        switch(type)
        {
            case "Chainmail":
                return new Chainmail(power);
            case "Cloth":
                return new Cloth(power);
            case "Leather":
                return new Leather(power);
        }
        return new Cloth(power);
    }
}

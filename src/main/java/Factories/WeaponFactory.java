package Factories;

import Item.*;

/**
 * Created by Michael on 5/19/2016.
 */
public class WeaponFactory
{
    public Weapon generate(String type, int power)
    {
        switch(type)
        {
            case "Sword":
                return new Sword(power);
            case "Hammer":
                return new Hammer(power);
            case "Staff":
                return new Staff(power);
            case "Bow":
                return new Bow(power);
            case "Dagger":
                return new Dagger(power);
        }
        return new Sword(power);
    }
}

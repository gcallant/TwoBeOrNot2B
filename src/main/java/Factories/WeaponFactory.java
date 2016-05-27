package Factories;

import Item.*;

/**
 * Created by Michael on 5/19/2016.
 */
public class WeaponFactory
{
    public Weapon generate(String type, int power)
    {
        if (type == null)
        {
            throw new NullPointerException("Type of weapon is invalid. Cannot create it.");
        }
        if (power < 0)
        {
            throw new IllegalArgumentException("Power is invalid for a weapon. Cannot create it.");
        }
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
            default:
                throw new IllegalArgumentException("Invalid type of weapon. Cannot create it.");
        }
    }
}

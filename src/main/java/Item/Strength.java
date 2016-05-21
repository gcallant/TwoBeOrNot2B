package Item;

import Characters.A_Character;

/**
 * Created by Michael on 5/18/2016.
 */
public class Strength extends Consumable
{
    public Strength(int power)
    {
        super(power*2);
    }

    public void use(A_Character character)
    {
        character.imbibe(this);
        character.giveTempStrength(getPower());
    }

    public void debibe(A_Character character)
    {
        character.removeTempStrength(getPower());
    }

    public String toString()
    {
        return super.toString() + "Strength (" + getPower() + ")";
    }
}

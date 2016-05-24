package Item;

import Characters.A_Character;

/**
 * Created by Michael on 5/18/2016.
 */
public class power extends Consumable
{
    public power(int power)
    {
        super(power);
    }

    public void use(A_Character character)
    {
        character.imbibe(this);
        double damageBuff = 1.0 + .25*((double)getPower());
        character.getConditions().giveDamageBuff(damageBuff, 4, "power Potion");
    }

    public String toString()
    {
        return super.toString() + "damage (" + getPower() + ")";
    }

}

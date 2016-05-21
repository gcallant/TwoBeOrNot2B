package Item;

import Characters.A_Character;

public class Healing extends Consumable
{
	public Healing(int power)
	{
		super(power*5);
	}

	public void use(A_Character character)
	{
		character.heal(getPower());
	}

	public void debibe(A_Character character){}

	public String toString()
	{
		return super.toString() + "Healing(" + getPower() + ")";
	}
}
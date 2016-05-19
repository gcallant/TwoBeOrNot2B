package Item;

import Characters.A_Character;

public class Healing extends Consumable
{
	public Healing(int power)
	{
		super(power);
	}

	public void use(A_Character character)
	{
		character.heal(getPower()*10);
	}

	public String toString()
	{
		return super.toString() + "Healing";
	}
}
package Character;

import Item.Storable;

public abstract class A_Character
{
	protected int health;
	protected int defense;
	protected int speed;
	protected int attack;

	public A_Character(int newHealth, int newDefense, int newSpeed, int newAttack)
	{
		health = newHealth;
		defense = newDefense;
		speed = newSpeed;
		attack = newAttack;
	}

}
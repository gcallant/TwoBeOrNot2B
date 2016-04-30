package Character;

import AttackAndDefendBehavior.*;

public abstract class A_Character
{
	protected String name;
	protected int health;
	protected int strength;
	protected int dexterity;
	protected int speed;
	protected int armor;
	protected I_Attack attackBehavior;
	protected I_Defend defendBehavior;

	public A_Character(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
	{
		setName(newName);
		setHealth(newHealth);
		setStrength(newStrength);
		setDexterity(newDexterity);
		setSpeed(newSpeed);
		setArmor(newArmor);
	}

	public void attack()
	{
		attackBehavior.performAttack();
	}

	public void defend()
	{
		defendBehavior.performDefense();
	}

	public String getName()
	{
		return name;
	}

	public int getHealth()
	{
		return health;
	}

	public int getStrength()
	{
		return strength;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public int getSpeed()
	{
		return speed;
	}

	public int getArmor()
	{
		return armor;
	}

	public void setName(String newName)
	{
		if(! HelperMethods.isValidName(newName))
		{
			throw new IllegalArgumentException("Invalid name");
		}
		name = newName;
	}

	public void setHealth(int newHealth)
	{
		if(HelperMethods.isValidInteger(newHealth + "") == null)
		{
			throw new IllegalArgumentException("Invalid health");
		}
		health = newHealth;
	}

	public void setStrength(int newStrength)
	{
		if(HelperMethods.isValidInteger(newStrength + "") == null)
		{
			throw new IllegalArgumentException("Invalid defense");
		}
		strength = newStrength;
	}

	public void setDexterity(int newDexterity)
	{
		if(HelperMethods.isValidInteger(newDexterity + "") == null)
		{
			throw new IllegalArgumentException("Invalid defense");
		}
		dexterity = newDexterity;
	}

	public void setSpeed(int newSpeed)
	{
		if(HelperMethods.isValidInteger(newSpeed + "") == null)
		{
			throw new IllegalArgumentException("Invalid speed");
		}
		speed = newSpeed;
	}

	public void setArmor(int newArmor)
	{
		if(HelperMethods.isValidInteger(newArmor + "") == null)
		{
			throw new IllegalArgumentException("Invalid attack");
		}
		armor = newArmor;
	}

	public void setAttackBehavior(I_Attack newAttackBehavior)
	{
		attackBehavior = newAttackBehavior;
	}

	public void setDefendBehavior(I_Defend newDefendBehavior)
	{
		defendBehavior = newDefendBehavior;
	}

	@Override
	public String toString()
	{
		return "Name: " + getName() + "\nHealth: " + getHealth() + "\nStrength: " + getStrength() + "\nDexterity: " + getDexterity() + "\nSpeed: " + getSpeed() + "\nArmor: " + getArmor();
	}
}
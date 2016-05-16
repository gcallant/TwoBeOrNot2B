package Characters;

import AttackAndDefendBehavior.*;

import java.util.*;

import Item.*;
import Inventory.*;

public abstract class A_Character
{
	protected String                name;
	protected int                   health;
	protected int                   strength;
	protected int                   dexterity;
	protected int                   speed;
	protected int                   armor;
	protected I_Attack              attackBehavior;
	protected I_Defend              defendBehavior;
	protected boolean               isDefeated;
	protected ArrayList<Consumable> usables;
	protected Inventory             inventory;

	public A_Character(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
	{
		setName(newName);
		setHealth(newHealth);
		setStrength(newStrength);
		setDexterity(newDexterity);
		setSpeed(newSpeed);
		setArmor(newArmor);
		isDefeated = false;
		usables = new ArrayList<Consumable>();
		inventory = new Inventory();
	}

	public abstract void takeAction(Party heroes, Party monsters);

	public int generateInitiative()//will generate the character's initiative.
	{
		return 0;
	}

	public void takeDamage(int total)
	{
		this.health -= total;
		if(health <= 0)
		{
			this.isDefeated = true;
		}
	}

	public void attack(A_Character toAttack)
	{
		toAttack.takeDamage(10);
	}

	public void defend()
	{
		defendBehavior.performDefense();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String newName)
	{
		if(! HelperMethods.isValidName(newName))
		{
			throw new IllegalArgumentException("Invalid name");
		}
		name = newName;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int newHealth)
	{
		if(HelperMethods.isValidInteger(newHealth + "") == null)
		{
			throw new IllegalArgumentException("Invalid health");
		}
		health = newHealth;
	}

	public int getStrength()
	{
		return strength;
	}

	public void setStrength(int newStrength)
	{
		if(HelperMethods.isValidInteger(newStrength + "") == null)
		{
			throw new IllegalArgumentException("Invalid defense");
		}
		strength = newStrength;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public void setDexterity(int newDexterity)
	{
		if(HelperMethods.isValidInteger(newDexterity + "") == null)
		{
			throw new IllegalArgumentException("Invalid defense");
		}
		dexterity = newDexterity;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int newSpeed)
	{
		if(HelperMethods.isValidInteger(newSpeed + "") == null)
		{
			throw new IllegalArgumentException("Invalid speed");
		}
		speed = newSpeed;
	}

	public int getArmor()
	{
		return armor;
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

	public boolean getDefeated()
	{
		return isDefeated;
	}

	public void setDefeated(boolean isDown)
	{
		isDefeated = isDown;
	}

	@Override
	public String toString()
	{
		return "Name: " + getName() + "\tHealth: " + getHealth() + "\tStrength: " + getStrength() +
				         "\tDexterity: " + getDexterity() + "\tSpeed: " + getSpeed() + "\tArmor: " + getArmor();
	}
}
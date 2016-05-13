package Character;

import AttackAndDefendBehavior.I_Attack;
import AttackAndDefendBehavior.I_Defend;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class A_Character
{
	protected String   name;
	protected int      health;
	protected int      strength;
	protected int      dexterity;
	protected int      speed;
	protected int      armor;
	protected I_Attack attackBehavior;
	protected I_Defend defendBehavior;
	protected boolean isDefeated;

	public A_Character(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
	{
		setName(newName);
		setHealth(newHealth);
		setStrength(newStrength);
		setDexterity(newDexterity);
		setSpeed(newSpeed);
		setArmor(newArmor);
		isDefeated = false;
	}

	public A_Character()
	{

	}

	public void takeAction(ArrayList<A_Hero> heroes, ArrayList<A_Monster> monsters)
	{
		Scanner input = new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("Choose an action:");
			System.out.print("1.) Attack\n" +
							"2.) Defend\n" +
							"3.) Use Special\n" +
							"4.) Use Item\n"+
							"5.) Run\n");
			choice = input.nextInt();
			switch(choice)
			{

			}

		}
		while(choice < 1 || choice > 5);  //TODO is this logic correct? -Grant

	}

	public int generateInitiative()//will generate the character's initiative.
	{
		return 0;
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
		return "Name: " + getName() + "\nHealth: " + getHealth() + "\nStrength: " + getStrength() +
				         "\nDexterity: " + getDexterity() + "\nSpeed: " + getSpeed() + "\nArmor: " + getArmor();
	}
}
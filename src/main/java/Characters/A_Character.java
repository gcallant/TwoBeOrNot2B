package Characters;

import AttackAndDefendBehavior.*;

import java.util.*;

import Item.*;
import Inventory.*;

public abstract class A_Character
{
	protected String                name;
	protected int                   health;
	protected int					maxHealth;
	protected int                   strength;
	protected int                   dexterity;
	protected int                   speed;
	protected Armor                 armor;
	protected Weapon				weapon;
	protected I_Attack              attackBehavior;
	protected I_Defend              defendBehavior;
	protected boolean               isDefeated;
	protected boolean				defending;
	protected Inventory             inventory;
	protected Random 				rand;
	protected int 					initiative;
	protected boolean				isStunned;

	public A_Character(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, Armor newArmor, Weapon newWeapon)
	{
		setName(newName);
		setHealth(newHealth);
		setStrength(newStrength);
		setDexterity(newDexterity);
		setSpeed(newSpeed);
		setArmor(newArmor);
		setWeapon(newWeapon);
		maxHealth = newHealth;
		isDefeated = false;
		inventory = new Inventory();
		rand = new Random();
		isStunned = false;
	}

	protected void sneakAttack(A_Character character)
	{
		int normalStrength = getStrength();
		System.out.println(getName() + " used sneak attack on " + character.getName());
		if(character.getHealth() == character.getMaxHealth())
		{
			setStrength(normalStrength*6);
		}
		attack(character);
		setStrength(normalStrength);
	}

	protected void magicStrike(Party heroes, Party monsters)
	{
		System.out.println(getName() + " used meteor shower!");
		for(A_Character character : monsters.getParty())
		{
			if(!character.getDefeated())
			{
				preformAttack(character);
			}
		}
	}

	protected void stunningStrike(A_Character character)
	{
		int normalStrength = getStrength();

		setStrength(normalStrength*2);

		System.out.println(getName() + " used stunning strike on " + character.getName());
		if(canAttack(character))
		{
			preformAttack(character);
			if(rand.nextBoolean())
			{
				System.out.println(character.getName() + " was stunned!");
				character.setStun();
			}
		}
		else
		{
			System.out.println("But missed!");
		}
	}

	protected void removeStun()
	{
		isStunned = false;
	}

	private void setStun()
	{
		isStunned = true;
	}

	public boolean isStunned()
	{
		return isStunned;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	public int getInitiative()
	{
		return this.initiative;
	}

	public abstract boolean takeAction(Party heroes, Party monsters);

	public void heal(int amount)
	{
		System.out.println(getName() + " was healed for " + amount + " HP!");
		health = Math.min(health + amount, maxHealth);
	}

	public void generateInitiative()//will generate the character's initiative.
	{
		resetInitiative();

		int randomValue;

		randomValue = rand.nextInt(ConstantValues.RandomInitiative.getValue());
		initiative = randomValue + speed;
	}

	public void resetInitiative()
	{
		initiative = 0;
	}

	public void takeDamage(int total)
	{
		this.health -= total;
		if(health <= 0)
		{
			health = 0;
			this.isDefeated = true;
		}
	}

	public boolean canAttack(A_Character toAttack)
	{
		int attackBonus = 0;
		switch(weapon.getAttackType())
		{
			case "dexterity":
				attackBonus = this.getDexterity();
				break;
			case "strength":
				attackBonus = this.getStrength();
				break;
		}
		attackBonus += weapon.getPower();
		attackBonus += rand.nextInt(ConstantValues.ChanceToHit.getValue());

		return attackBonus >= toAttack.totalDefense();
	}

	public int totalDefense()
	{
		int defense = armor.getPower();
		defense += getDexterity();

		return defense;
	}

	public void attack(A_Character toAttack)
	{
		if(canAttack(toAttack))
		{
			preformAttack(toAttack);
		}
		else
		{
			System.out.println(this.getName() + " attacked " + toAttack.getName() + " but missed!");
		}
	}

	public void preformAttack(A_Character toAttack)
	{
		int totalDamage = 0;

		totalDamage += weapon.getPower();
		totalDamage += getStrength();
		totalDamage += rand.nextInt(ConstantValues.RandomDamage.getValue());
		if(toAttack.isDefending())
		{
			totalDamage = totalDamage/2;
		}
		toAttack.takeDamage(totalDamage);

		System.out.println(this.getName() + " attacked " + toAttack.getName() + " for " + totalDamage + " damage!");

		if(toAttack.getDefeated())
		{
			System.out.println(this.getName() + " killed " + toAttack.getName() + "!");
		}
	}

	public void defend()
	{
		defending = true;
	}

	public void endDefend()
	{
		defending = false;
	}

	public boolean isDefending()
	{
		return defending;
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

	public Armor getArmor()
	{
		return armor;
	}

	public void setArmor(Storable newArmor)
	{
		if(newArmor == null || !(newArmor instanceof Armor) )
		{
			throw new IllegalArgumentException("Invalid armor");
		}
		armor = (Armor)newArmor;
	}

	public void setWeapon(Storable newWeapon)
	{
		if(newWeapon == null || !(newWeapon instanceof Weapon) )
		{
			throw new IllegalArgumentException("Invalid armor");
		}
		weapon = (Weapon)newWeapon;
	}

	public String compareWeapon(Weapon equipped, Weapon toCompare)
	{
		int powerDiff;
		if(equipped.getWeaponType() == toCompare.getWeaponType())
		{
			powerDiff = equipped.getPower() - toCompare.getPower();
			if(powerDiff >= 0)
			{
				return "+" + powerDiff;
			}
			return "" + powerDiff;

		}
		return "Not comparable. Weapons are of different types.";
	}

	public String compareArmor(Armor equipped, Armor toCompare)
	{
		int powerDiff;
		if(equipped.getArmorType() == toCompare.getArmorType())
		{
			powerDiff = equipped.getPower() - toCompare.getPower();
			if(powerDiff >= 0)
			{
				return "+" + powerDiff;
			}
			return "" + powerDiff;

		}
		return "Not comparable. Armor is of a different type.";
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
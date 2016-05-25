package Characters;

import java.util.*;

import BuffsAndDebuffs.Conditions;
import Item.*;
import PartyManagement.Party;
import com.google.common.base.Objects;

public abstract class A_Character
{
	private String     name;
	private int        health;
	private int	       maxHealth;
	private int        power;
	private int 	   cunning;
	private int        level;
	private int        experience;
	private Armor      armor;
	private Weapon     weapon;
	private boolean    isDefeated;
	private int        initiative;
	private ArmorType  armorType;
	private WeaponType weaponType;
	protected Conditions conditions;
	private CreatureType creatureType;
	private boolean isInvincible = false;
	private boolean hasMaxPower = false;
	protected Random   rand;

	public A_Character(String name, int health, int power, int cunning,
					   ArmorType armorType, Armor newArmor, WeaponType weaponType,
					   Weapon newWeapon, CreatureType creatureType)
	{
		this.name      = name;
		this.health    = health;
		this.power = power;
		this.cunning = cunning;
		this.armor     = newArmor;
		this.weapon    = newWeapon;
		this.maxHealth = health;
		this.armorType = armorType;
		this.weaponType = weaponType;
		this.creatureType = creatureType;

		this.level         = 1;
		this.experience    = 0;

		isDefeated = false;

		rand = new Random();
		conditions = new Conditions(getName());
	}


	public boolean takeAction(Party heroes, Party monsters)
	{
		if(conditions.confusedEffect(this, heroes, monsters))
		{
			if(isInvincible)
			{
				return true;
			}
			return false;
		}
		return true;
	}

	/*
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*  * * *   * * *   * * *     * * *   * * *      *      *      *
	*  *       *   *   *        *          *       * *     *      *
	*  * * *   * * *   * * *   *           *      * * *    *      *
	*      *   *       *        *          *     *     *   *      *
	*  * * *   *       * * *     * * *   * * *   *     *   * * *  *
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*/



	/*
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*    *      * * *   * * *      *        * * *   *   *   *
	*   * *       *       *       * *      *        * *     *
	*  *   *      *       *      *   *    *         *  *    *
	* * * * *     *       *     * * * *    *        *   *   *
	* *     *     *       *     *     *     * * *   *    *  *
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*/

	public void takeDamage(int total)
	{
		if(isInvincible)
		{
			return;
		}

		this.health -= total;
		if(health <= 0)
		{
			health = 0;
			this.isDefeated = true;
			System.out.println(getName() + " has died!");
		}
	}

	public boolean canAttack(A_Character toAttack)
	{
		int attackBonus = 0;
		if(hasMaxPower)
		{
			return true;
		}
		switch(weapon.getAttackType())
		{
			case "cunning":
				attackBonus = this.getCunning();
				break;
			case "power":
				attackBonus = this.getPower();
				break;
		}
		attackBonus += weapon.getPower();
		attackBonus += rand.nextInt(ConstantValues.ChanceToHit.getValue());
		attackBonus = conditions.addAttack(attackBonus);
		attackBonus = conditions.calculateAttack(attackBonus);

		return attackBonus >= toAttack.totalDefense();
	}

	public boolean attack(A_Character toAttack)
	{
		if(canAttack(toAttack))
		{
			preformAttack(toAttack);
			return true;
		}
		else
		{
			System.out.println(this.getName() + " attacked " + toAttack.getName() + " but missed!");
			return false;
		}
	}

	public void preformAttack(A_Character toAttack)
	{
		int totalDamage = 0;

		if(! hasMaxPower)
		{
			totalDamage += weapon.getPower();
			totalDamage += getPower();
			totalDamage += rand.nextInt(ConstantValues.RandomDamage.getValue());
			totalDamage = conditions.addDamage(totalDamage);
			totalDamage = conditions.calculateDamage(totalDamage);
			totalDamage = toAttack.conditions.reduceDamage(totalDamage);
		}
		else
		{
			totalDamage = Integer.MAX_VALUE;
		}

		System.out.println(this.getName() + " attacked " + toAttack.getName() + " for " + Math.max(totalDamage, 1) + " damage!");

		toAttack.takeDamage(Math.max(totalDamage, 1));
	}

	/*
	* * * * * * * * * * * * * * * * * * * * * * * * *
	*  *       * * *   *         *   * * *   *      *
	*  *       *        *       *    *       *      *
	*  *       * * *     *     *     * * *   *      *
	*  *       *          *   *      *       *      *
	*  * * *   * * *       * *       * * *   * * *  *
	* * * * * * * * * * * * * * * * * * * * * * * * *
	*/

	public int getLevel()
	{
		return this.level;
	}

	protected void levelUp()
	{
		health = maxHealth;
		level++;
		experience = 0;
		LevelUp.levelUp(this);
	}

	protected boolean canLevel()
	{
		return experience/(level*100) > 0;
	}

	public void gainExperience(int experience)
	{
		if(experience > 0)
		{
			this.experience += experience;
		}
		if(canLevel())
		{
			levelUp();
		}
	}

	public void upgradepower()
	{
		this.power += 2;
	}

	public void upgradecunning()
	{
		this.cunning += 1;
	}

	public void upgradeHealth()
	{
		this.health += 25;
		this.maxHealth += 25;
	}

	/*
	* * * * * * * * * * * * * * * * * * * * * * *
	*  * * *   * * *    *   *   * * *   * * *   *
	*  *       *   *    *   *     *     *   *   *
	*  * * *   *  **    *   *     *     * * *   *
	*  *       * * *    *   *     *     *       *
	*  * * *        *   * * *   * * *   *       *
	* * * * * * * * * * * * * * * * * * * * * * *
	*/

	public boolean canEquip(Armor armor)
	{
		return armorType == armor.getArmorType();
	}

	public boolean canEquip(Weapon weapon)
	{
		return weaponType == weapon.getWeaponType();
	}

	public Armor equip(Armor armor)
	{
		Armor temp = this.armor;
		this.armor = armor;
		return temp;
	}

	public Weapon equip(Weapon weapon)
	{
		Weapon temp = this.weapon;
		this.weapon = weapon;
		return temp;
	}

	/*
	* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*  * * *     * *     * * *   * * *     * *     *    * *
	*  *   *   *     *     *       *     *     *   * *  * *
	*  * * *   *     *     *       *     *     *   *  * * *
	*  *       *     *     *       *     *     *   *   ** *
	*  *         * *       *     * * *     * *     *    * *
	* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*/

	public void heal(int amount)
	{
		if(amount > 0)
		{
			health = Math.min(health + amount, maxHealth);
			removeDefeated();
		}
	}

	public void imbibe(Consumable consumable)
	{
		System.out.println(getName() + " imbibed a " + consumable + "!");
	}

	/*
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*  * * * *   * * *   * * *   *       *   * * *   * * *  *
	*  *     *   *   *     *     * *     *     *     *      *
	*  * * * *   * * *     *     *   *   *     *     * * *  *
	*  *         * *       *     *     * *     *         *  *
	*  *         *   *   * * *   *       *     *     * * *  *
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*/

	@Override
	public String toString()
	{
		return "Name: " + getName() + "\tHealth: " + getHealth() + "/" + getMaxHealth() + "\tpower: " + getPower() +
				         "\tcunning: " + getCunning() + "\t" + getArmor() + "\t" + getWeapon();
	}

	public String inventoryDisplay()
	{
		return "Name: " + getName() + " Health: " + getHealth() + "/" + getMaxHealth() + " Armor: " + getArmor() + " Weapon: " + getWeapon();
	}

	public String displayStats()
	{
		return "Name: " + getName() + " Level: " + getLevel() + " Experience: " + experience + "/" + (level*100) + " Health: " + getHealth() + "/" + getMaxHealth() + " power: " + getPower() + (power != getPower() ? ("(" + power + ")"):"") + " cunning: " + getCunning() + (cunning != getCunning() ? ("(" + cunning + ")"):"") + " " + getArmor() + " " + getWeapon();
	}

	public String battleDisplay()
	{
		String retString = "Name: " + getName() + " Health: " + getHealth() + "/" + getMaxHealth();
		retString += conditions.displayStats();
		if(conditions.hasBadCondition())
		{
			retString += " X";
		}
		return retString;
	}

	/*
	* * * * * * * * * * * * * * * * * *
	*  * * *    * * *   * * *   * * *  *
	*  *        *         *     *      *
	*  * * *    * * *     *     * * *  *
	*      *    *         *         *  *
	*  * * *    * * *     *     * * *  *
	* * * * * * * * * * * * * * * * * *
	*/

	private void setDefeated(boolean isDown)
	{
		isDefeated = isDown;
	}

	public void resetTurn()
	{
		int toHeal = conditions.takeTurnHealing(getMaxHealth());
		heal(toHeal);
		int damage = conditions.takeTurnDamage(getMaxHealth());
		takeDamage(damage);
	}

	public void endTurn()
	{
		conditions.endTurn();
	}

	public void removeDefeated()
	{
		isDefeated = false;
	}

	public void resetStats()
	{
		conditions.resetConditions();
	}

	public void setGodMod()
	{
		isInvincible = true;
		health = Integer.MAX_VALUE;
		maxHealth = Integer.MAX_VALUE;
	}

	public void setHasMaxPower()
	{
		hasMaxPower = true;
	}

	/*
	* * * * * * * * * * * * * * * * * * * *
	*  * * * *   * * *   * * *     * * *  *
	*  *         *         *       *      *
	*  *   * *   * * *     *       * * *  *
	*  *     *   *         *           *  *
	*  * * * *   * * *     *       * * *  *
	* * * * * * * * * * * * * * * * * * * *
	*/

	public Conditions getConditions()
	{
		return conditions;
	}

	public boolean getDefeated()
	{
		return isDefeated;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	public Armor getArmor()
	{
		return armor;
	}

	public String getName()
	{
		return name;
	}

	public int getHealth()
	{
		return health;
	}

	public int getPower()
	{
		return power;
	}

	public int getCunning()
	{
		return cunning;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	public CreatureType getCreatureType()
	{
		return creatureType;
	}

	private int totalDefense()
	{
		int defense = armor.getPower();
		defense += getCunning();

		return defense;
	}

	public int getInitiative()
	{
		return this.initiative;
	}

	public void generateInitiative()
	{
		int randomValue;

		randomValue = rand.nextInt(ConstantValues.RandomInitiative.getValue());
		initiative = randomValue + cunning;
	}

	protected void reassignConditons(Conditions conditions)
	{
		this.conditions = conditions;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof A_Character)) { return false; }
		A_Character that = (A_Character) o;
		return health == that.health &&
				         maxHealth == that.maxHealth &&
				         power == that.power &&
				         cunning == that.cunning &&
				         level == that.level &&
				         experience == that.experience &&
				         isDefeated == that.isDefeated &&
				         initiative == that.initiative &&
				         com.google.common.base.Objects.equal(name, that.name) &&
				         Objects.equal(armor, that.armor) &&
				         Objects.equal(weapon, that.weapon) &&
				         armorType == that.armorType &&
				         weaponType == that.weaponType &&
				         Objects.equal(conditions, that.conditions) &&
				         Objects.equal(rand, that.rand);
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(name, health, maxHealth, power, cunning, level, experience, armor, weapon,
		                        isDefeated, initiative, armorType, weaponType, conditions, rand);
	}
}
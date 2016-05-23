package Characters;

import java.util.*;

import BuffsAndDebuffs.Conditions;
import Item.*;
import PartyManagement.Party;
import com.google.common.base.*;
import com.google.common.base.Objects;

public abstract class A_Character
{
	private String     name;
	private int        health;
	private int	       maxHealth;
	private int        strength;
	private int        dexterity;
	private int        level;
	private int        experience;
	private Armor      armor;
	private Weapon     weapon;
	private boolean    isDefeated;
	private int        initiative;
	private ArmorType  armorType;
	private WeaponType weaponType;
	protected Conditions conditions;
	private int bleedDuration;

	protected Random   rand;

	public A_Character(String name, int health, int strength, int dexterity, ArmorType armorType, Armor newArmor, WeaponType weaponType, Weapon newWeapon)
	{
		this.name      = name;
		this.health    = health;
		this.strength  = strength;
		this.dexterity = dexterity;
		this.armor     = newArmor;
		this.weapon    = newWeapon;
		this.maxHealth = health;
		this.armorType = armorType;
		this.weaponType = weaponType;

		this.level         = 1;
		this.experience    = 0;

		isDefeated = false;

		rand = new Random();
		conditions = new Conditions(getName());
	}


	public abstract boolean takeAction(Party heroes, Party monsters);

	/*
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*  * * *   * * *   * * *     * * *   * * *      *      *      *
	*  *       *   *   *        *          *       * *     *      *
	*  * * *   * * *   * * *   *           *      * * *    *      *
	*      *   *       *        *          *     *     *   *      *
	*  * * *   *       * * *     * * *   * * *   *     *   * * *  *
	* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	*/


	protected void viciousBite(A_Character character)
	{
		if(canAttack(character))
		{
			System.out.println(getName() + "used Vicious Bite on " + character.getName() + "!");
			preformAttack(character);
			if(rand.nextBoolean())
			{
				System.out.println(character.getName() + " is bleeding from the attack!");
				//character.setBleed(3);
			}
		}
	}

	protected A_Monster summonSkeleton()
	{
		//will return a Skeleton to join the summoner in battle. Need to work out implementation details.
		return null;
	}

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

		totalDamage += weapon.getPower();
		totalDamage += getStrength();
		totalDamage += rand.nextInt(ConstantValues.RandomDamage.getValue());
		totalDamage = conditions.addDamage(totalDamage);
		totalDamage = conditions.calculateDamage(totalDamage);
		totalDamage = toAttack.conditions.reduceDamage(totalDamage);

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

	public void upgradeStrength()
	{
		this.strength += 2;
	}

	public void upgradeDexterity()
	{
		this.dexterity += 2;
	}

	public void upgradeHealth()
	{
		this.health += 25;
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
		return "Name: " + getName() + "\tHealth: " + getHealth() + "\tStrength: " + getStrength() +
				         "\tDexterity: " + getDexterity() + "\t" + getArmor() + "\t" + getWeapon();
	}

	public String inventoryDisplay()
	{
		return "Name: " + getName() + " Health: " + getHealth() + " Armor: " + getArmor() + " Weapon: " + getWeapon();
	}

	public String displayStats()
	{
		return "Name: " + getName() + " Level: " + getLevel() + " Health: " + getHealth() + " Strength: " + getStrength() + (strength != getStrength() ? ("(" + strength + ")"):"") + " Dexterity: " + getDexterity() + (dexterity != getDexterity() ? ("(" + dexterity + ")"):"") + " " + getArmor() + " " + getWeapon();
	}

	public String battleDisplay()
	{
		String retString = "Name: " + getName() + " Health: " + getHealth();
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

	public int getStrength()
	{
		return strength;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	private int totalDefense()
	{
		int defense = armor.getPower();
		defense += getDexterity();

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
		initiative = randomValue + dexterity;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof A_Character)) { return false; }
		A_Character that = (A_Character) o;
		return health == that.health &&
				         maxHealth == that.maxHealth &&
				         strength == that.strength &&
				         dexterity == that.dexterity &&
				         level == that.level &&
				         experience == that.experience &&
				         isDefeated == that.isDefeated &&
				         initiative == that.initiative &&
				         bleedDuration == that.bleedDuration &&
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
		return Objects.hashCode(name, health, maxHealth, strength, dexterity, level, experience, armor, weapon,
		                        isDefeated, initiative, armorType, weaponType, conditions, bleedDuration, rand);
	}
}
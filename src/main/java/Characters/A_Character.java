package Characters;

import AttackAndDefendBehavior.*;

import java.util.*;

import Item.*;
import Inventory.*;

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
		totalDamage = conditions.addDamage(totalDamage);
		totalDamage = conditions.calculateDamage(totalDamage);
		totalDamage = toAttack.conditions.reduceDamage(totalDamage);
		toAttack.takeDamage(totalDamage);

		System.out.println(this.getName() + " attacked " + toAttack.getName() + " for " + totalDamage + " damage!");

		if(toAttack.getDefeated())
		{
			System.out.println(this.getName() + " killed " + toAttack.getName() + "!");
		}
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

	private void levelUp()
	{
		strength += strengthIncrease();
		dexterity += dexterityIncrease();
		maxHealth += healthIncrease();
		health = maxHealth;
		level++;
		experience = 0;
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

	public int strengthIncrease()
	{
		return 0;
	}

	public int dexterityIncrease()
	{
		return 0;
	}

	public int healthIncrease()
	{
		return 0;
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
		System.out.println(getName() + " was healed for " + amount + " HP!");
		health = Math.min(health + amount, maxHealth);
		removeDefeated();
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
				         "\tDexterity: " + getDexterity() + "\tArmor: ";
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
		if(conditions.hasBadCondition())
		{
			retString += " bad condition";
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
		int toHeal = conditions.calculateRegen(getMaxHealth());
		if(toHeal > 0)
		{
			heal(toHeal);
		}
		int poison = conditions.calculatePoisonDamage(getMaxHealth());
		if(poison > 0)
		{
			System.out.println(getName() + " is poisoned and takes " + poison + " damage!");
			takeDamage(poison);
		}

		conditions.startTurn();
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
		conditions.recoverConditions();
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

	protected int getInitiative()
	{
		return this.initiative;
	}

	public void generateInitiative()
	{
		int randomValue;

		randomValue = rand.nextInt(ConstantValues.RandomInitiative.getValue());
		initiative = randomValue + dexterity;
	}
}
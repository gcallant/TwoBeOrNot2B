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
	private int		   tempStrength;
	private int 	   tempDexterity;
	private int        level;
	private int        experience;
	private Armor      armor;
	private Weapon     weapon;
	private boolean    isDefeated;
	private int        initiative;
	private boolean	   isStunned;
	private ArmorType  armorType;
	private WeaponType weaponType;
	private boolean    protection;
	private Consumable imbibed;
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

		this.tempDexterity = 0;
		this.tempStrength  = 0;
		this.level         = 1;
		this.experience    = 0;

		isDefeated = false;
		protection = false;

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

	protected void intimidatingShout(Party enemies)
	{
		System.out.println(getName() + " used intimidating shout!");
		for(int x = 0; x < enemies.size(); x++)
		{
			enemies.getCharacter(x).removeTempStrength(2*level);
		}
	}

	protected void resetShout(Party enemies)
	{
		System.out.println(getName() + "'s intimidating shout has ended!");
		for(int x = 0; x < enemies.size(); x++)
		{
			enemies.getCharacter(x).giveTempStrength(2*level);
		}
	}

	protected void healingLight(Party allies)
	{
		System.out.println(getName() + " healed their whole party for " + (2*strength));
		for(int x = 0; x < allies.size(); x++)
		{
			allies.getCharacter(x).heal(2*strength);
			allies.getCharacter(x).conditions.recoverConditions();
		}

	}

	protected void protect(A_Character character)
	{
		character.conditions.setProtected();
	}

	protected void sneakAttack(A_Character character)
	{
		int tempBoost = 0;
		System.out.println(getName() + " used sneak attack on " + character.getName());

		if((character.getHealth() == character.getMaxHealth()) || character.cannotAttack())
		{
			tempBoost = getStrength()*10;
			giveTempStrength(tempBoost);
		}

		attack(character);

		removeTempStrength(tempBoost);
	}

	protected void meteorShower(Party enemies)
	{
		System.out.println(getName() + " used meteor shower!");
		int totalEnemies = enemies.size();
		for(int x = 0; x < totalEnemies; x++)
		{
			preformAttack(enemies.getCharacter(x));
		}
	}

	protected void stunningStrike(A_Character character)
	{
		int tempBoost = level*3;
		giveTempStrength(tempBoost);

		System.out.println(getName() + " used stunning strike on " + character.getName());
		if(canAttack(character))
		{
			preformAttack(character);
			if(rand.nextBoolean())
			{
				System.out.println(character.getName() + " was stunned!");
				character.conditions.stunned(level);
			}
		}
		else
		{
			System.out.println("But missed!");
		}
		removeTempStrength(tempBoost);
	}

	protected void magicBuff()
	{
		System.out.println(getName() + " buffed themself with magic!");
		giveTempStrength(5*getLevel());
	}

	protected void removeMagicBuff()
	{
		System.out.println(getName() + " lost their magic buff");
		removeTempStrength(5*getLevel());
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

	protected boolean cannotAttack()
	{
		return isStunned();
	}

	private void takeDamage(int total)
	{
		this.health -= total;
		if(health <= 0)
		{
			health = 0;
			this.isDefeated = true;
		}
	}

	private boolean canAttack(A_Character toAttack)
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

	protected void attack(A_Character toAttack)
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

	private void preformAttack(A_Character toAttack)
	{
		int totalDamage = 0;

		totalDamage += weapon.getPower();
		totalDamage += getStrength();
		totalDamage += rand.nextInt(ConstantValues.RandomDamage.getValue());
		totalDamage = conditions.addDamage(totalDamage);
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

	protected void decrementRounds()
	{
		if(imbibed != null)
		{
			if(imbibed.decrementRounds())
			{
				removePotion();
			}
		}
	}

	protected void removePotion()
	{
		if(imbibed != null)
		{
			System.out.println(getName() + "'s " + imbibed + "has ended!");
			imbibed.debibe(this);
			imbibed = null;
		}
	}

	public void imbibe(Consumable consumable)
	{
		System.out.println(getName() + " imbibed a " + consumable + "!");
		this.imbibed = consumable;
	}

	public void giveTempStrength(int tempStrength)
	{
		this.tempStrength += tempStrength;
	}

	public void removeTempStrength(int tempStrength)
	{
		this.tempStrength -= tempStrength;
	}

	public void giveTempDexterity(int tempDexterity)
	{
		this.tempDexterity += tempDexterity;
	}

	public void removeTempDexterity(int tempDexterity)
	{
		this.tempDexterity -= tempDexterity;
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

	private void setStun()
	{
		isStunned = true;
	}

	private void setDefeated(boolean isDown)
	{
		isDefeated = isDown;
	}

	protected void removeProtection()
	{
		protection = false;
	}

	public void resetTurn()
	{
		conditions.endTurn();
		decrementRounds();
	}

	protected void removeStun()
	{
		isStunned = false;
	}

	public void removeDefeated()
	{
		isDefeated = false;
	}

	public void resetStats()
	{
		this.tempDexterity = 0;
		this.tempStrength  = 0;
		removePotion();
		conditions.recoverConditions();
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

	private Weapon getWeapon()
	{
		return weapon;
	}

	private Armor getArmor()
	{
		return armor;
	}

	public String getName()
	{
		return name;
	}

	protected int getHealth()
	{
		return health;
	}

	private int getStrength()
	{
		return strength + tempStrength;
	}

	private int getDexterity()
	{
		return Math.max(1,dexterity + tempDexterity);
	}

	private boolean isStunned()
	{
		return isStunned;
	}

	protected int getMaxHealth()
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
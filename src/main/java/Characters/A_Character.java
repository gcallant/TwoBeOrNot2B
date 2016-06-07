package Characters;

import Buffs.Conditions;
import Buffs.UndeadConditions;
import Item.*;
import PartyManagement.Party;
import Utilities.Display;

import java.util.Random;

public abstract class A_Character
{
	protected Conditions   conditions;
	protected Random       rand;
	private   String       name;
	private   int          health;
	private   int          maxHealth;
	private   int          power;
	private   int          cunning;
	private   int          level;
	private   int          experience;
	private   Armor        armor;
	private   Weapon       weapon;
	private   boolean      isDefeated;
	private   int          initiative;
	private   ArmorType    armorType;
	private   WeaponType   weaponType;
	private   CreatureType creatureType;
	private boolean isInvincible = false;
	private boolean hasMaxPower = false;
	private boolean isSummon;
	private A_Character owner;

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
		this.owner = null;

		this.level         = 1;
		this.experience    = 0;

		isDefeated = false;
		isSummon   = false;

		rand = new Random();
		if(creatureType == CreatureType.Undead)
		{
			conditions = new UndeadConditions(this);
		}
		else
		{
			conditions = new Conditions(this);
		}
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
		total = conditions.reduceDamage(total, false);
		this.health -= Math.max(total, 0);
		if(health <= 0)
		{
			health = 0;
			this.isDefeated = true;
			if(isSummon())
			{
				Display.displayMessage(getName() + " has been banished");
			}
			else
			{
				Display.displayMessage(getName() + " has died!");
			}
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
			case "Power":
				attackBonus = this.getPower();
				break;
		}
		attackBonus += weapon.getPower();
		int randomChance = rand.nextInt(ConstantValues.ChanceToHit.getValue());
		attackBonus += randomChance;
		attackBonus = conditions.addAttack(attackBonus);
		attackBonus = conditions.calculateAttack(attackBonus);

		if(randomChance == (ConstantValues.ChanceToHit.getValue() - 1))
		{
			return true;
		}

		return (attackBonus >= toAttack.totalDefense()) && (rand.nextInt(10) != 0);
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
			Display.displayMessage(this.getName() + " attacked " + toAttack.getName() + " but missed!");
			return false;
		}
	}

	public void preformAttack(A_Character toAttack)
	{
		int totalDamage = 0;

		if(! hasMaxPower)
		{
			totalDamage += weapon.getPower();
			totalDamage += getPower()*2;
			totalDamage += rand.nextInt(ConstantValues.RandomDamage.getValue());
			totalDamage = conditions.addDamage(totalDamage);
			totalDamage = conditions.calculateDamage(totalDamage);
		}
		else
		{
			totalDamage = Integer.MAX_VALUE;
		}

		if(toAttack.isInvincible)
		{
			totalDamage = 0;
		}


		Display.displayMessage(this.getName() + " attacked " + toAttack.getName() + " for " + Math.max(totalDamage, 1) + " damage!");

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

	public void setLevel(int level)
	{
		this.level = level;
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

	public int getExperience()
	{
		return experience;
	}

	public void setExperience(int experience)
	{
		this.experience = experience;
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

	public void upgradePower()
	{
		this.power += 1;
	}

	public void upgradeCunning()
	{
		this.cunning += 1;
	}

	public void upgradeHealth()
	{
		this.health += 35;
		this.maxHealth += 35;
	}

	public void upgradeAbilities(){}

	/*
	* * * * * * * * * * * * * * * * * * * * * * *
	*  * * *   * * *    *   *   * * *   * * *   *
	*  *       *   *    *   *     *     *   *   *
	*  * * *   *  *    *   *     *     * * *   *
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
		Display.displayMessage(getName() + " imbibed a " + consumable + "!");
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
		return "Name: " + getName() + "\tHealth: " + getHealth() + "/" + getMaxHealth() + "\tPower: " + getPower() +
				         "\tcunning: " + getCunning() + "\t" + getArmor() + "\t" + getWeapon();
	}

	public String inventoryDisplay()
	{
		return "Name: " + getName() + " Health: " + getHealth() + "/" + getMaxHealth() + " Armor: " + getArmor() + " Weapon: " + getWeapon();
	}

	public String displayStats()
	{
		return "Name: " + getName() + " Level: " + getLevel() + " Experience: " + experience + "/" + (level*100) + " Health: " + getHealth() + "/" + getMaxHealth() + " Power: " + getPower() + (power != getPower() ? ("(" + power + ")"):"") + " cunning: " + getCunning() + (cunning != getCunning() ? ("(" + cunning + ")"):"") + " " + getArmor() + " " + getWeapon();
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

	public void resetTurn()
	{
		int toHeal = conditions.takeTurnHealing(getMaxHealth());
		heal(toHeal);
		int damage = conditions.takeTurnDamage(getMaxHealth(), getHealth());
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

	public void setGodMode()
	{
		isInvincible = true;
	}

	public void disableGodMode()
	{
		isInvincible = false;
	}

	public void setHasMaxPower()
	{
		hasMaxPower = true;
	}

	public void disableMaxPower()
	{
		hasMaxPower = false;
	}

	public Conditions getConditions()
	{
		return conditions;
	}

	public void setConditions(Conditions conditions)
	{
		this.conditions = conditions;
	}

	public boolean getDefeated()
	{
		return isDefeated;
	}

	private void setDefeated(boolean isDown)
	{
		isDefeated = isDown;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	public Armor getArmor()
	{
		return armor;
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

	public String getName()
	{
		return name;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
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

	public int getBonusResistance()
	{
		return 0;
	}

	public int getResistance()
	{
		return Math.min(getPower(), getCunning()) + getBonusResistance();
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

	public boolean isSummon()
	{
		return isSummon;
	}

	public void setSummon(A_Character owner)
	{
		if(owner != null)
		{
			isSummon = true;
			this.owner = owner;
		}
	}

	public A_Character getOwner()
	{
		return this.owner;
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

		if(health != that.health) { return false; }
		if(maxHealth != that.maxHealth) { return false; }
		if(power != that.power) { return false; }
		if(cunning != that.cunning) { return false; }
		if(level != that.level) { return false; }
		if(experience != that.experience) { return false; }
		if(isDefeated != that.isDefeated) { return false; }
		if(initiative != that.initiative) { return false; }
		if(isInvincible != that.isInvincible) { return false; }
		if(hasMaxPower != that.hasMaxPower) { return false; }
		if(isSummon != that.isSummon) { return false; }
		if(conditions != null ? ! conditions.equals(that.conditions) : that.conditions != null) { return false; }
		if(rand != null ? ! rand.equals(that.rand) : that.rand != null) { return false; }
		if(name != null ? ! name.equals(that.name) : that.name != null) { return false; }
		if(armor != null ? ! armor.equals(that.armor) : that.armor != null) { return false; }
		if(weapon != null ? ! weapon.equals(that.weapon) : that.weapon != null) { return false; }
		if(armorType != that.armorType) { return false; }
		if(weaponType != that.weaponType) { return false; }
		if(creatureType != that.creatureType) { return false; }
		if(owner != null ? ! owner.equals(that.owner) : that.owner != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = conditions != null ? conditions.hashCode() : 0;
		result = 31 * result + (rand != null ? rand.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + health;
		result = 31 * result + maxHealth;
		result = 31 * result + power;
		result = 31 * result + cunning;
		result = 31 * result + level;
		result = 31 * result + experience;
		result = 31 * result + (armor != null ? armor.hashCode() : 0);
		result = 31 * result + (weapon != null ? weapon.hashCode() : 0);
		result = 31 * result + (isDefeated ? 1 : 0);
		result = 31 * result + initiative;
		result = 31 * result + (armorType != null ? armorType.hashCode() : 0);
		result = 31 * result + (weaponType != null ? weaponType.hashCode() : 0);
		result = 31 * result + (creatureType != null ? creatureType.hashCode() : 0);
		result = 31 * result + (isInvincible ? 1 : 0);
		result = 31 * result + (hasMaxPower ? 1 : 0);
		result = 31 * result + (isSummon ? 1 : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		return result;
	}
}
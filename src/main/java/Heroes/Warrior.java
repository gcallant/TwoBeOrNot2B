package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.*;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Warrior extends A_Hero
{
	private SpecialManager specialManager;
	private int level;

	public Warrior(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
	{
		super(name, health, power, cunning, ArmorType.Medium, armor, WeaponType.Heavy, weapon);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new StunningStrike());
		specialManager.addSpecialAbility(new Charge());
		specialManager.addSpecialAbility(new IntimidatingShout());
		specialManager.addSpecialAbility(new WarCry());
		specialManager.addSpecialAbility(new Roar());
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		return specialManager.chooseSpecialAbility(this, heroes, monsters);
	}

	public void resetStats()
	{
		super.resetStats();
		conditions.recoverConditions();
	}

	public String getName()
	{
		return super.getName() + " the Warrior";
	}

	public static String Information()
	{
		return "Warrior:\n" + StunningStrike.description() + "\n" + Charge.description() + "\n" + IntimidatingShout.description()
				+ "\n" + WarCry.description() + "\n" + Roar.description();
	}

	public void upgradeAbilities()
	{
		specialManager.upgradeAbilities();
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Warrior)) { return false; }
		if(! super.equals(o)) { return false; }

		Warrior warrior = (Warrior) o;

		if(level != warrior.level) { return false; }
		if(specialManager != null ? ! specialManager.equals(warrior.specialManager) : warrior.specialManager != null)
		{ return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (specialManager != null ? specialManager.hashCode() : 0);
		result = 31 * result + level;
		return result;
	}
}

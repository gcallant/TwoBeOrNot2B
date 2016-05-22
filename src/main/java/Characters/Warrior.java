package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import SpecialAbilities.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Warrior extends A_Hero
{
	SpecialManager specialManager;
	public Warrior(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Heavy, weapon);

		specialManager = new SpecialManager();

		specialManager.addSpecialAbility(new StunningStrike());
		specialManager.addSpecialAbility(new IntimidatingShout());
		specialManager.addSpecialAbility(new WarCry());

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
		return "Warrior: Warriors hit hard and can stun their opponents with their mighty swings but doing so tires them out. They can also intimidate their foes once per a battle";
	}

	public int strengthIncrease()
	{
		return 4;
	}

	public int dexterityIncrease()
	{
		return 1;
	}

	public int healthIncrease()
	{
		return 25;
	}
}

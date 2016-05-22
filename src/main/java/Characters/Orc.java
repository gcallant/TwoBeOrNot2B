package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Orc extends A_Monster
{
	private boolean exhausted;

	public Orc(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Light, weapon);
		exhausted = false;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		stunningStrike(heroes.getCharacter(rand.nextInt(heroes.size())));
		exhausted = true;
		return false;
	}

	public boolean cannotAttack()
	{
		return super.cannotAttack() || exhausted;
	}

	public void resetTurn()
	{
		super.resetTurn();
		exhausted = false;
	}

	public int getLevel()
	{
		return 3;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Orc orc = (Orc) o;

		if (exhausted != orc.exhausted) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (exhausted ? 1 : 0);
		return result;
	}
}

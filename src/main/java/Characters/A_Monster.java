package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Monster extends A_Character
{
	protected String name;
	protected Random rand;
	private   int    percentageOfSpecial;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof A_Monster)) { return false; }
		if(! super.equals(o)) { return false; }

		A_Monster a_monster = (A_Monster) o;

		if(percentageOfSpecial != a_monster.percentageOfSpecial) { return false; }
		if(name != null ? ! name.equals(a_monster.name) : a_monster.name != null) { return false; }
		if(rand != null ? ! rand.equals(a_monster.rand) : a_monster.rand != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (rand != null ? rand.hashCode() : 0);
		result = 31 * result + percentageOfSpecial;
		return result;
	}

	public A_Monster(String name, int health, int power, int cunning, ArmorType armorType,
	                 Armor armor, WeaponType weaponType, Weapon weapon, int percentageOfSpecial,
	                 int level, CreatureType creatureType)
	{
		super(name, health, power, cunning, armorType, armor, weaponType, weapon, creatureType);
		rand = new Random();
		this.percentageOfSpecial = percentageOfSpecial - 1;
		int curLevel = 1;

		while(curLevel < level)
		{

			levelUp();
			curLevel++;
		}
	}

	public void resetTurn()
	{
		super.resetTurn();
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		boolean noTurn, noSpecial, useSpecial;

		noTurn = conditions.cannotAttack();
		noSpecial = conditions.cannotUseSpecial();

		if(conditions.confusedEffect(this, heroes, monsters))
		{
			noTurn = true;
		}

		if(getDefeated())
		{
			return false;
		}

		resetTurn();

		useSpecial = (rand.nextInt(10) < percentageOfSpecial);

		if(noTurn)
		{
			endTurn();
			return false;
		}

		heroes.sortDefeated();
		monsters.sortDefeated();

		if(noSpecial)
		{
			useSpecial = false;
		}


		int choiceToAttack = rand.nextInt(heroes.size());

		if(useSpecial && !specialAbility(rand, heroes, monsters)){}
		else
		{
			A_Character toAttack = heroes.getCharacter(choiceToAttack);
			attack(toAttack);
		}
		endTurn();
		return false;
	}

	protected abstract void levelUp();

	public abstract boolean specialAbility(Random rand, Party heroes, Party monsters);

}
